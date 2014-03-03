package race;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import models.*;

import org.junit.Before;
import org.junit.Test;

public class RaceTest {

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
    public void testEmptyTimes() {
        assertEquals("; --.--.--; Start?; Slut?", race.print(1));
    }

	@Test
	public void testStartTime() {
		Time startTime = new Time("12.00.00");
		race.addStartTime(startTime);
		assertEquals("; --.--.--; 12.00.00; Slut?", race.print(1));
	}


	@Test
	public void testFinishTime() {
		Time finishTime = new Time("14.00.00");
		race.addFinishTime(finishTime);
		assertEquals("; --.--.--; Start?; 14.00.00", race.print(1));
	}

	@Test
	public void testPrintSimpleRace() {
		race.addStartTime(new Time("12.00.00"));
		race.addFinishTime(new Time("14.00.00"));
		assertEquals("; 02.00.00; 12.00.00; 14.00.00", race.print(1));
	}

	@Test
	public void testLapAndTimeRaceNoTimes() {
		race.addStartTime(new Time("12.00.00"));
		race.addFinishTime(new Time("13.00.00"));
		assertEquals("; 01.00.00; 12.00.00; 13.00.00", race.print(3));
	}

    @Test
    public void testCompareToEqual() {
        Race timeRace1 = new TimeRace(new Time("01.00.00"));
        Race timeRace2 = new TimeRace(new Time("01.00.00"));
        timeRace1.addStartTime(new Time("12.00.00"));
        timeRace2.addStartTime(new Time("12.00.00"));
        timeRace1.addFinishTime(new Time("13.00.00"));
        timeRace2.addFinishTime(new Time("13.00.00"));
        assertFalse(timeRace1.isBetter(timeRace2));
    }

    @Test
    public void testCompareToBetterLap() {
        Race timeRace1 = new TimeRace(new Time("01.00.00"));
        Race timeRace2 = new TimeRace(new Time("01.00.00"));
        timeRace1.addStartTime(new Time("12.00.00"));
        timeRace2.addStartTime(new Time("12.00.00"));
        timeRace1.addFinishTime(new Time("12.30.00"));
        timeRace2.addFinishTime(new Time("12.30.00"));
        timeRace1.addFinishTime(new Time("12.50.00"));
        assertTrue(timeRace1.isBetter(timeRace2));
    }

    @Test
    public void testCompareToBetterTime() {
        Race timeRace1 = new TimeRace(new Time("01.00.00"));
        Race timeRace2 = new TimeRace(new Time("01.00.00"));
        timeRace1.addStartTime(new Time("12.00.00"));
        timeRace2.addStartTime(new Time("12.00.00"));
        timeRace1.addFinishTime(new Time("12.30.00"));
        timeRace2.addFinishTime(new Time("13.00.00"));
        assertTrue(timeRace1.isBetter(timeRace2));
    }

    @Test
    public void testPrintEmptyLaprace() {
        Race lapRace = new LapRace(2);
        assertEquals("; 0; --.--.--; --.--.--; Start?; Slut?", lapRace.print(1));
    }

    @Test
    public void testPrintLimitOverAvailableTimes() {
        Race lapRace = new LapRace(1);
        lapRace.addStartTime(new Time("12.00.00"));
        lapRace.addFinishTime(new Time("12.30.00"));
        assertEquals("; 1; 00.30.00; 00.30.00; --.--.--; --.--.--; 12.00.00; --.--.--; --.--.--; 12.30.00", lapRace.print(3));
    }

    @Test
    public void testPrintLapOrTimeRace() {
        Race lapRace = new LapRace(2);
        lapRace.addStartTime(new Time("12.00.00"));
        lapRace.addFinishTime(new Time("12.30.00"));
        lapRace.addFinishTime(new Time("13.30.00"));
        assertEquals("; 2; 01.30.00; 00.30.00; 01.00.00; 12.00.00; 12.30.00; 13.30.00", lapRace.print(2));
    }

	@Test
	public void testPrintErrorMultipleStartTimes() {
		Race lapRace = new LapRace(2);
        lapRace.addStartTime(new Time("12.00.00"));
        lapRace.addStartTime(new Time("12.10.00"));
        lapRace.addStartTime(new Time("12.20.00"));
        lapRace.addFinishTime(new Time("13.00.00"));
        assertEquals("; 1; 01.00.00; 01.00.00; 12.00.00; 13.00.00; Flera starttider? 12.10.00 12.20.00", lapRace.print(1));
	}

    @Test
    public void testPrintErrorMultipleFinishTimes(){
        Race lapRace = new LapRace(1);
        lapRace.addStartTime(new Time("12.00.00"));
        lapRace.addFinishTime(new Time("12.30.00"));
        lapRace.addFinishTime(new Time("13.30.30"));
        lapRace.addFinishTime(new Time("14.20.10"));
        assertEquals("; 1; 00.30.00; 00.30.00; 12.00.00; 12.30.00; Flera maltider? 13.30.30 14.20.10", lapRace.print(1));
    }

	@Test
	public void testPrintErrorLapLessThan15() {
		Race lapRace = new LapRace(2);
		lapRace.addStartTime(new Time("12.00.00"));
		lapRace.addFinishTime(new Time("12.05.00"));
		assertEquals("; 1; 00.05.00; 00.05.00; 12.00.00; 12.05.00; Omöjlig varvtid?", lapRace.print(1));
	}

    @Test
    public void testCopySimpleRace() {
        assertTrue(race.copy().getClass().equals(SimpleRace.class));
    }

    @Test
    public void testLimitSimpleRace() {
        assertFalse(race.testLimit());
    }
}