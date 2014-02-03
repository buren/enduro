package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import sorter.SortFinishTime;
import sorter.SortName;
import sorter.SortStartTime;
import sorter.Sorter;
import models.Participant;
import models.TimeHandler;

public class Formatter {
	private TimeHandler time;
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

	public String generateResultList(ArrayList<String> startTimes,
			ArrayList<String> finishTimes) {
		if (startTimes.isEmpty() && finishTimes.isEmpty()) {
			return "Both lists are empty!";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("StartNo; Name; TotalTime; StartTime; ResultTime\n");
			int count = startTimes.size();
			for (int i = 0; i < count; i++) {
				sb.append(i + 1 + "; " + time.getName(new Participant(i+1)) + "; --.--.--; "
						+ time.getStart(new Participant(i+1)) + "; " + time.getFinish(new Participant(i+1)) + "\n");
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
		ArrayList<String> startList = new ArrayList<String>();
		ArrayList<String> endList = new ArrayList<String>();
		FileReader f = new FileReader();

		Iterator<String> starts = f.readFileByLine(pathToStartFile);

		Iterator<String> finishes = f.readFileByLine(pathToFinishFile);

		while (starts.hasNext()) {
			String temp = starts.next();
			int index = temp.indexOf(" ");
			startList.add(temp.substring(index + 1));
		}
		while (finishes.hasNext()) {
			String temp = finishes.next();
			int index = temp.indexOf(" ");
			endList.add(temp.substring(index + 1));
			
		}
		time = new TimeHandler();
		Sorter sort = new SortName();
		sort.insertInfo(pathToStartFile, "Name", time);
		sort = new SortFinishTime();
		sort.insertInfo(pathToStartFile, "Maltider", time);
		sort = new SortStartTime();
		sort.insertInfo(pathToStartFile, "StartTider", time);
		return generateResultList(startList, endList);
	}
}
