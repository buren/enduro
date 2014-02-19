package race;


public class LapRace extends Race {
    private int limit;

    public LapRace(int limit) {
        super();
        this.limit = limit;
    }


    @Override
    protected boolean testLimit() {
        return super.getLaps() < limit;
    }

    @Override
    public Race copy() {
        return new LapRace(limit);
    }
}
