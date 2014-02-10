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
			int labAmount = 0;
			formatter = new Formatter();
			try {
				do {
					labAmount = Integer.parseInt(JOptionPane
							.showInputDialog("Mata in antal varv"));
				} while (labAmount == 0);
			} catch (NumberFormatException ex) {
				statusText.setText("Felaktig inmatning! Måste vara en siffra.");
			}
			String resultat = formatter.generateResultList(sb.getPath(),
					fb.getPath(), nb.getPath(), labAmount);

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
