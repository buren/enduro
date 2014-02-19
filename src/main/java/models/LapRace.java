package models;


public class LapRace extends Race {
    private int limit;

    public LapRace(int limit) {
        super();
        this.limit = limit;
    }


    @Override
    protected boolean testLimit(Time time) {
        return super.getLaps() <= limit;
    }

    @Override
    protected Race copy() {
        return new LapRace(limit);
    }
}
