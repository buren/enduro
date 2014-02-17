package sorter;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import models.Participant;
import models.RaceEvent;

import org.junit.Before;
import org.junit.Test;

import sorter.SortFinishTime;
import sorter.SortName;
import sorter.SortStartTime;
import sorter.Sorter;
import utils.Enduro;

public class SorterTest {
	private RaceEvent raceEvent;

	@Before
	public void setUp() throws Exception {
		raceEvent = new RaceEvent(4);
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

		assertEquals("Should be same", "13.23.34", raceEvent.getFinish(p)
				.toString());

	}

	@Test
	public void testSetSortStartTime() {
		Sorter sort = new SortStartTime();
		Participant p1 = new Participant(1);
		Participant p2 = new Participant(2);
		Participant p3 = new Participant(3);
		Participant p4 = new Participant(4);
		Participant p5 = new Participant(5);
		raceEvent.addParticipant(p1);
		raceEvent.addParticipant(p2);
		raceEvent.addParticipant(p3);
		raceEvent.addParticipant(p4);
		raceEvent.addParticipant(p5);
		try {
			sort.insertInfo(
					Enduro.getInstance()
							.getResourcePath(
									"acceptanstester/iteration1/acceptanstest3_5/starttider.txt"),
					"StartTider", raceEvent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Should be same", "12.00.00", raceEvent.getStart(p1)
				.toString());

	}

	@Test
	public void testSetStartTime() {
		Sorter sort = new SortStartTime();
		Sorter namesort = new SortName();
		Participant p = new Participant(1);
		try {
			namesort.insertInfo(
					Enduro.getInstance()
							.getResourcePath(
									"acceptanstester/iteration1/acceptanstest3_5/namnfil.txt"),
					"Namn", raceEvent);
			sort.insertInfo(
					Enduro.getInstance()
							.getResourcePath(
									"acceptanstester/iteration1/acceptanstest3_5/starttider.txt"),
					"StartTider", raceEvent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Should be same", "12.00.00", raceEvent.getStart(p)
				.toString());

	}

	@Test
	public void testSetMultipleFinishTime() {
		RaceEvent rc = new RaceEvent(10);
		SortFinishTime sort = new SortFinishTime();
		Participant participant = new Participant(1);
		Sorter originalSort;

		try {
			String basePath = Enduro.getInstance().getResourcePath(
					"acceptanstester/iteration2/acceptanstest10/");
			String[] paths = new String[2];
			paths[0] = basePath + "maltider1.txt";
			paths[1] = basePath + "maltider2.txt";

			originalSort = new SortName();
			originalSort.insertInfo(basePath + "namnfil.txt", "Namn", rc);
			originalSort = new SortStartTime();
			originalSort.insertInfo(basePath + "starttider.txt", "StartTider",
					rc);
			sort.insertInfo(paths, "Maltider", rc);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		assertEquals("Should be same", "13.23.34", rc.getFinish(participant)
				.toString());

	}

	@Test
	public void testMassStartTimes() {
		Sorter sort = new SortStartTime();
		Sorter namesort = new SortName();
		Participant p = new Participant(1);
		try {
			namesort.insertInfo(
					Enduro.getInstance()
							.getResourcePath(
									"acceptanstester/iteration1/acceptanstest3_5/namnfil.txt"),
					"Namn", raceEvent);
			sort.insertInfo(
					Enduro.getInstance()
							.getResourcePath(
									"acceptanstester/iteration1/acceptanstest3_5/masstart.txt"),
					"StartTider", raceEvent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Should be same", "12.00.00", raceEvent.getStart(p)
				.toString());
	}

}