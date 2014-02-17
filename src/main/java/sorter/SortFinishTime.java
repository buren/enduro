package sorter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import models.Participant;
import models.RaceEvent;
import models.Time;

public class SortFinishTime extends Sorter {


    public void insertInfo(String[] filePath, String column, RaceEvent raceEvent)
            throws FileNotFoundException {
        try {

            Iterator[] itr = new Iterator[filePath.length];
            for (int i = 0; i < itr.length; i++) {
                itr[i] = fr.readFileByLine(filePath[i]);
            }

            String columns = (String) itr[0].next();
            for (int i = 1; i < itr.length; i++) {
                itr[i].next();
            }

            columns = formatString(columns);
            String[] column_list = columns.split(";");
            int columnNbr = 0;
            for (int i = 0; i < column_list.length; i++) {
                if (column_list[i].equals(column)) {
                    columnNbr = i;
                }

            }
            addInfo(columnNbr, itr, raceEvent);

        } catch (FileNotFoundException e) {
            throw e;
        }

    }

    @Override
    protected void addInfo(int columnNbr, Iterator itr, RaceEvent raceEvent) {
        while (itr.hasNext()) {
            String line = (String) itr.next();

            line = formatString(line);
            String[] lines = line.split(";");
            raceEvent.addFinish(new Participant(Integer.parseInt(lines[0])),
                    new Time(lines[columnNbr]));
        }
    }


    protected void addInfo(int columnNbr, Iterator[] itr, RaceEvent raceEvent) {
        ArrayList<Time> timeList = new ArrayList<>();
        ArrayList<Integer> intList = new ArrayList<>();

        for (int i = 0; i < itr.length; i++) {
            while (itr[i].hasNext()) {
                String line = (String) itr[i].next();
                line = formatString(line);
                String[] lines = line.split(";");
                timeList.add(new Time(lines[columnNbr]));
                intList.add(Integer.parseInt(lines[0]));
            }
        }
        sortTime(timeList, intList);


        for(int i=0; i<timeList.size(); i++) {
            raceEvent.addFinish(new Participant(intList.get(i)), timeList.get(i));
        }
    }

    /**
     * Sorts two lists, a list of times and IDs simultaneously, sorting by first time
     * @param timeList list of times to sort
     * @param idList list of IDs to sort
     */
    private void sortTime(ArrayList<Time> timeList, ArrayList<Integer> idList) {
        Time[] timeArray = new Time[timeList.size()];
        timeList.toArray(timeArray);
        Integer[] intArray = new Integer[idList.size()];
        idList.toArray(intArray);

        timeList.clear();
        idList.clear();

        for(int i = 0; i < timeArray.length; i++) {
            for(int j = 1; j < (intArray.length-i); j++) {
                if(timeArray[j].isBefore(timeArray[j-1])) {
                    Time temp = timeArray[j-1];
                    timeArray[j-1] = timeArray[j];
                    timeArray[j] = temp;

                    int intTemp = intArray[j-1];
                    intArray[j-1] = intArray[j];
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
