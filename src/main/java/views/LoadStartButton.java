package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import utils.Printer;

public class LoadStartButton extends JButton implements ActionListener {
	private String startfil;
	
	
	public LoadStartButton(String s) {
		super(s);
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		startfil = fc.getSelectedFile().getAbsolutePath();

	}
	
	public String getPath() {
		return startfil;
	}

}
