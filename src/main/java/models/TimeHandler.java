package models;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeHandler {
	private HashMap<String, Times> timeHandler;

	public TimeHandler() {
		timeHandler = new HashMap<String, Times>();

	}

	public void addStart(Participant participant, String startTime) {
		timeHandler.get(participant.getId()).setStart(startTime);
	}

	public String getStart(Participant participant) {
		return timeHandler.get(participant.getId()).getStart();
	}

	public void addFinish(Participant participant, String finishTime) {
		timeHandler.get(participant.getId()).setFinish(finishTime);
	}

	public String getFinish(Participant participant) {
		return timeHandler.get(participant.getId()).getFinish();
	}

	// public String print() {
	// StringBuilder sb = new StringBuilder();
	// sb.append("Start Time; Finish Time\n");
	// if (startTime.size() == 0) {
	// if (finishTime.size() == 0) {
	// return "Empty time list.";
	// }
	// for (String s : finishTime) {
	// sb.append("--.--.--; " + s + "\n");
	// }
	// return sb.toString();
	// } else if (finishTime.size() == 0) {
	// for (String s : startTime) {
	// sb.append(s + "; --.--.--\n");
	// }
	// return sb.toString();
	// } else {
	// for (int i = 0; i < startTime.size(); i++) {
	// sb.append(startTime.get(i) + "; " + finishTime.get(i) + "\n");
	// }
	// return sb.toString();
	// }
	// }
}
