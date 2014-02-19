package models;

public class TimeRace extends Race {
    private Time limit;

    public TimeRace(Time limit) {
        super();
        this.limit = limit;
    }


    @Override
    protected boolean testLimit() {
        return getTotal().isBefore(limit);
    }

    @Override
    protected Race copy() {
        return new TimeRace(limit);
    }
}
