package models;

import org.junit.Before;
import org.junit.Test;
import race.Race;
import race.SimpleRace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParticipantTest {
    private Participant p;

    @Before
    public void setUp() {
        p = new Participant(1);
        p.setRace(new SimpleRace());
    }

    @Test
    public void testGetId() {
        assertEquals(1, p.getId());
    }

    @Test
    public void testPrintEmptyName() {
        assertEquals("1; Not named; --.--.--; Start?; Slut?", p.print(1));
    }

    @Test
    public void testSetName() {
        p.setName("A Asson");
        assertEquals("1; A Asson; --.--.--; Start?; Slut?", p.print(1));
    }

    @Test
    public void testAddInfo() {
        p.addInfo("Klubb", "Kungarna");
        assertEquals("1; Not named; Kungarna; --.--.--; Start?; Slut?", p.print(1));
    }

    @Test
    public void testAddInfoHeader() {
        p.addInfo("Klubb", "Kungarna");
        assertEquals("StartNr; Namn; Klubb", p.printHeader());
    }

    @Test
    public void testAddMultipleInfo() {
        p.addInfo("Klubb", "Kungarna");
        p.addInfo("MC", "125");
        assertEquals("1; Not named; Kungarna; 125; --.--.--; Start?; Slut?", p.print(1));
    }

    @Test
    public void testAddMultipleInfoHeader() {
        p.addInfo("Klubb", "Kungarna");
        p.addInfo("MC", "125");
        assertEquals("StartNr; Namn; Klubb; MC", p.printHeader());
    }

    @Test
    public void testSetRace() {
        p = new Participant(1);
        Race race = new SimpleRace();
        p.setRace(race);
        assertTrue(race.equals(p.getRace()));
    }

    @Test
    public void testSetRaceClass() {
        p.setRaceClass("SNABB");
        assertTrue(p.getRaceClass().equals("SNABB"));
    }

    @Test
    public void testEqualSameID(){
        Participant p2 = new Participant(1);
        assertTrue(p.equals(p2));
    }

    @Test
    public void testEqualSameObject() {
        assertTrue(p.equals(p));
    }

    @Test
    public void testEqualNull() {
        assertFalse(p.equals(null));
    }

    @Test
    public void testEqualOtherClass() {
        assertFalse(p.equals(this));
    }

}
