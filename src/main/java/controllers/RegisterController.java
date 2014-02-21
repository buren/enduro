package controllers;

import utils.FileWriter;

import java.util.ArrayList;
import java.util.Collections;

public class RegisterController {
    private String filePath;

    /**
     * @param filePath , URL-address of target folder.
     */
    public RegisterController(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Changes the format of the strings and also writes the latest return value
     * to file along with all previous values generated by this class instance.
     *
     * @param resText , String representation of text contained within the
     *                resultfield of the GUI.
     * @param startNr , String representation of the start number of the participant
     *                to be registered.
     * @param time    , String representation of the time to register.
     * @return three parameters in the following format: startNr;time\n
     */
    public String formatResults(String resText, String startNr, String time) {
        String out = "";
        StringBuilder sb = new StringBuilder();
        String[] startNumbers = startNr.split(",");
        for (String startNumber : startNumbers) {
            startNr = startNumber;
            startNr = startNr.trim();
            out = startNr + ";" + time + "\n";
            sb.append(out);
        }
        String registeredText = resText + out;
        writeToFile(registeredText);
        return sb.toString();
    }

    private void writeToFile(String registeredText) {
        String[] results = registeredText.split("\n");
        ArrayList<String> lines = new ArrayList<>();
        Collections.addAll(lines, results);
        FileWriter.writeFile(filePath, lines.iterator());
    }
}
