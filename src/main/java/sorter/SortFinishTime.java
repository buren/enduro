package sorter;

import java.util.Iterator;

import models.Participant;
import models.RaceEvent;
import models.Time;

public class SortFinishTime extends Sorter {

	@Override
	protected void addInfo(int columnNbr, Iterator itr, RaceEvent raceEvent) {
		while (itr.hasNext()) {
			String line = (String) itr.next();
			String[] lines = line.split("; ");
			raceEvent.addFinish(new Participant(Integer.parseInt(lines[0])),
					new Time(lines[columnNbr]));
			
		}
	}

}
