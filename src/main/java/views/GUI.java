package views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI extends JFrame {

	private static final long serialVersionUID = -2948560310654842046L;

	private JPanel topPanel;
	private JTextArea registerArea;
	private JButton registerButton;

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

		topPanel = new JPanel();
		this.add(topPanel);
		topPanel.setLayout(new GridLayout(1, 2));
		registerButton = new JButton("Register");
		registerButton.setPreferredSize(new Dimension(width / 2, height / 2));
		registerButton.addMouseListener(new ClickListener());
		topPanel.add(new TextArea());
		topPanel.add(registerButton);

		registerArea = new JTextArea("Hello World");
		registerArea.setEditable(false);
		this.add(registerArea);
	}
	
	private class ClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}