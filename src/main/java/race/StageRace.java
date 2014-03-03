package race;

import models.Time;

public class StageRace extends Race {
	public StageRace() {
		super();
	}

	@Override
	public void addStartTime(Time startTime) {
		boolean addedStart = false;
		for (int i = 0; i < laps.size() && !addedStart; i++) {
			if (laps.get(i).getStart().isEmpty()) {
				laps.get(i).setStart(startTime);
				addedStart = true;
			}
		}
		if (!addedStart) {
			Lap lap = new Lap();
			lap.setStart(startTime);
			laps.add(lap);
		}
	}

	/**
	 * Add a new finishtime
	 * 
	 * @param finishTime
	 *            time to add.
	 */
	public void addFinishTime(Time finishTime) {
		boolean addedFinish = false;
		for (int i = 0; i < laps.size() && !addedFinish; i++) {
			if (laps.get(i).getFinish().isEmpty()) {
				laps.get(i).setFinish(finishTime);
				addedFinish = true;
			}
		}
		if (!addedFinish) {
			Lap lap = new Lap();
			lap.setFinish(finishTime);
			laps.add(lap);
		}
	}

	/**
	 * Return the total time this race took.
	 * 
	 * @return time elapsed during whole race.
	 */
	protected Time getTotal() {
		Time totalTime = new Time();
		for (Lap lap : laps) {
			totalTime = totalTime.addTo(lap.getTotalTime());
		}
		return totalTime;
	}

	/**
	 * Print a formatted result for this race.
	 * 
	 * @param printLimit
	 *            max number of Stages to print.
	 * @return a formatted string for this race.
	 */
	@Override
	public String print(int printLimit) {
		StringBuilder sb = new StringBuilder();
		sb.append("; ").append(getTotal());
		sb.append("; ").append(getLaps());
		for (int i = 0; i < printLimit; i++) {
			sb.append("; ").append(getLapTimeElapsed(i));
		}
		for (int i = 0; i < printLimit; i++) {
			if (i < laps.size()) {
				sb.append("; ").append(laps.get(i).getStart());
				sb.append("; ").append(laps.get(i).getFinish());
			} else {
				sb.append("; --.--.--; --.--.--");
			}
		}
		return sb.toString();
	}

	/**
	 * Prints a header for the result
	 * 
	 * @param printLimit
	 *            max number of stages to print.
	 * @return a formatted string.
	 */
	public String printHeader(int printLimit) {
		StringBuilder sb = new StringBuilder();
		sb.append("; Totaltid; #Etapper");
		for (int i = 0; i < printLimit; i++) {
			sb.append("; Etapp").append(i + 1);
		}
		for (int i = 0; i < printLimit; i++) {
			sb.append("; Start").append(i + 1);
			sb.append("; Mal").append(i + 1);
		}
		sb.append("\n");
		return sb.toString();
	}

	/**
	 * Test if we are at limit yet.
	 * 
	 * @return true if we can add more laps.
	 */
	@Override
	protected boolean testLimit() {
		return true;
	}

	/**
	 * @return a new Race with the same time limit
	 */
	@Override
	public Race copy() {
		Race tmprace = new StageRace();
		tmprace.changeLapTimeLimit(lapLimitTime.toString());
		return tmprace;
	}

	
	@Override
	public int compareTo(Race race) {
		return 0;
	}

}
