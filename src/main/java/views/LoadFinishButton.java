package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoadFinishButton extends JButton implements ActionListener {

	private String malfil;

	public LoadFinishButton(String s) {
		super(s);
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();

		fc.showOpenDialog(this);
		malfil = fc.getSelectedFile().getAbsolutePath();

	}

	public String getPath() {
		return malfil;
	}

}
