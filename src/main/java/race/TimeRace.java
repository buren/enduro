package race;

import models.Time;

public class TimeRace extends Race {
	private Time limit;

	/**
	 * Create a new TimeRace
	 * 
	 * @param limit
	 *            the time limit for the race.
	 */
	public TimeRace(Time limit) {
		super();
		this.limit = limit;
	}

	/**
	 * Test if we are at limit yet.
	 * 
	 * @return true if we can add more laps.
	 */
	@Override
	protected boolean testLimit() {
		return getTotal().isBefore(limit);
	}

	/**
	 * @return a new TimeRace with the same time limit and lapLimitTime
	 */
	@Override
	public Race copy() {
		Race tmpRace = new TimeRace(limit);
		tmpRace.changeLapTimeLimit(lapLimitTime.toString());
		return tmpRace;
	}

	@Override
	public int compareTo(Race race) {
		if (race.getClass().equals(this.getClass())) {
			return getCompletedLaps() - race.getCompletedLaps();
		}
		throw new IllegalArgumentException("Jämförelse med annan objekttyp.");
	}
}
