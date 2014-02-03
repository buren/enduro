package utils;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FormatterTest {

	private ArrayList<String> startTimes;
	private ArrayList<String> finishTimes;
	private Formatter formatter;
	private Enduro enduro;

	@Before
	public void setUp() throws Exception {
		formatter = new Formatter();
		startTimes = new ArrayList<String>();
		finishTimes = new ArrayList<String>();
		enduro = Enduro.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSimpleResultCase() {
		startTimes.add("12.12.12");
		startTimes.add("14.14.14");
		startTimes.add("17.17.17");
		finishTimes.add("13.13.13");
		finishTimes.add("19.19.19");
		finishTimes.add("20.16.48");
		String resultList = formatter.generateResultList(startTimes, finishTimes);
		assertEquals(resultList, "StartNo; TotalTime; StartTime; ResultTime\n"
				+ "1; --.--.--; 12.12.12; 13.13.13\n"
				+ "2; --.--.--; 14.14.14; 19.19.19\n"
				+ "3; --.--.--; 17.17.17; 20.16.48\n");
	}

	@Test
	public void testEmptyLists() {
		Formatter printer = new Formatter();
		String result = printer.generateResultList(startTimes, finishTimes);
		assertEquals(result, "Both lists are empty!");
	}

	@Test
	public void testResultsWithFiles() throws FileNotFoundException {
		FileReader f = new FileReader();
		String path = Enduro.getInstance().getResourcePath(
				"acceptanstester/iteration1/acceptanstest3/");
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
				formatter.generateResultList(path + "starttider.txt", path + "maltider.txt"),
				sb.toString());
	}

	@Test
	public void testWrongFilePath() {
		boolean success = false;
		try {
			formatter.generateResultList("lololzosdldsl",
					"dhrlhrol.malware.exe.virus.ru.warez");
		} catch (FileNotFoundException e) {
			success = true;
		} finally {
			assertEquals("Should have raised FileNotFoundException",
					true, success);
		}
	}
	
	@Test
	public void testReadColumnNames() {
		try {
			String path = Enduro.getInstance().getResourcePath(
					"acceptanstester/iteration1/acceptanstest3_4/");
			
			
			assertEquals(formatter.readColumnNames(path + "namnfil.txt"), "StartNo; Namn");
			
			

			assertEquals(formatter.readColumnNames(path + "resultat.txt"), "StartNo; Name; TotalTime; StartTime; ResultTime");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testReadColumnNamesFileNotFoundException() {
		boolean success = false;
		try {
			formatter.readColumnNames(Enduro.getInstance().getResourcePath(
				"acceptanstester/iteration1/acceptanstest3_4/catsNdogs.txt"));
			
	
		} catch (FileNotFoundException e) {
			success = true;
		} finally {
			assertEquals("Should have raised FileNotFoundException",
					true, success);
		}
	}
	
	@Test
	public void testEmptyFileException() throws FileNotFoundException {
		boolean success = false;
		try {
			formatter.readColumnNames(enduro.getResourcePath("/utils/testEmpty.csv"));
		} catch (IllegalStateException e) {
			success = true;
		}
		assertEquals("Should raise illegalStateException", true, success);
	}
}
