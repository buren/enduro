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
import models.Time;

public class Formater {
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

	public String generateResultList(ArrayList<Time> startTimes,
			ArrayList<Time> finishTimes, ArrayList<String> nameList) {
		if (startTimes.isEmpty() && finishTimes.isEmpty()) {
			return "Both lists are empty!";
		} else {
			StringBuilder sb = new StringBuilder();

			sb.append("StartNo; Name; TotalTime; StartTime; ResultTime\n");

			int count = startTimes.size();
			for (int i = 0; i < count; i++) {
				Time totalTime = startTimes.get(i)
						.compareTo(finishTimes.get(i));
				sb.append(i + 1 + "; " + nameList.get(i) + "; " + totalTime
						+ "; " + startTimes.get(i) + "; " + finishTimes.get(i)
						+ "\n");

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
			String pathToFinishFile, String pathToNameFile)
			throws FileNotFoundException {

		time = new TimeHandler();

		Sorter sort = new SortName();
		sort.insertInfo(pathToNameFile, "Namn", time);

		sort = new SortFinishTime();
		Participant p = new Participant(1);
		sort.insertInfo(pathToFinishFile, "Maltider", time);
		sort = new SortStartTime();
		p = new Participant(1);
		sort.insertInfo(pathToStartFile, "StartTider", time);

		StringBuilder sb = new StringBuilder();
		sb.append("StartNo; Name; TotalTime; StartTime; ResultTime\n");
		int count = time.size();
		for (int i = 0; i < count; i++) {
			Time totalTime = time.getStart(new Participant(i + 1)).compareTo(
					time.getFinish(new Participant(i + 1)));
			sb.append(i + 1 + "; " + time.getName(new Participant(i + 1))
					+ "; " + totalTime + "; "
					+ time.getStart(new Participant(i + 1)) + "; "
					+ time.getFinish(new Participant(i + 1)) + "\n");

		}
		return sb.toString();
	}
}
