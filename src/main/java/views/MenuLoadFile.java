package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import utils.Formatter;

public class MenuLoadFile extends JMenuItem implements ActionListener {

	private Formatter formatter;

	public MenuLoadFile(String flag) {
		super(flag);
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		String startfil = fc.getSelectedFile().getAbsolutePath();
		fc.showOpenDialog(this);
		String malfil = fc.getSelectedFile().getAbsolutePath();
		try {
			formatter = new Formatter();
			String resultat = formatter.generateResultList(startfil, malfil);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
