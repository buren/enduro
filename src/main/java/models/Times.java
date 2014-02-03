package models;

public class Times {

	private Time startTime;
	private Time finishTime;

	public Times() {
		startTime = new Time();
		finishTime = new Time();
	}

	public void setStart(String time) {
		startTime = new Time(time);
	}

	public void setFinish(String time) {
		finishTime = new Time(time);
	}

	/**
	 * Gets the duration of the race for the participant.
	 * 
	 * @return the duration of the race unless either the start time or the
	 *         finish time is missing.
	 */
	public Time getTotalTime() {
		return startTime.compareTo(finishTime);
	}

	public Time getStart() {
		return startTime;
	}

	public Time getFinish() {
		return finishTime;
	}
}
