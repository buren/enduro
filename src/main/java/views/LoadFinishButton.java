package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class LoadFinishButton extends JButton implements ActionListener {

    private ArrayList<String> malfiler;
    private JTextArea statusText;

    /**
     * Creates a loadFinishbutton
     *
     * @param statusText the statusText to print to
     */
    public LoadFinishButton(JTextArea statusText) {
        super("Ladda in målfil");
        this.addActionListener(this);
        this.statusText = statusText;
        malfiler = new ArrayList<>();
    }

    /**
     * Loads a finishTimes file
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        try {
            malfiler.add(fc.getSelectedFile().getAbsolutePath());
            statusText.append("Måltider " + malfiler.size() + " inläst\n");
        } catch (NullPointerException nullPointer) {
            statusText.append("Ingen fil vald \n");
        }
    }

    /**
     * @return paths to the finishTime files
     */
    public String[] getPaths() {
        String[] paths = new String[malfiler.size()];
        malfiler.toArray(paths);
        return paths;
    }

}
