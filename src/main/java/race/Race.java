package race;


import models.Lap;
import models.Time;

import java.util.ArrayList;

public abstract class Race {
    protected ArrayList<Lap> laps = new ArrayList<>();

    public Race() {
        laps.add(new Lap());
    }

    public void addStart(Time time) {
        laps.get(0).setStart(time);
    }

    private Time getStart() {
        return laps.get(0).getStart();
    }

    private Time getFinish() {
        return laps.get(laps.size() - 1).getFinish();
    }

    private Time getLastTime() {
        if (getFinish().isEmpty())
            return laps.get(laps.size() - 1).getStart();
        return getFinish();
    }

    protected int getLaps() {
        return laps.size();
    }

    private int getCompletedLaps() {
        if (laps.get(laps.size() - 1).getFinish().isEmpty())
            return laps.size() - 1;
        return laps.size();
    }

    private Time getLapTimeElapsed(int lap) {
        if (laps.size() <= lap)
            return new Time();
        return laps.get(lap).getTotalTime();
    }

    private Time getLapTime(int lap) {
        if (laps.size() <= lap)
            return new Time();
        return laps.get(lap).getFinish();
    }

    protected Time getTotal() {
        return getStart().compareTo(getFinish());
    }

    public void addTime(Time time) {
        if (laps.get(laps.size() - 1).getFinish().isEmpty())
            laps.get(laps.size() - 1).setFinish(time);
        else {
            throw new IndexOutOfBoundsException("Max antal varv tillagda.");
        }
        if (testLimit()) {
            Lap lap = new Lap();
            lap.setStart(time);
            laps.add(lap);
        }
    }

    protected abstract boolean testLimit();

    public String print(int printLimit) {
        StringBuilder sb = new StringBuilder();
        sb.append("; ").append(getCompletedLaps());
        if (getFinish().isEmpty()) {
            sb.append("; ").append(getStart().compareTo(laps.get(laps.size() - 1).getStart()));
        } else {
            sb.append("; ").append(getTotal());
        }
        for (int i = 0; i < printLimit; i++) {
            sb.append("; ").append(getLapTimeElapsed(i));
        }
        sb.append("; " + getStart());
        for (int i = 0; i < printLimit - 1; i++) {
            Time lapTime = getLapTime(i);
            if (lapTime.equals(getLastTime()))
                sb.append("; " + new Time());
            else
                sb.append("; " + lapTime);
        }
        sb.append("; " + getLastTime());
        return sb.toString();
    }

    public abstract Race copy();
}
