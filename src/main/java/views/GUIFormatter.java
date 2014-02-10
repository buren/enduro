package views;

import javax.swing.*;

import java.awt.*;

public class GUIFormatter extends JFrame {
	private static final long serialVersionUID = -2948560310654842046L;

	private JPanel topPanel;
	private LoadStartButton sb;
	private LoadFinishButton fb;
	private LoadNamesButton nb;
	private JPanel textPanel;
	private JTextArea statusText;

	public GUIFormatter() {

		this.setTitle("Enduro Formatter");
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

		textPanel = new JPanel();
		statusText = new JTextArea();
		textPanel.add(statusText);

		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 1));
		sb = new LoadStartButton("Ladda in startfil", statusText);
		fb = new LoadFinishButton("Ladda in målfil", statusText);
		nb = new LoadNamesButton("Ladda in namnfil", statusText);
		topPanel.add(sb);
		topPanel.add(fb);
		topPanel.add(nb);
		topPanel.add(new PrintButton("Spara resultat till fil", sb, fb, nb,
				statusText));

		this.add(topPanel);
		this.add(textPanel);

		this.pack();
	}

	public JButton getNameButton() {
		return nb;
	}

	public JButton getStartButton() {
		return sb;
	}

	public JButton getFinishButton() {
		return fb;
	}

	public JTextArea getStatusText() {
		return statusText;
	}

}
