package controllers;

import java.io.FileNotFoundException;
import java.util.Iterator;

import models.RaceEvent;
import utils.FileWriter;
import utils.Formatter;

public class FormatterController {

	private Formatter formatter;

	/**
	 * 
	 * @param s , the number of laps wanted for this contest.
	 */
	public FormatterController(String s) {
		formatter = new Formatter(s);
	}
	
	public FormatterController(int time) {
		formatter = new Formatter(time);
	}

	/**
	 * Writes the Strings within the Iterator to the given filepath.
	 * 
	 * @param filePath
	 *            , URL-address of target folder.
	 * @param iter
	 *            , iterator of strings to be written.
	 */
	public void writeToFile(String filePath, Iterator<String> iter) {
		FileWriter.writeFile(filePath, iter);
	}

	/**
	 * Returns a string of the results table generated by the files at each
	 * location.
	 * 
	 * @param startPath
	 *            , URL-address of file containing start times.
	 * @param finishPath
	 *            , URL-address of file containing start times.
	 * @param namePath
	 *            , URL-address of file containing start times.
	 * @param nLaps
	 *            , Number of laps to be displayed in the result table.
	 * @return
	 * @throws FileNotFoundException
	 */
	public String result(String startPath, String[] finishPath, String namePath,
			int nLaps) throws FileNotFoundException {

		return formatter.generateResultList(startPath, finishPath, namePath,
				nLaps);

	}

}
