package sorter;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.Iterator;

import models.LapRace;
import models.Participant;
import models.RaceEvent;

import org.junit.Before;
import org.junit.Test;

import utils.Enduro;
import utils.FileReader;

public class ModelInitiatorTest {
	private RaceEvent raceEvent;
    FileReader fr;
    String path;

	@Before
	public void setUp() throws Exception {
        path = Enduro.getInstance().getResourcePath("acceptanstester/iteration1/acceptanstest3_5/");
		raceEvent = new RaceEvent(new LapRace(4));
        fr = new FileReader();
	}

	@Test
	public void testSetName() {
		ModelInitiator sort = new SortName();
        FileReader fr = new FileReader();
        try {
            Iterator iterator = fr.readFileByLine(path+"namnfil.txt");
			sort.insertInfo(iterator,"Name", raceEvent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Should be same", "Bengt Bsson", raceEvent.getParticipant(2).getName());

	}

	@Test
	public void testSetFinishTime() {
		ModelInitiator sort = new SortFinishTime();
		try {
            Iterator iterator = fr.readFileByLine(path+"maltider.txt");
			sort.insertInfo(iterator, "Maltider", raceEvent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Should be same", "13.23.34", raceEvent.getParticipant(1).getRace().print(3));

	}

	@Test
	public void testSetSortStartTime() {
		ModelInitiator sort = new SortStartTime();
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
            Iterator iterator = fr.readFileByLine(path+"starttider.txt");
			sort.insertInfo(iterator, "StartTider", raceEvent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Should be same", "12.00.00", raceEvent.getParticipant(1).getRace().getStart().toString());
	}

	@Test
	public void testSetStartTime() {
		ModelInitiator sort = new SortStartTime();
		ModelInitiator nameSort = new SortName();
		try {
            Iterator iterator = fr.readFileByLine(path+"namnfil.txt");
			nameSort.insertInfo(iterator, "Namn", raceEvent);
            iterator = fr.readFileByLine(path+"starttider.txt");
			sort.insertInfo(iterator,"StartTider", raceEvent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Should be same", "12.00.00", raceEvent.getParticipant(1).getRace().getStart().toString());

	}

	@Test
	public void testSetMultipleFinishTime() {
		RaceEvent rc = new RaceEvent(new LapRace(10));
		SortFinishTime sort = new SortFinishTime();
		ModelInitiator originalSort;

		try {
			String basePath = Enduro.getInstance().getResourcePath(
					"acceptanstester/iteration2/acceptanstest10/");


            Iterator iterator = fr.readFileByLine(basePath + "namnfil.txt");
            originalSort = new SortName();
            originalSort.insertInfo(iterator, "Namn", rc);

            iterator = fr.readFileByLine(basePath + "starttider.txt");
            originalSort = new SortStartTime();
            originalSort.insertInfo(iterator, "StartTider",	rc);

            String[] paths = new String[2];
            paths[0] = basePath + "maltider1.txt";
            paths[1] = basePath + "maltider2.txt";
            Iterator[] iterators = new Iterator[2];
            iterators[0] = fr.readFileByLine(paths[0]);
            iterators[1] = fr.readFileByLine(paths[1]);
            sort.insertInfo(iterators, "Maltider", rc);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		assertEquals("Should be same", "13.23.34", rc.getParticipant(1).print(3));

	}

	@Test
	public void testMassStartTimes() {
		ModelInitiator sort = new SortStartTime();
		ModelInitiator namesort = new SortName();
		try {
            Iterator iterator = fr.readFileByLine(path+"namnfil.txt");
			namesort.insertInfo(iterator, "Namn", raceEvent);
            iterator = fr.readFileByLine(path+"masstart.txt");
			sort.insertInfo(iterator, "StartTider", raceEvent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("Should be same", "12.00.00", raceEvent.getParticipant(1).getRace().getStart().toString());
	}

}