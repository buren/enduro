package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert.*;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import models.TimeHandler;

public class FileParserTest {
	Enduro enduro;
	
	@Before
    public void setUp() throws Exception {
        enduro = Enduro.getInstance();
    }

    @After
    public void tearDown() throws Exception { }
	
	@Test
	public void testParsing() throws FileNotFoundException {
		FileParser fileParser = new FileParser();
		ArrayList<String> timeHandler = fileParser.parseFile(enduro.getResourcePath("/acceptanstester/iteration1/acceptanstest3/starttider.txt"));

		Assert.assertEquals("12.00.00", timeHandler.get(0));
		Assert.assertEquals("12.01.00", timeHandler.get(1));
		Assert.assertEquals("12.02.00", timeHandler.get(2));
		Assert.assertEquals("12.03.00", timeHandler.get(3));
		Assert.assertEquals("12.04.00", timeHandler.get(4));
	}

}
