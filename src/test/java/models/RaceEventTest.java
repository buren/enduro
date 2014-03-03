package models;

import org.junit.Before;
import org.junit.Test;

import race.SimpleRace;
import utils.Enduro;

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

        participant.getRace().setStart(new Time("05.00.00"));
        p2.getRace().setStart(new Time("11.00.00"));

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
                "StartNr; Namn; #Varv; TotalTid; Varv1; Varv2; Start; Varvning1; Mal\n" +
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
                "StartNr; Namn; #Varv; TotalTid; Varv1; Varv2; Varv3; Start; Varvning1; Varvning2; Mal\n" +
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
                "StartNr; Namn; #Varv; TotalTid; Varv1; Start; Mal\n" +
                "1; Not named; --.--.--; Start?; Slut?\n" +
                "SENIOR\n" +
                "StartNr; Namn; #Varv; TotalTid; Varv1; Start; Mal\n" +
                "2; Not named; --.--.--; Start?; Slut?\n";
        assertEquals(expected, raceEvent.print(1));
    }


}
