package sorter;

import static org.junit.Assert.*;
import models.Participant;
import models.Time;
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
				"Name", time);
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
	
	@Test
	public void fyra_fyra_fulhack() {
		Sorter sort = new SortName();
		sort.insertInfo(
				Enduro.getInstance()
						.getResourcePath(
								"acceptanstester/iteration1/acceptanstest5/namnfil.txt"),
				"Namn", time);
		sort = new SortFinishTime();
		Participant p = new Participant(1);
		sort.insertInfo(
				Enduro.getInstance()
						.getResourcePath(
								"acceptanstester/iteration1/acceptanstest5/maltider.txt"),
				"Maltider", time);
		sort = new SortStartTime();
		p = new Participant(1);
		sort.insertInfo(
				Enduro.getInstance()
						.getResourcePath(
								"acceptanstester/iteration1/acceptanstest5/starttider.txt"),
				"StartTider", time);		
	
	StringBuilder sb = new StringBuilder();
	int count = 5;
	sb.append("StartNo; Name; TotalTime; StartTime; ResultTime\n");
	for (int i = 0; i < count; i++) {
		Time totalTime = time.getStart(new Participant(i+1))
				.compareTo(time.getFinish(new Participant(i+1)));
		sb.append(i + 1 + "; " + time.getName(new Participant(i + 1))
				+ "; " + totalTime + "; "
				+ time.getStart(new Participant(i + 1)) + "; "
				+ time.getFinish(new Participant(i + 1)) + "\n");

	}
	System.out.println(sb.toString());
	}
	
}