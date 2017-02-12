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
import java.util.Arrays;
import java.util.List;

/**
 * CD class takes command input and changes the working directory
 * 
 * It also has a manual that can be displayed
 *
 */
public class CD extends Command {
  /**
   * If the path exists navigate to it or Print error messages
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // cd has 2 inputs
    if (commands.get(0).equals("cd") & commands.size() == 2) {
      // navigate the path to the desired dir
      Dir manipulatedDir = navigatePath((String) commands.get(1));
      // if the path exists, change the workingDir
      if (!(manipulatedDir == null)) {
        JShell.setWorkingDir(manipulatedDir);
      }
    } else {
      printGenericError("Invalid command, for help type: man CMD");
    }
  }

  /**
   * Prints out the manual for this command
   */
  public static void manual() {
    System.out.println("cd DIR: \nChange directory"
        + " to DIR(which may be relative" + "or may be a full path)");
  }

}
