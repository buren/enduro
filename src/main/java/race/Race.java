package race;

import models.Time;

import java.util.ArrayList;

public abstract class Race {
	protected ArrayList<Lap> laps = new ArrayList<Lap>();
	protected ArrayList<Time> multipleStart = new ArrayList<Time>();
	protected ArrayList<Time> multipleFinish = new ArrayList<Time>();
	protected Time lapLimitTime;

	/**
	 * Create a new Race.
	 */
	public Race() {
		laps.add(new Lap());
		lapLimitTime = new Time("00.15.00");
	}

	/**
	 * Test if we are at limit yet.
	 * 
	 * @return true if we can add more laps.
	 */
	protected abstract boolean testLimit();

	public boolean isBetter(Race compare) {
		if (getCompletedLaps() == compare.getCompletedLaps())
			return getTotal().isBefore(compare.getTotal());
		return getCompletedLaps() > compare.getCompletedLaps();
	}

    /**
     *
     * @param race
     * @return Positive if better, negative if worse, 0 if equal.
     */
    public int compareTo(Race race) {
        if (race.getClass().equals(this.getClass())) {
            if (getCompletedLaps() < race.getCompletedLaps()){
                return -1;
            }
            else if (getCompletedLaps() > race.getCompletedLaps()){
                return 1;
            }
            else if (getCompletedLaps() == race.getCompletedLaps()) {
                return getTotal().compareTo(race.getTotal());
            }
        }
        throw new IllegalArgumentException("Jämförelse med annan objekttyp.");
    }

	/**
	 * @return Time the race was started.
	 */
	private Time getStart() {
		return laps.get(0).getStart();
	}

	/**
	 * Set the starttime.
	 *
	 * @param time
	 *            Time to set as start.
	 */
	public void addStartTime(Time time) {
		if (laps.get(0).getStart().isEmpty())
			laps.get(0).setStart(time);
		else
			multipleStart.add(time);
	}

	/**
	 * @return return the Time a finishtime was last registered.
	 */
	private Time getFinish() {
		if (isLastTimeEmpty())
			return laps.get(laps.size() - 1).getStart();
		return laps.get(laps.size() - 1).getFinish();
	}

	/**
	 * Add a new finishtime
	 *
	 * @param time
	 *            time to add.
	 */
	public void addFinishTime(Time time) {
		if (isLastTimeEmpty())
			laps.get(laps.size() - 1).setFinish(time);
		if (testLimit()) {
			Lap lap = new Lap();
			lap.setStart(time);
			laps.add(lap);
		} else {
			multipleFinish.add(time);
		}
	}

	/**
	 * Tests if the last finishTime in the race is empty
	 *
	 * @return True if it is, else false.
	 */
	private boolean isLastTimeEmpty() {
		return laps.get(laps.size() - 1).getFinish().isEmpty();
	}

	/**
	 * @return return the number of started laps.
	 */
	protected int getLaps() {
		return laps.size();
	}

    /**
     * @return return the number of completed laps.
     */
    public int getCompletedLaps() {
        if (isLastTimeEmpty())
            return laps.size() - 1;
        return laps.size();
    }

	/**
	 * Return the total time this race took.
	 *
	 * @return time elapsed during whole race.
	 */
	protected Time getTotal() {
		return getStart().getDifference(getFinish());
	}

	/**
	 * Changes the lapTimeLimit to newTimeLimit
	 */
	public void changeLapTimeLimit(String newTimeLimit) {
		lapLimitTime = new Time(newTimeLimit);
	}

	/**
	 * @return a new Race with the same time limit
	 */
	public abstract Race copy();


	/**
	 * Return time spent running a lap.
	 *
	 * @param lap
	 *            specified lap
	 * @return time spent running a lap.
	 */
	protected Time getLapTimeElapsed(int lap) {
		if (laps.size() <= lap)
			return new Time();
		return laps.get(lap).getTotalTime();
	}

	/**
	 * Return when the lap was finished.
	 *
	 * @param lap
	 *            specified lap
	 * @return when lap was finished.
	 */
	private Time getLapTime(int lap) {
		if (laps.size() <= lap)
			return new Time();
		return laps.get(lap).getFinish();
	}

	/**
	 * Creates a string with information in the extra column
	 *
	 * @param printLimit
	 *            Number of laps that we print
	 * @return returns a string with the error information
	 */
	private String printErrors(int printLimit) {
		boolean lapTime = false;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < printLimit; i++) {
			if (getLapTimeElapsed(i).isBefore(lapLimitTime)
					&& !getLapTime(i).isEmpty()) {
				lapTime = true;
			}
		}
		if (lapTime)
			sb.append("; Omöjlig varvtid?");
		if (multipleStart.size() > 0) {
			sb.append("; Flera starttider?");
			for (int i = 0; i < multipleStart.size(); i++) {
				sb.append(" ").append(multipleStart.get(i).toString());
			}
		}
		if (multipleFinish.size() > 1) {
			sb.append("; Flera maltider?");
			for (int i = 1; i < multipleFinish.size(); i++) {
				sb.append(" ").append(multipleFinish.get(i).toString());
			}
		}
		return sb.toString();
	}

	/**
	 * Print a formatted result for this race.
	 *
	 * @param printLimit
	 *            max number of laps to print.
	 * @return a formatted string for this race.
	 */
	public String print(int printLimit) {
		StringBuilder sb = new StringBuilder();
		sb.append("; ").append(getCompletedLaps());
		sb.append("; ").append(getTotal());
		for (int i = 0; i < printLimit; i++) {
			sb.append("; ").append(getLapTimeElapsed(i));
		}
		if (getStart().isEmpty()) {
			sb.append("; ").append("Start?");
		} else
			sb.append("; ").append(getStart());
		for (int i = 0; i < printLimit - 1; i++) {
			Time lapTime = getLapTime(i);
			if (lapTime.equals(getFinish())) // Used to make sure finishTime is
												// not printed twice.
				sb.append("; ").append(new Time());
			else
				sb.append("; ").append(lapTime);
		}
		if (getFinish().isEmpty() || getFinish().equals(getStart()))
			sb.append("; ").append("Slut?");
		else
			sb.append("; ").append(getFinish());
		sb.append(printErrors(printLimit));
		return sb.toString();
	}

	public String printSorted() {
		StringBuilder sb = new StringBuilder();
		sb.append("; ").append(getCompletedLaps());
		sb.append("; ").append(getTotal());
		for (int i = 0; i < getCompletedLaps(); i++) {
			sb.append("; ").append(getLapTimeElapsed(i));
		}
		return sb.toString();
	}

    /**
     * Prints a header for the result
     * @param printLimit max number of laps to print.
     * @return a formatted string.
     */
    public String printHeader(int printLimit) {
        StringBuilder sb = new StringBuilder();
        sb.append("; #Varv; TotalTid");
        for (int i = 0; i < printLimit; i++) {
            sb.append("; Varv").append(i + 1);
        }
        sb.append("; Start");
        for (int i = 0; i < printLimit - 1; i++) {
            sb.append("; Varvning").append(i + 1);
        }
        sb.append("; Mal\n");
        return sb.toString();
    }

    public boolean allLapsWithinLimit(){
    	for(Lap lap: laps){
    		if(lap.getTotalTime().isBefore(lapLimitTime)){
    		    return false;
    		}
    	}
    	return true;
    }

    /**
     * A Private class to easily abstract laps.
     */
    protected class Lap {

        private Time startTime;
        private Time finishTime;

        /**
         * Create a lap consisting of one start time and one finish time.
         */
        public Lap() {
            startTime = new Time();
            finishTime = new Time();
        }

        /**
         * Set the start time.
         *
         * @param startTime
         *            Time to set as start.
         */
        public void setStart(Time startTime) {
            this.startTime = startTime;
        }

        /**
         * Set the finish time.
         *
         * @param finishTime
         *            Time to set as finish.
         */
        public void setFinish(Time finishTime) {
            this.finishTime = finishTime;
        }

        /**
         * Return the duration of the race.
         *
         * @return the duration of the race, if incomplete, return a empty time.
         */
        public Time getTotalTime() {
            return startTime.getDifference(finishTime);
        }

        /**
         * Return the start time
         *
         * @return start time
         */
        public Time getStart() {
            return startTime;
        }

        /**
         * Return the finish time
         *
         * @return finish time
         */
        public Time getFinish() {
            return finishTime;
        }
    }

}
