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
 * CAT class takes command input and terminates the program
 * 
 * It also has a manual that can be displayed
 */
public class EXIT extends Command {
  /**
   * Sets the running of JShell to false or prints out errors
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // exit - end the program
    if (commands.get(0).equals("exit") & commands.size() == 1) {
      JShell.setRunning(false);
    } else {
      printGenericError("Invalid command, for help type: man CMD");
    }

  }

  /**
   * Prints out the manual for this command
   */
  public static void manual() {
    System.out.println("exit: \nQuit the program");
  }

}
