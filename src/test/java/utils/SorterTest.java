package utils;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.Participant;
import models.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import race.LapRace;
import race.Race;
import race.TimeRace;

public class SorterTest {
	private ArrayList<Participant> participantList;
	private Sorter sort;

	@Before
	public void setUp() throws Exception {
		participantList = new ArrayList<Participant>();
		sort = new Sorter();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void sortByNumberOfLapsTest() {

		Participant p0 = new Participant(0);
		Participant p1 = new Participant(1);
		Race r0 = new TimeRace(new Time("01.00.00"));
		Race r1 = new TimeRace(new Time("01.00.00"));
		r0.setStart(new Time("12.00.00"));
		r0.addTime(new Time("12.15.00"));
		r0.addTime(new Time("12.30.00"));
		r1.setStart(new Time("12.00.00"));
		r1.addTime(new Time("12.20.00"));
		p0.setRace(r0);
		p1.setRace(r1);
		participantList.add(p0);
		participantList.add(p1);
		participantList = sort.sort(participantList);
		assertEquals(participantList.get(0), p0);
		r1.addTime(new Time());
		r1.addTime(new Time());
		participantList = sort.sort(participantList);
		assertEquals(participantList.get(0), p1);

	}

	@Test
	public void multipleSortByNumberOfLapsTest() {

		Participant p0 = new Participant(0);
		Participant p1 = new Participant(1);
		Participant p2 = new Participant(2);
		Participant p3 = new Participant(3);
		Race r0 = new TimeRace(new Time("01.00.00"));
		Race r1 = new TimeRace(new Time("01.00.00"));
		Race r2 = new TimeRace(new Time("01.00.00"));
		Race r3 = new TimeRace(new Time("01.00.00"));
		r0.addTime(new Time());
		r0.addTime(new Time());
		r0.addTime(new Time());
		r1.addTime(new Time());
		r1.addTime(new Time());
		r2.addTime(new Time());
		r2.addTime(new Time());
		r2.addTime(new Time());
		r2.addTime(new Time());
		r2.addTime(new Time());
		r3.addTime(new Time());
		r3.addTime(new Time());
		r3.addTime(new Time());
		p0.setRace(r0);
		p1.setRace(r1);
		p2.setRace(r2);
		p3.setRace(r3);
		participantList.add(p0);
		participantList.add(p1);
		participantList.add(p2);
		participantList.add(p3);
		participantList = sort.sort(participantList);
		assertEquals(participantList.get(0), p2);
		assertEquals(participantList.get(1), p0);
		assertEquals(participantList.get(2), p3);
		assertEquals(participantList.get(3), p1);

	}

	@Test
	public void sortEmptyListTest() {
		participantList.clear();
		sort.sort(participantList);
		assertEquals(participantList.size(), 0);
	}

	@Test
	public void sortByTotalTimeTest() {

		Participant p0 = new Participant(0);
		Participant p1 = new Participant(1);
		Race r0 = new LapRace(1);
		Race r1 = new LapRace(1);
		r0.setStart(new Time("12.00.00"));
		r0.addTime(new Time("12.30.00"));
		r1.setStart(new Time("12.00.00"));
		r1.addTime(new Time("12.20.00"));
		p0.setRace(r0);
		p1.setRace(r1);
		participantList.add(p0);
		participantList.add(p1);
		participantList = sort.sort(participantList);
		assertEquals(participantList.get(0), p1);
		r1.addTime(new Time("13.00.00"));
		participantList = sort.sort(participantList);
		assertEquals(participantList.get(0), p0);

	}

}
