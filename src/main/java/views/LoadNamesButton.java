package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoadNamesButton extends JButton implements ActionListener {

	private String namnfil;

	public LoadNamesButton(String s) {
		super(s);
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();

		fc.showOpenDialog(this);
		namnfil = fc.getSelectedFile().getAbsolutePath();

	}

	public String getPath() {
		return namnfil;
	}

}
