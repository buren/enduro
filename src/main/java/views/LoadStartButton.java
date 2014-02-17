package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;

public class LoadStartButton extends JButton implements ActionListener {
	private String startfil;
	private JTextArea statusText;

    /**
     * Creates a loadStartbutton
     * @param s the buttontext
     * @param statusText the statusText to print to
     */
	public LoadStartButton(String s, JTextArea statusText) {
		super(s);
		this.addActionListener(this);
		this.statusText = statusText;
	}

    /**
     * Sets the path to the startTimes file
     * @param e
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		try {
			startfil = fc.getSelectedFile().getAbsolutePath();
			statusText.append("Startfil inläst\n");
		} catch (NullPointerException nullPointer) {
			statusText.append("Ingen fil vald \n");
		}
	}

    /**
     * Returns the path to the startTimes file
     * @return the path
     */
	public String getPath() {
		return startfil;
	}
}
