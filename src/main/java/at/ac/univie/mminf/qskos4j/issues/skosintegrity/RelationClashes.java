package at.ac.univie.mminf.qskos4j.issues.skosintegrity;

import at.ac.univie.mminf.qskos4j.issues.HierarchyGraphBuilder;
import at.ac.univie.mminf.qskos4j.issues.Issue;
import at.ac.univie.mminf.qskos4j.report.CollectionReport;
import at.ac.univie.mminf.qskos4j.util.Pair;
import at.ac.univie.mminf.qskos4j.util.TupleQueryResultUtil;
import at.ac.univie.mminf.qskos4j.util.graph.NamedEdge;
import at.ac.univie.mminf.qskos4j.util.progress.MonitoredIterator;
import at.ac.univie.mminf.qskos4j.util.vocab.SparqlPrefix;
import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.openrdf.OpenRDFException;
import org.openrdf.model.Value;
import org.openrdf.query.TupleQueryResult;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Finds <a href="https://github.com/cmader/qSKOS/wiki/Quality-Issues#wiki-Relation_Clashes">Associative vs. Hierarchical Relation Clashes</a>.
 */
public class RelationClashes extends Issue<CollectionReport<Pair<Value>>> {

    private HierarchyGraphBuilder hierarchyGraphBuilder;

    public RelationClashes(HierarchyGraphBuilder hierarchyGraphBuilder) {
        super(hierarchyGraphBuilder.getVocabRepository(),
              "rc",
              "Relation Clashes",
              "Covers condition S27 from the SKOS reference document (Associative vs. Hierarchical Relation Clashes)",
              IssueType.ANALYTICAL
        );

        this.hierarchyGraphBuilder = hierarchyGraphBuilder;
    }

    @Override
    protected CollectionReport<Pair<Value>> invoke() throws OpenRDFException {
        Graph<Value, NamedEdge> hierarchyGraph = hierarchyGraphBuilder.createGraph();

        Collection<Pair<Value>> clashes = new HashSet<Pair<Value>>();

        Iterator<Pair<Value>> it = new MonitoredIterator<Pair<Value>>(
                findRelatedConcepts(),
                progressMonitor);

        while (it.hasNext()) {
            Pair<Value> conceptPair = it.next();
            try {
                if (pathExists(hierarchyGraph, conceptPair)) {
                    clashes.add(conceptPair);
                }
            }
            catch (IllegalArgumentException e) {
                // one of the concepts not in graph, no clash possible
            }
        }

        return new CollectionReport<Pair<Value>>(clashes);
    }

    private Collection<Pair<Value>> findRelatedConcepts() throws OpenRDFException {
        TupleQueryResult result = vocabRepository.query(createRelatedConceptsQuery());
        return TupleQueryResultUtil.createCollectionOfValuePairs(result, "concept1", "concept2");
    }

    private String createRelatedConceptsQuery() {
        return SparqlPrefix.SKOS +
            "SELECT DISTINCT ?concept1 ?concept2 WHERE {" +
                "?concept1 skos:related|skos:relatedMatch ?concept2 ." +
            "}";
    }

    private boolean pathExists(Graph<Value, NamedEdge> hierarchyGraph, Pair<Value> conceptPair) {
        if (new DijkstraShortestPath<Value, NamedEdge>(
                hierarchyGraph,
                conceptPair.getFirst(),
                conceptPair.getSecond()).getPathEdgeList() == null)
        {
            return new DijkstraShortestPath<Value, NamedEdge>(
                    hierarchyGraph,
                    conceptPair.getSecond(),
                    conceptPair.getFirst()).getPathEdgeList() != null;
        }
        return true;
    }
}