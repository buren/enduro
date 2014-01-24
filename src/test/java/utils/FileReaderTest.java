package utils;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;



public class FileReaderTest {
    FileReader fileReader;

    @Before
    public void setUp() throws Exception {
        fileReader = new FileReader();
    }

    @After
    public void tearDown() throws Exception { }

    @Test
    public void testCanFindFile() throws Exception {
        Assert.assertNotNull("Test file missing", fileReader.readFile("/utils/testCanFind.csv"));
    }

    @Test
    public void testCanReadFile() throws Exception {
        ArrayList<String> lines = fileReader.readFile("/utils/testCanFind.csv");
        Assert.assertEquals("Should be 1", "1", lines.get(0));
        Assert.assertEquals("Should be 1", "2", lines.get(1));
        Assert.assertEquals("Should be 1", "3", lines.get(2));
        Assert.assertEquals("Should have 3 lines", 3, lines.size());
    }

    @Test
    public void testCanReadEmptyFile() throws Exception {
        ArrayList<String> lines = fileReader.readFile("/utils/testEmpty.csv");
        Assert.assertEquals("Should have 0 lines", 0, lines.size());
    }

    @Test
    public void testCanReadEmptyLineFile() throws Exception {
        ArrayList<String> lines = fileReader.readFile("/utils/testEmptyLine.csv");
        Assert.assertEquals("Should have 0 lines", 0, lines.size());
    }

    @Test
    public void testDiscardsBlankLines() throws Exception  {
        ArrayList<String> lines = fileReader.readFile("/utils/testEmptyLinesInBetween.csv");
        Assert.assertEquals("Should have 1 line", 1, lines.size());
        Assert.assertEquals("Should be 'a'", lines.get(0), "a");
    }


    @Test
    public void testThrowsFileNotFoundException() {
        boolean success = false;
        try {
            fileReader.readFile("/file_does_not_exist");
        } catch (FileNotFoundException e) {
            success = true;
        } finally {
            Assert.assertEquals("Should have raised FileNotFoundException", true, success);
        }
    }

}
