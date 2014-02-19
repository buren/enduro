package models;


import java.util.ArrayList;

public abstract class Race {
    private ArrayList<Lap> laps = new ArrayList<>();

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

    private Time getLapTimeElapsed(int lap) {
        return laps.get(lap).getTotalTime();
    }

    private Time getLapTime(int lap) {
        return laps.get(lap).getFinish();
    }

    private Time getTotal() {
        Time start = laps.get(0).getStart();
        Time finish = laps.get(laps.size() - 1).getFinish();
        return start.compareTo(finish);
    }

    public void addTime(Time time) {
        laps.get(laps.size() - 1).setFinish(time);
        if (testLimit(time)) {
            Lap lap = new Lap();
            lap.setStart(time);
            laps.add(lap);
        }
    }

    protected abstract boolean testLimit(Time time);

    public String print(int printLimit) {
        StringBuilder sb = new StringBuilder();
        sb.append("; ").append(laps.size());
        sb.append("; ").append(getTotal());
        for (int i = 0; i < printLimit ; i++) {
            sb.append("; ").append(getLapTimeElapsed(i));
        }
        for (int i = 0; i < printLimit; i++) {
            sb.append("; ").append(getLapTime(i));
        }
        return sb.toString();
    }

    protected abstract Race copy();
}
