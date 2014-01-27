package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Times {

	private String startTime;
	private String finishTime;

	public Times() {
		startTime = "--.--.--";
		finishTime = "--.--.--";
	}

	public void setStart(String s) {
		startTime = s;
	}

	public void setFinish(String s) {
		finishTime = s;
	}

	public String getTotalTime() {
		if (startTime.equals("--.--,--") || finishTime.equals("--.--.--")) {

			return "Start or Finish time missing.";
		}

		return totalTime();
	}

	public String getStart() {
		return startTime;
	}

	public String getFinish() {
		return finishTime;
	}

	private String totalTime() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date startDate, finishDate, totalDate;
		totalDate = new Date();
		try {
			startDate = df.parse(startTime);
			finishDate = df.parse(finishTime);
			if(!(finishDate.getTime()-startDate.getTime()<0)){
			totalDate.setTime(finishDate.getTime()-startDate.getTime());
			String time = df.format(totalDate);
			return time;
			}
			else{
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "--.--.--";
	}

}
