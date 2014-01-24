package utils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class FileReader {

    /**
     * Returns a list with each non-empty line for specified file.
     * @param relativeFilePath absolute path to file.
     * @return ArrayList<String> with each non-empty line.
     * @throws FileNotFoundException
     */
    public ArrayList<String> readFile(String relativeFilePath) throws FileNotFoundException {
        ArrayList<String> fileLines = new ArrayList<String>();
        FileInputStream fStream = new FileInputStream(this.getResourcePath("../") + relativeFilePath);
        DataInputStream in = new DataInputStream(fStream);
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
        return fileLines;
    }

    /**
     * Get path relative to root.
     * @param relativePath the relative path to get.
     * @return the absolute path to resource.
     */
    private String getResourcePath(String relativePath) {
        URL resourceUrl = getClass().getResource(relativePath);
        return resourceUrl.getPath();
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
