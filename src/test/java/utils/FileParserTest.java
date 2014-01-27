package utils;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import junit.framework.Assert;
import models.TimeHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FileParserTest {
	Enduro enduro;
	FileParser fileParser;
	TimeHandler timeHandler;
	@Before
    public void setUp() throws Exception {
		fileParser = new FileParser();
        enduro = Enduro.getInstance();
        timeHandler = new TimeHandler();
    }

    @After
    public void tearDown() throws Exception { }

	@Test
	public void testStartParsing() throws FileNotFoundException {
		timeHandler = fileParser.parseStartFile(enduro.getResourcePath("/acceptanstester/iteration1/acceptanstest3/starttider.txt"));

		assertEquals("12.00.00", timeHandler.getStart(0));
		assertEquals("12.01.00", timeHandler.getStart(1));
		assertEquals("12.02.00", timeHandler.getStart(2));
		assertEquals("12.03.00", timeHandler.getStart(3));
		assertEquals("12.04.00", timeHandler.getStart(4));
	}

	@Test
	public void testFinishParsing() throws FileNotFoundException {
		timeHandler = fileParser.parseFinishFile(enduro.getResourcePath("/acceptanstester/iteration1/acceptanstest3/maltider.txt"));

		Assert.assertEquals("13.23.34", timeHandler.getFinish(0));
		Assert.assertEquals("13.15.16", timeHandler.getFinish(1));
		Assert.assertEquals("13.05.06", timeHandler.getFinish(2));
		Assert.assertEquals("13.12.07", timeHandler.getFinish(3));
		Assert.assertEquals("13.16.07", timeHandler.getFinish(4));
	}

}
