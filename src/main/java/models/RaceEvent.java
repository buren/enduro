package models;

import race.Race;

import java.util.ArrayList;

public class RaceEvent {
	private ArrayList<Participant> participants, notRegisteredParticipants;
	private Race raceType;
	public static final int START_TIME = 0, LAP_TIME = 1;

	/**
	 * Create a new RaceEvent
	 * 
	 * @param raceType
	 *            type of race this raceEvent is going to be.
	 */
	public RaceEvent(Race raceType) {
		participants = new ArrayList<>();
		notRegisteredParticipants = new ArrayList<>();
		this.raceType = raceType;
	}

	/**
	 * Add a participant to the event.
	 * 
	 * @param participant
	 *            participant to add to RaceEvent
	 */
	public void addParticipant(Participant participant) {
		participant.setRace(newRace());
		participants.add(participant);
	}

	/**
	 * Add a invalid participant to the event.
	 * 
	 * @param participant
	 *            participant to add as invalid.
	 * @param reason
	 *            reason why the participant is invalid.
	 * @param time
	 *            type of time that was invalid
	 */
	public void addNotRegisteredParticipant(Participant participant,
			int reason, Time time) {
		if (!notRegisteredParticipants.contains(participant)) {
			participant.setRace(newRace());
			notRegisteredParticipants.add(participant);
		} else {
			for (Participant p : notRegisteredParticipants) {
				if (p.equals(participant)) {
					participant = p;
				}
			}
		}
		switch (reason) {
		case START_TIME:
			participant.getRace().setStart(time);
			break;
		case LAP_TIME:
			participant.getRace().addTime(time);
			break;
		}

	}

	/**
	 * Return a participant by ID
	 * 
	 * @param ID
	 *            of wanted participant
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
	 * @param ID
	 *            of wanted participant
	 * @return true if this race contains a participant with this id, else
	 *         false.
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
	 * @param startTime
	 *            Time to set as start for all participants.
	 */
	public void setAllStartTimes(Time startTime) {
		for (Participant p : participants) {
			p.getRace().setStart(startTime);
		}
	}

	/**
	 * Print a formatted result table as string.
	 * 
	 * @param printLimit
	 *            max number of laps to print
	 * @return a formatted result string.
	 */
	public String print(int printLimit) {
		StringBuilder sb = new StringBuilder();
		sb.append(print(printLimit, participants));
		if (!(notRegisteredParticipants.size() == 0)) {
			sb.append("Icke existerande startnummer:\n");
			sb.append(print(printLimit, notRegisteredParticipants));
		}

		return sb.toString();
	}

	private String print(int printLimit, ArrayList<Participant> list) {
		{
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
				sb.append("StartNr; Namn; #Varv; TotalTid");
				for (int i = 0; i < printLimit; i++) {
					sb.append("; Varv").append(i + 1);
				}
				sb.append("; Start");
				for (int i = 0; i < printLimit - 1; i++) {
					sb.append("; Varvning").append(i + 1);
				}
				sb.append("; Mal\n");
				for (Participant p : list) {
					if (p.getRaceClass() == raceClass)
						sb.append(p.print(printLimit)).append("\n");
				}
			}
			return sb.toString();
		}
	}

	/**
	 * @return a new race for this event, to easily create new races of same
	 *         type and limit.
	 */
	private Race newRace() {
		return raceType.copy();
	}
}
