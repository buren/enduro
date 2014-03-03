package models;

import race.Race;

import java.util.ArrayList;

public class RaceEvent {
    private ArrayList<Participant> participants, invalidParticipants;
    private Race raceType;
    public final int INVALID_START_TIME = 0, INVALID_FINISH_TIME = 1;


    /**
     * Create a new RaceEvent
     *
     * @param raceType type of race this raceEvent is going to be.
     */
    public RaceEvent(Race raceType) {
        participants = new ArrayList<Participant>();
        invalidParticipants = new ArrayList<Participant>();
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
    public void addInvalidParticipant(Participant participant, int reason, Time time) {
        participant.setRace(newRace());
        switch (reason) {
            case INVALID_START_TIME:
                participant.getRace().setStart(time);
                break;
            case INVALID_FINISH_TIME:
                participant.getRace().addTime(time);
        }
        invalidParticipants.add(participant);
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
     * @return true if this race contains a participant with this id, else false.
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
            p.getRace().setStart(startTime);
        }
    }

    /**
     * Print a formatted result table as string.
     *
     * @param printLimit max number of laps to print
     * @return a formatted result string.
     */
    public String print(int printLimit) {
        StringBuilder sb = new StringBuilder();
        sb.append("StartNo; Name; #Laps; TotalTime");
        for (int i = 0; i < printLimit; i++) {
            sb.append("; Lap").append(i + 1);
        }
        sb.append("; Start");
        for (int i = 0; i < printLimit - 1; i++) {
            sb.append("; Checkpoint").append(i + 1);
        }
        sb.append("; Finish\n");
        for (Participant p : participants) {
            sb.append(p.print(printLimit)).append("\n");
        }
        return sb.toString();
    }

    /**
     * @return a new race for this event, to easily create new races of same type and limit.
     */
    private Race newRace() {
        return raceType.copy();
    }

}
