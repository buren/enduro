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
    public void testPrintSimple() {
        Time startTime = new Time("12.00.00");
        race.setStart(startTime);
        Time finishTime = new Time("14.00.00");
        race.addTime(finishTime);
        assertEquals("", "02.00.00; "+startTime+"; "+finishTime, race.print(1));
    }




}