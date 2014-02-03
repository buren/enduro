package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TimeTest {
	Time time;
	
	@Before
	public void setUp() {
		time = new Time("12.12.12");
	}
	
	@Test
	public void testStringTime() {
		assertEquals("12.12.12", time.toString());
	}
	
	@Test
	public void testCompareTo() {
		Time time2 = new Time("10.10.10");
		
		Time time3 = time.compareTo(time2);
		assertEquals("02.02.02", time3.toString());
	}
	
	@Test
	public void testCompareToNegative() {
		time = new Time("10.10.10");
		Time time2 = new Time("12.12.12");
		
		Time time3 = time.compareTo(time2);
		assertEquals("02.02.02", time3.toString());
	}
	
	@Test
	public void testEmptyTime() {
		time = new Time();
		assertEquals("--.--.--", time.toString());
		assertTrue(time.isEmpty());
	}

}