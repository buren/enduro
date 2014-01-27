package views;

import javax.swing.*;

import java.awt.*;

public class GUI extends JFrame {
	private static final long serialVersionUID = -2948560310654842046L;

	private JPanel topPanel;
	private JTextArea resultField;
	private RegisterButton registerButton;
	private JTextArea registerField;

	public GUI() {
		this.setTitle("Enduro");
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(new GridLayout(2, 1));
		int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2;
		int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2;
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		
		registerField = new JTextArea();
		resultField = new JTextArea();
		resultField.setEditable(false);
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 2));
		registerButton = new RegisterButton("Register", registerField, resultField);
		registerButton.setPreferredSize(new Dimension(width / 2, height / 2));
		
		this.add(topPanel);
		this.add(resultField);
		topPanel.add(registerField);
		topPanel.add(registerButton);


	}

}