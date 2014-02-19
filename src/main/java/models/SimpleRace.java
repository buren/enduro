package models;

public class SimpleRace extends Race {

    private Time start;
    private Time finish;

    public SimpleRace() {
        start = new Time();
        finish = new Time();
    }

    @Override
    public Time getStart() {
        return start;
    }

    @Override
    public void setStart(Time start) {
        this.start = start;
    }


    public Time getFinish() {
        return finish;
    }

    public void addTime(Time finish) {
        this.finish = finish;
    }

    public Time getTotal() {
        return start.compareTo(finish);
    }

    protected boolean testLimit() {
        return false;
    }

    @Override
    public String print(int printLimit) {
        return getTotal()+"; "+start+"; "+finish;
    }

    @Override
    protected Race copy() {
        return new SimpleRace();
    }
}
