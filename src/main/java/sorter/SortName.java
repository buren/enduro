package sorter;

import java.util.Iterator;

import models.Participant;
import models.RaceEvent;

public class SortName extends Sorter {

	@Override
	protected void addInfo(int columnNbr, Iterator itr, RaceEvent time) {
		while (itr.hasNext()) {
			String line = (String) itr.next();
			line = formatString(line);	
			String[] lines = line.split(";");
			time.addName(new Participant(Integer.parseInt(lines[0])),
					lines[columnNbr]);
		}
	}

}
