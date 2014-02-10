package models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RaceEventTest {
	private RaceEvent raceEvent;
	private Participant part1, part2;

	@Before
	public void setUp() {
		raceEvent = new RaceEvent(1);
		part1 = new Participant(1);
		part2 = new Participant(2);
	}

	@Test
	public void testAddStartTime() {
		raceEvent.addStart(part1, new Time("12.00.00"));
		assertEquals("Should be the same", "12.00.00", raceEvent.getStart(part1).toString());
	}

	@Test
	public void testAddMultipleStartTimes() {
		raceEvent.addStart(part1,new Time("12.00.00"));
		raceEvent.addStart(part2,new Time("13.00.00"));

		assertEquals("Should be the same", "13.00.00", raceEvent.getStart(part2).toString());
		assertEquals("Should be the same", "12.00.00", raceEvent.getStart(part1).toString());

	}

	@Test
	public void testAddFinishTime() {
		raceEvent.addFinish(part1, new Time("12.00.00"));

		assertEquals("Should be the same", "12.00.00", raceEvent.getFinish(part1).toString());

	}

	@Test
	public void testGetNonExistingStartTime() throws Exception {
		assertEquals("Should be the same", raceEvent.getStart(part2).toString(), new Time().toString());
	}

	@Test
	public void testGetNonExistingFinishTime() throws Exception {
		assertEquals("Should be the same", raceEvent.getFinish(part2).toString(), new Time().toString());
	}

	@Test
	public void testAddMultipleFinishTimes() {
		raceEvent.addFinish(part1, new Time("12.00.00"));
		raceEvent.addFinish(part2, new Time("13.00.00"));
		assertEquals("Should be the same", "12.00.00", raceEvent.getFinish(part1).toString());
		assertEquals("Should be the same", "13.00.00", raceEvent.getFinish(part2).toString());
	}

	@Test
	public void testStartAndFinish() {
		raceEvent.addStart(part1, new Time("12.00.00"));
		raceEvent.addFinish(part1, new Time("13.00.00"));
		assertEquals("Should be the same", "12.00.00", raceEvent.getStart(part1).toString());
		assertEquals("Should be the same", "13.00.00", raceEvent.getFinish(part1).toString());
	}

	@Test

	public void testName() {
		raceEvent.addName(part1, "Calle");
		assertEquals("Shall be the same", "Calle", part1.getName());
	}

	@Test
	public void testIfInTime() {
		raceEvent.addName(part1, "Calle");
		assertEquals("Should be the same", raceEvent.getName(new Participant(1)),
				"Calle");
	}

	public void testTotalTime() {
		raceEvent.addStart(part1, new Time("12.00.00"));
		raceEvent.addFinish(part1, new Time("13.00.00"));
		assertEquals("Should be 01.00.00", "01.00.00", raceEvent.getTotalTime(part1).toString());

	}
}
