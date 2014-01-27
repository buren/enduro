package models;

public class Participant {
    private String id;
    private String name;

    public Participant(String name, String id) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

}
