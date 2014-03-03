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
     * @param start Time to set as start.
     */
    @Override
    public void addStartTime(Time start) {
        if(this.start.isEmpty())
            this.start = start;
        else
            multipleStart.add(start);
    }

    /**
     * Add a new finishtime
     *
     * @param finish time to add.
     * @return true if a Time was successfully added, else false.
     */
    @Override
    public void addFinishTime(Time finish) {
        if(this.finish.isEmpty())
            this.finish = finish;
        else
            multipleFinish.add(finish);
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
     * Test if we are at limit yet.
     *
     * @return always false, no laps.
     */
    @Override
    protected boolean testLimit() {
        return false;
    }

    /**
     * Creates a string with information in the extra column
     *
     * @return returns a string with the error information
     */
    private String printErrors() {
        StringBuilder sb = new StringBuilder();
        if (getTotal().isBefore(lapLimitTime) && !(getTotal().isEmpty()))
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
     * @param printLimit max number of laps to print.
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
        sb.append(printErrors());
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

    /**
     * Prints a header for the result
     *
     * @param printLimit max number of laps to print.
     * @return a formatted string.
     */
    @Override
    public String printHeader(int printLimit) {
        StringBuilder sb = new StringBuilder();
        sb.append("; TotalTid");
        sb.append("; Start");
        sb.append("; Mal\n");
        return sb.toString();
    }

}
