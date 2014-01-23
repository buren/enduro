package utils;

import java.io.*;
import java.util.ArrayList;

public class FileReader {

    public static ArrayList<String> readFile(String filePath) {
        ArrayList<String> fileLines = new ArrayList<String>();
        try {
            FileInputStream fStream = new FileInputStream(filePath);
            DataInputStream in = new DataInputStream(fStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                fileLines.add(line);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLines;
    }

}
