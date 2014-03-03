package race;

import models.Time;
import sun.print.resources.serviceui_pt_BR;

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
	public void addStartTime(Time start) {
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
	public void addFinishTime(Time finish) {
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
		StringBuilder sb = new StringBuilder();
		sb.append("; ").append(getTotal());
		sb.append("; ");
		if (start.isEmpty())
			sb.append("Start?");
		else
			sb.append(start.toString());
		sb.append("; ");
		if (finish.isEmpty()) {
			sb.append("Slut?");
		} else {
			sb.append(finish.toString());
		}
		return sb.toString();
	}

	/**
	 * @return a new SimpleRace with the same time limit and lapLimitTime
	 */
	@Override
	public Race copy() {
		Race tmprace = new SimpleRace();
		tmprace.changeLapTimeLimit(lapLimitTime.toString());
		return tmprace;
	}

	@Override
	public int compareTo(Race race) {
		if (race.getClass().equals(this.getClass())) {
			return getTotal().compareTo(race.getTotal());
		}
		throw new IllegalArgumentException("Jämförelse med annan objekttyp.");
	}
}
