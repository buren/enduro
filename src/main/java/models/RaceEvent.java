package models;

import race.Race;
import utils.Sorter;

import java.util.ArrayList;

public class RaceEvent {
    private ArrayList<Participant> participants, notRegisteredParticipants;
    private Race raceType;
    private Sorter sorter;
    public static final int START_TIME = 0, LAP_TIME = 1, SORT = 0,
            DONT_SORT = 1;

    /**
     * Create a new RaceEvent
     *
     * @param raceType type of race this raceEvent is going to be.
     */
    public RaceEvent(Race raceType) {
        participants = new ArrayList<Participant>();
        notRegisteredParticipants = new ArrayList<Participant>();
        sorter = new Sorter();
        this.raceType = raceType;
    }

    /**
     * Add a participant to the event.
     *
     * @param participant participant to add to RaceEvent
     */
    public void addParticipant(Participant participant) {
        participant.setRace(newRace());
        participants.add(participant);
    }

    /**
     * Add a invalid participant to the event.
     *
     * @param participant participant to add as invalid.
     * @param reason      reason why the participant is invalid.
     * @param time        type of time that was invalid
     */

    public void addNotRegisteredParticipant(Participant participant,
                                            int reason, Time time) {
        if (notRegisteredParticipants.contains(participant)) {
            for (Participant p : notRegisteredParticipants)
                if (p.equals(participant))
                    participant = p;
        } else {
            participant.setRace(newRace());
            notRegisteredParticipants.add(participant);
        }
        if (reason == START_TIME)
            participant.getRace().addStartTime(time);
        else if (reason == LAP_TIME)
            participant.getRace().addFinishTime(time);
    }

    /**
     * Return a participant by ID
     *
     * @param ID of wanted participant
     * @return wanted participant if it exists, else null.
     */
    public Participant getParticipant(int ID) {
        for (Participant p : participants) {
            if (p.getId() == ID)
                return p;
        }
        return null;
    }

    /**
     * Search if this raceEvent contains a participant, by id.
     *
     * @param ID of wanted participant
     * @return true if this race contains a participant with this id, else
     * false.
     */
    public boolean containsParticipant(int ID) {
        for (Participant p : participants) {
            if (p.getId() == ID)
                return true;
        }
        return false;
    }

    /**
     * Set the startTime time of all participants.
     *
     * @param startTime Time to set as start for all participants.
     */
    public void setAllStartTimes(Time startTime) {
        for (Participant p : participants) {
            p.getRace().addStartTime(startTime);
        }
    }

    /**
     * Sets the starttime for everyone in the class 'className'.
     *
     * @param className A string containing the class name that we want to start
     * @param startTime The time that we want the starttimes to be
     */
    public void setAllClassStart(String className, Time startTime) {
        for (Participant p : participants) {
            if (p.getRaceClass().equals(className)) {
                p.getRace().addStartTime(startTime);
            }
        }
    }

    /**
     * @return a new race for this event, to easily create new races of same
     * type and limit.
     */
    private Race newRace() {
        return raceType.copy();
    }

    public void sort() {
        participants = sorter.sort(participants);
        addPlacement();
    }

    private void addPlacement() {
        ArrayList<String> raceClasses = new ArrayList<String>();
        for (Participant p : participants) {
            String raceClass = p.getRaceClass();
            if (!raceClass.isEmpty() && !(raceClasses.contains(raceClass))) {
                raceClasses.add(raceClass);
            }
        }
        for (String raceClass : raceClasses) {
            int place = 1;
            for (int i = 0; i < participants.size() - 1; i++) {
                Participant p = participants.get(i);
                if (p.getRaceClass().equals(raceClass)
                        && p.getRace().allLapsWithinLimit()) {
                    participants.get(i).setPlacment(Integer.toString(place++));
                }
            }
            for (int i = 0; i < participants.size(); i++) {
                Participant p = participants.get(i);
                if(p.getPlacement().equals("-")) {
                    participants.remove(p);
                    participants.add(participants.size()-1 ,p);
                }
            }
        }
    }

    /**
     * Print a formatted result table as string.
     *
     * @param printLimit max number of laps to print
     * @return a formatted result string.
     */
    public String print(int printLimit, int sortOption) {
        StringBuilder sb = new StringBuilder();
        if (sortOption == DONT_SORT) {
            sb.append(print(printLimit, participants));
            if (!(notRegisteredParticipants.size() == 0)) {
                sb.append("Icke existerande startnummer:\n");
                sb.append(print(printLimit, notRegisteredParticipants));
            }
        } else if (sortOption == SORT) {
            sort();
            sb.append(printSorted(participants));

        }

        return sb.toString();
    }

    /**
     * Print a formatted result table as string.
     *
     * @param printLimit max number of laps to print
     * @return a formatted result string.
     */
    public String print(int printLimit) {
        StringBuilder sb = new StringBuilder();
        sb.append(print(printLimit, participants));
        if (notRegisteredParticipants.size() > 0) {
            sb.append("Icke existerande startnummer:\n");
            sb.append(print(printLimit, notRegisteredParticipants));
        }
        return sb.toString();
    }

    private String printSorted(ArrayList<Participant> list) {
        ArrayList<String> raceClasses = new ArrayList<String>();
        for (Participant p : list) {
            String raceClass = p.getRaceClass();
            if (!raceClass.isEmpty() && !(raceClasses.contains(raceClass))) {
                raceClasses.add(raceClass);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String raceClass : raceClasses) {
            if (raceClass != "None")
                sb.append(raceClass).append("\n");
            sb.append("Plac; ");
            sb.append(list.get(0).printHeader());
            sb.append("; #Varv; TotalTid");
            for (int i = 0; i < participants.get(0).getRace()
                    .getCompletedLaps(); i++) {
                sb.append("; Varv").append(i + 1);
            }
            sb.append("\n");
            for (Participant p : list) {
                if (p.getRaceClass() == raceClass)
                    sb.append(p.printSorted()).append("\n");
            }
        }
        return sb.toString();
    }

    private String print(int printLimit, ArrayList<Participant> list) {
        ArrayList<String> raceClasses = new ArrayList<String>();
        for (Participant p : list) {
            String raceClass = p.getRaceClass();
            if (!raceClass.isEmpty() && !(raceClasses.contains(raceClass))) {
                raceClasses.add(raceClass);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String raceClass : raceClasses) {
            if (raceClass != "None")
                sb.append(raceClass).append("\n");
            sb.append(list.get(0).printHeader());
            sb.append(raceType.printHeader(printLimit));
            for (Participant p : list) {
                if (p.getRaceClass() == raceClass)
                    sb.append(p.print(printLimit)).append("\n");
            }
        }
        return sb.toString();
    }
}
