package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrintTest {

	TimeHandlerDummy th;
	@Before
	public void setUp() {
		th = new TimeHandlerDummy();
	}

	@Test
	public void testAddThenPrint() {
		th.addStart("12.12.12");
		th.addFinish("13.13.13");
		assertEquals(th.print(), "12.12.12; 13.13.13");
	}

	@Test
	public void testPrintEmpty() {
		assertEquals(th.print(), "Empty time list.");

	}

	@Test
	public void testPrintMissingFinishTime() {
		th.addStart("12.12.12");
		assertEquals(th.print(), "12.12.12; --.--.--");
	}

	public void testPrintMissingStartTime() {
		th.addFinish("12.12.12");
		assertEquals(th.print(), "--.--.--; 12.12.12");
	}

	@Test
	public void testPrintMultipleTimes() {
		th.addStart("12.12.12");
		th.addStart("12.12.13");
		th.addStart("12.12.14");
		th.addFinish("13.13.12");
		th.addFinish("13.13.13");
		th.addFinish("13.13.14");
		assertEquals(th.print(),
				"12.12.12; 13.13.12\n12.12.13; 13.13.13\n12.12.14; 13.13.14");
	}
}
