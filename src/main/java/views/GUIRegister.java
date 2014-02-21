package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import controllers.RegisterController;

public class GUIRegister extends JFrame {
    private static final long serialVersionUID = -2948560310654842046L;

    private JPanel panel;
    private JTextArea resultField;
    private JTextField registerField;
    private RegisterButton registerButton;

    /**
     * Creates a new Register GUI
     *
     * @param regCont the register controller
     */
    public GUIRegister(RegisterController regCont) {
        this.setTitle("Enduro");
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridLayout(3, 1));
        int width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2;
        int height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2;
        this.setSize(width, height);
        this.setLocationRelativeTo(null);

        TimeArea timeArea = new TimeArea();
        this.add(timeArea);

        resultField = new JTextArea();
        resultField.setEditable(false);
        registerField = new JTextField();
        registerField.setHorizontalAlignment(JTextField.CENTER);
        Font font1 = new Font("SansSerif", Font.BOLD, 60);
        Font font2 = new Font("SansSerif", Font.BOLD, 16);
        registerField.setFont(font1);
        resultField.setFont(font2);
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        registerButton = new RegisterButton(this, regCont);

        registerButton.setPreferredSize(new Dimension(width / 2, height / 2));

        this.add(panel);
        this.add(resultField);
        panel.add(registerField);
        panel.add(registerButton);

        this.pack();
    }

    /**
     * @return the panel
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * @return the resultField
     */
    public JTextArea getResult() {
        return resultField;
    }

    /**
     * @return the registerField
     */
    public JTextField getRegister() {
        return registerField;
    }

    /**
     * @return the RegisterButton
     */
    public JButton getRegisterButton() {
        return registerButton;
    }

}