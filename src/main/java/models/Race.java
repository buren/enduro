package models;

import java.util.ArrayList;

public abstract class Race {
	protected ArrayList<Lap> laps;
	protected int size;

	/**
	 * Creates a new race
	 * @param lapCap , max number of laps allowed.
	 */
    public Race(Object o) {
	}


	/**
     * Sets startTime, if multiple laps then for the first lap.
     * @param startTime the new startTime
     */
	public void setStart(Time startTime) {
		laps.get(0).setStart(startTime);
	}

    /**
     * Gets the startTime, if multiple laps then for the first lap.
     * @return startTime the startTime
     */
	public Time getStart() {
		return laps.get(0).getStart();
	}

	/**
	 * Sets a finishtime for the race.
	 * @param finishTime the finishTime
	 */
	public void setFinish(Time finishTime) {
		if (laps.get(size).getFinish().isEmpty() == true ){
		laps.get(size).setFinish(finishTime);	
		}
	}

	/**
	 * Returns the finishtime, if no finishtime has been registered returns the
	 * last laptime.
	 * 
	 * @return finishTime
	 */
	public Time getFinish() {
		if (laps.get(size).getFinish().isEmpty())
			return laps.get(size).getStart();
		return laps.get(size).getFinish();
	}

	/**
	 * Returns the total time elapsed.
	 * 
	 * @return the total time elapsed
	 */
	public Time getTotalTime() {
		Time start = laps.get(0).getStart();
		Time finish;
		if (laps.get(size).getFinish().isEmpty()) {
			finish = laps.get(size).getStart();
		} else {
			finish = laps.get(size).getFinish();
		}
		return start.compareTo(finish);
	}

	/**
	 * Returns the total time of a lap. IE. time spent running that lap
	 * 
	 * @param lap , lap 1 is the first lap.
	 * @return the elapsed time for specified lap.
	 */
	public Time getLapTime(int lap) {
		if (lap > laps.size()) {
			return new Time();
		}
		return laps.get(lap - 1).getTotalTime();
	}

	/**
	 * Returns number of laps finished
	 * @return number of laps finished
	 */
	public int getFinishedLaps() {
		return size;
	}

    /**
     * Returns the current lap
     * @return the current lap
     */
	public int getCurrentLap() {
		return getFinishedLaps() + 1;
	}


	/**
	 * Returns the actual time the lap was started.
	 * 
	 * @param lap
	 *            , lap 1 is the first lap.
	 * @return
	 */
	public Time getLapStartTime(int lap) {
		if (lap > laps.size()) {
			return new Time();
		}
		return laps.get(lap - 1).getStart();
	}

	/**
	 * When passing the finish/start line, adds a new finishtime to the current
	 * lap and starts a new lap with the same time.
     *
	 * If lap based race it doesn't add new laps past the lapcap.
	 * If time based race it doesn't add new laps past the timecap.
	 * @param lapTime Time to add.
	 */
	public	abstract void setLapTime(Time lapTime); 
		

		
	
}
