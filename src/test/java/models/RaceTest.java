package models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RaceTest {
	private Race race;
	@Before
	public void setUp() {
		race = new Race();
	}
	
	@Test
	public void testEmpty() {
		assertEquals("Should be one", race.getNumberOfLaps(), 1);
	}
	
	@Test
	public void testStartTime() {
		race.setStart(new Time("12.00.00"));
		assertEquals("Should be 12.00.00", race.getStart(), new Time("12.00.00"));
	}
	
	@Test
	public void testFinishTime() {
		race.setStart(new Time("12.00.00"));
		race.setFinish(new Time("14.00.00"));
		assertEquals("Should be 14.00.00", race.getFinish(), new Time("14.00.00"));
	}
	
	@Test
	public void testTwoLapsFinish() {
		race.setStart(new Time("12.00.00"));
		race.setLapTime(new Time("12.30.00"));
		race.setLapTime(new Time("13.00.00"));
		assertEquals("Should be 13.00.00", race.getFinish(), new Time("13.00.00"));
	}
	
	@Test
	public void testNoFinishTime() {
		race.setStart(new Time("12.00.00"));
		assertEquals("Should be 12.00.00", race.getFinish(), new Time("12.00.00"));
	}
	
	@Test
	public void testNoStartTime() {
		assertEquals("Should be --.--.--", race.getFinish(), new Time());
	}
	
	@Test
	public void testFinishTimeWithNoStartTime() {
		assertEquals("Should be --.--.--", race.getFinish(), new Time());
	}
	
	@Test
	public void getOneLapTime() {
		race.setStart(new Time("12.00.00"));
		race.setLapTime(new Time("12.30.00"));
		race.setLapTime(new Time("13.50.00"));
		assertEquals("Should be 00.30.00", race.getLapTime(1), new Time("00.30.00"));
	}
	
	@Test
	public void getLapStartingTime() {
		race.setStart(new Time("12.00.00"));
		race.setLapTime(new Time("12.30.00"));
		race.setLapTime(new Time("13.50.00"));
		assertEquals("Should be 13.50.00", race.getLapStartTime(3), new Time("13.50.00"));
	}
	
}
