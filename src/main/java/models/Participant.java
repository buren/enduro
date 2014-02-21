package models;

import race.Race;

public class Participant {
    private int id;
    private String name;
    private Race race;

    /**
     * Participant identifies by their id/startnumber, two participants with the same id are considered the same.
     *
     * @param id id of participant
     */
    public Participant(int id) {
        this.id = id;
        name = "Not named";
    }

    /**
     * Return the participant id
     *
     * @return id of participant
     */
    public int getId() {
        return id;
    }

    /**
     * Set participant name to parameter name
     *
     * @param name name of participant
     */
    public void setName(String name) {
        this.name = name.trim();
    }

    /**
     * Return the participants name
     *
     * @return name of participant
     */
    public String getName() {
        return name;
    }

    /**
     * Connects the participant to a race
     *
     * @param race race the participant is racing.
     */
    public void setRace(Race race) {
        this.race = race;
    }

    /**
     * Return the participants race.
     *
     * @return race of participant
     */
    public Race getRace() {
        return race;
    }

    /**
     * Print a formatted result string.
     *
     * @param printLimit max number of laps to print.
     * @return a formatted string.
     */
    public String print(int printLimit) {
        return id + "; " + name + race.print(printLimit);
    }
}
