package acceptanceTests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.Iterator;

import controllers.FormatterController;
import org.junit.Before;
import org.junit.Test;

import utils.Enduro;
import utils.FileReader;

public class acceptanceTest {
    private Enduro enduro;
    private String basePath;
    private String pathToNameFile;
    private String pathToStartFile;
    private String[] pathsToFinishFiles;
    private String pathToExpectedResult;


    @Before
    public void setUp() throws Exception {
        enduro = Enduro.getInstance();
    }

    private void makePaths() {
        pathToNameFile = basePath + "namnfil.txt";
        pathToStartFile = basePath + "starttider.txt";
        pathToExpectedResult = basePath + "resultat.txt";
        pathsToFinishFiles = new String[1];
        pathsToFinishFiles[0] = basePath + "maltider.txt";
    }

    @Test
    public void acceptanceTest6() throws FileNotFoundException {
        FormatterController fc = new FormatterController();

        basePath = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration1/acceptanstest6/");
        makePaths();

        String resultList = fc.result(pathToStartFile, pathsToFinishFiles, pathToNameFile,
                FormatterController.LAP_RACE, "1", 1);
        assertEquals(readFileToString(pathToExpectedResult), resultList);
    }

    @Test
    public void acceptanceTest9() throws FileNotFoundException {
        FormatterController fc = new FormatterController();

        basePath = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration2/acceptanstest9/");
        makePaths();

        String resultList = fc.result(pathToStartFile, pathsToFinishFiles, pathToNameFile,
                FormatterController.LAP_RACE, "3", 3);
        assertEquals(readFileToString(pathToExpectedResult), resultList);
    }

    @Test
    public void acceptanceTest9TimeRace() throws FileNotFoundException {
        FormatterController fc = new FormatterController();

        basePath = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration2/acceptanstest9/");
        makePaths();
        pathToExpectedResult = basePath + "resultatTimeRace.txt";

        String resultList = fc.result(pathToStartFile, pathsToFinishFiles, pathToNameFile,
                FormatterController.TIME_RACE, "01.00.00", 3);
        assertEquals(readFileToString(pathToExpectedResult), resultList);
    }

    @Test
    public void acceptanceTest10() throws FileNotFoundException {
        FormatterController fc = new FormatterController();

        basePath = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration2/acceptanstest10/");
        makePaths();
        String pathToFinishFile1 = basePath + "maltider1.txt";
        String pathToFinishFile2 = basePath + "maltider2.txt";
        String[] finishFileArray = {pathToFinishFile1, pathToFinishFile2};

        String result = fc.result(pathToStartFile, finishFileArray, pathToNameFile,
                FormatterController.LAP_RACE, "3", 3);
        assertEquals(readFileToString(pathToExpectedResult), result);
    }

    @Test
    public void acceptanceTest13() throws FileNotFoundException {
        FormatterController fc = new FormatterController();

        basePath = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration2/acceptanstest13/");
        makePaths();
        String pathToFinishFile1 = basePath + "maltider1.txt";
        String pathToFinishFile2 = basePath + "maltider2.txt";
        String[] finishFileArray = {pathToFinishFile1, pathToFinishFile2};

        String result = fc.result(pathToStartFile, finishFileArray, pathToNameFile,
                FormatterController.LAP_RACE, "3", 2);
        assertEquals(readFileToString(pathToExpectedResult), result);
    }

    @Test
    public void acceptanceTest15() throws FileNotFoundException {
        FormatterController fc = new FormatterController();

        basePath = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration2/acceptanstest15/");
        makePaths();
        String resultList = fc.result(pathToStartFile, pathsToFinishFiles, pathToNameFile,
                FormatterController.LAP_RACE, "30", 3);
        assertEquals(readFileToString(pathToExpectedResult), resultList);
    }

    @Test
    public void acceptanceTest16() throws FileNotFoundException {
        FormatterController formatterController = new FormatterController();

        basePath = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration2/acceptanstest16/");
        makePaths();
        String result = formatterController.result(pathToStartFile, pathsToFinishFiles, pathToNameFile,
                FormatterController.LAP_RACE, "3", 3);
        assertEquals(readFileToString(pathToExpectedResult), result);
    }

    @Test
    public void acceptanceTest17() throws FileNotFoundException {
        FormatterController formatterController = new FormatterController();

        basePath = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration2/acceptanstest17/");
        makePaths();
        String pathToFinishFile1 = basePath + "maltider1.txt";
        String pathToFinishFile2 = basePath + "maltider2.txt";
        String[] finishFileArray = {pathToFinishFile1, pathToFinishFile2};

        String result = formatterController.result(pathToStartFile, finishFileArray, pathToNameFile,
                FormatterController.LAP_RACE, "3", 3);
        assertEquals(readFileToString(pathToExpectedResult), result);
    }

    private String readFileToString(String filePath)
            throws FileNotFoundException {
        FileReader fr = new FileReader();
        Iterator<String> itr = fr.readFileByLine(filePath);
        StringBuilder sb = new StringBuilder();

        while (itr.hasNext()) {
            sb.append(itr.next());
            sb.append("\n");
        }
        return sb.toString();
    }
}