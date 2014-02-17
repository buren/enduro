package models;

import static org.junit.Assert.*;
import models.Race;
import models.Time;

import org.junit.Before;
import org.junit.Test;

public class RaceTest {
	private Race lapRace;
	private Race timeRace;

	@Before
	public void setUp() {
		lapRace = new LapRace(3);
		timeRace = new TimeRace("00.30.00");
	}

	@Test
	public void testEmpty() {
		assertEquals("Should be one", lapRace.getCurrentLap(), 1);
	}

	@Test
	public void testStartTime() {
		lapRace.setStart(new Time("12.00.00"));
		assertEquals("Should be 12.00.00", lapRace.getStart(), new Time(
				"12.00.00"));
	}

	@Test
	public void testFinishTime() {
		lapRace.setStart(new Time("12.00.00"));
		lapRace.setFinish(new Time("14.00.00"));
		assertEquals("Should be 14.00.00", lapRace.getFinish(), new Time(
				"14.00.00"));
	}

	@Test
	public void testTwoLapsFinish() {
		lapRace.setStart(new Time("12.00.00"));
		lapRace.setLapTime(new Time("12.30.00"));
		lapRace.setLapTime(new Time("13.00.00"));
		assertEquals("Should be 13.00.00", lapRace.getFinish(), new Time(
				"13.00.00"));
	}

	@Test
	public void testNoFinishTime() {
		lapRace.setStart(new Time("12.00.00"));
		assertEquals("Should be 12.00.00", lapRace.getFinish(), new Time(
				"12.00.00"));
	}

	@Test
	public void testNoStartTime() {
		assertEquals("Should be --.--.--", lapRace.getStart(), new Time());
	}

	@Test
	public void testFinishTimeWithNoStartTime() {
		assertEquals("Should be --.--.--", lapRace.getFinish(), new Time());
	}

	@Test
	public void getOneLapTime() {
		lapRace.setStart(new Time("12.00.00"));
		lapRace.setLapTime(new Time("12.30.00"));
		lapRace.setLapTime(new Time("13.50.00"));
		assertEquals("Should be 00.30.00", lapRace.getLapTime(1), new Time(
				"00.30.00"));
	}

	@Test
	public void getLapStartingTime() {
		lapRace.setStart(new Time("12.00.00"));
		lapRace.setLapTime(new Time("12.30.00"));
		lapRace.setLapTime(new Time("13.50.00"));
		assertEquals("Should be 13.50.00", lapRace.getLapStartTime(3),
				new Time("13.50.00"));
	}

	@Test
	public void addTooManyLaps() {
		lapRace.setStart(new Time("12.00.00"));
		lapRace.setLapTime(new Time("12.30.00"));
		lapRace.setLapTime(new Time("13.50.00"));
		lapRace.setLapTime(new Time("14.50.00"));
		lapRace.setLapTime(new Time("15.50.00"));
		assertEquals("Should be three", lapRace.getCurrentLap(), 3);
		assertEquals("Should be same", lapRace.getTotalTime(), new Time("02.50.00"));
	}

	@Test
	public void testLapTime() {
		lapRace.setStart(new Time("12.00.00"));
		lapRace.setLapTime(new Time("12.30.00"));
		lapRace.setLapTime(new Time("12.40.00"));
		assertEquals("Should be the same ", lapRace.getLapTime(1), new Time(
				"00.30.00"));
		assertEquals("Should be the same ", lapRace.getLapTime(2), new Time(
				"00.10.00"));
	}

	@Test
	public void testRaceTimeCap() {
		timeRace.setStart(new Time("12.00.00"));
		timeRace.setLapTime(new Time("12.15.00"));
		timeRace.setLapTime(new Time("12.35.00"));
		assertEquals("Should be the same", timeRace.getLapTime(1), new Time(
				"00.15.00"));
		assertEquals("Should be the same ", timeRace.getLapTime(2), new Time(
				"00.20.00"));
		assertEquals("Should be the same (two)", timeRace.getCurrentLap(), 2);
	}
}