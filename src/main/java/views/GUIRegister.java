package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

public class GUIRegister extends JFrame {
	private static final long serialVersionUID = -2948560310654842046L;

	private JPanel panel;
	private JTextArea resultField;
	private JTextArea registerField;
	private RegisterButton registerButton;

	public GUIRegister() {
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
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));

		registerButton = new RegisterButton("Registrera");

		registerButton.setPreferredSize(new Dimension(width / 2, height / 2));

		this.add(panel);
		this.add(resultField);
		panel.add(registerField);
		panel.add(registerButton);

		this.pack();
	}

	public JPanel getPanel(){
		return panel;
	}
	
	public JTextArea getResult(){
		return resultField;
	}
	public JTextArea getRegister(){
		return registerField;
	}
	
	public JButton getRegisterButton(){
		return registerButton;
	}
	
}