package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import controllers.FormatterController;

public class ResetButton extends JButton implements ActionListener{
	
	private FormatterController formCont;
	private JTextArea statusText;
	
	public ResetButton(FormatterController formCont, JTextArea statusText){
		super("Återställ");
		this.formCont = formCont;
		this.statusText=statusText;
		addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		formCont.resetRace();
		statusText.setText("Återställd, fyll i nya kriterier.");
	}

}
