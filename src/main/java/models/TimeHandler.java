package models;

import java.util.ArrayList;

public class TimeHandler {
	private ArrayList<String> startTime;
	private ArrayList<String> finishTime;

	public TimeHandler() {
		startTime = new ArrayList<String>();
		finishTime = new ArrayList<String>();
	}

	public void addStart(String startTime) {
		this.startTime.add(startTime);
	}

	public String getStart(int i) {
		return startTime.get(i);
	}

	public void addFinish(String finishTime) {
		this.finishTime.add(finishTime);
	}

	public String getFinish(int i) {
		return finishTime.get(i);
	}

	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("Start Time; Finish Time\n");
		if (startTime.size() == 0) {
			if (finishTime.size() == 0) {
				return "Empty time list.";
			}
			for (String s : finishTime) {
				sb.append("--.--.--; " + s + "\n");
			}
			return sb.toString();
		} else if (finishTime.size() == 0) {
			for (String s : startTime) {
				sb.append(s + "; --.--.--\n");
			}
			return sb.toString();
		} else {
			for (int i = 0; i < startTime.size(); i++) {
				sb.append(startTime.get(i) + "; " + finishTime.get(i) + "\n");
			}
			return sb.toString();
		}
	}
}
