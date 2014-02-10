package controllers;

import utils.FileWriter;

public class RegisterController {
	private String filePath;
	private String allText;

	public RegisterController(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 
	 * @param resText, String representation of text contained within the resultfield of the GUI.
	 * @param startNr, String representation of the start number of the participant to be registered.
	 * @param time, String representation of the time to register.
	 * @return
	 */
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
