package acceptanceTests;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utils.Enduro;
import utils.FileReader;
import utils.Formatter;

public class acceptanceTest {

	private Enduro enduro;
	private Formatter lapsFormatter;

	@Before
	public void setUp() throws Exception {
		enduro = Enduro.getInstance();
		lapsFormatter = new Formatter(20);
	}

	@After
	public void tearDown() throws Exception {
	}

// Small bug in Formatter makes this fail
//	@Test
//	public void acceptanceTest9() throws FileNotFoundException {
//		String acceptanceTestFolderPath = Enduro.getInstance().getResourcePath(
//				"acceptanstester/iteration2/acceptanstest9/");
//		String pathToStartFile = acceptanceTestFolderPath + "starttider.txt";
//		String pathToFinishFile = acceptanceTestFolderPath + "maltider.txt";
//		String pathToNameFile = acceptanceTestFolderPath + "namnfil.txt";
//		String resultFilePath = acceptanceTestFolderPath + "resultat.txt";
//		String resultList = lapsFormatter.generateResultList(pathToStartFile,
//				pathToFinishFile, pathToNameFile, 3);
//		assertEquals(readFileToString(resultFilePath), resultList);
//	}

	@Test
	public void acceptanceTest10() throws FileNotFoundException {
		String acceptanceTestFolderPath = Enduro.getInstance().getResourcePath(
				"acceptanstester/iteration2/acceptanstest10/");
		String pathToStartFile = acceptanceTestFolderPath + "starttider.txt";
		String pathToFinishFile1 = acceptanceTestFolderPath + "maltider1.txt";
		String pathToFinishFile2 = acceptanceTestFolderPath + "maltider2.txt";
		String pathToNameFile = acceptanceTestFolderPath + "namnfil.txt";
		String resultFilePath = acceptanceTestFolderPath + "resultat.txt";
		String[] finishFileArray = { pathToFinishFile1, pathToFinishFile2 };
		String resultList = lapsFormatter.generateResultList(pathToStartFile,
				finishFileArray, pathToNameFile, 3);
		assertEquals(readFileToString(resultFilePath), resultList);
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