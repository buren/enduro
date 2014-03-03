package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import race.Race;
import models.Participant;

public class Sorter {
	
	/**
	 * 
	 * @param participantList, list with Participants to be sorted.
	 * @return a list sorted with participant considered best first.
	 */

	public ArrayList<Participant> sort(ArrayList<Participant> participantList) {
		Participant[] pArray = new Participant[participantList.size()];
		participantList.toArray(pArray);
		Arrays.sort(pArray);	//Sorts array in order last-best.
		ArrayList<Participant> pList = new ArrayList<Participant>(Arrays.asList(pArray));
		Collections.reverse(pList); // Reverses list to be best-last.
		return pList;
	}
}