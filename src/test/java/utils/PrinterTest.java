package utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PrinterTest {

	private ArrayList<String> startTimes;
	private ArrayList<String> finishTimes;
	private Printer printer;
	@Before
	public void setUp() throws Exception {
		printer = new Printer();
		startTimes = new ArrayList<String>();
		finishTimes = new ArrayList<String>();	
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
		
		String resultList = printer.generateResultList(startTimes, finishTimes);
		
		assertEquals(resultList, "StartNo; TotalTime; StartTime; ResultTime\n"
				+ "1; --.--.--; 12.12.12; 13.13.13\n"
				+ "2; --.--.--; 14.14.14; 19.19.19\n"
				+ "3; --.--.--; 17.17.17; 20.16.48\n");
		
		
	}
	
	@Test
	public void testEmptyLists() {
		Printer printer = new Printer();
		String result = printer.generateResultList(startTimes, finishTimes);
		assertEquals(result, "Both lists are empty!");
		
	}
	
	@Test
	public void testResultsWithFiles() {
		
		FileReader f = new FileReader();
		String path = Enduro.getInstance().getResourcePath(
				"acceptanstester/iteration1/acceptanstest3/resultat.txt");
		
		Iterator<String> iter;
		StringBuilder sb = new StringBuilder();
		try {
			iter = f.readFileByLine(path);
			while(iter.hasNext()) {
				sb.append(iter.next() + "\n");
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(printer.generateResultList("starttider.txt", "maltider.txt"), sb.toString());
	}

}





