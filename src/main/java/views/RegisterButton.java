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

    /**
     * Creates a RegisterButton
     * @param s the buttontext
     * @param gui the GUI
     * @param cont the registerController
     */
	public RegisterButton(String s, GUIRegister gui, RegisterController cont) {
		super(s);
		this.cont = cont;
		this.gui = gui;
		addActionListener(this);
	}

    /**
     * Sets a new registration
     * @param e
     */
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
					if (startNr == null) {
						throw new IllegalArgumentException(
								"Avbruten registrering");
					}
					respons = cont.formatResults(gui.getResult().getText(),
							startNr, time);
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
