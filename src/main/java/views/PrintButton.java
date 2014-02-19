package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

import controllers.FormatterController;

public class PrintButton extends JButton implements ActionListener {

	private LoadStartButton sb;
	private LoadFinishButton fb;
	private LoadNamesButton nb;
	private JTextArea statusText;
	private FormatterController formCont;
    private JComboBox raceType;
    JTextField limitField;

    /**
     * Creates a new PrintButton
     * @param s the button text.
     * @param sb a LoadStartButton
     * @param fb a LoadFinishButton
     * @param nb a LoadNamesButton
     * @param statusText the statusText
     * @param formCont the formatterController
     */
	public PrintButton(LoadStartButton sb, LoadFinishButton fb,
			LoadNamesButton nb, JTextArea statusText,
			FormatterController formCont, JComboBox raceType, JTextField limitField) {
		super("Spara resultat till fil");
		this.addActionListener(this);
		this.sb = sb;
		this.fb = fb;
		this.nb = nb;
		this.statusText = statusText;
		this.formCont = formCont;
        this.raceType = raceType;
        this.limitField = limitField;
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
						nb.getPath(),raceType.getSelectedIndex(), limitField.getText(), Integer.parseInt(inputValue));  //TODO; fix shit
				String[] results = resultat.split("\n");
				ArrayList<String> lines = new ArrayList<>();
                Collections.addAll(lines, results);
				formCont.writeToFile(filePath, lines.iterator());
				statusText.setText("Resultatfil utskriven!");
			} catch (FileNotFoundException ex) {
                ex.printStackTrace();
				statusText.setText("Fel! En av filerna hittades inte!");
			} catch (NumberFormatException numberFormatex) {
				statusText.append("Fel! Endast siffror tillåtna när du väljer antal varv. \n");
			}
		}
	}
}
