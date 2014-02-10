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
		try {
			String filePath = fc.getSelectedFile().getAbsolutePath();
			String inputValue = JOptionPane
					.showInputDialog("Hur många varvtider önskas?");
			formatter = new Formatter();
			String resultat = formatter.generateResultList(sb.getPath(),
					fb.getPath(), nb.getPath(), Integer.parseInt(inputValue));

			String[] results = resultat.split("\n");
			ArrayList<String> lines = new ArrayList<String>();
			for (String s : results) {
				lines.add(s);
			}
			writer.writeFile(filePath, lines.iterator());
			statusText.setText("Resultatfil utskriven!");
		} catch (FileNotFoundException ex) {
			statusText.setText("Fel! En av filerna hittades inte!");
		} catch (NullPointerException nullPointer) {
			statusText.append("Ingen fil valdes.");
		} catch (NumberFormatException numberFormatex) {
			statusText.append("Fel! Endast siffror tillåtna när du väljer antal varv. \n");
		} catch (Exception randomException) {
			statusText.setText("Fel! Resultatfil ej utskriven!");
			randomException.printStackTrace();
		}
	}
}
