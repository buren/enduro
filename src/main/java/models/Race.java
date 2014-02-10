package models;

import java.util.ArrayList;

public class Race {
	private ArrayList<Lap> laps;
	private int size;
	
	public Race() {
		laps = new ArrayList<>();
		laps.add(new Lap());
	}

	public void setStart(Time startTime) {
		laps.get(0).setStart(startTime);
		
	}

	public Time getStart() {
		return laps.get(0).getStart();
	}

	/**
	 * Sets a finishtime for the race.
	 * @param finishTime
	 */
	public void setFinish(Time finishTime) {
		laps.get(size).setFinish(finishTime);
	}

	/**
	 * Returns the finishtime, if no finishtime has been registered
	 * returns the last laptime.
	 * @return
	 */
	public Time getFinish() {
		if(laps.get(size).getFinish().isEmpty())
			return laps.get(size).getStart();
		return laps.get(size).getFinish();
	}

	/**
	 * Returns the total time elapsed.
	 * @return
	 */
	public Time getTotalTime() {
		Time start = laps.get(0).getStart();
		Time finish;
		if(laps.get(size).getFinish().isEmpty()) {
			finish = laps.get(size).getStart(); 
		} else {
			finish = laps.get(size).getFinish();
		}
		return start.compareTo(finish);
	}
	
	/**
	 * Returns the total time of a lap. IE. time spent running that lap
	 * @param lap, lap 1 is the first lap.
	 * @return
	 */
	public Time getLapTime(int lap) {
		return laps.get(lap-1).getTotalTime();
	}
	
	/**
	 * Returns number of laps Started
	 * @return
	 */
	public int getNumberOfLaps() {
		return size+1;
	}
	
	/**
	 * Returns the actual time the lap was started.
	 * @param lap, lap 1 is the first lap.
	 * @return
	 */
	public Time getLapStartTime(int lap) {
		return laps.get(lap-1).getStart();
	}
	
	/**
	 * When passing the finish/start line, adds a new finishtime to the current lap
	 * and starts a new lap with the same time.
	 * @param lapTime
	 */
	public void setLapTime(Time lapTime) {
		laps.get(size).setFinish(lapTime);
		Lap newLap = new Lap();
		newLap.setStart(lapTime);
		laps.add(newLap);
		size++;
	}
}