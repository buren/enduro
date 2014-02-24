package models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import race.SimpleRace;

public class RaceEventTest {
    private RaceEvent raceEvent;
    private Participant participant;

    @Before
    public void setUp() {
        raceEvent = new RaceEvent(new SimpleRace());
        participant = new Participant(1);
    }

    @Test
    public void testAddParticipant() {
        raceEvent.addParticipant(participant);
        assertTrue("Should contain participant 1", raceEvent.containsParticipant(1));
        assertFalse("Should not contain participant 2", raceEvent.containsParticipant(2));

        assertEquals("Should get participant", participant, raceEvent.getParticipant(1));
    }

    @Test
    public void testSetAllStartTimes() {
        Participant p2, p3;
        p2 = new Participant(2);
        p3 = new Participant(3);

        raceEvent.addParticipant(participant);
        raceEvent.addParticipant(p2);
        raceEvent.addParticipant(p3);

        p2.getRace().addStartTime(new Time("11.00.00"));
        p3.getRace().addStartTime(new Time("10.00.00"));

        Time newTime = new Time("12.00.00");
        raceEvent.setAllStartTimes(newTime);

        assertEquals("Should be same", "--.--.--; 12.00.00; Slut?", participant.getRace().print(3));
        assertEquals("Should be same", "--.--.--; 12.00.00; Slut?", p2.getRace().print(3));
        assertEquals("Should be same", "--.--.--; 12.00.00; Slut?", p3.getRace().print(3));
    }


}
