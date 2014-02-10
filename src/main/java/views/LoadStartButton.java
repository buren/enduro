package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

public class LoadStartButton extends JButton implements ActionListener {
	private String startfil;
	private JTextArea statusText;
	
	public LoadStartButton(String s, JTextArea statusText) {
		super(s);
		this.addActionListener(this);
		this.statusText = statusText;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		startfil = fc.getSelectedFile().getAbsolutePath();
		
		statusText.append("Startfil inläst\n");

	}
	
	public String getPath() {
		return startfil;
	}

}
