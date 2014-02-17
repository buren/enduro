package models;

import java.util.ArrayList;

public class TimeRace extends Race {
    private ArrayList<Lap> laps;
    private Time limit;

    public TimeRace(Time limit) {
        laps = new ArrayList<>();
        laps.add(new Lap());
        this.limit = limit;
    }


    @Override
    protected boolean testLimit(Time time) {
        return time.isBefore(limit);
    }

    @Override
    protected Race copy() {
        return new TimeRace(limit);
    }
}
