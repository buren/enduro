package models;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

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
	private void registerParticipants(Iterator<String> nameIterator) {
		String header = nameIterator.next();
		String[] headerSplit = header.split(";");
		String raceClass = "";
		while (nameIterator.hasNext()) {
			String line = nameIterator.next();
			if (!line.contains(";")) {
				raceClass = line.toString();
			} else {
				String[] lines = line.split(";");
				int id = Integer.parseInt(lines[0]);
				Participant participant = new Participant(id);
				participant.setName(lines[1]);
				for (int i = 2; i < headerSplit.length; i++) {
					participant.addInfo(headerSplit[i], lines[i]);
				}
				if (!raceClass.isEmpty()) {
					participant.setRaceClass(raceClass);
				}
				raceEvent.addParticipant(participant);
			}
		}
	}

	/**
	 * Register the start times.
	 * 
	 * @param startTimesIterator
	 *            iterator containing the start times.
	 */
	public void registerStartTimes(Iterator<String>[] startTimesIterator) {
		ArrayList<Time> timeList = new ArrayList<Time>();
		ArrayList<Integer> intList = new ArrayList<Integer>();

		boolean masstart = false;
		for (Iterator<String> iterator : startTimesIterator) {
			while (iterator.hasNext() && (!masstart)) {
				String line = iterator.next();
				String[] lines = line.split(";");

				Time startTime = new Time(lines[1]);
				if (lines[0].trim().equals("*")) {
					raceEvent.setAllStartTimes(startTime);
					masstart = true;
				} else {
					try {
						intList.add(Integer.parseInt(lines[0]));
						timeList.add(startTime);
					} catch (NumberFormatException e) {
						raceEvent.setAllClassStart(lines[0], startTime);
					}

				}
			}
		}

		if (!masstart) {
			sortTimesWithID(timeList, intList);
			for (int i = 0; i < timeList.size(); i++) {
				int id = intList.get(i);
				Time startTime = timeList.get(i);
				if (raceEvent.containsParticipant(id))
					raceEvent.getParticipant(id).getRace()
							.addStartTime(startTime);
				else {
					Participant invalidParticipant = new Participant(id);
					raceEvent.addNotRegisteredParticipant(invalidParticipant,
							RaceEvent.START_TIME, startTime);
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
	public void registerFinishTimes(Iterator<String>[] finishTimesIterator) {
		ArrayList<Time> timeList = new ArrayList<Time>();
		ArrayList<Integer> intList = new ArrayList<Integer>();

		for (Iterator<String> iterator : finishTimesIterator) {
			while (iterator.hasNext()) {
				String line = iterator.next();
				String[] lines = line.split(";");
				intList.add(Integer.parseInt(lines[0]));
				timeList.add(new Time(lines[1]));
			}
		}
		sortTimesWithID(timeList, intList);

		for (int i = 0; i < timeList.size(); i++) {
			int id = intList.get(i);
			Time finishTime = timeList.get(i);
			if (raceEvent.containsParticipant(id))
				raceEvent.getParticipant(id).getRace()
						.addFinishTime(finishTime);
			else {
				Participant notRegisteredParticipant = new Participant(id);
				raceEvent.addNotRegisteredParticipant(notRegisteredParticipant,
						RaceEvent.LAP_TIME, finishTime);
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
	private void sortTimesWithID(ArrayList<Time> timeList,
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
