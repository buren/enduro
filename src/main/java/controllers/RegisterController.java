package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import utils.FileWriter;
import views.PreregisterPane;

public class RegisterController {
	private String filePath;

	public RegisterController(String filePath) {
		this.filePath = filePath;
	}

	public String printResults(String regText, String resText) {
		Date date = new Date();
		String out;
		String time = new SimpleDateFormat("HH.mm.ss").format(date);
		if (regText.equals("")) {
			PreregisterPane prp = new PreregisterPane("Förregistrering");
			String s;
			do {
				s = prp.showInputDialog("Förregistrerad id");
				if (s == null) {
					throw new IllegalArgumentException("Avbruten registrering");
				}
			} while (s.equals(""));

			out = s + ";" + time + "\n";
			String allText = resText + out;

			FileWriter.writeFile(filePath, allText);

		} else {
			out = regText + ";" + time + "\n";
			String allText = resText + out;

			FileWriter.writeFile(filePath, allText);

		}
		return out;

	}
}
