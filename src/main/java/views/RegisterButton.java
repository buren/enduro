package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controllers.RegisterController;

public class RegisterButton extends JButton implements ActionListener,
		KeyListener {

	RegisterController cont;
	GUIRegister gui;

	/**
	 * Creates a RegisterButton
	 * 
	 * @param cont
	 *            the registerController
	 */
	public RegisterButton(GUIRegister gui, RegisterController cont) {
		super("Registrera");
		this.cont = cont;
		this.gui = gui;
		addActionListener(this);

	}

	/**
	 * Sets a new registration
	 * 
	 * @param e
	 *            event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		action();
	}
	
	private void action() {
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
	
	/**
	 * Looks for enter being pressed, calls void
	 * method 'action'
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {

		int id = arg0.getKeyCode();
		if (id == KeyEvent.VK_ENTER)
			action();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// Do nothing

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

		// Do nothing

	}
}
