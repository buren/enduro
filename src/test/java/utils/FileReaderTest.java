package utils;

import java.io.FileNotFoundException;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import utils.Enduro;
import utils.FileReader;


public class FileReaderTest {
    FileReader fileReader;
    Enduro enduro;

    @Before
    public void setUp() throws Exception {
        fileReader = new FileReader();
        enduro = Enduro.getInstance();
    }

    @Test
    public void testCanFindFile() throws Exception {
        Assert.assertNotNull("Test file missing", fileReader.readFileByLine(enduro.getResourcePath("/utils/testCanFind.csv")));
    }

    @Test
    public void testCanReadFile() throws Exception {
        Iterator<String> lines = fileReader.readFileByLine(enduro.getResourcePath("/utils/testCanFind.csv"));
        Assert.assertEquals("Should be 1", "1", lines.next());
        Assert.assertEquals("Should be 1", "2", lines.next());
        Assert.assertEquals("Should be 1", "3", lines.next());
        Assert.assertEquals("Should have have no more lines", false, lines.hasNext());
    }

    @Test
    public void testCanReadEmptyFile() throws Exception {
        Iterator<String> lines = fileReader.readFileByLine(enduro.getResourcePath("/utils/testEmpty.csv"));
        Assert.assertEquals("Should be empty", false, lines.hasNext());
    }

    @Test
    public void testCanReadEmptyLineFile() throws Exception {
        Iterator<String> lines = fileReader.readFileByLine(enduro.getResourcePath("/utils/testEmptyLine.csv"));
        Assert.assertEquals("Should be empty", false, lines.hasNext());
    }

    @Test
    public void testDiscardsBlankLines() throws Exception {
        Iterator<String> lines = fileReader.readFileByLine(enduro.getResourcePath("/utils/testEmptyLinesInBetween.csv"));
        Assert.assertEquals("Should have line", true, lines.hasNext());
        Assert.assertEquals("Should be 'a'", lines.next(), "a");
    }

    @Test
    public void testThrowsFileNotFoundException() throws Exception {
        boolean success = false;
        try {
            fileReader.readFileByLine(enduro.getResourcePath("/file_does_not_exist"));
        } catch (FileNotFoundException e) {
            success = true;
        } finally {
            Assert.assertEquals("Should have raised FileNotFoundException", true, success);
        }
    }

}
