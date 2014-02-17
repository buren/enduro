package utils;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import models.Participant;
import models.RaceEvent;
import models.Time;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Enduro;
import utils.FileReader;
import utils.Formatter;

public class FormatterTest {
    private Formatter formatter;
    private Enduro enduro;


    @Before
    public void setUp() throws Exception {
        formatter = new Formatter("20");
        enduro = Enduro.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testWrongFilePath() {
        boolean success = false;
        try {
            formatter.generateResultList("lololzosdldsl",
                    "dhrlhrol.malware.exe.virus.ru.warez", "asduiasdhj12", 1);
        } catch (FileNotFoundException e) {
            success = true;
        } finally {
            assertEquals("Should have raised FileNotFoundException", true,
                    success);
        }
    }

    @Test
    public void testReadColumnNames() {
        try {
            String path = Enduro.getInstance().getResourcePath(
                    "acceptanstester/iteration1/acceptanstest3_4/");

            assertEquals(formatter.readColumnNames(path + "namnfil.txt"),
                    "StartNo; Namn");

            assertEquals(formatter.readColumnNames(path + "resultat.txt"),
                    "StartNo; Name; TotalTime; StartTime; ResultTime");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadColumnNamesFileNotFoundException() {
        boolean success = false;
        try {
            formatter
                    .readColumnNames(Enduro
                            .getInstance()
                            .getResourcePath(
                                    "acceptanstester/iteration1/acceptanstest3_4/catsNdogs.txt"));

        } catch (FileNotFoundException e) {
            success = true;
        } finally {
            assertEquals("Should have raised FileNotFoundException", true,
                    success);
        }
    }

    @Test
    public void testEmptyFileException() throws FileNotFoundException {
        boolean success = false;
        try {
            formatter.readColumnNames(enduro
                    .getResourcePath("/utils/testEmpty.csv"));
        } catch (IllegalStateException e) {
            success = true;
        }
        assertEquals("Should raise illegalStateException", true, success);
    }
}
