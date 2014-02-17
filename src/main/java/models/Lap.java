package models;

public class Lap {

	private Time startTime;
	private Time finishTime;

	/**
	 * A lap consisting of one start time and one finish time.
	 */
	public Lap() {
		startTime = new Time();
		finishTime = new Time();
	}

	/**
	 * Sets the start time to parameter.
	 * 
	 * @param startTime
	 */
	public void setStart(Time startTime) {
		this.startTime = startTime;
	}

	/**
	 * Sets the finish time to parameter.
	 * 
	 * @param finishTime
	 */
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

	/**
	 * Returns the start time
	 * @return start time
	 */
	public Time getStart() {
		return startTime;
	}

	/**
     * Returns the finish time
	 * @return finish time
	 */
	public Time getFinish() {
		return finishTime;
	}
}
