package controllers;

import acceptanceTests.acceptanceTest;
import org.junit.Test;
import utils.Enduro;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class FormatterControllerTest {

    @Test
    public void acceptanceTest5() throws FileNotFoundException {
        FormatterController fc = new FormatterController();

        String basePath = Enduro.getInstance().getResourcePath(
                "acceptanstester/iteration1/");
        String pathToNameFile = basePath + "acceptanstest5/namnfil.txt";
        String pathToStartFile = basePath + "acceptanstest5/starttider.txt";
        String pathToExpectedResult = basePath + "acceptanstest5/resultat.txt";
        String[] pathsToFinishFiles = new String[1];
        pathsToFinishFiles[0] = basePath + "acceptanstest5/maltider.txt";

        String resultList = fc.result(pathToStartFile, pathsToFinishFiles, pathToNameFile,
                FormatterController.SIMPLE_RACE, "1", 1);
        String[] results = resultList.split("\n");
        ArrayList<String> lines = new ArrayList<String>();
        Collections.addAll(lines, results);
        fc.writeToFile(basePath + "formatterWriteTest.txt", lines.iterator());
        assertEquals(acceptanceTest.readFileToString(pathToExpectedResult), resultList);
    }
}
