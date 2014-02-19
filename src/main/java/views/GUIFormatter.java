package views;

import javax.swing.*;

import controllers.FormatterController;

import java.awt.*;

public class GUIFormatter extends JFrame {
	private static final long serialVersionUID = -2948560310654842046L;

    private LoadStartButton sb;
	private LoadFinishButton fb;
	private LoadNamesButton nb;
    private JTextArea statusText;

    /**
     * Creates a new Formatter GUI
     * @param formCont the formatter Controller
     */
	public GUIFormatter(FormatterController formCont) {
        this.setTitle("Enduro Formatter");
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(new GridLayout(2, 1));
		int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2;
		int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2;
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
        FormatterController formCont1 = formCont;

        JPanel textPanel = new JPanel();
		statusText = new JTextArea();
		textPanel.add(statusText);

        String[] raceTypes = {"SimpleRace", "LapRace", "TimeRace"};
        JComboBox raceType = new JComboBox(raceTypes);
        JTextField limitField = new JTextField();

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));
        sb = new LoadStartButton(statusText);
        fb = new LoadFinishButton(statusText);
        nb = new LoadNamesButton(statusText);
        topPanel.add(sb);
        topPanel.add(fb);
        topPanel.add(nb);
        topPanel.add(new PrintButton(sb, fb, nb, statusText, formCont, raceType, limitField));


		this.add(topPanel);
		this.add(textPanel);

		this.pack();
	}

    /**
     * @return the namebutton
     */
	public JButton getNameButton() {
		return nb;
	}

    /**
     *
     * @return the StartButton
     */
	public JButton getStartButton() {
		return sb;
	}

    /**
     *
     * @return the Finishbutton
     */
	public JButton getFinishButton() {
		return fb;
	}

    /**
     *
     * @return the StatusText
     */
	public JTextArea getStatusText() {
		return statusText;
	}

}
