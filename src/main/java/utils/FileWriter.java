package utils;

import java.io.*;
import java.util.ArrayList;

public class FileWriter {

    public static void writeFile(String relativePath, ArrayList<String> lines) {
        Writer writer = null;
        String absolutePath = Enduro.getInstance().getResourcePath(relativePath);
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(absolutePath), "UTF-8"));
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
