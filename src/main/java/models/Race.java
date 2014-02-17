package models;

import java.util.ArrayList;

public class Race {
	private ArrayList<Lap> laps;
	private int size;
	private int lapCap;
	private Time timeCap;

	/**
	 * 
	 * @param lapCap - lap cap for race.
	 *
	 */
	public Race(int lapCap) {
		laps = new ArrayList<>();
		laps.add(new Lap());
		this.lapCap = lapCap;
	}

	/**
	 * 
	 * @param timeCap - time cap for race.
	 */
	public Race(String timeCap) {
		this.timeCap = new Time(timeCap);
		laps = new ArrayList<>();
		laps.add(new Lap());
	}

	/**
	 * 
	 * @param startTime - set start time for the race.
	 */
	public void setStart(Time startTime) {
		laps.get(0).setStart(startTime);
	}

	/**
	 * 
	 * @return - start time for the race.
	 */
	public Time getStart() {
		return laps.get(0).getStart();
	}

	/**
	 * Sets a finishtime for the race if missing.
	 * 
	 * @param finishTime
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
	 * @return
	 */
	public Time getFinish() {
		if (laps.get(size).getFinish().isEmpty())
			return laps.get(size).getStart();
		return laps.get(size).getFinish();
	}

	/**
	 * Returns the total time elapsed.
	 * 
	 * @return
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
	 * @param lap
	 *            , lap 1 is the first lap.
	 * @return
	 */
	public Time getLapTime(int lap) {
		if (lap > laps.size()) {
			return new Time();
		}
		return laps.get(lap - 1).getTotalTime();
	}

	/**
	 * Returns number of laps finished
	 * 
	 * @return number of laps finished
	 */
	public int getFinishedLaps() {
		return size;
	}
	
	/**
	 * 
	 * @return current lap
	 */
	public int getCurrentLap() {
		return getFinishedLaps() + 1;
	}

	/**
	 * 
	 * @return lap cap for race
	 */
	public int getLapsCap() {
		return lapCap;
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
	 * If lap based race it doesn't add new laps past the lapcap.
	 * If time based race it doesn't add new laps past the timecap.
	 * @param lapTime
	 */
	public void setLapTime(Time lapTime) {
		setFinish(lapTime);
		if (lapCap != 0) {
			if (size + 1 < lapCap) {
				Lap newLap = new Lap();
				newLap.setStart(lapTime);
				laps.add(newLap);
				size++;
			}
		} else {
			if (timeCap.addTimes(getStart()).compareValue(lapTime) > 0) {
				Lap newLap = new Lap();
				newLap.setStart(lapTime);
				laps.add(newLap);
				size++;
			}

		}
	}
}
