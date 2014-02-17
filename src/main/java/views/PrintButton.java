package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import utils.Formatter;

import javax.swing.*;

import controllers.FormatterController;
import utils.FileWriter;

public class PrintButton extends JButton implements ActionListener {

	private LoadStartButton sb;
	private LoadFinishButton fb;
	private LoadNamesButton nb;
	private JTextArea statusText;
	private FormatterController formCont;

    /**
     * Creates a new PrintButton
     * @param s the button text.
     * @param sb a LoadStartButton
     * @param fb a LoadFinishButton
     * @param nb a LoadNamesButton
     * @param statusText the statusText
     * @param formCont the formatterController
     */
	public PrintButton(String s, LoadStartButton sb, LoadFinishButton fb,
			LoadNamesButton nb, JTextArea statusText,
			FormatterController formCont) {
		super(s);
		this.addActionListener(this);
		this.sb = sb;
		this.fb = fb;
		this.nb = nb;
		this.statusText = statusText;
		this.formCont = formCont;
	}

    /**
     * Prints the results to a file
     * @param e
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(this);
		String filePath;
		File f = fc.getSelectedFile();
		if (f != null) {
			filePath = f.getAbsolutePath();
			try {
				String inputValue = JOptionPane
						.showInputDialog("Hur många varvtider önskas?");
				String resultat = formCont.result(sb.getPath(), fb.getPaths(),
						nb.getPath(), Integer.parseInt(inputValue));
				String[] results = resultat.split("\n");
				ArrayList<String> lines = new ArrayList<String>();
				for (String s : results) {
					lines.add(s);
				}
				formCont.writeToFile(filePath, lines.iterator());
				statusText.setText("Resultatfil utskriven!");
			} catch (FileNotFoundException ex) {
                ex.printStackTrace();
				statusText.setText("Fel! En av filerna hittades inte!");
			} catch (NumberFormatException numberFormatex) {
				statusText.append("Fel! Endast siffror tillåtna när du väljer antal varv. \n");
//			} catch (Exception randomException) {
//				statusText.setText(randomException.getMessage());
			}
		}
	}
}
