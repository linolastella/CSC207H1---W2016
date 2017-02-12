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
 * PUSHD class takes command input and changes the working directory and saves
 * the previous Dir in a history record
 * 
 * It also has a manual that can be displayed
 *
 */
public class PUSHD extends Command {
  /**
   * If the path exists navigate to it and save the previous Dir in the
   * dirHistory or Print error messages
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // pushd has 2 inputs
    if (commands.get(0).equals("pushd") & commands.size() == 2) {
      // navigate the path to the desired dir
      Dir manipulatedDir = navigatePath((String) commands.get(1));
      // if the path exists, change the workingDir and save the old dir in
      // the dirHistory
      if (!(manipulatedDir == null)) {
        ArrayList mod = JShell.getDirHistory();
        mod.add(JShell.getWorkingDir());
        JShell.setDirHistory(mod);
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
    System.out.println("pushd DIR: \nSaves the current"
        + "working directory, and changes teh new cuurent" + "\nworking "
        + "directory to DIR");
  }

}
