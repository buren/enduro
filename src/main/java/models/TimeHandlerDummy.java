package models;

import java.util.ArrayList;

public class TimeHandlerDummy {
	ArrayList<String> startTimes = new ArrayList<String>();
	ArrayList<String> finishTimes = new ArrayList<String>();

	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("Start Time; Finish Time\n");
		if (startTimes.size() == 0) {
			if (finishTimes.size() == 0) {
				return "Empty time list.";
			}
			for (String s : finishTimes) {
				sb.append("--.--.--; " + s + "\n");
			}
			return sb.toString();
		} else if (finishTimes.size() == 0) {
			for (String s : startTimes) {
				sb.append(s + "; --.--.--\n");
			}
			return sb.toString();
		} else {
			for (int i = 0; i < startTimes.size(); i++) {
				sb.append(startTimes.get(i) + "; " + finishTimes.get(i) + "\n");
			}
			return sb.toString();
		}
	}
}