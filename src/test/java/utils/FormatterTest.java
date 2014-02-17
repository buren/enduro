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
	private Formatter timeFormatter;
	private Enduro enduro;
	private Formatter lapFormatter;


	@Before
	public void setUp() throws Exception {
		names = new ArrayList<String>();
		lapFormatter = new Formatter(15);
		timeFormatter = new Formatter("20.20.15");
		startTimes = new ArrayList<Time>();
		finishTimes = new ArrayList<Time>();
		enduro = Enduro.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		lapFormatter = null;
		timeFormatter = null;
		startTimes = null;
		finishTimes = null;
		enduro = null;
		names = null;
	}

	@Test
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
		String resultList = timeFormatter.generateResultList(startTimes,
				finishTimes, names, 1);
		assertEquals(resultList,
				"StartNo; Name; TotalTime; StartTime; ResultTime\n"
						+ "1; Oskar; 01.01.01; 12.12.12; 13.13.13\n"
						+ "2; Viktor; 05.05.05; 14.14.14; 19.19.19\n"
						+ "3; Patrik; 02.59.31; 17.17.17; 20.16.48\n");
		
	}
	
	

	@Test
	public void testEmptyLists() {
		String result = timeFormatter.generateResultList(startTimes, finishTimes,
				names, 1);
		assertEquals(result, "Listorna är tomma!");
	}

	@Test
	public void testWrongFilePath() {
		boolean success = false;
		try {
			timeFormatter.generateResultList("lololzosdldsl",
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

			assertEquals(timeFormatter.readColumnNames(path + "namnfil.txt"),
					"StartNo; Namn");

			assertEquals(timeFormatter.readColumnNames(path + "resultat.txt"),
					"StartNo; Name; TotalTime; StartTime; ResultTime");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadColumnNamesFileNotFoundException() {
		boolean success = false;
		try {
			timeFormatter
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
			timeFormatter.readColumnNames(enduro
					.getResourcePath("/utils/testEmpty.csv"));
		} catch (IllegalStateException e) {
			success = true;
		}
		assertEquals("Should raise illegalStateException", true, success);
	}

}
