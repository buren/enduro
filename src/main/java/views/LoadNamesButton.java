package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoadNamesButton extends JButton implements ActionListener {

	private String namnfil;
	private JTextArea statusText;

	public LoadNamesButton(String s, JTextArea statusText) {
		super(s);
		this.addActionListener(this);
		this.statusText = statusText;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();

		fc.showOpenDialog(this);
		namnfil = fc.getSelectedFile().getAbsolutePath();
		statusText.append("Namnfil inläst\n");

	}

	public String getPath() {
		return namnfil;
	}

}
