package views;

import javax.swing.*;

import java.awt.*;

public class GUI extends JFrame {
	private static final long serialVersionUID = -2948560310654842046L;

	private JPanel topPanel;
	private JTextArea textArea;
	private JButton registerButton;
	private JTextArea startNbrArea;

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

		startNbrArea = new JTextArea("Enter start number");
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		startNbrArea.setFont(font1);
		topPanel = new JPanel();
		this.add(topPanel);
		topPanel.setLayout(new GridLayout(1, 2));
		registerButton = new JButton("Register");
		registerButton.setPreferredSize(new Dimension(width / 2, height / 2));
		topPanel.add(startNbrArea);
		topPanel.add(registerButton);

		textArea = new JTextArea("Hello World");
		textArea.setEditable(false);
		this.add(textArea);

		this.pack();
	}

}