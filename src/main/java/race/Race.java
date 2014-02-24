package race;


import models.Time;

import java.util.ArrayList;

public abstract class Race {
    protected ArrayList<Lap> laps = new ArrayList<>();
    protected ArrayList<Time> multipleStart = new ArrayList<>();

    /**
     * Create a new Race.
     */
    public Race() {
        laps.add(new Lap());
    }

    /**
     * Set the starttime.
     *
     * @param time Time to set as start.
     */
    public void setStart(Time time) {
    	if (laps.get(0).getStart().isEmpty()) 
    		laps.get(0).setStart(time);
    	else
    		multipleStart.add(time);
    	
    }

    /**
     * @return Time the race was started.
     */
    private Time getStart() {
        return laps.get(0).getStart();
    }

    /**
     * @return return the Time a finishtime was last registered.
     */
    private Time getFinish() {
        if (laps.get(laps.size() - 1).getFinish().isEmpty())
            return laps.get(laps.size() - 1).getStart();
        return laps.get(laps.size() - 1).getFinish();
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
    private int getCompletedLaps() {
        if (laps.get(laps.size() - 1).getFinish().isEmpty())
            return laps.size() - 1;
        return laps.size();
    }

    /**
     * Return time spent running a lap.
     *
     * @param lap specified lap
     * @return time spent running a lap.
     */
    private Time getLapTimeElapsed(int lap) {
        if (laps.size() <= lap)
            return new Time();
        return laps.get(lap).getTotalTime();
    }

    /**
     * Return when the lap was finished.
     *
     * @param lap specified lap
     * @return when lap was finished.
     */
    private Time getLapTime(int lap) {
        if (laps.size() <= lap)
            return new Time();
        return laps.get(lap).getFinish();
    }

    /**
     * Return the total time this race took.
     *
     * @return time elapsed during whole race.
     */
    protected Time getTotal() {
        return getStart().compareTo(getFinish());
    }

    /**
     * Add a new finishtime
     *
     * @param time time to add.
     * @return true if a Time was successfully added, else false.
     */
    public void addTime(Time time) {
        if (laps.get(laps.size() - 1).getFinish().isEmpty())
            laps.get(laps.size() - 1).setFinish(time);
        if (testLimit()) {
            Lap lap = new Lap();
            lap.setStart(time);
            laps.add(lap);
        }
    }

    /**
     * Test if we are at limit yet.
     *
     * @return true if we can add more laps.
     */
    protected abstract boolean testLimit();

    /**
     * Creates a string with information in the extra column
     * @param printLimit Number of laps that we print
     * @return returns a string with the error information
     */
    protected String printErrors(int printLimit) {
    	boolean lapTime = false;
    	StringBuilder sb = new StringBuilder();
    	 for (int i = 0; i < printLimit; i++) {
    		  if (getLapTimeElapsed(i).isBefore(new Time("00.15.00")) && !getLapTime(i).isEmpty()) {
    			  lapTime = true;
    		  }
    	  }   	 
    	 
    	 if (lapTime)
    		 sb.append("; Omöjlig varvtid?");
    	 if (multipleStart.size() > 0) {
    		 sb.append("; Flera starttider? ");
    		 for (int i = 0; i < multipleStart.size(); i++) {
    			 sb.append(multipleStart.get(i).toString() + " ");
    		 }
    	 }
    	 
    	return sb.toString();
    }
    /**
     * Print a formatted result for this race.
     *
     * @param printLimit max number of laps to print.
     * @return a formatted string for this race.
     */
    public String print(int printLimit) {
    	
        StringBuilder sb = new StringBuilder();
        sb.append("; ").append(getCompletedLaps());
        sb.append("; ").append(getTotal());
        for (int i = 0; i < printLimit; i++) {
            sb.append("; ").append(getLapTimeElapsed(i));
            
        }
        if(getStart().isEmpty()) {
        	sb.append("; ").append("Start?");
        }
        else
        	sb.append("; ").append(getStart());
        for (int i = 0; i < printLimit - 1; i++) {
            Time lapTime = getLapTime(i);
            if (lapTime.equals(getFinish()))    //Since we want the finishtime in the correct column, check this.
                sb.append("; ").append(new Time());
            else
                sb.append("; ").append(lapTime);
        }
        if (getFinish().isEmpty())
        	sb.append("; ").append("Slut?");
        else
        	sb.append("; ").append(getFinish());
        
        sb.append(printErrors(printLimit));
        return sb.toString();
        
    }

    /**
     * @return a new Race with the same time limit
     */
    public abstract Race copy();

    /**
     * A Private class to easily abstract laps.
     */
    private class Lap {

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
         * @param startTime Time to set as start.
         */
        public void setStart(Time startTime) {
            this.startTime = startTime;
        }

        /**
         * Set the finish time.
         *
         * @param finishTime Time to set as finish.
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
            return startTime.compareTo(finishTime);
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

