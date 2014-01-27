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
}
