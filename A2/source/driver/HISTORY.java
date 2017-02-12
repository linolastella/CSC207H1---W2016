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
 * HISTORY class takes command input and prints out the requested history
 * 
 * It also has a manual that can be displayed
 */

public class HISTORY extends Command {
  /**
   * Prints the requested history or Prints errors
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // history has two inputs
    if (commands.get(0).equals("history") & commands.size() == 2) {
      try {
        // record the second input to manipulate
        int numberOfCommands = Integer.valueOf((String) commands.get(1));
        // print the last inputs indicated
        while (numberOfCommands != 0) {
          // print the input
          ArrayList mod = JShell.getInputs();
          System.out.println(Integer.toString(mod.size() + 1 - numberOfCommands)
              + ". " + mod.get(mod.size() - numberOfCommands));
          // move to the previous input
          numberOfCommands--;
        }
      } catch (IndexOutOfBoundsException | NumberFormatException e) {
        printGenericError("Invalid command, for help type: man CMD");
      }
    }
    // history is a lone input
    else if (commands.get(0).equals("history") & commands.size() == 1) {
      ArrayList mod = JShell.getInputs();
      // go through every object in the inputs and print them out
      for (int k = 0; k < mod.size(); k++) {
        System.out.println((k + 1) + ". " + mod.get(k));
      }
    } else {
      printGenericError("Invalid command, for help type: man CMD");
    }
  }

  /**
   * Prints out the manual for this command
   */
  public static void manual() {
    System.out.println("history [number]: \nPrints out every command up to "
        + "[number].\nIf no [number] input is given then every command is "
        + "printed.");
  }
}
