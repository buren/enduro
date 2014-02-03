package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;

import utils.FileWriter;
import utils.Printer;

public class PrintButton extends JButton implements ActionListener {

	private Printer printer;
	private LoadStartButton sb;
	private LoadFinishButton fb;
	private FileWriter writer;

	public PrintButton(String s, LoadStartButton sb, LoadFinishButton fb) {
		super(s);
		this.addActionListener(this);
		this.sb = sb;
		this.fb = fb;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.showSaveDialog(this);
		String filePath = fc.getSelectedFile().getAbsolutePath();
		try {
			printer = new Printer();
			String resultat = printer.generateResultList(sb.getPath(),
					fb.getPath());

			String[] results = resultat.split("\n");
			ArrayList<String> lines = new ArrayList<String>();
			for (String s : results) {
				lines.add(s);
			}

			System.out.println(resultat);
			writer.writeFile(filePath, lines.iterator());
		} catch (FileNotFoundException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

}
