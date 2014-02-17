package main;

import java.io.File;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import utils.Enduro;
import views.GUIFormatter;
import views.GUIRegister;
import controllers.FormatterController;
import controllers.RegisterController;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		Object[] options = { "Registrering", "Formaterare" };
		int n = JOptionPane.showOptionDialog(frame, "Välj programdel",
				"Enduro", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		System.out.println(n);
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
			FormatterController formCont = new FormatterController();
			new GUIFormatter(formCont);
		}

	}
}
