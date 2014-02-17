package models;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import models.Participant;
import models.RaceEvent;
import models.Time;

import org.junit.Before;
import org.junit.Test;

import sorter.SortFinishTime;
import sorter.SortName;
import sorter.SortStartTime;
import sorter.Sorter;
import utils.Enduro;

public class RaceEventTest {
	private RaceEvent raceEvent;
	private Participant part1, part2;
	private Enduro enduro;

	@Before
	public void setUp() {

		enduro = Enduro.getInstance();
		raceEvent = new RaceEvent(3);

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
	public void testIfInTime() {
		raceEvent.addParticipant(part1);
		part1.setName("Calle");
		assertEquals("Should be the same", raceEvent.getName(new Participant(1)),
				"Calle");
	}
	
	@Test
	public void testTotalTime() {
		raceEvent.addStart(part1, new Time("12.00.00"));
		raceEvent.addFinish(part1, new Time("13.00.00"));
		assertEquals("Should be 01.00.00", "01.00.00", raceEvent.getTotalTime(part1).toString());

	}
	
	@Test
	public void testMultipleLapTimesFromFile() throws FileNotFoundException {
		Sorter sort = new SortName();
		sort.insertInfo(enduro.getResourcePath("acceptanstester/iteration2/acceptanstest9/namnfil.txt"), "Namn", raceEvent);
		sort = new SortStartTime();
		sort.insertInfo(enduro.getResourcePath("acceptanstester/iteration2/acceptanstest9/starttider.txt"), "StartTider", raceEvent);
		sort = new SortFinishTime();
		sort.insertInfo(enduro.getResourcePath("acceptanstester/iteration2/acceptanstest9/maltider.txt"), "Maltider", raceEvent);
		assertEquals("Should be 00.30.00", raceEvent.getLapTime(new Participant(1), 1), new Time("00.30.00"));
		assertEquals("Should be 00.30.00", raceEvent.getLapTime(new Participant(1), 2), new Time("00.30.00"));
		assertEquals("Should be 12.00.00", raceEvent.getLapStartTime(new Participant(1), 2), new Time("12.30.00"));
		
	}
	

}
