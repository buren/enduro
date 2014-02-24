package race;

import models.Time;

public class SimpleRace extends Race {

	private Time start;
	private Time finish;

	/**
	 * Create a new simpleRace, with a starttime and a finishtime.
	 */
	public SimpleRace() {
		start = new Time();
		finish = new Time();
	}

	/**
	 * Set the starttime.
	 * 
	 * @param start
	 *            Time to set as start.
	 */
	@Override
	public void setStart(Time start) {
		this.start = start;
	}

	/**
	 * Return the total time this race took.
	 * 
	 * @return time elapsed during whole race.
	 */
	@Override
	public Time getTotal() {
		return start.getDifference(finish);
	}

	/**
	 * Add a new finishtime
	 * 
	 * @param finish
	 *            time to add.
	 * @return true if a Time was successfully added, else false.
	 */
	@Override
	public void addTime(Time finish) {
		this.finish = finish;
	}

	/**
	 * Test if we are at limit yet.
	 * 
	 * @return always false, no laps.
	 */
	@Override
	protected boolean testLimit() {
		return false;
	}

	/**
	 * Print a formatted result for this race.
	 * 
	 * @param printLimit
	 *            max number of laps to print.
	 * @return a formatted result as string.
	 */
	@Override
	public String print(int printLimit) {
		String startString;
		String finishString;
		if (start.isEmpty()) {
			startString = "Start?";
		} else {
			startString = start.toString();
		}
		if (finish.isEmpty()) {
			finishString = "Slut?";
		} else {
			finishString = finish.toString();
		}
		return getTotal() + "; " + startString + "; " + finishString;
	}

	/**
	 * @return a new SimpleRace with the same time limit
	 */
	@Override
	public Race copy() {
		return new SimpleRace();
	}

	@Override
	public int compareTo(Race race) {
		if (race.getClass().equals(this.getClass())) {
			return getTotal().compareTo(race.getTotal());
		}
		throw new IllegalArgumentException("Jämförelse med annan objekttyp.");
	}
}
