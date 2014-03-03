package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class LapTimeLimitButton extends JButton implements ActionListener {
	private JTextArea statusText;
	private String lapTimeLimit;

	/**
	 * Creates a new LapTimeLimitButton
	 * 
	 * @param statusText
	 *            the statusText to print to
	 */
	public LapTimeLimitButton(JTextArea statusText) {
		super("Ändra Varvgräns");
		this.statusText = statusText;
		lapTimeLimit = "00.15.00";
		addActionListener(this);
	}

	/**
	 * Returnes the current lapTimeLimit.
	 * 
	 * @return lapTimeLimit, timeLimit of a lap.
	 */
	public String getLimit() {
		return lapTimeLimit;
	}

	/**
	 * Creates a promt that asks for a new lapTimeLimit to change to.
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		lapTimeLimit = JOptionPane
				.showInputDialog("Vad vill du ska vara den nya tidsgränsen på ett varv? tex hh.mm.ss");
		statusText.append("Varvgräns bytt till " + lapTimeLimit + ".\n");
	}

}
