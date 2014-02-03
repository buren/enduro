package sorter;

import static org.junit.Assert.*;
import models.Participant;
import models.TimeHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Enduro;

public class SorterTest {
	private TimeHandler time;

	@Before
	public void setUp() throws Exception {
		time = new TimeHandler();
	}

	@Test
	public void testSetName() {
		Sorter sort = new SortName();
		sort.insertInfo(
				Enduro.getInstance()
						.getResourcePath(
								"acceptanstester/iteration1/acceptanstest3_5/namnfil.txt"),
				"Namn", time);
		assertEquals("Should be same", time.getName(new Participant(2)),
				"Bengt Bsson");

	}

	@Test
	public void testSetFinishTime() {
		Sorter sort = new SortFinishTime();
		Participant p = new Participant(1);
		sort.insertInfo(
				Enduro.getInstance()
						.getResourcePath(
								"acceptanstester/iteration1/acceptanstest3_5/maltider.txt"),
				"Maltider", time);
		assertEquals("Should be same", "13.23.34", time.getFinish(p).toString());

	}

	@Test
	public void testSetStartTime() {
		Sorter sort = new SortStartTime();
		Participant p = new Participant(1);
		sort.insertInfo(
				Enduro.getInstance()
						.getResourcePath(
								"acceptanstester/iteration1/acceptanstest3_5/resultat.txt"),
				"StartTider", time);
		assertEquals("Should be same", "12.00.00", time.getStart(p).toString());

	}

}