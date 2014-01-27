package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class Printer {

	public Printer() {

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
			String pathToFinishFile) {
		ArrayList<String> startList = new ArrayList<String>();
		ArrayList<String> endList = new ArrayList<String>();

		FileReader f = new FileReader();
		String s = Enduro.getInstance().getResourcePath(
				"acceptanstester/iteration1/acceptanstest3");
		try {
			Iterator<String> starts = f.readFileByLine(s + "/"
					+ pathToStartFile);
			Iterator<String> finishes = f.readFileByLine(s + "/"
					+ pathToFinishFile);

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

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		return generateResultList(startList, endList);
	}
}
