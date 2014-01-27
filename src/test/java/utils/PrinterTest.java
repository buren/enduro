package utils;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
		assertEquals(result, "StartNo; TotalTime; StartTime; ResultTime\n");
		
	}

}
