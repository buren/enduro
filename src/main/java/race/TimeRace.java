package race;

import models.Time;

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
    public Race copy() {
        return new TimeRace(limit);
    }
}
