package sorter;

import java.util.Iterator;

import models.Participant;
import models.RaceEvent;
import models.Time;

public class SortStartTime extends Sorter {

    /**
     * Adds information within the file path to the correct column
     * @param columnNbr the number of the column
     * @param itr the iterator containing the rows
     * @param raceEvent the raceEvent that gets the information.
     */
	@Override
	protected void addInfo(int columnNbr, Iterator itr, RaceEvent raceEvent) {

		while (itr.hasNext()) {
			String line = (String) itr.next();
			line = formatString(line);
			String[] lines = line.split(";");
			if (lines[0].equals("*")) {
				for (Participant p : raceEvent.getKeySet()) {
					raceEvent.addStart(p, new Time(lines[columnNbr]));
				}
			} else {
				for (int i = 0; i < raceEvent.size(); i++) {
					Participant p = raceEvent.getParticipant(Integer
							.parseInt(lines[0]));
					raceEvent.addStart(p, new Time(lines[columnNbr]));
				}
			}
		}
	}

}
