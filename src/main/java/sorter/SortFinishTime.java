package sorter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import models.Participant;
import models.RaceEvent;
import models.Time;

public class SortFinishTime extends Sorter {
    ArrayList<Time> timeList;
    ArrayList<Integer> intList;

    public void insertInfo(String[] filePath, String column, RaceEvent raceEvent)
            throws FileNotFoundException {
        try {

            Iterator[] itr = new Iterator[filePath.length];
            for (int i = 0; i < itr.length; i++) {
                itr[i] = fr.readFileByLine(filePath[i]);
            }
            //Iterator itr = fr.readFileByLine(filePath);
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

    @Override
    protected void addInfo(int columnNbr, Iterator[] itr, RaceEvent raceEvent) {
        timeList = new ArrayList<Time>();
        intList = new ArrayList<Integer>();

        for (int i = 0; i < itr.length; i++) {
            while (itr[i].hasNext()) {
                String line = (String) itr[i].next();
                line = formatString(line);
                String[] lines = line.split(";");
                timeList.add(new Time(lines[columnNbr]));
                intList.add(Integer.parseInt(lines[0]));
            }
        }




            raceEvent.addFinish(new Participant(Integer.parseInt(lines[0])),
                    new Time(lines[columnNbr]));

    }

    private void sortTime() {

    }
}
