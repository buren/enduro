package models;

import race.Race;

import java.util.TreeMap;

public class Participant {
    private int id;
    private String name;
    private Race race;
    private String raceClass;
    private TreeMap<String, String> extraInfo;

    /**
     * Participant identifies by their id/startnumber, two participants with the same id are considered the same.
     *
     * @param id id of participant
     */
    public Participant(int id) {
        this.id = id;
        extraInfo = new TreeMap<String, String>();
        name = "Not named";
        raceClass = "None";
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
     * Adds a piece of extra information to this participant
     *
     * @param key   the type of information
     * @param value the actual information
     */
    public void addInfo(String key, String value) {
        extraInfo.put(key.trim(), value.trim());
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
     * Sets the class of the participant.
     *
     * @param raceClass Name of the class.
     */
    public void setRaceClass(String raceClass) {
        this.raceClass = raceClass;
    }

    /**
     * @return class for this participant.
     */
    public String getRaceClass() {
        return raceClass;
    }

    /**
     * Print a formatted result string.
     *
     * @param printLimit max number of laps to print.
     * @return a formatted string.
     */
    public String print(int printLimit) {
        String values = "";
        if (!extraInfo.isEmpty()) {
            String[] keyArray = new String[extraInfo.keySet().size()];
            extraInfo.keySet().toArray(keyArray);
            for (int i = 0; i < keyArray.length; i++) {
                values +="; "+extraInfo.get(keyArray[i]);
            }
        }

        return id + "; " + name+ values + race.print(printLimit);
    }

    public String printHeader() {
        String keys = "";
        if (!extraInfo.isEmpty()) {
            String[] keyArray = new String[extraInfo.keySet().size()];
            extraInfo.keySet().toArray(keyArray);
            for (int i = 0; i < keyArray.length; i++) {
                keys += "; " + keyArray[i];
            }
        }
        return "StartNr; Namn" + keys;
    }
}
