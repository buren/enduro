package sorter;

import java.util.Iterator;

import models.Participant;
import models.RaceEvent;

public class SortName extends Sorter {

	@Override
	protected void addInfo(int columnNbr, Iterator itr, RaceEvent raceEvent) {
		while (itr.hasNext()) {
			String line = (String) itr.next();

			line = formatString(line);	
			String[] lines = line.split(";");
			Participant participant = new Participant(Integer.parseInt(lines[0]));
			participant.setName(lines[columnNbr]);
			raceEvent.addParticipant(participant);
		}
	}
}
