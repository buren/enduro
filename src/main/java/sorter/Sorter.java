package sorter;

import java.io.FileNotFoundException;
import java.util.Iterator;

import models.RaceEvent;
import utils.FileReader;

public abstract class Sorter {
	protected FileReader fr;

	public Sorter() {
		fr = new FileReader();
	}
	
	/**
	 * 
	 * @param filePath , URL-address of target folder.
	 * @param column , name of column to sort
	 * @param raceEvent , RaceEvent to sort
	 * @throws FileNotFoundException
	 */

	public void insertInfo(String filePath, String column, RaceEvent raceEvent)
			throws FileNotFoundException {
		try {
			Iterator itr = fr.readFileByLine(filePath);
			String columns = (String) itr.next();
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
	
	/**
	 * reformats string to remove blankspaces and replace "; " and ", " with ";".
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
	 * adds information within the file path to the correct column
	 * @param columnNbr
	 * @param itr
	 * @param time
	 */

	protected abstract void addInfo(int columnNbr, Iterator<String> itr,
			RaceEvent time);

}
