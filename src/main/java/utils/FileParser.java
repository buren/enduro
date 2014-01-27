package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import models.Participant;
import models.TimeHandler;

public class FileParser {
	
	public TimeHandler parseStartFile(String filePath) throws FileNotFoundException {

		TimeHandler timeHandler = new TimeHandler();
		
		Scanner scan = new Scanner(new File(filePath));
		while (scan.hasNext()) {
			String line = scan.nextLine();
			String strings[] = line.split("; ");
			timeHandler.addStart((new Participant(strings[0])), strings[1]);
		}
		return timeHandler;
	}
	
	public TimeHandler parseFinishFile(String filePath) throws FileNotFoundException {

		TimeHandler timeHandler = new TimeHandler();
		
		Scanner scan = new Scanner(new File(filePath));
		while (scan.hasNext()) {
			String line = scan.nextLine();
			String strings[] = line.split("; ");
			timeHandler.addFinish((new Participant(strings[0])), strings[1]);
		}
		return timeHandler;
	}

}
