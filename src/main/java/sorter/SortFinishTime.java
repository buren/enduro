package sorter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import models.Participant;
import models.RaceEvent;
import models.Time;

public class SortFinishTime extends Sorter {


    /**
     * Finds the correct column, and then inserts it in the raceEvent
     *
     * @param files     , URL-address of target files.
     * @param column    , name of column to sort
     * @param raceEvent , RaceEvent to sort
     * @throws FileNotFoundException
     */
    public void insertInfo(Iterator[] files, String column, RaceEvent raceEvent) {
        String columns = (String) files[0].next();
        for (int i = 1; i < files.length; i++) {
            files[i].next();
        }

        columns = formatString(columns);
        String[] column_list = columns.split(";");
        int columnNbr = 0;
        for (int i = 0; i < column_list.length; i++) {
            if (column_list[i].equals("Maltider"))
                columnNbr = i;
        }
        addInfo(columnNbr, files, raceEvent);
    }

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
            Time finishTime = new Time(lines[columnNbr]);
            int id = Integer.parseInt(lines[0]);
            if (raceEvent.containsParticipant(id))
                raceEvent.getParticipant(id).getRace().addTime(finishTime);
            else {
                Participant p = new Participant(id);
                p.getRace().addTime(finishTime);
            }
        }
    }


    /**
     * Adds information within the file path to the correct column, for multiple files
     *
     * @param columnNbr the number of the column
     * @param itr       the iterators containing the rows
     * @param raceEvent the raceEvent that gets the information.
     */
    protected void addInfo(int columnNbr, Iterator[] itr, RaceEvent raceEvent) {
        ArrayList<Time> timeList = new ArrayList<>();
        ArrayList<Integer> intList = new ArrayList<>();

        for (Iterator iterator : itr) {
            while (iterator.hasNext()) {
                String line = (String) iterator.next();
                line = formatString(line);
                String[] lines = line.split(";");
                timeList.add(new Time(lines[columnNbr]));
                intList.add(Integer.parseInt(lines[0]));
            }
        }
        sortTime(timeList, intList);

        for (int i = 0; i < timeList.size(); i++) {
            int id = intList.get(i);
            Time finishTime = timeList.get(i);
            if (raceEvent.containsParticipant(id))
                raceEvent.getParticipant(id).getRace().addTime(finishTime);
            else {
                Participant p = new Participant(id);
                p.getRace().addTime(finishTime);
            }
        }
    }

    /**
     * Sorts two lists, a list of times and IDs simultaneously, sorting by first time
     *
     * @param timeList list of times to sort
     * @param idList   list of IDs to sort
     */
    private void sortTime(ArrayList<Time> timeList, ArrayList<Integer> idList) {
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
