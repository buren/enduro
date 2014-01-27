package main;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Enduro;
import utils.FileReader;


public class MainTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testResultListOutput() {
		Enduro e = Enduro.getInstance();
		String s = e.getResourcePath("../");
		FileReader r = new FileReader();
	}

}
