package race;


public class LapRace extends Race {
    private int limit;

    /**
     * Create a new LapRace
     *
     * @param limit how many laps to be run.
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
     * @return a new lapRace with the same lap limit
     */
    @Override
    public Race copy() {
        return new LapRace(limit);
    }
}
