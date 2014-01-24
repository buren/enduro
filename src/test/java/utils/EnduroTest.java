package utils;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EnduroTest {

    @After
    public void setUp() throws Exception { }

    @Before
    public void tearDown() throws Exception { }

    @Test
    public void testGetResourcePath() throws Exception {
        Enduro enduro = Enduro.getInstance();
        Assert.assertNotNull("Enduro instance should not be null", enduro.getResourcePath(""));
        Assert.assertEquals("Should have /utils/ in path", true, enduro.getResourcePath("utils/").contains("/utils/"));
    }
}
