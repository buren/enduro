package controllers;

import java.io.FileNotFoundException;
import java.util.Iterator;

import utils.FileWriter;
import utils.Formatter;

public class FormatterController {

	private Formatter formatter;
	
	public FormatterController() {
		formatter = new Formatter();
	}
	
	
	
	
	
	public void writeToFile(String filePath, Iterator<String> iter) {
		FileWriter.writeFile(filePath, iter);
	}
	
	public String result (String startPath, String finishPath, String namePath, int nLaps) throws FileNotFoundException {
	
		return formatter.generateResultList(startPath, finishPath, namePath, nLaps);
		
		
	}
	
	
}
