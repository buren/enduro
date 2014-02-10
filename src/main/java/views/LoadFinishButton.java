package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoadFinishButton extends JButton implements ActionListener {

	private String malfil;
	private JTextArea statusText;

	public LoadFinishButton(String s, JTextArea statusText) {
		super(s);
		this.addActionListener(this);
		this.statusText = statusText;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		try {
			malfil = fc.getSelectedFile().getAbsolutePath();
			statusText.append("Måltider inläst\n");
		} catch (NullPointerException nullPointer) {
			statusText.append("Ingen fil vald \n");
		}
	}

	public String getPath() {
		return malfil;
	}

}
