package models;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TimeHandlerTest {
	private TimeHandler time;

	@Before
	public void setUp() {
		time = new TimeHandler();
	}

	@Test
	public void testAddStartTime() {
		time.addStart("12.00.00");
		assertEquals("Should be the same", "12.00.00", time.getStart(0));

	}

	@Test
	public void testAddMultipleStartTimes() {
		time.addStart("12.00.00");
		time.addStart("13.00.00");
		assertEquals("Should be the same", "12.00.00", time.getStart(0));
		assertEquals("Should be the same", "13.00.00", time.getStart(1));
	}

	@Test
	public void testAddFinishTime() {
		time.addFinish("12.00.00");
		assertEquals("Should be the same", "12.00.00", time.getFinish(0));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetNonExistingStartTime() throws Exception {
		time.addStart("12.00.00");
		time.getStart(2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetNonExistingFinishTime() throws Exception {
		time.addFinish("12.00.00");
		time.getFinish(2);
	}

	@Test
	public void testAddMultipleFinishTimes() {
		time.addFinish("12.00.00");
		time.addFinish("13.00.00");
		assertEquals("Should be the same", "12.00.00", time.getFinish(0));
		assertEquals("Should be the same", "13.00.00", time.getFinish(1));
	}

}
