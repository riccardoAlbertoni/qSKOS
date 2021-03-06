package at.ac.univie.mminf.qskos4j.issues;

import at.ac.univie.mminf.qskos4j.issues.concepts.AuthoritativeConcepts;
import at.ac.univie.mminf.qskos4j.issues.concepts.InvolvedConcepts;
import at.ac.univie.mminf.qskos4j.issues.relations.ReflexivelyRelatedConcepts;
import at.ac.univie.mminf.qskos4j.util.vocab.RepositoryBuilder;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.eclipse.rdf4j.RDF4JException;

import java.io.IOException;

public class ReflexivelyRelatedConceptsTest {

    private ReflexivelyRelatedConcepts reflexivelyRelatedConcepts;

    @Before
    public void setUp() throws IOException, RDF4JException
    {
        reflexivelyRelatedConcepts = new ReflexivelyRelatedConcepts(new AuthoritativeConcepts(new InvolvedConcepts()));
        reflexivelyRelatedConcepts.setRepositoryConnection(
                new RepositoryBuilder().setUpFromTestResource("reflexivelyRelatedConcepts.rdf").getConnection());
    }

    @Test
    public void mappingRelationsMisuseCount() throws RDF4JException {
        Assert.assertEquals(2, reflexivelyRelatedConcepts.getResult().getData().size());
    }

}
