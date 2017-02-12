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
 * ECHO class takes command input and prints it out or stores it in a file
 * 
 * It also has a manual that can be displayed
 */
public class ECHO extends Command {

  /**
   * If a string is given alone it is printed or If a string and file is given
   * with a > parameter the file is overwritten or If a string and file is given
   * with a >> parameter the file is appended or Prints errors
   * 
   * Also, if the requested file does not exist, it is made
   * 
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // echo can have 2 inputs
    if (commands.get(0).equals("echo")
        & ((commands.size() == 2) | (commands.size() == 4))) {
      // set the print parameters
      ArrayList parameters = outFileParameters(commands);
      // print or store the input
      specialPrint((String) commands.get(1), parameters);
      // adjust intergace according to execution
      if (!((boolean) parameters.get(0))) {
        System.out.println();
      }

    } else {
      printGenericError("Invalid command, for help type: man CMD");
    }
  }


  /**
   * Prints out the manual for this command
   */
  public static void manual() {
    System.out.println("echo STRING: \nPrint STRING.");
  }
}
