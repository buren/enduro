package views;


import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private static final long serialVersionUID = -2948560310654842046L;
    private JPanel buttonPanel;
    private JTextArea textArea;
    private JButton button;


    public GUI(){
        this.setTitle("Enduro");
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        int width =  java.awt.Toolkit.getDefaultToolkit().getScreenSize().width  / 2;
        int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);

        buttonPanel = new JPanel();
        button = new JButton("Example");
        buttonPanel.add(button);
        this.add(buttonPanel, BorderLayout.EAST);

        textArea = new JTextArea("Hello World");
        textArea.setEditable(false);
        this.add(textArea, BorderLayout.CENTER);
    }

}