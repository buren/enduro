package main;

import controllers.FormatterController;
import controllers.RegisterController;
import views.GUIFormatter;
import views.GUIRegister;

import javax.swing.*;
import java.io.File;

public class Main {

    /**
     * Starts the program
     * @param args  command line arguments
     */
	public static void main(String[] args) {
		String s;
		JFrame frame = new JFrame();
		Object[] options = { "Registrering", "Formaterare" };
		int n = JOptionPane.showOptionDialog(frame, "Välj programdel",
				"Enduro", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (n == 0) {
			File f;
			String filePath;
			JFileChooser fc = new JFileChooser();
			fc.showSaveDialog(null);
			f = fc.getSelectedFile();
			if (f == null) {
				System.exit(0);
			}
			filePath = f.getAbsolutePath();
			RegisterController regCont = new RegisterController(filePath);
			GUIRegister reg = new GUIRegister(regCont);
		} else if (n == 1) {

			Object[] options2 = { "Varv-lopp", "Tids-lopp" };
			n = JOptionPane.showOptionDialog(frame, "Välj racetyp", "Enduro",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options2, options2[0]);
			if (n == 0) {
				do {
					s = JOptionPane
							.showInputDialog("Hur många varv ingår i loppet?");
				} while (s != null && s.equals(""));
				if (s != null) {
					FormatterController formCont = new FormatterController(Integer.parseInt(s));
					new GUIFormatter(formCont);

				}
			}
			if(n == 1) {
				do {
					s = JOptionPane
							.showInputDialog("Hur lång tid pågår loppet? (hh.mm.ss) ");
				} while (s != null && s.equals(""));
				if (s != null) {
					FormatterController formCont = new FormatterController(s);
					new GUIFormatter(formCont);
				}
			}
		}
	}
}
