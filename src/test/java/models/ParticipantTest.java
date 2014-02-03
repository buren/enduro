package models;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParticipantTest {
    private Participant p;

    @Before
    public void setUp() throws Exception {
        p = new Participant(1);
    }

    @After
    public void tearDown() throws Exception {
        p = null;
    }

    @Test
    public void testGetId() throws Exception {
        Assert.assertEquals("Should be 1", 1, p.getId());
    }
    

    @Test
    public void testGetName() throws Exception {
    	Assert.assertEquals("Not named", p.getName());
    	p.setName("Oskar");
        Assert.assertEquals("Oskar", p.getName());
    }
    
    @Test
    public void testEquals(){
    	Participant q = new Participant(1);
    	Participant r = new Participant(2);
    	assertTrue(q.equals(p));
    	assertTrue(p.equals(q));
    	
    	assertFalse(q.equals(r));
    }
}
