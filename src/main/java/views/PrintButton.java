package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import utils.Formatter;

import javax.swing.*;

import utils.FileWriter;

public class PrintButton extends JButton implements ActionListener {

	private Formatter formatter;
	private LoadStartButton sb;
	private LoadFinishButton fb;
	private LoadNamesButton nb;
	private FileWriter writer;
	private JTextArea statusText;

	public PrintButton(String s, LoadStartButton sb, LoadFinishButton fb,
			LoadNamesButton nb, JTextArea statusText) {
		super(s);
		this.addActionListener(this);
		this.sb = sb;
		this.fb = fb;
		this.nb = nb;
		this.statusText = statusText;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(this);
		String filePath = fc.getSelectedFile().getAbsolutePath();
		
		try {
			formatter = new Formatter();
			String resultat = formatter.generateResultList(sb.getPath(),
					fb.getPath(), nb.getPath());

			String[] results = resultat.split("\n");
			ArrayList<String> lines = new ArrayList<String>();
			for (String s : results) {
				lines.add(s);
			}
			writer.writeFile(filePath, lines.iterator());
			
			statusText.setText("Resultatfil utskriven!");
		} catch (FileNotFoundException ex) {
			statusText.setText("Fel! En av filerna hittades inte!");
		} catch (Exception randomException) {
			statusText.setText("Fel! Resultatfil ej utskriven!");
			randomException.printStackTrace();
		}
	}

}
