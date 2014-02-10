package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controllers.RegisterController;

public class RegisterButton extends JButton implements ActionListener {

	RegisterController cont;
	GUIRegister gui;

	public RegisterButton(String s, GUIRegister gui, RegisterController cont) {
		super(s);
		this.cont = cont;
		this.gui = gui;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PreregisterPane prp = new PreregisterPane("Förregistrering");
		String startNr;
		String respons;
		Date date = new Date();
		String time = new SimpleDateFormat("HH.mm.ss").format(date);
		try {
			if (gui.getRegister().getText().equals("")) {
				do {
					startNr = JOptionPane.showInputDialog("Förregistrerad id");
					respons = cont.formatResults(gui.getResult().getText(),
							startNr, time);

					if (startNr == null) {
						throw new IllegalArgumentException(
								"Avbruten registrering");
					}
				} while (startNr.equals(""));

			} else {
				respons = cont.formatResults(gui.getResult().getText(), gui
						.getRegister().getText(), time);
			}
			gui.getResult().append(respons);
			gui.getRegister().setText("");
		} catch (IllegalArgumentException ex) {

		}
	}
}
