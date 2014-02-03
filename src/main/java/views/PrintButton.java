package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;

import utils.FileWriter;
import utils.Formater;

public class PrintButton extends JButton implements ActionListener {

	private Formater formatter;
	private LoadStartButton sb;
	private LoadFinishButton fb;
	private LoadNamesButton nb;
	private FileWriter writer;

	public PrintButton(String s, LoadStartButton sb, LoadFinishButton fb,
			LoadNamesButton nb) {
		super(s);
		this.addActionListener(this);
		this.sb = sb;
		this.fb = fb;
		this.nb = nb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(this);
		String filePath = fc.getSelectedFile().getAbsolutePath();
		try {
			formatter = new Formater();
			String resultat = formatter.generateResultList(sb.getPath(),
					fb.getPath(), nb.getPath());

			String[] results = resultat.split("\n");
			ArrayList<String> lines = new ArrayList<String>();
			for (String s : results) {
				lines.add(s);
			}

			writer.writeFile(filePath, lines.iterator());
		} catch (FileNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

}
