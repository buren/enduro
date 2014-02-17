package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class LoadNamesButton extends JButton implements ActionListener {

	private String namnfil;
	private JTextArea statusText;

    /**
     * Creates a loadNamesbutton
     * @param s the buttontext
     * @param statusText the statusText to print to
     */
	public LoadNamesButton(String s, JTextArea statusText) {
		super(s);
		this.addActionListener(this);
		this.statusText = statusText;
	}

    /**
     * Sets the path to the name file
     * @param e
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		try {
			namnfil = fc.getSelectedFile().getAbsolutePath();
			statusText.append("Namnfil inläst\n");
		} catch (NullPointerException nullPointer) {
			statusText.append("Ingen fil vald \n");
		}
	}

    /**
     * Gets the path to the nameFile
     * @return path to nameFile
     */
	public String getPath() {
		return namnfil;
	}
}
