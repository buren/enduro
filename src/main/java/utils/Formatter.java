package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Formatter {

	
	/**
	 * Actually returns the first line of the file.
	 * 
	 * @param filePath - Path to file
	 * @return A string with columns in the format "columnOne; columnTwo; columnThree; columnFour" should be returned.
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
			sb.append("StartNo; TotalTime; StartTime; ResultTime\n");
			int count = startTimes.size();

			for (int i = 0; i < count; i++) {
				sb.append(i + 1 + "; --.--.--; " + startTimes.get(i) + "; "
						+ finishTimes.get(i) + "\n");
			}
			return sb.toString();
		}
	}

	public String generateResultList(String pathToStartFile,
			String pathToFinishFile) throws FileNotFoundException {
		ArrayList<String> startList = new ArrayList<String>();
		ArrayList<String> endList = new ArrayList<String>();
		FileReader f = new FileReader();
		// OBS HÅRDKODAT
		String s = Enduro.getInstance().getResourcePath(
				"acceptanstester/iteration1/acceptanstest3");
		Iterator<String> starts = f.readFileByLine(s + "/" + pathToStartFile);
		Iterator<String> finishes = f
				.readFileByLine(s + "/" + pathToFinishFile);
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
		return generateResultList(startList, endList);
	}
}
