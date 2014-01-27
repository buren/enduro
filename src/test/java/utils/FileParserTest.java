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
	
	@Before
    public void setUp() throws Exception {
		fileParser = new FileParser();
        enduro = Enduro.getInstance();
    }

    @After
    public void tearDown() throws Exception { }
	
	@Test
	public void testParsing() throws FileNotFoundException {
		TimeHandler timeHandler = fileParser.parseFile(enduro.getResourcePath("/acceptanstester/iteration1/acceptanstest3/starttider.txt"));

		assertEquals("12.00.00", timeHandler.getStart(0));
		assertEquals("12.01.00", timeHandler.getStart(1));
		assertEquals("12.02.00", timeHandler.getStart(2));
		assertEquals("12.03.00", timeHandler.getStart(3));
		assertEquals("12.04.00", timeHandler.getStart(4));
	}

}
