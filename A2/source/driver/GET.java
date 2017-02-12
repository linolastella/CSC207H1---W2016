// **********************************************************
// Assignment2:
// Student1: Lino Lastella
// CDF user_name: c5lastel
// UT Student #: 1001237654
// Author: Lino Lastella
//
// Student2: Kevin Schubert
// CDF user_name: g5schube
// UT Student #: 1001549997
// Author: Kevin Schubert
//
// Student3: Kasra Foroughi-Mobarakeh
// CDF user_name: c5foroug
// UT Student #: 1001519635
// Author: Kasra Foroughi-Mobarakeh
//
// Student4: Amogh Viswanath
// CDF user_name: c4viswan
// UT Student #: 1001075196
// Author: Amogh Viswanath
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package driver;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Get class takes a URL which is supplied by the user, retrieves the file at
 * the URL, and adds it to the current working directory
 **/
public class GET extends Command {

  /**
   * Checks to see if URL is available - if so, grab the contents of the URL
   * (.txt or .html) and save it as a new file in the current working directory
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   **/

  public static void execute(ArrayList commands) {
    // Checks to see if syntax for the get command is being followed
    if (commands.get(0).equals("get") & commands.size() == 2) {
      String urlAddress = (String) commands.get(1);
      URL url; // make a URL Object
      try {
        url = new URL(urlAddress);
        readURLContents(url);
      } catch (IOException i) {// Checks to see if the URL is correct
        System.out.println("Invalid URL address: Please enter a new one");
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      printGenericError("Invalid commnad: Please refer to man CMD");
    }
  }

  /**
   * Reads and saves the contents of the URL into a String.
   * 
   * @param url URL object is sent so that it can be read
   * @throws IOException This is an error checked for the url to check if the
   *         contents are available
   **/
  private static void readURLContents(URL url) throws IOException {
    // BuuferReader allows us to read files
    BufferedReader in =
        new BufferedReader(new InputStreamReader(url.openStream()));
    // StringBuilder to turn the contents of the file into a string
    StringBuilder contents = new StringBuilder();
    String lineSeparator = System.getProperty("line.separator");
    // Builds a StringBuilder of the contents of the URL
    try {
      String inputLine;
      while ((inputLine = in.readLine()) != null)
        contents.append(inputLine + lineSeparator);
    } finally {
      in.close();
    }
    // Acquires the file name - whatever ends with .txt or .html
    if (url.getFile().endsWith(".html") || url.getFile().endsWith(".txt")) {
      String checkFileName = url.getFile();
      List<String> fileName =
          new ArrayList<String>(Arrays.asList(checkFileName.split("/")));
      sendURLContents(contents.toString(), fileName.get(fileName.size() - 1));
    }
  }

  /**
   * This method sends the URL contents with the appropriate fileName to the
   * current working directory. If the file already exists, it will overwrite
   * the file contents with the URLContents - or add onto it (depending on
   * >/>>). If the file doesn't exists - it will make a new file in the current
   * working directory with that name
   * 
   * @param URLContents the string which has been acquired from the URL
   * @param fileName the fileName which ends with a .txt or .html
   **/
  private static void sendURLContents(String URLContents, String fileName) {
    int k = 0;
    try {
      while (!JShell.getWorkingDir().getDirContents().get(k).toString()
          .equals(fileName)) {
        k++;
      }
    } catch (IndexOutOfBoundsException e) {
      File a = new File(fileName);
      JShell.getWorkingDir().storeFile(a);
      k = JShell.getWorkingDir().getDirContents().size() - 1;
    }

    ((File) JShell.getWorkingDir().getDirContents().get(k))
        .setContents(URLContents);
  }


  public static void manual() {
    System.out.println("get URL: \nURL is a web address."
        + " Retrieve the file at that URL and add it \nto the current working "
        + "directory.");
  }
}
