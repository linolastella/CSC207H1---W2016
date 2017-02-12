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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * CAT class takes command input and prints out files given in the input
 * 
 * It also has a manual that can be displayed
 */
public class CAT extends Command {

  /**
   * Prints out all file contents or Prints errors
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // cat has at least 2 inputs
    if (commands.get(0).equals("cat") & commands.size() > 1) {
      try {
        printFileContents(commands);
      } catch (IndexOutOfBoundsException e) {
        // the file was not found, print the error message
        printGenericError("A file does not exist");
      }
    } else {
      printGenericError("Invalid command, for help type: man CMD");
    }
  }

  /**
   * Prints out all file contents
   * 
   * NOTE: This method is not compatible with all input. It assumes the given
   * file names are valid. This method is designed to fail cases certain cases
   * in order to trigger error printing in execute.
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  private static void printFileContents(ArrayList commands) {
    // create a string object to add file contents to
    String fileContents = "";
    // find a matching file for each file requested in the input
    for (int inputIndex = 1; inputIndex < commands.size(); inputIndex++) {
      try {
      // set an index to scan the dir contents
      int dirContentsIndex = 0;
      // if no matching file is found
      Dir manipulatedDir = JShell.getWorkingDir();
      while (!manipulatedDir.getDirContents().get(dirContentsIndex).toString()
          .equals(commands.get(inputIndex))) {
        // move to the next file in the contents
        dirContentsIndex++;
      }
      // when the file is found, add it to the string
      fileContents = fileContents
          + ((File) manipulatedDir.getDirContents().get(dirContentsIndex))
              .returnContents()
          + " ";
      } catch (IndexOutOfBoundsException e){
        System.out.println("Parameter " + inputIndex + " is invalid");
      }
      
    }
    // print the file contents
    System.out.println(fileContents);
  }


  /**
   * Prints out the manual for this command
   */
  public static void manual() {
    System.out.println("cat [File1] [File2 ...]: \nPrints out on the shell the "
        + "contents of [File1] and all the other optional files "
        + "[File2 ...].");
  }
}
