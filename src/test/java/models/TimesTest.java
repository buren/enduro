package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TimesTest {
	Times times;

	@Before
	public void setUp() {
		times = new Times();
	}

	@Test
	public void testNoTimes() {
		assertEquals("--.--.--", times.getStart().toString());
		assertEquals("--.--.--", times.getFinish().toString());
	}
	@Test
	public void testStartTimeOnly(){
		times.setStart("12.12.12");
		assertEquals("12.12.12", times.getStart().toString());
		assertEquals("--.--.--", times.getFinish().toString());
	}
	@Test
	public void testFinishTimeOnly(){
		times.setFinish("12.12.12");
		assertEquals("12.12.12", times.getFinish().toString());
		assertEquals("--.--.--", times.getStart().toString());
	}
	
	@Test
	public void testTotalTime(){
		times.setStart("12.00.00");
		times.setFinish("12.00.01");
		assertEquals("00.00.01", times.getTotalTime().toString());
	}

}
