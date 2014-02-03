package views;

import javax.swing.*;

import utils.Enduro;
import utils.FileWriter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GUIRegister extends JFrame {
	private static final long serialVersionUID = -2948560310654842046L;

	private JPanel topPanel;
	private JTextArea resultField;
	private RegisterButton registerButton;
	private JTextArea registerField;
	private String filePath;

	public GUIRegister(String filePath) {
		this.filePath = filePath;
		this.setTitle("Enduro");
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(new GridLayout(2, 1));
		int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2;
		int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2;
		this.setSize(width, height);
		this.setLocationRelativeTo(null);

		resultField = new JTextArea();
		resultField.setEditable(false);
		registerField = new JTextArea();
		Font font1 = new Font("SansSerif", Font.BOLD, 60);
		Font font2 = new Font("SansSerif", Font.BOLD, 16);
		registerField.setFont(font1);
		resultField.setFont(font2);
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 2));

		registerButton = new RegisterButton("Registrera", this);

		registerButton.setPreferredSize(new Dimension(width / 2, height / 2));

		this.add(topPanel);
		this.add(resultField);
		topPanel.add(registerField);
		topPanel.add(registerButton);

		this.pack();
	}

	/**
	 * Prints the resultField to a .txt file and updates the registerField
	 * 
	 */
	public void printResults() {
		Date date = new Date();
		try {
			if (registerField.getText().equals("")) {
				PreregisterPane prp = new PreregisterPane("Förregistrering");
				String s = prp.showInputDialog("Förregistrerad id");
				String time = new SimpleDateFormat("HH.mm.ss").format(date);
				String out = s + ";" + time + "\n";
				String allText = resultField.getText() + out;

				FileWriter.writeFile(filePath, allText);

				resultField.append(out);
				registerField.setText("");

			} else {
				String time = new SimpleDateFormat("HH.mm.ss").format(date);
				String out = registerField.getText() + ";" + time + "\n";
				String allText = resultField.getText() + out;

				FileWriter.writeFile(filePath, allText);

				resultField.append(out);
				registerField.setText("");
			}
		} catch (NumberFormatException e) {
			registerField.setText("");
		}

	}

}