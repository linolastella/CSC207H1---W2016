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
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * All complex commands shall inherit from this class
 * 
 * Command classes take parsed input and execute if appropriate
 */
public class MAN extends Command {
  /**
   * Executes desired function based on input or Prints errors
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // man has 2 inputs
    if (commands.get(0).equals("man") & commands.size() == 2) {
      // check the parameter after to avoid index errors
      if (commands.get(1).equals("CMD")) {
        System.out.println(JShell.getManual());
      }
      // print the error message if invalid
      else {
        try {
          Class<?> x = Class
              .forName("driver." + ((String) commands.get(1)).toUpperCase());
          Method y = x.getMethod("manual", null);
          y.invoke(y, null);
        } catch (NoSuchMethodException | ClassNotFoundException
            | InvocationTargetException | IllegalAccessException e) {
          printGenericError("Invalid command, for help type: man CMD");
        }
      }
    } else {
      printGenericError("Invalid command, for help type: man CMD");
    }
  }

  /**
   * Prints out the manual for this command
   */
  public static void manual() {
    System.out.println("man CMD: " + "\nPrints manual for all commands");
  }

}
