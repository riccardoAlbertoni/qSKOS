package at.ac.univie.mminf.qskos4j.issues.concepts.test;

import at.ac.univie.mminf.qskos4j.QSkos;
import at.ac.univie.mminf.qskos4j.issues.concepts.AuthoritativeConcepts;
import at.ac.univie.mminf.qskos4j.issues.concepts.InvolvedConcepts;
import at.ac.univie.mminf.qskos4j.issues.concepts.UndocumentedConcepts;
import at.ac.univie.mminf.qskos4j.util.IssueTestCase;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openrdf.OpenRDFException;
import org.openrdf.model.Resource;
import org.openrdf.model.Value;

import java.io.IOException;
import java.util.Collection;


public class UndocumentedConceptsTest extends IssueTestCase {

	private UndocumentedConcepts undocumentedConcepts;
	
	@Before
	public void setUp() throws OpenRDFException, IOException {
        undocumentedConcepts = (UndocumentedConcepts) setUpIssue(
            "documentedConcepts.rdf",
            new UndocumentedConcepts(new AuthoritativeConcepts(new InvolvedConcepts())));
	}
	
	@Test
	public void testAverageDocumentationCoverageRatio() throws OpenRDFException {
		Assert.assertEquals(1, undocumentedConcepts.getResult().getData().size());
	}
	
}
