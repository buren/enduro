package models;

import java.util.HashMap;

public class TimeHandler {
	private HashMap<Participant, Times> timeHandler;

	public TimeHandler() {
		timeHandler = new HashMap<Participant, Times>();
	}

	public int size() {
		return timeHandler.size();
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

	public void addStart(Participant participant, String startTime) {
		if (timeHandler.get(participant) == null) {
			addParticipant(participant);
		}
		timeHandler.get(participant).setStart(startTime);
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
		if (timeHandler.get(participant) == null) {
			return new Time();
		}

		Time start = timeHandler.get(participant).getStart();
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
	public void addFinish(Participant participant, String finishTime) {
		if (timeHandler.get(participant) == null) {
			addParticipant(participant);
		}
		
		timeHandler.get(participant).setFinish(finishTime);
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
		if (timeHandler.get(participant) == null) {
//			 if (timeHandler.get(participant).equals(participant)){
			// && timeHandler.get(participant).equals(participant)) {
			return new Time();
		}
		if (timeHandler.get(participant).equals(participant)) {
			return timeHandler.get(participant).getFinish();
		}
		Time finish = timeHandler.get(participant).getFinish();

		return finish;
	}

	public Time getTotalTime(Participant participant) {
		if (timeHandler.get(participant) == null) {
			return new Time();
		}
		return timeHandler.get(participant).getTotalTime();
	}

	private void addParticipant(Participant participant) {
		Times time = new Times();
		timeHandler.put(participant, time);
	}

	public void addName(Participant participant, String name) {
		if (timeHandler.get(participant) == null) {
			addParticipant(participant);
		}
		int id = participant.getId();
		for (Participant p : timeHandler.keySet()) {
			if (p.getId() == id) {
				p.setName(name);
			}
		}
	}

	public String getName(Participant participant) {
		for (Participant p : timeHandler.keySet()) {
			if (p.getId() == participant.getId()) {
				return p.getName();
			}
		}
		addParticipant(participant);
		return participant.getName();

	}

}
