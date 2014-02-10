package models;

import java.util.HashMap;

public class RaceEvent {
	private HashMap<Participant, Race> raceEvent;
	private int laps;

	public RaceEvent(int laps) {
		raceEvent = new HashMap<Participant, Race>();
		this.laps = laps;
	}

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
			// if (timeHandler.get(participant).equals(participant)){
			// && timeHandler.get(participant).equals(participant)) {
			return new Time();
		}
		if (raceEvent.get(participant).equals(participant)) {
			return raceEvent.get(participant).getFinish();
		}
		Time finish = raceEvent.get(participant).getFinish();

		return finish;
	}

	public Time getTotalTime(Participant participant) {
		if (raceEvent.get(participant) == null) {
			return new Time();
		}
		return raceEvent.get(participant).getTotalTime();
	}

	private void addParticipant(Participant participant) {
		Race race = new Race(laps);
		raceEvent.put(participant, race);
	}

	public void addName(Participant participant, String name) {
		if (raceEvent.get(participant) == null) {
			addParticipant(participant);
		}
		int id = participant.getId();
		for (Participant p : raceEvent.keySet()) {
			if (p.getId() == id) {
				p.setName(name);
			}
		}
	}

	public String getName(Participant participant) {
		for (Participant p : raceEvent.keySet()) {
			if (p.getId() == participant.getId()) {
				return p.getName();
			}
		}
		addParticipant(participant);
		return participant.getName();

	}

	public Time getLapStartTime(Participant participant, int lap) {
		if (raceEvent.get(participant) == null) {
			return new Time();
		}
		return raceEvent.get(participant).getLapStartTime(lap);
	}
	
	public Time getLapTime(Participant participant, int lap) {
		if (raceEvent.get(participant) == null) {
			return new Time();
		}
		return raceEvent.get(participant).getLapTime(lap);
	}

}
