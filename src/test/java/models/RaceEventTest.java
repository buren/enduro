package models;

import org.junit.Before;
import org.junit.Test;

import race.SimpleRace;

import static org.junit.Assert.*;

public class RaceEventTest {
    private RaceEvent raceEvent;
    private Participant participant;

    @Before
    public void setUp() {
        raceEvent = new RaceEvent(new SimpleRace());
        participant = new Participant(1);
        raceEvent.addParticipant(participant);
    }

    @Test
    public void testContainsNonExistingParticipant() {
        assertFalse(raceEvent.containsParticipant(10));
    }
    
    @Test
    public void testContainsParticipant() {
        assertTrue(raceEvent.containsParticipant(1));
    }
    
    @Test
    public void testCopy() {
        participant.getRace().getClass().equals(SimpleRace.class);
    }

    @Test
    public void testGetParticipant() {
        assertTrue(raceEvent.getParticipant(1).equals(participant));
    }

    @Test
    public void testGetNonExistingParticipant() {
        assertEquals(raceEvent.getParticipant(10), null);
    }

    @Test
    public void testSetAllStartTimes() {
        Participant p2 = new Participant(2);
        raceEvent.addParticipant(p2);

        participant.getRace().addStartTime(new Time("05.00.00"));
        p2.getRace().addStartTime(new Time("14.00.00"));

        Time newTime = new Time("12.00.00");
        raceEvent.setAllStartTimes(newTime);

        assertEquals("Should be same", "; --.--.--; 12.00.00; Slut?", participant.getRace().print(3));
        assertEquals("Should be same", "; --.--.--; 12.00.00; Slut?", p2.getRace().print(3));
    }

    @Test
    public void testAddNotRegistered() {
        raceEvent = new RaceEvent(new SimpleRace());
        Participant invalidParticipant = new Participant(1);
        raceEvent.addNotRegisteredParticipant(invalidParticipant, RaceEvent.START_TIME, new Time("12.00.00"));
        String expected = "Icke existerande startnummer:\n" +
                "StartNr; Namn; TotalTid; Start; Mal\n" +
                "1; Not named; --.--.--; 12.00.00; Slut?\n";
        assertEquals(expected, raceEvent.print(2));
    }

    @Test
    public void testAddNotRegisteredTwice() {
        raceEvent = new RaceEvent(new SimpleRace());
        Participant invalid = new Participant(2);
        Participant invalid2 = new Participant(2);
        raceEvent.addNotRegisteredParticipant(invalid, RaceEvent.START_TIME, new Time("12.00.00"));
        raceEvent.addNotRegisteredParticipant(invalid2, RaceEvent.LAP_TIME, new Time("13.00.00"));
        String actual = raceEvent.print(3);
        String expected = "Icke existerande startnummer:\n" +
                "StartNr; Namn; TotalTid; Start; Mal\n" +
                "2; Not named; 01.00.00; 12.00.00; 13.00.00\n";
        assertEquals(expected, actual);
    }

    @Test
    public void testAddClasses() {
        Participant p2 = new Participant(2);
        participant.setRaceClass("JUNIOR");
        p2.setRaceClass("SENIOR");
        raceEvent.addParticipant(p2);
        String expected = "JUNIOR\n" +
                "StartNr; Namn; TotalTid; Start; Mal\n" +
                "1; Not named; --.--.--; Start?; Slut?\n" +
                "SENIOR\n" +
                "StartNr; Namn; TotalTid; Start; Mal\n" +
                "2; Not named; --.--.--; Start?; Slut?\n";
        assertEquals(expected, raceEvent.print(1));
    }
    
    @Test
    public void testStartOneByClass()
    {
    	participant.setRaceClass("Lexforce");
    	raceEvent.setAllClassStart("Lexforce", new Time("13.37.00"));
    	assertEquals(raceEvent.print(0), "Lexforce\n" + 
    	"StartNr; Namn; TotalTid; Start; Mal\n" +
    	"1; Not named; --.--.--; 13.37.00; Slut?\n"	);
    }
    
    @Test
    public void testStartManyByClass() {
    	participant.setRaceClass("Lexforce");    	
    	Participant p1= new Participant(14);
    	p1.setRaceClass("Lexforce");
    	Participant p2= new Participant(16);
    	p2.setRaceClass("Lexforce");
    	Participant p3= new Participant(15);
    	p3.setRaceClass("Lexforce");
    	raceEvent.addParticipant(p1);
    	raceEvent.addParticipant(p2);
    	raceEvent.addParticipant(p3);
    	raceEvent.setAllClassStart("Lexforce", new Time("13.37.00"));
    	assertEquals(raceEvent.print(0), "Lexforce\n" + 
    	"StartNr; Namn; TotalTid; Start; Mal\n" +
    	"1; Not named; --.--.--; 13.37.00; Slut?\n"	+
    	"14; Not named; --.--.--; 13.37.00; Slut?\n" +
    	"16; Not named; --.--.--; 13.37.00; Slut?\n" +
    	"15; Not named; --.--.--; 13.37.00; Slut?\n");
    }

    @Test
    public void testManyClassesStart() {
    	participant.setRaceClass("Lexforce");    	
    	Participant p1= new Participant(14);
    	p1.setRaceClass("Lexforce");
    	Participant p2= new Participant(16);
    	p2.setRaceClass("StarBase");
    	Participant p3= new Participant(15);
    	p3.setRaceClass("StarBase");
    	raceEvent.addParticipant(p1);
    	raceEvent.addParticipant(p2);
    	raceEvent.addParticipant(p3);
    	raceEvent.setAllClassStart("Lexforce", new Time("13.37.00"));
    	raceEvent.setAllClassStart("StarBase", new Time("04.20.00"));
    	assertEquals(raceEvent.print(0), "Lexforce\n" + 
    	"StartNr; Namn; TotalTid; Start; Mal\n" +
    	"1; Not named; --.--.--; 13.37.00; Slut?\n"	+
    	"14; Not named; --.--.--; 13.37.00; Slut?\n" +
    	"StarBase\n" + 
    	"StartNr; Namn; TotalTid; Start; Mal\n" +
    	"16; Not named; --.--.--; 04.20.00; Slut?\n" +
    	"15; Not named; --.--.--; 04.20.00; Slut?\n");
    	
    	System.out.println(raceEvent.print(0));
    }

    @Test
    public void testClassCaseSensitivity() {
    	participant.setRaceClass("StarBase");    	
    	Participant p1= new Participant(14);
    	p1.setRaceClass("StarBase");
    	Participant p2= new Participant(16);
    	p2.setRaceClass("StarBase");
    	raceEvent.addParticipant(p1);
    	raceEvent.addParticipant(p2);
    	raceEvent.setAllClassStart("Starbase", new Time("14.20.00"));
    	assertEquals(raceEvent.print(0), "StarBase\n" + 
    	    	"StartNr; Namn; TotalTid; Start; Mal\n" +
    	    	"1; Not named; --.--.--; Start?; Slut?\n"	+
    	    	"14; Not named; --.--.--; Start?; Slut?\n" +
    	    	"16; Not named; --.--.--; Start?; Slut?\n");
    }
    
    
}
