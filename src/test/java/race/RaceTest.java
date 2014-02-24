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
	public void testStartTime() {
		assertEquals("Should be empty totaltime and asking for missing times",
				"--.--.--; Start?; Slut?", race.print(1));
		Time startTime = new Time("12.00.00");
		race.addStartTime(startTime);
		assertEquals(
				"Should be startTime, followed by empty totalTime and asking for finishtime",
				"--.--.--; " + startTime + "; Slut?", race.print(1));
	}

	@Test
	public void testFinishTime() {
		Time finishTime = new Time("14.00.00");
		race.addFinishTime(finishTime);
		assertEquals(
				"Should be asking for starttime, empty totaltime followed by 14.00.00",
				"--.--.--; Start?; " + finishTime, race.print(1));
	}

	@Test
	public void testPrintSimpleRace() {
		Time startTime = new Time("12.00.00");
		race.addStartTime(startTime);
		Time finishTime = new Time("14.00.00");
		race.addFinishTime(finishTime);
		assertEquals("Should be same", "02.00.00; " + startTime + "; "
				+ finishTime, race.print(1));
	}

	@Test
	public void testPrintLapRace() {
		Race lapRace = new LapRace(3);
		lapRace.addStartTime(new Time("12.00.00"));
		lapRace.addFinishTime(new Time("12.30.00"));
		lapRace.addFinishTime(new Time("13.30.00"));
		lapRace.addFinishTime(new Time("14.30.00"));
		String print = lapRace.print(3);
		assertEquals(
				"Should be same",
				"; 3; 02.30.00; 00.30.00; 01.00.00; 01.00.00; 12.00.00; 12.30.00; 13.30.00; 14.30.00",
				print);
		print = lapRace.print(4);
		assertEquals(
				"Should be same",
				"; 3; 02.30.00; 00.30.00; 01.00.00; 01.00.00; --.--.--; 12.00.00; 12.30.00; 13.30.00; --.--.--; 14.30.00",
				print);
	}

	@Test
	public void testPrintTimeRace() {
		Race timeRace = new TimeRace(new Time("01.00.00"));
		timeRace.addStartTime(new Time("12.00.00"));
		timeRace.addFinishTime(new Time("12.30.00"));
		timeRace.addFinishTime(new Time("13.00.00"));
		String print = timeRace.print(2);
		assertEquals(
				"Should be same",
				"; 2; 01.00.00; 00.30.00; 00.30.00; 12.00.00; 12.30.00; 13.00.00",
				print);
	}

	@Test
	public void testLimitLapRace() {
		Race lapRace = new LapRace(3);

		boolean testLap = lapRace.testLimit();
		assertTrue(testLap);
		lapRace.addFinishTime(new Time("00.00.00"));
		assertTrue(lapRace.testLimit());
		lapRace.addFinishTime(new Time("00.01.00"));
		lapRace.addFinishTime(new Time("00.02.00"));
		assertFalse(lapRace.testLimit());
	}

	@Test
	public void testLimitTimeRace() {
		Race timeRace = new TimeRace(new Time("01.00.00"));
		timeRace.addStartTime(new Time("12.00.00"));
		assertTrue(timeRace.testLimit());
		timeRace.addFinishTime(new Time("12.30.00"));
		assertTrue(timeRace.testLimit());
		timeRace.addFinishTime(new Time("13.00.00"));
		assertFalse(timeRace.testLimit());
	}

	@Test
	public void testLapAndTimeRaceNoTimes() {
		Race lapRace = new LapRace(2);
		RaceEvent event = new RaceEvent(lapRace);
		Participant p = new Participant(1);
		event.addParticipant(p);
		assertEquals(
				"Should be same",
				"StartNr; Namn; #Varv; TotalTid; Varv1; Varv2; Start; Varvning1; Mal\n"
						+ "1; Not named; 0; --.--.--; --.--.--; --.--.--; Start?; --.--.--; Slut?\n",
				event.print(2));
	}

	@Test
	public void testMultipleStartTimes() {
		Race lapRace = new LapRace(2);
		RaceEvent event = new RaceEvent(lapRace);
		Participant p = new Participant(1);
		event.addParticipant(p);
		event.setAllStartTimes(new Time("12.00.00"));
		event.setAllStartTimes(new Time("12.45.00"));
		assertEquals(
				"Should be same",
				"StartNr; Namn; #Varv; TotalTid; Varv1; Varv2; Start; Varvning1; Mal\n"
						+ "1; Not named; 0; --.--.--; --.--.--; --.--.--; 12.00.00; --.--.--; 12.00.00; Flera starttider? 12.45.00 \n",
				event.print(2));
	}

	@Test
	public void testBiggerMultipleStartTimes() {
		Race lapRace = new LapRace(2);
		RaceEvent event = new RaceEvent(lapRace);
		Participant p1 = new Participant(1);
		Participant p2 = new Participant(2);
		Participant p3 = new Participant(3);
		Participant p4 = new Participant(4);
		event.addParticipant(p1);
		event.addParticipant(p2);
		event.addParticipant(p3);
		event.addParticipant(p4);
		event.setAllStartTimes(new Time("12.00.00"));
		event.setAllStartTimes(new Time("12.45.00"));

		StringBuilder s = new StringBuilder();
		s.append("StartNr; Namn; #Varv; TotalTid; Varv1; Varv2; Start; Varvning1; Mal\n");
		s.append("1; Not named; 0; --.--.--; --.--.--; --.--.--; 12.00.00; --.--.--; 12.00.00; Flera starttider? 12.45.00 \n");
		s.append("2; Not named; 0; --.--.--; --.--.--; --.--.--; 12.00.00; --.--.--; 12.00.00; Flera starttider? 12.45.00 \n");
		s.append("3; Not named; 0; --.--.--; --.--.--; --.--.--; 12.00.00; --.--.--; 12.00.00; Flera starttider? 12.45.00 \n");
		s.append("4; Not named; 0; --.--.--; --.--.--; --.--.--; 12.00.00; --.--.--; 12.00.00; Flera starttider? 12.45.00 \n");
		assertEquals("Should be same", s.toString(), event.print(2));
	}

	@Test
	public void testLapLessThan15() {
		Race lapRace = new LapRace(2);
		RaceEvent event = new RaceEvent(lapRace);
		Participant p1 = new Participant(1);
		event.addParticipant(p1);
		p1.getRace().addStartTime(new Time("12.00.00"));
		p1.getRace().addFinishTime(new Time("12.05.00"));
		String s = "StartNr; Namn; #Varv; TotalTid; Varv1; Varv2; Start; Varvning1; Mal\n"
				+ "1; Not named; 1; 00.05.00; 00.05.00; --.--.--; 12.00.00; --.--.--; 12.05.00; Omöjlig varvtid?\n";
		assertEquals("Should be same", event.print(2), s);
	}

}