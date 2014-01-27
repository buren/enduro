package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

import models.TimeHandler;

public class FileParser {
	
	public TimeHandler parseStartFile(String filePath) throws FileNotFoundException {
		FileReader fileReader = new FileReader();
		Iterator<String> iterator = fileReader.readFileByLine(filePath);
		
		TimeHandler timeHandler = new TimeHandler();
		
		while(iterator.hasNext()) {
			String line = (String) iterator.next();
			line = line.substring(3);
			timeHandler.addStart(line);
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
			timeHandler.addFinish(line);
		}
		return timeHandler;
	}

}
