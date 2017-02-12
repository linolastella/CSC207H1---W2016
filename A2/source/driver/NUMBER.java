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
 * NUMBER class takes in one input in a particular format ( "![number] ) and
 * executes the command number [number] in the commands history.
 * 
 * Returns an appropriate error message when necessary.
 *
 */

public class NUMBER extends Command {

  /**
   * Executes the appropriate command or print out an error message.
   */
  public static void execute(ArrayList commands) {
    int commandToExecuteIndex = 0;
    // number has only one input.
    if (commands.get(0).equals("number") & commands.size() == 2) {
      try {
        // Get the index of the command the user wants to execute.
        commandToExecuteIndex = Integer.parseInt(commands.get(1).toString());
        // checks to see if number >= 1 and doesn't exceed the size of mod
        if (commandToExecuteIndex >= 1
            & commandToExecuteIndex <= JShell.getInputs().size()) {
          checkCommandHelper(commandToExecuteIndex - 1);
        } else {
          printGenericError("Invalid input: Please refer to man CMD");
        }
      } catch (NumberFormatException e) { // Catches if its not an integer
        printGenericError("Invalid: Please refer to man CMD");
      }
    } else {
      printGenericError("Invalid: Please refer to man CMD");
    }
  }

  private static void checkCommandHelper(int commmandToExecuteIndex) {
    String stringCommand =
        (String) JShell.getInputs().get(commmandToExecuteIndex);
    List<String> commands =
        new ArrayList<String>(Arrays.asList(stringCommand.split(" ")));
    commands = JShell.parseInput((ArrayList) commands);
    JShell.attemptExecute((ArrayList) commands);
  }

  /**
   * Prints out the manual for this command.
   */
  public static void manual() {
    System.out.println("![number]: \nthis command will recall any of previous "
        + "inputs commands from the history and executes it. ");
  }
}
