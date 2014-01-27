package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class RegisterButton extends JButton implements ActionListener {
	
	private JTextArea regField;
	private JTextArea resField;

		public RegisterButton(String s, JTextArea regField, JTextArea resField){
			super(s);
			this.regField=regField;
			this.resField=resField;
			this.addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			Date date = new Date();
//			System.out.println(regField.getText());
			try {
				Integer.parseInt(regField.getText());
				 String time = new SimpleDateFormat("HH:mm:ss").format(date);
				 resField.append(regField.getText()+"	"+time+"\n");
				 regField.setText("");
	
			}
			catch (NumberFormatException e) {
				regField.setText("");
			}
			
		
		}
		
		
}
