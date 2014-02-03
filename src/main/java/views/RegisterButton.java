package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTextArea;

import utils.Enduro;
import utils.FileWriter;

public class RegisterButton extends JButton implements ActionListener {
	
	private GUIRegister gui;

		public RegisterButton(String s, GUIRegister gui){
			super(s);
			this.gui = gui;
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			gui.printResults();
		}
		
		
}
