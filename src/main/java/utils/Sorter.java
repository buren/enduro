package utils;

import java.util.ArrayList;

import models.Participant;
import models.Time;

public class Sorter {

	public ArrayList<Participant> sortByNumberOfLaps(ArrayList<Participant> participantList) {

		Participant[] participantArray = new Participant[participantList.size()];
		participantList.toArray(participantArray);
		participantList.clear();

		for (int i = 0; i < participantArray.length; i++) {
			for (int j = 1; j < (participantArray.length - i); j++) {
				if (participantArray[j - 1].getRace().isBetter(
						participantArray[j].getRace())) {
					Participant temp = participantArray[j - 1];
					participantArray[j - 1] = participantArray[j];
					participantArray[j] = temp;
				}
			}
		}
		for (int i = participantArray.length - 1; i >= 0; i--) {
			participantList.add(participantArray[i]);
		}
		return participantList;
	}

	public ArrayList<Participant> sortByTotalTime(ArrayList<Participant> participantList) {
		Participant[] participantArray = new Participant[participantList.size()];
		participantList.toArray(participantArray);

		for (int i = 0; i < participantArray.length; i++) {
			for (int j = 1; j < (participantArray.length - i); j++) {
				if (participantArray[j - 1].getRace().isBetter(participantArray[j].getRace())) {
					Participant temp = participantArray[j - 1];
					participantArray[j - 1] = participantArray[j];
					participantArray[j] = temp;
				}
			}
		}
		participantList.clear();
		for (int i = participantArray.length - 1; i >= 0; i--) {
			participantList.add(participantArray[i]);
		}
		return participantList;
	}
}
