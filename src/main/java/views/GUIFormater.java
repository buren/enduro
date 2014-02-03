package views;

import javax.swing.*;

import java.awt.*;

public class GUIFormater extends JFrame {
	private static final long serialVersionUID = -2948560310654842046L;

	private JPanel topPanel;
	private LoadStartButton sb;
	private LoadFinishButton fb;

	public GUIFormater() {
		this.setTitle("Enduro Formater");
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(new GridLayout(2, 1));
		int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2;
		int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2;
		this.setSize(width, height);
		this.setLocationRelativeTo(null);

		Font font1 = new Font("SansSerif", Font.BOLD, 60);
		Font font2 = new Font("SansSerif", Font.BOLD, 16);

		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1, 3));
		sb = new LoadStartButton("Ladda in startfil");
		fb = new LoadFinishButton("Ladda in målfil");
		topPanel.add(sb);
		topPanel.add(fb);
		topPanel.add(new PrintButton("Spara resultat till fil", sb, fb));

		this.add(topPanel);

		this.pack();
	}

}
