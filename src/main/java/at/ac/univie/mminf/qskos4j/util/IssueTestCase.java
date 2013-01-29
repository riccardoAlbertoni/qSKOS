package at.ac.univie.mminf.qskos4j.util;

import at.ac.univie.mminf.qskos4j.util.vocab.VocabRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.openrdf.OpenRDFException;
import org.openrdf.rio.RDFFormat;

import java.io.File;
import java.io.IOException;
import java.net.URL;


@Ignore
public class IssueTestCase {
	
	protected VocabRepository setUpRepository(String testFileName)
		throws OpenRDFException, IOException 
	{
		URL conceptsUrl = getClass().getResource("/"+ testFileName);
		File conceptsFile = new File(conceptsUrl.getFile());
		Assert.assertNotNull(conceptsFile);
        return new VocabRepository(conceptsFile, null, RDFFormat.RDFXML);
	}
	
}
