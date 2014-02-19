package sorter;

import java.io.FileNotFoundException;
import java.util.Iterator;

import models.RaceEvent;

public abstract class Sorter {

    /**
     * Creates a new sorter
     */
    public Sorter() {}

    /**
     * Finds the correct column, and then inserts it in the raceEvent
     *
     * @param fileIterator  , iterator for the file.
     * @param column    , name of column to sort
     * @param raceEvent , RaceEvent to sort
     * @throws FileNotFoundException
     */
    public void insertInfo(Iterator fileIterator, String column, RaceEvent raceEvent) {

        String columns = (String) fileIterator.next();
        columns = formatString(columns);
        String[] column_list = columns.split(";");
        int columnNbr = 0;
        for (int i = 0; i < column_list.length; i++) {
            if (column_list[i].equals(column)) {
                columnNbr = i;
            }
            addInfo(columnNbr, fileIterator, raceEvent);
        }
    }

    /**
     * Reformats string to remove blankspaces and replace "; " and ", " with ";".
     *
     * @param string , string to be reformatted
     * @return reformatted string
     */
    protected String formatString(String string) {
        while (string.contains("\\s\\s")) {
            string = string.replaceAll("\\s\\s", " ");
        }
        while (string.contains("; ")) {
            string = string.replaceAll("; ", ";");
        }
        return string;

    }

    /**
     * Adds information within the file path to the correct column
     *
     * @param columnNbr the number of the column
     * @param itr       the iterator containing the rows
     * @param raceEvent the raceEvent that gets the information.
     */
    protected abstract void addInfo(int columnNbr, Iterator itr, RaceEvent raceEvent);


}
