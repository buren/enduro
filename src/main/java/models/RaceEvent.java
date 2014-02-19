package models;

import race.Race;

import java.util.ArrayList;

public class RaceEvent {
	private ArrayList<Participant> participants, invalidParticipants;
    private Race raceType;
    public final int INVALID_START_TIME = 0, INVALID_FINISH_TIME = 1;


	/**
	 * Creates a new RaceEvent
	 */
	public RaceEvent(Race raceType) {
		participants = new ArrayList<>();
        invalidParticipants = new ArrayList<>();
        this.raceType = raceType;
	}

	/**
	 * Gets the number of participants
	 * 
	 * @return number of participants
	 */
	public int size() {
		return participants.size();
	}

	/**
	 * 
	 * @param participant
	 *            participant to add to RaceEvent
	 */
	public void addParticipant(Participant participant) {
        participant.setRace(newRace());
		participants.add(participant);
	}
    public void addInvalidParticipant(Participant participant, int reason, Time time){
        participant.setRace(newRace());
        switch (reason) {
            case INVALID_START_TIME:
                participant.getRace().addStart(time);
                break;
            case INVALID_FINISH_TIME:
                participant.getRace().addTime(time);
        }
        invalidParticipants.add(participant);
    }

    public Participant getParticipant(int ID) {
        for(Participant p : participants ) {
            if (p.getId() == ID)
                return p;
        }
        return null;
    }

    public boolean containsParticipant(int ID) {
        for(Participant p : participants ) {
            if (p.getId() == ID)
                return true;
        }
        return false;
    }

    public void setAllStartTimes(Time start) {
        for(Participant p : participants ) {
            p.getRace().addStart(start);
        }
    }

    public String print(int printLimit) {
        StringBuilder sb = new StringBuilder();
        sb.append("StartNo; Name; #Laps; TotalTime");
        for (int i = 0; i < printLimit; i++) {
            sb.append("; Lap").append(i+1);
        }
        sb.append("; Start");
        for (int i = 0; i < printLimit -1; i++) {
            sb.append("; Checkpoint").append(i+1);
        }
        sb.append("; Finish\n");
        for(Participant p : participants ) {
            sb.append(p.print(printLimit)).append("\n");
        }
        return sb.toString();
    }

    private Race newRace() {
        return raceType.copy();
    }

}
