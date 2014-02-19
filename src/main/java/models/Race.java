package models;


import java.util.ArrayList;

public abstract class Race {
    protected ArrayList<Lap> laps = new ArrayList<>();

    public Race() {
        laps.add(new Lap());
    }

    public Time getStart() {
        return laps.get(0).getStart();
    }

    public void setStart(Time time) {
        laps.get(0).setStart(time);
    }

    protected int getLaps() {
        return laps.size();
    }

    private int getCompletedLaps() {
        if (laps.get(laps.size() - 1).getFinish().equals(new Time()))
            return laps.size() - 1;
        return laps.size();
    }

    private Time getLapTimeElapsed(int lap) {
        return laps.get(lap).getTotalTime();
    }

    private Time getLapTime(int lap) {
        return laps.get(lap).getFinish();
    }

    protected Time getTotal() {
        Time start = laps.get(0).getStart();
        Time finish = laps.get(laps.size() - 1).getFinish();
        return start.compareTo(finish);
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
        sb.append("; ").append(getTotal());
        for (int i = 0; i < printLimit; i++) {
            if (i > laps.size() - 1) {
                sb.append("; " + new Time());
            } else {
                sb.append("; ").append(getLapTimeElapsed(i));
            }
        }
        sb.append("; " + getStart());
        for (int i = 0; i < printLimit-1; i++) {
            if (i > laps.size() - 2) {
                sb.append("; " + new Time());
            } else {
                sb.append("; ").append(getLapTime(i));
            }
        }
        sb.append("; " + laps.get(laps.size() - 1).getFinish());
        return sb.toString();
    }

    protected abstract Race copy();
}
