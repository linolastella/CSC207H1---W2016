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
 * POPD class takes command input and returns the user to their previous saved
 * directory
 * 
 * It also has a manual that can be displayed
 */
public class POPD extends Command {
  /**
   * Changes JSHells workingDir to the previously saved directory in dirHistory
   * or Prints errors
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // popd has only one input
    if (commands.get(0).equals("popd") & commands.size() == 1) {
      // if the dir history is empty print a message to indicate so
      if (JShell.getDirHistory().size() == 0) {
        printGenericError("Directory history is empty");
      }
      // if the dir history contains a dir
      else {
        // change the working dir
        ArrayList mod = JShell.getDirHistory();
        JShell.setWorkingDir((Dir) mod.get(mod.size() - 1));
        // remove the dir from the history
        mod.remove(mod.size() - 1);
        JShell.setDirHistory(mod);
      }
    } else {
      printGenericError("Invalid command, for help type: man CMD");
    }
  }

  /**
   * Prints out the manual for this command
   */
  public static void manual() {
    System.out.println("popd: \nRemoves the previous working directory and "
        + "changes the current working directory to it.");
  }
}
