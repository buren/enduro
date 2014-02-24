package models;

import java.util.ArrayList;
import java.util.Iterator;

public class ModelInitiator {

	private RaceEvent raceEvent;

	/**
	 * Create a new ModelInitiator
	 * 
	 * @param nameIterator
	 *            iterator containing the names of the valid participants
	 * @param raceEvent
	 *            raceEvent to initiate with data.
	 */
	public ModelInitiator(Iterator nameIterator, RaceEvent raceEvent) {
		this.raceEvent = raceEvent;
		registerParticipants(nameIterator);
	}

	/**
	 * Register the valid participants for the event
	 * 
	 * @param nameIterator
	 *            iterator containing the names of the valid participants.
	 */
	private void registerParticipants(Iterator nameIterator) {
		nameIterator.next(); // Jump first line
		while (nameIterator.hasNext()) {
			String line = (String) nameIterator.next();
			String[] lines = line.split(";");
			int id = Integer.parseInt(lines[0]);
			Participant participant = new Participant(id);
			participant.setName(lines[1]);
			raceEvent.addParticipant(participant);
		}
	}

	/**
	 * Register the start times.
	 * 
	 * @param startTimesIterator
	 *            iterator containing the start times.
	 */
	public void registerStartTimes(Iterator startTimesIterator) {
		while (startTimesIterator.hasNext()) {
			String line = (String) startTimesIterator.next();
			String[] rows = line.split(";");

			Time startTime = new Time(rows[1]);

			if (rows[0].trim().equals("*")) {
				raceEvent.setAllStartTimes(startTime);
			} else {
				int id = Integer.parseInt(rows[0]);
				if (raceEvent.containsParticipant(id))
					raceEvent.getParticipant(id).getRace().setStart(startTime);
				else {
					Participant invalidParticipant = new Participant(id);
					raceEvent.addInvalidParticipant(invalidParticipant,
							raceEvent.INVALID_START_TIME, startTime);
				}
			}
		}
	}

	/**
	 * Register the finish times.
	 * 
	 * @param finishTimesIterator
	 *            iterator containing the finish times.
	 */
	public void registerFinishTimes(Iterator[] finishTimesIterator) {
		ArrayList<Time> timeList = new ArrayList<>();
		ArrayList<Integer> intList = new ArrayList<>();

		for (Iterator iterator : finishTimesIterator) {
			while (iterator.hasNext()) {
				String line = (String) iterator.next();
				String[] lines = line.split(";");
				intList.add(Integer.parseInt(lines[0]));
				timeList.add(new Time(lines[1]));
			}
		}
		sortFinishTimes(timeList, intList);

		for (int i = 0; i < timeList.size(); i++) {
			int id = intList.get(i);
			Time finishTime = timeList.get(i);
			if (raceEvent.containsParticipant(id))
				raceEvent.getParticipant(id).getRace().addTime(finishTime);
			else {
				Participant invalidParticipant = new Participant(id);
				raceEvent.addInvalidParticipant(invalidParticipant,
						raceEvent.INVALID_FINISH_TIME, finishTime);
			}
		}
	}

	/**
	 * Sorts two lists, a list of times and IDs simultaneously, sorting by first
	 * time
	 * 
	 * @param timeList
	 *            list of times to sort
	 * @param idList
	 *            list of IDs to sort
	 */
	private void sortFinishTimes(ArrayList<Time> timeList,
			ArrayList<Integer> idList) {
		Time[] timeArray = new Time[timeList.size()];
		timeList.toArray(timeArray);
		Integer[] intArray = new Integer[idList.size()];
		idList.toArray(intArray);

		timeList.clear();
		idList.clear();

		for (int i = 0; i < timeArray.length; i++) {
			for (int j = 1; j < (intArray.length - i); j++) {
				if (timeArray[j].isBefore(timeArray[j - 1])) {
					Time temp = timeArray[j - 1];
					timeArray[j - 1] = timeArray[j];
					timeArray[j] = temp;

					int intTemp = intArray[j - 1];
					intArray[j - 1] = intArray[j];
					intArray[j] = intTemp;
				}
			}
		}
		for (int i = 0; i < timeArray.length; i++) {
			timeList.add(timeArray[i]);
			idList.add(intArray[i]);
		}
	}
}
