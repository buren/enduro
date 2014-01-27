package utils;

import models.Participant;
import models.TimeHandler;

import java.io.FileNotFoundException;
import java.util.Iterator;

public class FileParser {
	
	public TimeHandler parseStartFile(String filePath) throws FileNotFoundException {

		FileReader fileReader = new FileReader();
		Iterator<String> iterator = fileReader.readFileByLine(filePath);

		TimeHandler timeHandler = new TimeHandler();

		while (iterator.hasNext()) {
			String line = (String) iterator.next();
			line = line.substring(3);
			timeHandler.addStart(new Participant(" "), " ");
		}
		return timeHandler;
	}
	
	public TimeHandler parseFinishFile(String filePath) throws FileNotFoundException {
		FileReader fileReader = new FileReader();
		Iterator<String> iterator = fileReader.readFileByLine(filePath);
		
		TimeHandler timeHandler = new TimeHandler();
		while(iterator.hasNext()) {
			String line = (String) iterator.next();
			line = line.substring(3);
			timeHandler.addFinish(new Participant(" "), " ");
		}
		return timeHandler;
	}

}
