package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import models.Time;

public class Formatter {

	/**
	 * Actually returns the first line of the file.
	 * 
	 * @param filePath
	 *            - Path to file
	 * @return A string with columns in the format
	 *         "columnOne; columnTwo; columnThree; columnFour" should be
	 *         returned.
	 * @throws FileNotFoundException
	 */
	public String readColumnNames(String filePath) throws FileNotFoundException {
		FileReader f = new FileReader();

		Iterator<String> itr = f.readFileByLine(filePath);
		String columns;
		if (itr.hasNext()) {
			columns = itr.next();
		} else {
			throw new IllegalStateException("Input file is empty.");
		}
		return columns;
	}

	public String generateResultList(ArrayList<Time> startTimes,
			ArrayList<Time> finishTimes) {
		if (startTimes.isEmpty() && finishTimes.isEmpty()) {
			return "Both lists are empty!";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("StartNr; TotalTid; StartTider; Maltider\n");
			int count = startTimes.size();

			for (int i = 0; i < count; i++) {
				Time totalTime = startTimes.get(i).compareTo(finishTimes.get(i));
				sb.append(i + 1 + "; "+totalTime+"; " + startTimes.get(i) + "; "
						+ finishTimes.get(i) + "\n");
			}
			return sb.toString();
		}
	}

	/**
	 * @param pathToStartFile
	 * @param pathToFinishFile
	 * @return
	 * @throws FileNotFoundException
	 */
	public String generateResultList(String pathToStartFile,
			String pathToFinishFile) throws FileNotFoundException {
		ArrayList<Time> startList = new ArrayList<Time>();
		ArrayList<Time> endList = new ArrayList<Time>();
		FileReader f = new FileReader();

		Iterator<String> starts = f.readFileByLine(pathToStartFile);

		Iterator<String> finishes = f.readFileByLine(pathToFinishFile);

		while (starts.hasNext()) {
			String temp = starts.next();
			int index = temp.indexOf(" ");
			startList.add(new Time(temp.substring(index + 1)));
		}
		while (finishes.hasNext()) {
			String temp = finishes.next();
			int index = temp.indexOf(" ");
			endList.add(new Time(temp.substring(index + 1)));
		}
		return generateResultList(startList, endList);
	}
}
