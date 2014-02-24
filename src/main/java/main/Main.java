package main;

import controllers.FormatterController;
import controllers.RegisterController;
import views.GUIFormatter;
import views.GUIRegister;

import javax.swing.*;
import java.io.File;

public class Main {

    private static final int REGISTER_GUI = 0, FORMATTER_GUI = 1;

    /**
     * Starts the program
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Object[] options = {"Registrering", "Formaterare"};
        int startOption = JOptionPane.showOptionDialog(null, "Välj programdel",
                "Enduro", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (startOption == REGISTER_GUI) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(null);
            File file = fileChooser.getSelectedFile();
            if (file == null) {
                System.exit(0);
            }
            String filePath = file.getAbsolutePath();
            new GUIRegister(new RegisterController(filePath));
        } else if (startOption == FORMATTER_GUI) {
            new GUIFormatter(new FormatterController());
        }
    }
}
