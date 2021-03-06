package utils;

import java.io.*;
import java.util.Iterator;

public class FileWriter {

    /**
     * Writes each line to specified path.
     *
     * @param filePath URL-address of target folder.
     * @param lines    lines to be written to file.
     */
    public static void writeFile(String filePath, Iterator<String> lines) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filePath), "UTF-8"));
            while (lines.hasNext()) {
                writer.write(lines.next() + "\n");
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
