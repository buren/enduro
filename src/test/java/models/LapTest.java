package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LapTest {
	Lap lap;

	@Before
	public void setUp() {
		lap = new Lap();
	}

	@Test
	public void testNoTimes() {
		assertEquals("--.--.--", lap.getStart().toString());
		assertEquals("--.--.--", lap.getFinish().toString());
	}
	@Test
	public void testStartTimeOnly(){
		lap.setStart(new Time("12.12.12"));
		assertEquals("12.12.12", lap.getStart().toString());
		assertEquals("--.--.--", lap.getFinish().toString());
	}
	@Test
	public void testFinishTimeOnly(){
		lap.setFinish(new Time("12.12.12"));
		assertEquals("12.12.12", lap.getFinish().toString());
		assertEquals("--.--.--", lap.getStart().toString());
	}
	
	@Test
	public void testTotalTime(){
		lap.setStart(new Time("12.00.00"));
		lap.setFinish(new Time("12.00.01"));
		assertEquals("00.00.01", lap.getTotalTime().toString());
	}

}
