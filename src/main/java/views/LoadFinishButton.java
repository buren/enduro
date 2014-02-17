package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class LoadFinishButton extends JButton implements ActionListener {

    private ArrayList<String> malfiler;
    private JTextArea statusText;

    public LoadFinishButton(String s, JTextArea statusText) {
        super(s);
        this.addActionListener(this);
        this.statusText = statusText;
        malfiler = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        try {
            malfiler.add(fc.getSelectedFile().getAbsolutePath());
            statusText.append("Måltider "+malfiler.size()+1+" inläst\n");
        } catch (NullPointerException nullPointer) {
            statusText.append("Ingen fil vald \n");
        }
    }

    public String[] getPaths() {
        String[] paths = new String[malfiler.size()];
        malfiler.toArray(paths);
        return paths;
    }

}
