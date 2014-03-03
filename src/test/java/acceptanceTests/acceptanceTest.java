package acceptanceTests;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.Iterator;

import controllers.FormatterController;

import org.junit.Before;
import org.junit.Test;

import utils.Enduro;
import utils.FileReader;
import utils.Sorter;

public class acceptanceTest {
	private Enduro enduro;
	private String basePath;
	private String pathToNameFile;
	private String[] pathsToStartFiles;
	private String[] pathsToFinishFiles;
	private String pathToExpectedResult;
	private String standardLapLimit;

	@Before
	public void setUp() throws Exception {
		enduro = Enduro.getInstance();
		standardLapLimit = "00.15.00";
	}

	private void makePaths() {
		pathToNameFile = basePath + "namnfil.txt";
		pathsToStartFiles = new String[1];
		pathsToStartFiles[0] = basePath + "starttider.txt";
		pathToExpectedResult = basePath + "resultat.txt";
		pathsToFinishFiles = new String[1];
		pathsToFinishFiles[0] = basePath + "maltider.txt";
	}

	@Test
	public void acceptanceTest5() throws FileNotFoundException {
		FormatterController fc = new FormatterController();

		basePath = enduro.getInstance().getResourcePath(
				"acceptanstester/iteration1/acceptanstest5/");
		makePaths();
		String resultList = fc.result(pathsToStartFiles, pathsToFinishFiles, pathToNameFile, FormatterController.SIMPLE_RACE, "1", 1, standardLapLimit, FormatterController.DONT_SORT);
		assertEquals(readFileToString(pathToExpectedResult), resultList);
	}

	@Test
	public void acceptanceTest6() throws FileNotFoundException {
		FormatterController fc = new FormatterController();

		basePath = enduro.getInstance().getResourcePath(
				"acceptanstester/iteration1/acceptanstest6/");
		makePaths();

		String resultList = fc.result(pathsToStartFiles, pathsToFinishFiles, pathToNameFile, FormatterController.LAP_RACE, "1", 1, standardLapLimit, FormatterController.DONT_SORT);
		assertEquals(readFileToString(pathToExpectedResult), resultList);
	}

	@Test
	public void acceptanceTest9() throws FileNotFoundException {
		FormatterController fc = new FormatterController();

		basePath = enduro.getInstance().getResourcePath(
				"acceptanstester/iteration2/acceptanstest9/");
		makePaths();

		String resultList = fc.result(pathsToStartFiles, pathsToFinishFiles,pathToNameFile, FormatterController.LAP_RACE, "3", 3, standardLapLimit, FormatterController.DONT_SORT);

		assertEquals(readFileToString(pathToExpectedResult), resultList);
	}

	@Test
	public void acceptanceTest9TimeRace() throws FileNotFoundException {
		FormatterController fc = new FormatterController();

		basePath = enduro.getInstance().getResourcePath(
				"acceptanstester/iteration2/acceptanstest9/");
		makePaths();
		pathToExpectedResult = basePath + "resultatTimeRace.txt";

		String resultList = fc.result(pathsToStartFiles, pathsToFinishFiles, pathToNameFile, FormatterController.TIME_RACE, "01.00.00", 3, standardLapLimit, FormatterController.DONT_SORT);

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
		String[] finishFileArray = { pathToFinishFile1, pathToFinishFile2 };

		String result = fc.result(pathsToStartFiles, finishFileArray, pathToNameFile, FormatterController.LAP_RACE, "3", 3, standardLapLimit, FormatterController.DONT_SORT);

		assertEquals(readFileToString(pathToExpectedResult), result);
	}

	@Test
	public void acceptanceTest11() throws FileNotFoundException {
		FormatterController fc = new FormatterController();

		basePath = enduro.getInstance().getResourcePath(
				"acceptanstester/iteration2/acceptanstest11/");
		makePaths();
		String pathToFinishFile1 = basePath + "maltider1.txt";
		String pathToFinishFile2 = basePath + "maltider2.txt";
		String[] finishFileArray = { pathToFinishFile1, pathToFinishFile2 };

		String result = fc.result(pathsToStartFiles, finishFileArray,
				pathToNameFile, FormatterController.LAP_RACE, "3", 3, standardLapLimit, FormatterController.DONT_SORT);
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
		String[] finishFileArray = { pathToFinishFile1, pathToFinishFile2 };

		String result = fc.result(pathsToStartFiles, finishFileArray, pathToNameFile, FormatterController.LAP_RACE, "3", 2, standardLapLimit, FormatterController.DONT_SORT);

		assertEquals(readFileToString(pathToExpectedResult), result);
	}

	@Test
	public void acceptanceTest15() throws FileNotFoundException {

		FormatterController fc = new FormatterController();

		basePath = enduro.getInstance().getResourcePath(
				"acceptanstester/iteration2/acceptanstest15/");
		makePaths();
		String resultList = fc.result(pathsToStartFiles, pathsToFinishFiles,
				pathToNameFile, FormatterController.LAP_RACE, "30", 3, standardLapLimit, FormatterController.DONT_SORT);

		assertEquals(readFileToString(pathToExpectedResult), resultList);
	}

	@Test
	public void acceptanceTest16() throws FileNotFoundException {
		FormatterController formatterController = new FormatterController();

		basePath = enduro.getInstance().getResourcePath(
				"acceptanstester/iteration2/acceptanstest16/");
		makePaths();

		String result = formatterController.result(pathsToStartFiles,
				pathsToFinishFiles, pathToNameFile,
				FormatterController.LAP_RACE, "3", 3, standardLapLimit, FormatterController.DONT_SORT);
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
		String[] finishFileArray = { pathToFinishFile1, pathToFinishFile2 };

		String result = formatterController.result(pathsToStartFiles,
				finishFileArray, pathToNameFile, FormatterController.LAP_RACE, "3", 3, standardLapLimit, FormatterController.DONT_SORT);

		assertEquals(readFileToString(pathToExpectedResult), result);
	}

	@Test
	public void acceptanceTest18Sort() throws FileNotFoundException {
		FormatterController formatterController = new FormatterController();

		String path = enduro.getInstance().getResourcePath(
				"acceptanstester/iteration2/acceptanstest18/");
		String pathToNameFile = path + "namnfil.txt";
		String[] pathToStartFile = { path + "starttider.txt" };
		String pathToFinishFile1 = path + "maltider1.txt";
		String pathToFinishFile2 = path + "maltider2.txt";
		String[] finishFileArray = { pathToFinishFile1, pathToFinishFile2 };
		String resultFilePath = path + "sortresultat.txt";

		String result = formatterController.result(pathToStartFile,
				finishFileArray, pathToNameFile, FormatterController.LAP_RACE,
				"3", 3, "00.20.00", FormatterController.SORT);
		assertEquals(readFileToString(resultFilePath), result);
	}

	@Test
	public void acceptanceTest18DontSort() throws FileNotFoundException {
		FormatterController formatterController = new FormatterController();

		String path = enduro.getInstance().getResourcePath(
				"acceptanstester/iteration2/acceptanstest18/");
		String pathToNameFile = path + "namnfil.txt";
		String[] pathToStartFile = { path + "starttider.txt" };
		String pathToFinishFile1 = path + "maltider1.txt";
		String pathToFinishFile2 = path + "maltider2.txt";
		String[] finishFileArray = { pathToFinishFile1, pathToFinishFile2 };
		String resultFilePath = path + "resultat.txt";

		String result = formatterController.result(pathToStartFile,
				finishFileArray, pathToNameFile, FormatterController.LAP_RACE,
				"3", 3, standardLapLimit, FormatterController.DONT_SORT);
		assertEquals(readFileToString(resultFilePath), result);
	}

	@Test
	public void acceptanceTest19() throws FileNotFoundException {
		FormatterController formatterController = new FormatterController();

		basePath = enduro.getInstance().getResourcePath(
				"acceptanstester/iteration3/acceptanstest19b/");
		makePaths();

		String pathToStartFile1 = basePath + "starttider1.txt";
		String pathToStartFile2 = basePath + "starttider2.txt";
		String[] startFileArray = { pathToStartFile1, pathToStartFile2 };

		String pathToFinishFile1 = basePath + "maltider1.txt";
		String pathToFinishFile2 = basePath + "maltider2.txt";
		String[] finishFileArray = { pathToFinishFile1, pathToFinishFile2 };

		String result = formatterController.result(startFileArray,
				finishFileArray, pathToNameFile,
				FormatterController.STAGE_RACE, "", 2, standardLapLimit, FormatterController.DONT_SORT);

		assertEquals(readFileToString(pathToExpectedResult), result);
	}

    @Test
    public void acceptanceTest24() throws FileNotFoundException {
        FormatterController formatterController = new FormatterController();

        basePath = enduro.getInstance().getResourcePath(
                "acceptanstester/iteration3/acceptanstest24/");
        makePaths();

        String pathToFinishFile1 = basePath + "maltider1.txt";
        String pathToFinishFile2 = basePath + "maltider2.txt";
        String[] finishFileArray = { pathToFinishFile1, pathToFinishFile2 };

        String result = formatterController.result(pathsToStartFiles,
                finishFileArray, pathToNameFile, FormatterController.LAP_RACE, "3", 3, standardLapLimit, FormatterController.DONT_SORT);

        assertEquals(readFileToString(pathToExpectedResult), result);
    }

	private static String readFileToString(String filePath)
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