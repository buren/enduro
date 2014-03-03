package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

import controllers.FormatterController;
import utils.FileWriter;

public class PrintButton extends JButton implements ActionListener {

	private LoadStartButton sb;
	private LoadFinishButton fb;
	private LoadNamesButton nb;
	private LapTimeLimitButton tb;
	private JTextArea statusText;
	private FormatterController formCont;
	private JComboBox raceType;
	private JTextField limitField;

	/**
	 * Creates a new PrintButton
	 * 
	 * @param sb
	 *            a LoadStartButton
	 * @param fb
	 *            a LoadFinishButton
	 * @param nb
	 *            a LoadNamesButton
	 * @param statusText
	 *            the statusText
	 * @param formCont
	 *            the formatterController
	 * @param tb
	 *            a LaptimeLimitButton
	 */
	public PrintButton(LoadStartButton sb, LoadFinishButton fb,
			LoadNamesButton nb, JTextArea statusText,
			FormatterController formCont, JComboBox raceType,
			JTextField limitField, LapTimeLimitButton tb) {
		super("Spara resultat till fil");
		this.addActionListener(this);
		this.sb = sb;
		this.fb = fb;
		this.nb = nb;
		this.tb = tb;
		this.statusText = statusText;
		this.formCont = formCont;
		this.raceType = raceType;
		this.limitField = limitField;
	}
    /**
     * Prints the results to a file
     *
     * @param e event
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
            	int sortButton = JOptionPane.YES_NO_OPTION;
            	int sortOption = JOptionPane.showConfirmDialog(this, "Vill du sortera?", "Sortera",sortButton);
                String printLimitString = "";
                if(sortOption==1){
                printLimitString = JOptionPane.showInputDialog("Hur många varvtider önskas skrivas ut?");
                }
                int printLimit = Integer.parseInt(printLimitString);
                String limitFieldText = limitField.getText();
                int raceTypeInt = raceType.getSelectedIndex();
                if (limitFieldText.isEmpty() && (raceTypeInt == FormatterController.LAP_RACE
                        || raceTypeInt == FormatterController.LAP_RACE))
                    throw new IllegalArgumentException();
                String resultat = formCont.result(sb.getPath(), fb.getPaths(),
                        nb.getPath(), raceType.getSelectedIndex(), limitFieldText , printLimit, tb.getLimit(), sortOption);  //TODO; snälla gör snyggare
                String[] results = resultat.split("\n");
                ArrayList<String> lines = new ArrayList<String>();
                Collections.addAll(lines, results);
                FileWriter.writeFile(filePath, lines.iterator());
                statusText.setText("Resultatfil utskriven!");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                statusText.setText("Fel! En av filerna hittades inte!");
            } catch (NumberFormatException numberFormatex) {
                statusText.append("Fel! Endast siffror tillåtna när du väljer antal varv. \n");
            } catch (IllegalArgumentException ex) {
                statusText.append("Fel! Ange maxvarv eller maxtid!");
            }
        }
    }
}
