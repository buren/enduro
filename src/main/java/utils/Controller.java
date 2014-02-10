package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import views.GUIFormatter;
import views.GUIRegister;
import views.PreregisterPane;
import models.RaceEvent;

public class Controller {

	private RaceEvent raceEvent;
	private GUIRegister reg;
	private String filePath;

	public Controller(RaceEvent raceEvent, GUIRegister GUIregister,
			String filePath) {
		this.raceEvent = raceEvent;
		this.reg = GUIregister;
		this.filePath = filePath;
	}

	public void initiate() {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					printResults();
				} catch (IllegalArgumentException e) {

				}
			}
		};
		reg.getRegisterButton().addActionListener(actionListener);
	}

	private void printResults() {
		Date date = new Date();
		String out;
		String time = new SimpleDateFormat("HH.mm.ss").format(date);
		try {
			if (reg.getRegister().getText().equals("")) {
				PreregisterPane prp = new PreregisterPane("Förregistrering");
				String s;
				do {
					s = prp.showInputDialog("Förregistrerad id");
					if (s == null) {
						throw new IllegalArgumentException(
								"Avbruten registrering");
					}
				} while (s.equals(""));

				out = s + ";" + time + "\n";
				String allText = reg.getResult().getText() + out;

				FileWriter.writeFile(filePath, allText);

			} else {
				out = reg.getRegister().getText() + ";" + time + "\n";
				String allText = reg.getResult().getText() + out;

				FileWriter.writeFile(filePath, allText);

			}
			String y = reg.getResult().getText() + out;
			reg.getResult().setText(y);
			reg.getRegister().setText("");
		} catch (NumberFormatException e) {
			reg.getRegister().setText("");
		}

	}

}
