package sorter;

import java.util.Iterator;

import models.Participant;
import models.RaceEvent;
import models.Time;

public class SortStartTime extends Sorter {

	@Override
	protected void addInfo(int columnNbr, Iterator itr, RaceEvent raceEvent) {
		while (itr.hasNext()) {
			String line = (String) itr.next();

			line = formatString(line);
			String[] lines = line.split(";");
			
			raceEvent.addStart(new Participant(Integer.parseInt(lines[0])),
					new Time(lines[columnNbr]));
		}
	}

}
