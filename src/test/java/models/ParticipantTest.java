package models;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParticipantTest {
    private Participant p;

    @Before
    public void setUp() throws Exception {
        p = new Participant("1");
    }

    @After
    public void tearDown() throws Exception {
        p = null;
    }

    @Test
    public void testGetAndSetEndTime() throws Exception {
        Assert.assertNull("Should be null", p.getEndTime());
        String endTime = "1:00:00";
        p.setEndTime(endTime);
        Assert.assertEquals("Should be the same", endTime, p.getEndTime());
    }

    @Test
    public void testGetAndSetStartTime() throws Exception {
        Assert.assertNull("Should be null", p.getStartTime());
        String endTime = "1:00:00";
        p.setStartTime(endTime);
        Assert.assertEquals("Should be the same", endTime, p.getStartTime());
    }

    @Test
    public void testGetId() throws Exception {
        Assert.assertEquals("Should be 1", "1", p.getId());
    }
}
