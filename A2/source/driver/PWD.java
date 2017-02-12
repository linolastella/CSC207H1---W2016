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

import java.util.ArrayList;

/**
 * PWD class takes command input and prints out the working directory path
 * 
 * It also has a manual that can be displayed
 *
 */
public class PWD extends Command {
  /**
   * Prints the path for the working directory or Prints errors
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // check if the parameters are correct
    if (commands.get(0).equals("pwd") & commands.size() == 1) {
      // obtain a copy of the working dir to manipulate
      Dir manipulatedDir = JShell.getWorkingDir();
      // create a string to add paths to
      String workingDirPath = "";
      // if you are already in the MASTER
      if (manipulatedDir.getParent() == null){
        workingDirPath = "/";
      }
      // until the master directory is reached add up the dirs
      while (!(manipulatedDir.getParent() == null)) {
        // add the dir to the path and move to the parent
        workingDirPath = "/" + manipulatedDir.toString() + workingDirPath;
        manipulatedDir = manipulatedDir.getParent();
      }
      // print the path
      System.out.println(workingDirPath);
    }
    // prints the error if incorrect
    else {
      printGenericError("Invalid command, for help type: man CMD");
    }
  }


  /**
   * Prints out the manual for this command
   */
  public static void manual() {
    System.out.println(
        "pwd: \nPrint the current working" + "directory (and all its paths)");

  }
}
