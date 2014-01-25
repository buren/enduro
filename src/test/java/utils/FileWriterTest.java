package utils;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FileWriterTest {
    FileReader fileReader;
    Enduro enduro;
    ArrayList<String> lines;

    @Before
    public void setUp() throws Exception {
        fileReader = new FileReader();
        enduro = Enduro.getInstance();
        lines = new ArrayList<String>();
        for (String line : new String[]{"1", "2", "3", "4", "5"}) {
            lines.add(line);
        }
    }

    @After
    public void tearDown() throws Exception {
        lines = null;
    }

    @Test
    public void testCanWriteEMptyFile() {
        String file = enduro.getResourcePath("utils/testOutputEmptyFile.csv");
        FileWriter.writeFile(file, new ArrayList<String>().iterator());
        try {
            fileReader.readFileByLine(file);
        } catch (FileNotFoundException e) {
            Assert.fail("Write failed: FileNotFoundException!");
        }
    }

    @Test
    public void testCanWriteFile() {
        String file = enduro.getResourcePath("utils/testOutputFile.csv");
        FileWriter.writeFile(file, lines.iterator());
        ArrayList<String> resultLines = null;
        try {
            resultLines = fileReader.readFileByLine(file);
        } catch (FileNotFoundException e) {
            Assert.fail("Write failed: FileNotFoundException!");
        }
        Assert.assertEquals("Should be 5", 5, lines.size());
        for (int i = 0; i < resultLines.size(); i++) {
            Assert.assertEquals("Should be equal!", lines.get(i), resultLines.get(i));
        }
    }


}
