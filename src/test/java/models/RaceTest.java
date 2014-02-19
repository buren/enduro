package models;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RaceTest {;
    private Race race;

	@Before
	public void setUp() {
        race = new SimpleRace();
	}

	@Test
	public void testEmpty() {
		assertEquals("Should be one", 1, race.getLaps());
	}

	@Test
	public void testStartTime() {
        assertTrue("Should be empty", race.getStart().isEmpty());
        Time startTime = new Time("12.00.00");
		race.setStart(startTime);
		assertEquals("Should be 12.00.00", startTime, race.getStart());
        assertEquals("Should be startTime, followed by empty totalTime and finishTime", "--.--.--; "+startTime+"; --.--.--", race.print(1));
	}

	@Test
	public void testFinishTime() {
		Time finishTime = new Time("14.00.00");
		race.addTime(finishTime);
		assertEquals("Should be empty start and totaltimes followed by 14.00.00", "--.--.--; --.--.--; "+finishTime, race.print(1));
	}

	@Test
    public void testPrintSimpleRace() {
        Time startTime = new Time("12.00.00");
        race.setStart(startTime);
        Time finishTime = new Time("14.00.00");
        race.addTime(finishTime);
        assertEquals("Should be same", "02.00.00; "+startTime+"; "+finishTime, race.print(1));
    }

    @Test
    public void testPrintLapRace() {
        Race lapRace = new LapRace(3);
        lapRace.setStart(new Time("12.00.00"));
        lapRace.addTime(new Time("12.30.00"));
        lapRace.addTime(new Time("13.30.00"));
        lapRace.addTime(new Time("14.30.00"));
        String print = lapRace.print(3);
        assertEquals("Should be same","; 3; 02.30.00; 00.30.00; 01.00.00; 01.00.00; 12.00.00; 12.30.00; 13.30.00; 14.30.00", print);
        print = lapRace.print(4);
        assertEquals("Should be same","; 3; 02.30.00; 00.30.00; 01.00.00; 01.00.00; --.--.--; 12.00.00; 12.30.00; 13.30.00; --.--.--; 14.30.00",print);
    }

    @Test
    public void testPrintTimeRace() {
        Race timeRace = new TimeRace(new Time("01.00.00"));
        timeRace.setStart(new Time("12.00.00"));
        timeRace.addTime(new Time("12.30.00"));
        timeRace.addTime(new Time("13.00.00"));
        String print = timeRace.print(2);
        assertEquals("Should be same", "; 2; 01.00.00; 00.30.00; 00.30.00; 12.00.00; 12.30.00; 13.00.00",print);
    }

    @Test
    public void testLimitLapRace() {
        Race lapRace = new LapRace(3);

        boolean testLap = lapRace.testLimit();
        assertTrue(testLap);
        lapRace.addTime(new Time("00.00.00"));
        assertTrue(lapRace.testLimit());
        lapRace.addTime(new Time("00.01.00"));
        lapRace.addTime(new Time("00.02.00"));
        assertFalse(lapRace.testLimit());
    }

    @Test
    public void testLimitTimeRace() {
        Race timeRace = new TimeRace(new Time("01.00.00"));
        timeRace.setStart(new Time("12.00.00"));
        assertTrue(timeRace.testLimit());
        timeRace.addTime(new Time("12.30.00"));
        assertTrue(timeRace.testLimit());
        timeRace.addTime(new Time("13.00.00"));
        assertFalse(timeRace.testLimit());
    }




}