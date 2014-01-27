package utils;

import java.util.ArrayList;

public class Printer {

	public Printer() {

	}

	public String generateResultList(ArrayList<String> startTimes,
			ArrayList<String> finishTimes) {
		StringBuilder sb = new StringBuilder();
		sb.append("StartNo; TotalTime; StartTime; ResultTime\n");
		int count = startTimes.size();
		
		for (int i = 0; i < count; i++) {
			sb.append(i+1 + "; --.--.--; " + startTimes.get(i) + "; " + finishTimes.get(i) + "\n");
		}
		
		
	
		
		
		
		return sb.toString();
	}

}
