package utils;

import models.Participant;
import models.TimeHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import junit.framework.Assert;


public class FileParserTest {
	Enduro enduro;
	FileParser fileParser;
	TimeHandler timeHandler;
	Participant p;
	
	@Before
    public void setUp() throws Exception {
		fileParser = new FileParser();
        enduro = Enduro.getInstance();
        timeHandler = new TimeHandler();
        p = new Participant("1");
        
    }

    @After
    public void tearDown() throws Exception { }

	@Test
	public void testStartParsing() throws FileNotFoundException {
		timeHandler = fileParser.parseStartFile(enduro.getResourcePath("/acceptanstester/iteration1/acceptanstest3/starttider.txt"));

		Assert.assertEquals("12.00.00", timeHandler.getStart(p));
		Assert.assertEquals("12.01.00", timeHandler.getStart(new Participant("2")));
		Assert.assertEquals("12.02.00", timeHandler.getStart(new Participant("3")));
		Assert.assertEquals("12.03.00", timeHandler.getStart(new Participant("4")));
		Assert.assertEquals("12.04.00", timeHandler.getStart(new Participant("5")));
	}

	@Test
	public void testFinishParsing() throws FileNotFoundException {
		timeHandler = fileParser.parseFinishFile(enduro.getResourcePath("/acceptanstester/iteration1/acceptanstest3/maltider.txt"));

		Assert.assertEquals("13.23.34", timeHandler.getFinish(new Participant("1")));
		Assert.assertEquals("13.15.16", timeHandler.getFinish(new Participant("2")));
		Assert.assertEquals("13.05.06", timeHandler.getFinish(new Participant("3")));
		Assert.assertEquals("13.12.07", timeHandler.getFinish(new Participant("4")));
		Assert.assertEquals("13.16.07", timeHandler.getFinish(new Participant("5")));
	}

}
