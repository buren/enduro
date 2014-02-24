package views;

import javax.swing.*;

import controllers.FormatterController;

import java.awt.*;

public class GUIFormatter extends JFrame {
    private static final long serialVersionUID = -2948560310654842046L;

    /**
     * Creates a new Formatter GUI
     *
     * @param formCont the formatter Controller
     */
    public GUIFormatter(FormatterController formCont) {
        this.setTitle("Enduro Formatter");
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridLayout( 1, 2));
        int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2;
        int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);

        JPanel statusTextPanel = new JPanel();
        statusTextPanel.setLayout(new GridLayout(1,1));
        JTextArea statusText = new JTextArea();
        statusText.setEnabled(false);
        statusTextPanel.add(statusText);

        String[] raceTypes = {"Enkelt Race", "Varv-race", "Tids-Race"};
        JComboBox raceType = new JComboBox(raceTypes);
        JTextField limitField = new JTextField();

        JPanel raceOptionsPanel = new JPanel();
        raceOptionsPanel.setLayout(new GridLayout(1, 2));
        raceOptionsPanel.add(raceType);
        raceOptionsPanel.add(limitField);



        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));
        LoadStartButton sb = new LoadStartButton(statusText);
        LoadFinishButton fb = new LoadFinishButton(statusText);
        LoadNamesButton nb = new LoadNamesButton(statusText);
        buttonPanel.add(sb);
        buttonPanel.add(fb);
        buttonPanel.add(nb);
        buttonPanel.add(new PrintButton(sb, fb, nb, statusText, formCont, raceType, limitField));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2, 1));
        leftPanel.add(buttonPanel);
        leftPanel.add(raceOptionsPanel);
        this.add(leftPanel);
        this.add(statusTextPanel);

        this.pack();
    }
}
