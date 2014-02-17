package models;

import java.util.HashMap;
import java.util.Set;

public class RaceEvent {
	private HashMap<Participant, Race> raceEvent;
	private int laps;
	private String time;

	/**
	 * Creates a new RaceEvent
	 * 
	 * @param laps
	 *            The number of laps in the race
	 */
	public RaceEvent(int laps) {
		raceEvent = new HashMap<Participant, Race>();
		this.laps = laps;
	}

	/**
	 * Creates a new RaceEvent
	 * 
	 * @param time
	 *            The timelimit
	 */
	public RaceEvent(String time) {
		raceEvent = new HashMap<Participant, Race>();
		this.time = time;
	}

	/**
	 * Gets the number of participants
	 * 
	 * @return number of participants
	 */
	public int size() {
		return raceEvent.size();
	}

	/**
	 * Links starting time to participant. If participant is missing it adds it
	 * to hashmap.
	 * 
	 * @param participant
	 *            Participant you want to link time to
	 * @param startTime
	 *            Value of starting time
	 */
	public void addStart(Participant participant, Time startTime) {
		if (raceEvent.get(participant) == null) {
			addParticipant(participant);
		}
		raceEvent.get(participant).setStart(startTime);
	}

	/**
	 * Gets start value linked to participant
	 * 
	 * @param participant
	 *            Participant you want to know value of
	 * @return "--.--.--" if participant is missing, otherwise it returns the
	 *         value.
	 */
	public Time getStart(Participant participant) {
		if (raceEvent.get(participant) == null) {
			return new Time();
		}

		Time start = raceEvent.get(participant).getStart();
		return start;
	}

	/**
	 * Links finish time to participant. If participant is missing it adds it to
	 * hashmap.
	 * 
	 * @param participant
	 *            Participant you want to link time to
	 * @param finishTime
	 *            Value of finish time
	 */
	public void addFinish(Participant participant, Time finishTime) {
		if (raceEvent.get(participant) == null) {
			addParticipant(participant);
		}
		raceEvent.get(participant).setLapTime(finishTime);
	}

	/**
	 * Gets finish value linked to participant
	 * 
	 * @param participant
	 *            Participant you want to know value of
	 * @return "--.--.--" if participant is missing, otherwise it returns the
	 *         value.
	 */
	public Time getFinish(Participant participant) {
		if (raceEvent.get(participant) == null) {
			return new Time();
		}
		if (raceEvent.get(participant).equals(participant)) {
			return raceEvent.get(participant).getFinish();
		}
		Time finish = raceEvent.get(participant).getFinish();
		return finish;
	}

	/**
	 * 
	 * @param participant
	 * @return the total race time for participant.
	 */
	public Time getTotalTime(Participant participant) {
		if (raceEvent.get(participant) == null) {
			return new Time();
		}
		return raceEvent.get(participant).getTotalTime();
	}

	/**
	 * 
	 * @param participant
	 *            participant to add to RaceEvent
	 */
	public void addParticipant(Participant participant) {
		Race race;
		if (laps == 0 && time != null) {
			race = new Race(time);
		} else {
			race = new Race(laps);
		}
		raceEvent.put(participant, race);
	}

	/**
	 * 
	 * @param participant
	 *            participant to rename
	 * @param name
	 *            name to be assigned
	 */
	public void changeName(Participant participant, String name) {
		if (raceEvent.get(participant) == null) {
			throw new IllegalArgumentException("Oregistrerad deltagare");
		}
		for (Participant p : raceEvent.keySet()) {
			if (p.equals(participant)) {
				p.setName(name);
			}
		}
	}

	/**
	 * Gets the name of a participant
	 * 
	 * @param participant
	 *            A participant with the same id as we want to get the name of.
	 * @return Returns the name of the participant, otherwise it returns
	 *         "Not Named"
	 */
	public String getName(Participant participant) {
		for (Participant p : raceEvent.keySet()) {
			if (p.getId() == participant.getId()) {
				return p.getName();
			}
		}
		throw new IllegalArgumentException(
				"Angiven deltagare är ej registrerad");

	}

	/**
	 * Gets the race for the participant
	 * 
	 * @param participant
	 *            A participant with the same id as we want to get the race
	 *            from.
	 * @return
	 */
	public Race getRace(Participant participant) {
		if (raceEvent.get(participant) == null) {
			throw new IllegalArgumentException("Obefintlig deltagare");
		}
		return raceEvent.get(participant);
	}

	/**
	 * Gets the time when the lap started
	 * 
	 * @param participant
	 *            A participant with the same id as we want to get the lap start
	 *            time from.
	 * @param lap
	 *            The lap we want the time for
	 * @return Returns the lap start time
	 */
	public Time getLapStartTime(Participant participant, int lap) {
		if (raceEvent.get(participant) == null) {
			return new Time();
		}
		return raceEvent.get(participant).getLapStartTime(lap);
	}

	/**
	 * Gets the time when the lap started
	 * 
	 * @param participant
	 *            A participant with the same id as we want to get the lap start
	 *            time from.
	 * @param lap
	 *            The lap we want the time for
	 * @return Returns the elapsed time
	 */
	public Time getLapTime(Participant participant, int lap) {
		if (raceEvent.get(participant) == null) {
			return new Time();
		}
		return raceEvent.get(participant).getLapTime(lap);
	}

	public Set<Participant> getKeySet() {
		return raceEvent.keySet();
	}

	public Participant getParticipant(int id) {
		for (Participant p : raceEvent.keySet()) {
			if (p.getId() == id) {
				return p;
			}
		}
		throw new IllegalArgumentException("Participant med id nr " + id
				+ " är ej registrerad");
	}

}
