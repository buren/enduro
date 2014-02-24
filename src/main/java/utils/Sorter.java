package utils;

import java.util.ArrayList;

import race.Race;
import models.Participant;

public class Sorter {

	public ArrayList<Participant> sort(ArrayList<Participant> participantList) {

		Participant[] participantArray = new Participant[participantList.size()];
		participantList.toArray(participantArray);
		ArrayList<Participant> sortedList = new ArrayList<Participant>();
		boolean flag = true;
		
		while(flag){
			flag = false;
			for(int i = 0; i < participantArray.length-2; i++){
				Race race1 = participantArray[i].getRace();
				Race race2 = participantArray[i+1].getRace();
				if(race1.compareTo(race2) > 0){
					Participant temp = participantArray[i];
					participantArray[i] = participantArray[i+1];
					participantArray[i+1] = temp;
					flag = true;
				}
			}
		}

		for (int i = participantArray.length - 1; i >= 0; i--) {
			sortedList.add(participantArray[i]);
		}
		return sortedList;
	}
}













//		for (int i = 0; i < participantArray.length; i++) {
//			for (int j = 1; j < (participantArray.length - i); j++) {
//				if (participantArray[j - 1].getRace().compareTo(participantArray[j].getRace()) >= 0) {
//					Participant temp = participantArray[j - 1];
//					participantArray[j - 1] = participantArray[j];
//					participantArray[j] = temp;
//				}
//			}
//		}