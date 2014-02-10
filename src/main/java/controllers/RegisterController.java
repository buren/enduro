package controllers;

import utils.FileWriter;

public class RegisterController {
	private String filePath;
	private String allText;

	public RegisterController(String filePath) {
		this.filePath = filePath;
	}

	public String formatResults(String resText, String startNr, String time) {
		String out;
		out = startNr + ";" + time + "\n";
		allText = resText + out;
		writeToFile(allText);
		return out;

	}

	private void writeToFile(String s) {
		FileWriter.writeFile(filePath, allText);
	}
}
