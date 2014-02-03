package models;

import java.util.HashMap;

public class TimeHandler {
	private HashMap<Participant, Times> timeHandler;

	public TimeHandler() {
		timeHandler = new HashMap<Participant, Times>();
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
			return new Time();
		}
		Time finish = timeHandler.get(participant).getFinish();

		return finish;
	}

	private void addParticipant(Participant participant) {
		Times time = new Times();
		timeHandler.put(participant, time);
	}

}
