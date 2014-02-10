package models;

public class Lap {

	private Time startTime;
	private Time finishTime;

	public Lap() {
		startTime = new Time();
		finishTime = new Time();
	}

	public void setStart(Time startTime) {
		this.startTime = startTime;
	}

	public void setFinish(Time finishTime) {
		this.finishTime = finishTime;
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
