package models;

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

	public String totalTime() {
		return "Totaltiden";
	}

	public String getStart() {
		return startTime;
	}

	public String getFinish() {
		return finishTime;
	}

}
