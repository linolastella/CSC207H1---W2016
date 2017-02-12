package driver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriter {

  public static void writeFile(String content, String fileName) {
    try {
      PrintWriter fileToWrite = new PrintWriter(fileName);
      fileToWrite.println(content);
      fileToWrite.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
