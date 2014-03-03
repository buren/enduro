package race;

public class LapRace extends Race {
	private int limit;

	/**
	 * Create a new LapRace
	 * 
	 * @param limit
	 *            how many laps to be run.
	 */
	public LapRace(int limit) {
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
		return super.getLaps() < limit;
	}

	/**
	 * @return a new lapRace with the same lap limit and lapLimitTime
	 */
	@Override
	public Race copy() {
		Race tmprace = new LapRace(limit);
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
