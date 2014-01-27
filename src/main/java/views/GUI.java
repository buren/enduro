package views;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
	private static final long serialVersionUID = -2948560310654842046L;
	private JPanel buttonPanel;
	private JButton button;
	private JTextArea startNumberArea;
	private JPanel topPanel;

	public GUI() {
		this.setTitle("Enduro");
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2;
		int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2;
		this.setSize(width, height);
		this.setLocationRelativeTo(null);

		buttonPanel = new JPanel();
		button = new JButton("Example");
		buttonPanel.add(button);
		this.add(buttonPanel, BorderLayout.EAST);

		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		startNumberArea = new JTextArea("Enter start number");
		startNumberArea.setEditable(true);
		topPanel.add(startNumberArea, BorderLayout.WEST);
		this.add(topPanel, BorderLayout.NORTH);
	}

}