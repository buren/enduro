package utils;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import models.Participant;
import models.RaceEvent;
import models.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Enduro;
import utils.FileReader;
import utils.Formatter;

public class FormatterTest {

	private ArrayList<Time> startTimes;
	private ArrayList<Time> finishTimes;
	private ArrayList<String> names;
	private Formatter formatter;
	private Enduro enduro;


	@Before
	public void setUp() throws Exception {
		formatter = new Formatter("20");
		startTimes = new ArrayList<Time>();
		finishTimes = new ArrayList<Time>();
		enduro = Enduro.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	// @Test
	public void testSimpleResultCase() {
		startTimes.add(new Time("12.12.12"));
		startTimes.add(new Time("14.14.14"));
		startTimes.add(new Time("17.17.17"));
		finishTimes.add(new Time("13.13.13"));
		finishTimes.add(new Time("19.19.19"));
		finishTimes.add(new Time("20.16.48"));
		names.add("Oskar");
		names.add("Viktor");
		names.add("Patrik");
		String resultList = formatter.generateResultList(startTimes,
				finishTimes, names, 1);
		assertEquals(resultList,
				"StartNr; Namn; TotalTid; StartTider; Maltider\n"
						+ "1; Oskar 01.01.01; 12.12.12; 13.13.13\n"
						+ "2; Vikar 05.05.05; 14.14.14; 19.19.19\n"
						+ "3; Patrik 02.59.31; 17.17.17; 20.16.48\n");
	}

	@Test
	public void testEmptyLists() {

		String result = formatter.generateResultList(startTimes, finishTimes,
				names, 1);
		assertEquals(result, "Listorna är tomma!");
	}

	// @Test
	public void testResultsWithFiles() throws FileNotFoundException {
		FileReader f = new FileReader();
		String path = Enduro.getInstance().getResourcePath(

		"acceptanstester/iteration1/acceptanstest3_4/");

		Iterator<String> iter;
		StringBuilder sb = new StringBuilder();
		try {
			iter = f.readFileByLine(path + "resultat.txt");
			while (iter.hasNext()) {
				sb.append(iter.next() + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals(

				formatter.generateResultList(path + "starttider.txt", path
						+ "maltider.txt", path + "namnfil.txt", 1),
				sb.toString());
	}

	@Test
	public void testWrongFilePath() {
		boolean success = false;
		try {
			formatter.generateResultList("lololzosdldsl",
					"dhrlhrol.malware.exe.virus.ru.warez", "asduiasdhj12", 1);
		} catch (FileNotFoundException e) {
			success = true;
		} finally {
			assertEquals("Should have raised FileNotFoundException", true,
					success);
		}
	}

	@Test
	public void testReadColumnNames() {
		try {
			String path = Enduro.getInstance().getResourcePath(
					"acceptanstester/iteration1/acceptanstest3_4/");

			assertEquals(formatter.readColumnNames(path + "namnfil.txt"),
					"StartNo; Namn");

			assertEquals(formatter.readColumnNames(path + "resultat.txt"),
					"StartNo; Name; TotalTime; StartTime; ResultTime");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadColumnNamesFileNotFoundException() {
		boolean success = false;
		try {
			formatter
					.readColumnNames(Enduro
							.getInstance()
							.getResourcePath(
									"acceptanstester/iteration1/acceptanstest3_4/catsNdogs.txt"));

		} catch (FileNotFoundException e) {
			success = true;
		} finally {
			assertEquals("Should have raised FileNotFoundException", true,
					success);
		}
	}

	@Test
	public void testEmptyFileException() throws FileNotFoundException {
		boolean success = false;
		try {
			formatter.readColumnNames(enduro
					.getResourcePath("/utils/testEmpty.csv"));
		} catch (IllegalStateException e) {
			success = true;
		}
		assertEquals("Should raise illegalStateException", true, success);
	}
	
//	@Test
//	public void testPrintActualLapTimes() {
//		RaceEvent event = new RaceEvent(20);
//		Participant p1 = new Participant(10);
//		p1.setName("Gunde Svan");
//		event.addParticipant(p1);
//		event.addStart(p1, new Time("12:12:12"));
//		event.getRace(p1).setLapTime(new Time("13:13:13"));
//		System.out.println(event.getRace(p1).getStart());
//	
//		System.out.println(formatter.printActualLapTimes(p1, 2));
//		
//		assertEquals(formatter.printActualLapTimes(p1, 2), "13:13:13");
//		
//	}
}
