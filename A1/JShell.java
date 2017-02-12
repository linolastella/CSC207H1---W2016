// **********************************************************
// Assignment1:
// Student1: Lino Lastella
// CDF user_name: c5lastel
// UT Student #: 1001237654
// Author:
//
// Student2: Amogh Viswanath
// CDF user_name: c4viswan
// UT Student #: 1001075196
// Author:
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package a1;

import java.util.*;
import java.io.*;

public class JShell {
  private static String errorMessage = "Invalid command, please try again";

  // Create an hashmap storing commands as keys and the respective number of
  // arguments as values
  private static HashMap<String, Integer> commandsToArguments =
      new HashMap<String, Integer>();

  public static void main(String[] args) {

    // Populate the hashmap
    commandsToArguments.put("mkdir", 1);
    commandsToArguments.put("cd", 1);
    commandsToArguments.put("cat", 1);
    commandsToArguments.put("get", 1);
    commandsToArguments.put("ls", 0);
    commandsToArguments.put("exit", 0);
    commandsToArguments.put("pwd", 0);
    commandsToArguments.put("cp", 2);
    commandsToArguments.put("mv", 2);
    commandsToArguments.put("echo", 3);

    // Start prompting the user for a command
    while (true) {
      System.out.print("/# ");
      BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
      try {
        String command = b.readLine();
        String[] arrayOfInputs = command.trim().split("[ ]+");

        // If the user does not input any command, just prompt again
        if (command.trim().equals("")) {
        }

        // If the input is exit, quit the shell
        else {
          if (command.trim().equals("exit")) {
            break;
          }

          else {
            // Check if the input is a valid input and if so perform it,
            // otherwise display an error message
            if (isValidInput(command)) {
              System.out.println(arrayOfInputs[0]);
              for (int i = 1; i < arrayOfInputs.length; i++) {
                System.out.print(arrayOfInputs[i] + " ");
              }
              System.out.print("\n");
            } else {
              System.out.println(errorMessage);
            }
          }
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  // Helper static function. It checks if the input is in the correct format
  private static boolean isValidInput(String input) {

    // Given a string, get an array removing all extra white spaces
    String[] arrayOfInputs = input.trim().split("[ ]+");
    int arguments = arrayOfInputs.length;

    // Treat echo differently
    if (arrayOfInputs[0].equals("echo")) {
      return (input.contains(" > ") || input.contains(" >> "))
          && (arguments == 4)
          && (arrayOfInputs[2].equals(">") || arrayOfInputs[2].equals(">>"));
    } else {

      try {
        int value = commandsToArguments.get(arrayOfInputs[0]);
        return value + 1 == arguments;
      } catch (RuntimeException NullPointerException) {
        return false;
      }
    }
  }
}
