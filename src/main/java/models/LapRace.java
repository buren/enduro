package models;


public class LapRace extends Race {
    private int limit;

    public LapRace(int limit) {
        this.limit = limit;
    }


    @Override
    protected boolean testLimit(Time time) {
        if(super.getLaps() <= limit)
            return true;
        else
            return false;
    }

    @Override
    protected Race copy() {
        return new LapRace(limit);
    }
}
