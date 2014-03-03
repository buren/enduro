package views;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class RegisterField extends JTextField  {

	public RegisterField(RegisterButton register) {
		super();
		addKeyListener(register);
	}


}
