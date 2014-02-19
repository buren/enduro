package acceptanceTests;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.Iterator;

import controllers.FormatterController;
import org.junit.After;
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

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void acceptanceTest9() throws FileNotFoundException {
        FormatterController formatterController = new FormatterController();

		String path = Enduro.getInstance().getResourcePath(
				"acceptanstester/iteration2/acceptanstest9/");
        String pathToNameFile = path + "namnfil.txt";
        String pathToStartFile = path + "starttider.txt";
        String[] pathsFinishFiles = {path + "maltider.txt"};
        String resultFilePath = path + "resultat.txt";

		String resultList = formatterController.result(pathToStartFile, pathsFinishFiles, pathToNameFile, formatterController.LAP_RACE, "3", 3);
		assertEquals(readFileToString(resultFilePath), resultList);
	}

	@Test
	public void acceptanceTest10() throws FileNotFoundException {
        FormatterController fc = new FormatterController();

		String path = Enduro.getInstance().getResourcePath(
				"acceptanstester/iteration2/acceptanstest10/");
        String pathToNameFile = path + "namnfil.txt";
        String pathToStartFile = path + "starttider.txt";
        String pathToFinishFile1 = path + "maltider1.txt";
        String pathToFinishFile2 = path + "maltider2.txt";
        String[] finishFileArray = { pathToFinishFile1, pathToFinishFile2 };
        String resultFilePath = path + "resultat.txt";

        String result = fc.result(pathToStartFile, finishFileArray, pathToNameFile, fc.LAP_RACE, "3", 3);
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
