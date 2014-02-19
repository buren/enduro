package utils;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class FileWriter {

	/**
	 * Writes each line to specified path.
	 * 
	 * @param filePath
	 *            URL-address of target folder.
	 * @param lines
	 *            lines to be written to file.
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
	
/**
 * Writes each line to specified path.
 * @param filePath URL-address of target folder.
 * @param string string to be written.
 */

	public static void writeFile(String filePath, String string) {
		Writer writer = null;
		try {
			Scanner scan = new Scanner(string);
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath), "UTF-8"));
			while (scan.hasNext()) {
				writer.write(scan.next() + "\n");
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
