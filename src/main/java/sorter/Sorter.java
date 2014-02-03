package sorter;

import java.io.FileNotFoundException;
import java.util.Iterator;

import models.TimeHandler;
import utils.FileReader;

public abstract class Sorter {
	// protected TimeHandler time;
	private FileReader fr;

	public Sorter() {
		fr = new FileReader();
	}

	public void insertInfo(String filePath, String column, TimeHandler time) {
		try {
			Iterator itr = fr.readFileByLine(filePath);
			String columns = (String) itr.next();
			String[] column_list = columns.split("; ");
			int columnNbr = 0;
			for (int i = 0; i < column_list.length; i++) {
				if (column_list[i].equals(column)) {
					columnNbr = i;
				}

			}
			addInfo(columnNbr, itr, time);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	protected abstract void addInfo(int columnNbr, Iterator itr,
			TimeHandler time);

}
