package sorter;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import models.Participant;
import models.Time;
import models.RaceEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Enduro;

public class SorterTest {
	private RaceEvent raceEvent;

	@Before
	public void setUp() throws Exception {
		raceEvent = new RaceEvent();
	}

	@Test
	public void testSetName() {
		Sorter sort = new SortName();
		try {
			sort.insertInfo(
					Enduro.getInstance()
							.getResourcePath(
									"acceptanstester/iteration1/acceptanstest3_5/namnfil.txt"),
					"Name", raceEvent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Should be same", raceEvent.getName(new Participant(2)),
				"Bengt Bsson");

	}

	@Test
	public void testSetFinishTime() {
		Sorter sort = new SortFinishTime();
		Participant p = new Participant(1);
		try {
			sort.insertInfo(
					Enduro.getInstance()
							.getResourcePath(
									"acceptanstester/iteration1/acceptanstest3_5/maltider.txt"),
					"Maltider", raceEvent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Should be same", "13.23.34", raceEvent.getFinish(p).toString());

	}

	@Test
	public void testSetStartTime() {
		Sorter sort = new SortStartTime();
		Participant p = new Participant(1);
		try {
			sort.insertInfo(
					Enduro.getInstance()
							.getResourcePath(
									"acceptanstester/iteration1/acceptanstest3_5/resultat.txt"),
					"StartTider", raceEvent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Should be same", "12.00.00", raceEvent.getStart(p).toString());

	}



}