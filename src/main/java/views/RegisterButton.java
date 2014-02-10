package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
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
		String respons = cont.printResults(gui.getRegister().getText(), gui
				.getResult().getText());
		gui.getResult().append(respons);
		gui.getRegister().setText("");
	}
}
