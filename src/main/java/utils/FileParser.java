package utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class FileParser {
	
	public ArrayList<String> parseFile(String filePath) throws FileNotFoundException {
		FileReader fileReader = new FileReader();
		Iterator<String> iterator = fileReader.readFileByLine(filePath);
		
		ArrayList<String> timeHandler = new ArrayList<String>();
		
		while(iterator.hasNext()) {
			String line = (String) iterator.next();
			line = line.substring(3);
			timeHandler.add(line);
		}
		return timeHandler;
	}

}
