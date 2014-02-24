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

    @Before
    public void setUp() throws Exception {
        enduro = Enduro.getInstance();
    }

    @Test
    public void acceptanceTest9() throws FileNotFoundException {
        FormatterController formatterController = new FormatterController();

        String path = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration2/acceptanstest9/");
        String pathToNameFile = path + "namnfil.txt";
        String pathToStartFile = path + "starttider.txt";
        String[] pathsFinishFiles = {path + "maltider.txt"};
        String resultFilePath = path + "resultat.txt";

        String resultList = formatterController.result(pathToStartFile, pathsFinishFiles, pathToNameFile, FormatterController.LAP_RACE, "3", 3);
        assertEquals(readFileToString(resultFilePath), resultList);
    }

    @Test
    public void acceptanceTest9TimeRace() throws FileNotFoundException {
        FormatterController formatterController = new FormatterController();

        String path = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration2/acceptanstest9/");
        String pathToNameFile = path + "namnfil.txt";
        String pathToStartFile = path + "starttider.txt";
        String[] pathsFinishFiles = {path + "maltider.txt"};
        String resultFilePath = path + "resultatTimeRace.txt";

        String resultList = formatterController.result(pathToStartFile, pathsFinishFiles, pathToNameFile, FormatterController.TIME_RACE, "01.00.00", 3);
        assertEquals(readFileToString(resultFilePath), resultList);
    }

    @Test
    public void acceptanceTest10() throws FileNotFoundException {
        FormatterController formatterController = new FormatterController();

        String path = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration2/acceptanstest10/");
        String pathToNameFile = path + "namnfil.txt";
        String pathToStartFile = path + "starttider.txt";
        String pathToFinishFile1 = path + "maltider1.txt";
        String pathToFinishFile2 = path + "maltider2.txt";
        String[] finishFileArray = {pathToFinishFile1, pathToFinishFile2};
        String resultFilePath = path + "resultat.txt";

        String result = formatterController.result(pathToStartFile, finishFileArray, pathToNameFile, FormatterController.LAP_RACE, "3", 3);
        assertEquals(readFileToString(resultFilePath), result);
    }

    @Test
    public void acceptanceTest13() throws FileNotFoundException {
        FormatterController formatterController = new FormatterController();

        String path = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration2/acceptanstest13/");
        String pathToNameFile = path + "namnfil.txt";
        String pathToStartFile = path + "starttider.txt";
        String pathToFinishFile1 = path + "maltider1.txt";
        String pathToFinishFile2 = path + "maltider2.txt";
        String[] finishFileArray = {pathToFinishFile1, pathToFinishFile2};
        String resultFilePath = path + "resultat.txt";

        String result = formatterController.result(pathToStartFile, finishFileArray, pathToNameFile, FormatterController.LAP_RACE, "3", 2);
        assertEquals(readFileToString(resultFilePath), result);
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
