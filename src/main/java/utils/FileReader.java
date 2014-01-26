package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FileReader {

    /**
     * Returns a list with each non-empty line for specified file.
     * @param filePath absolute path to file.
     * @return ArrayList<String> with each non-empty line.
     * @throws FileNotFoundException
     */
    public Iterator<String> readFileByLine(String filePath) throws FileNotFoundException {
        ArrayList<String> fileLines = new ArrayList<String>();
        FileInputStream inputStream = new FileInputStream(filePath);
        DataInputStream in = new DataInputStream(inputStream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                if (!this.isBlankString(line)) {
                    fileLines.add(line);
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLines.iterator();
    }


    /**
     * Returns true if string is non-empty.
     * @param str the string to be checked.
     * @return true if string is blank, false otherwise.
     */
    private boolean isBlankString(String str) {
        for (char ch : str.trim().toCharArray()) {
            if (ch != ' ') {
                return false;
            }
        }
        return true;
    }


}
