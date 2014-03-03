package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class LoadStartButton extends JButton implements ActionListener {
    private ArrayList<String> startfiler;
    private JTextArea statusText;

    /**
     * Creates a loadStartbutton
     *
     * @param statusText the statusText to print to
     */
    public LoadStartButton(JTextArea statusText) {
        super("Ladda in startfil");
        this.addActionListener(this);
        startfiler = new ArrayList<String>();
        this.statusText = statusText;
    }

    /**
     * Sets the path to the startTimes file
     *
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        try {
            startfiler.add(fc.getSelectedFile().getAbsolutePath());
            statusText.append("Startfil inläst\n");
        } catch (NullPointerException nullPointer) {
            statusText.append("Ingen fil vald \n");
        }
    }

    /**
     * Returns the path to the startTimes file
     *
     * @return the path
     */
    public String[] getPath() {
        String[] paths = new String[startfiler.size()];
        startfiler.toArray(paths);
        return paths;
    }
}
