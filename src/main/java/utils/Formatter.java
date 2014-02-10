package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import sorter.SortFinishTime;
import sorter.SortName;
import sorter.SortStartTime;
import sorter.Sorter;
import models.Participant;
import models.RaceEvent;
import models.Time;

public class Formatter {
	private RaceEvent raceEvent;

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
			return "Listorna är tomma!";
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

		raceEvent = new RaceEvent();

		Sorter sort = new SortName();
		sort.insertInfo(pathToNameFile, "Namn", raceEvent);

		sort = new SortFinishTime();
		Participant p = new Participant(1);
		sort.insertInfo(pathToFinishFile, "Maltider", raceEvent);
		sort = new SortStartTime();
		p = new Participant(1);
		sort.insertInfo(pathToStartFile, "StartTider", raceEvent);

		StringBuilder sb = new StringBuilder();
		sb.append("StartNo; Name; TotalTime; StartTime; ResultTime\n");
		int count = raceEvent.size();
		for (int i = 0; i < count; i++) {
			Time totalTime = raceEvent.getStart(new Participant(i + 1)).compareTo(
					raceEvent.getFinish(new Participant(i + 1)));
			sb.append(i + 1 + "; " + raceEvent.getName(new Participant(i + 1))
					+ "; " + totalTime + "; "
					+ raceEvent.getStart(new Participant(i + 1)) + "; "
					+ raceEvent.getFinish(new Participant(i + 1)) + "\n");

		}
		return sb.toString();
	}
}
