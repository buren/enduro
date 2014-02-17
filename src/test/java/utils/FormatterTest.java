package utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

public class FormatterTest {
	private Formatter timeFormatter;
	private Enduro enduro;
	private Formatter lapFormatter;


	@Before
	public void setUp() throws Exception {
		lapFormatter = new Formatter(15);
		timeFormatter = new Formatter("20.20.15");

		enduro = Enduro.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		lapFormatter = null;
		timeFormatter = null;
		enduro = null;
	}


	@Test
	public void testWrongFilePath() {
		boolean success = false;
		try {
			timeFormatter.generateResultList("lololzosdldsl",
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

			assertEquals(timeFormatter.readColumnNames(path + "namnfil.txt"),
					"StartNo; Namn");

			assertEquals(timeFormatter.readColumnNames(path + "resultat.txt"),
					"StartNo; Name; TotalTime; StartTime; ResultTime");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadColumnNamesFileNotFoundException() {
		boolean success = false;
		try {
			timeFormatter
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
			timeFormatter.readColumnNames(enduro
					.getResourcePath("/utils/testEmpty.csv"));
		} catch (IllegalStateException e) {
			success = true;
		}
		assertEquals("Should raise illegalStateException", true, success);
	}

}
