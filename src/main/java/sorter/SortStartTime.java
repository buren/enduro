package sorter;

import java.util.Iterator;

import models.Participant;
import models.RaceEvent;
import models.Time;

public class SortStartTime extends Sorter {

    /**
     * Adds information within the file path to the correct column
     *
     * @param columnNbr the number of the column
     * @param itr       the iterator containing the rows
     * @param raceEvent the raceEvent that gets the information.
     */
    @Override
    protected void addInfo(int columnNbr, Iterator itr, RaceEvent raceEvent) {
        while (itr.hasNext()) {
            String line = (String) itr.next();
            line = formatString(line);
            String[] lines = line.split(";");
            Time startTime = new Time(lines[columnNbr]);
            int id = Integer.parseInt(lines[0]);
            if (lines[0].equals("*")) {
                raceEvent.setAllStartTimes(startTime);
            } else {
                if (raceEvent.containsParticipant(id))
                    raceEvent.getParticipant(id).getRace().setStart(startTime);
                else {
                    Participant p = new Participant(id);
                    p.getRace().setStart(startTime);
                }
            }
        }
    }

}
