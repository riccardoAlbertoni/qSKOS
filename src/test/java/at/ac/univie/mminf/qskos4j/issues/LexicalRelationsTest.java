package at.ac.univie.mminf.qskos4j.issues;

import at.ac.univie.mminf.qskos4j.issues.concepts.InvolvedConcepts;
import at.ac.univie.mminf.qskos4j.issues.labels.LexicalRelations;
import at.ac.univie.mminf.qskos4j.util.QskosTestCase;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openrdf.OpenRDFException;

import java.io.IOException;

/**
 * Created by christian
 * Date: 26.01.13
 * Time: 14:18
 */
public class LexicalRelationsTest extends QskosTestCase {

    private LexicalRelations lexicalRelations;

    @Before
    public void setUp() throws OpenRDFException, IOException {
        lexicalRelations = new LexicalRelations(new InvolvedConcepts(setUpRepository("components.rdf")));
    }

    @Test
    public void testLexicalRelationsCount() throws OpenRDFException {
        Assert.assertEquals(29, lexicalRelations.getResult().getData().longValue());
    }

}
