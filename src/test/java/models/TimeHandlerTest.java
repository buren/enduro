package models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TimeHandlerTest {
	private TimeHandler time;
	private Participant part1, part2;

	@Before
	public void setUp() {
		time = new TimeHandler();
		part1 = new Participant(1);
		part2 = new Participant(2);
	}

	@Test
	public void testAddStartTime() {
		time.addStart(part1, "12.00.00");
		assertEquals("Should be the same", "12.00.00", time.getStart(part1));
	}

	@Test
	public void testAddMultipleStartTimes() {
		time.addStart(part1, "12.00.00");
		time.addStart(part2, "13.00.00");
		assertEquals("Should be the same", "13.00.00", time.getStart(part2));
		assertEquals("Should be the same", "12.00.00", time.getStart(part1));
	}

	@Test
	public void testAddFinishTime() {
		time.addFinish(part1, "12.00.00");
		assertEquals("Should be the same", "12.00.00", time.getFinish(part1));
	}

	@Test
	public void testGetNonExistingStartTime() throws Exception {
		assertEquals("Should be the same", time.getStart(part2), "--.--.--");
	}

	@Test
	public void testGetNonExistingFinishTime() throws Exception {
		assertEquals("Should be the same", time.getFinish(part2), "--.--.--");
	}

	@Test
	public void testAddMultipleFinishTimes() {
		time.addFinish(part1, "12.00.00");
		time.addFinish(part2, "13.00.00");
		assertEquals("Should be the same", "12.00.00", time.getFinish(part1));
		assertEquals("Should be the same", "13.00.00", time.getFinish(part2));
	}

	@Test
	public void testStartAndFinish() {
		time.addStart(part1, "12.00.00");
		time.addFinish(part1, "13.00.00");
		assertEquals("Should be the same", "12.00.00", time.getStart(part1));
		assertEquals("Should be the same", "13.00.00", time.getFinish(part1));
	}

}
