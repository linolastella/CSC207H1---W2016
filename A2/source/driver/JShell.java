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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * JShell class runs the interface as well as parses and analyzes user input to
 * conduct commands accordingly
 * 
 */
public class JShell {
  // workingDir tracks the dir the user is in
  private static Dir workingDir = Dir.createMasterDir();
  // manual is just a string that explains the program to the user
  public static String manual = "exit: \nQuit the program\n\nmkdir DIR: "
      + "\nCreate directories, each of which may be relative"
      + "\nto the current directory or may be a full path."
      + "\n\ncd DIR: \nChange directory to DIR(which may be relative"
      + "\nor may be a full path)\n\nls [-R] [PATH]..:\nIf [-R] is present,"
      + " recursively list all subdirectories.\nIf no paths are given,"
      + "prints the contents(file or directories) of the"
      + "\ncurrent directory. Otherwise, for PATH, prints the contents\n"
      + "(file or directories).\n\npwd: \nPrint the current working"
      + "directory (and all its paths) \n\nmv OLDPATH NEWPATH: \nMove item "
      + "OLDPATH to NEWPATH. Both OLDPATH and NEWPATH may be relative to\nthe "
      + "current directory or may be full paths. If NEWPATH is a directory,\n"
      + "move the item into the directory.\n\ncp OLDPATH NEWPATH: \nLike mv, "
      + "but don't remove OLDPATH. \nIf OLDPATH is a directory, "
      + "copy the contents.\n\npushd DIR: \nSaves the current"
      + "working directory, and changes teh new cuurent" + "\nworking "
      + "directory to DIR\n\npopd: \nRemove the top most directory"
      + "and makes it the current working directory"
      + "\n\nhistory[number]: \nPrints out recent commands(up to number)"
      + "\n\ncat FILE1 [FILE2..]: \nDisplay the contents of FILE1 and other "
      + "files [File2..] in the shell. \n\nget URL: \nURL is a web address."
      + " Retrieve the file at that URL and add it \nto the current working "
      + "directory. \n\necho STRING:" + " \nPrint String \n\n!NUMBER: \nThis "
      + "command will recall and execute the command associated with the \n"
      + "command number NUMBER of the history.\n\ngrep [-R] REGEX PATH ... \n"
      + "If [-R] is not supplied, print any lines containing REGEX in PATH, which"
      + "\nmust be a file. If [-R] is supplied, and PATH is a directory, "
      + "recursively \ntraverse the directory and, for all lines in all files "
      + "that contain REGEX, \nprint the path to the file (including the "
      + "filename), then a colon, \nthen the line that contained REGEX. ";


  // inputs is a history that records all valid commands given
  private static ArrayList inputs = new ArrayList();

  // dirHistory is a history that records navigated dirs
  private static ArrayList dirHistory = new ArrayList();

  // running indicates if the program is still running
  private static boolean running = true;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // running indicates if the program is running
    boolean running = true;
    while (JShell.isRunning()) {
      // Every line starts with /#
      System.out.print("#/ ");
      String input = in.nextLine();
      // if the inputs cancels a given newline, replace the //n with /n
      input = input.replaceAll("\\\\n", "\n");
      // add the input to the input history
      inputs.add(input);
      // convert the input into an array list spliced at white spaces
      List<String> commands =
          new ArrayList<String>(Arrays.asList(input.split(" ")));
      commands = parseInput((ArrayList) commands);
      // if user enters nothing, reprompt
      if (commands.size() == 0) {
        // remove the false input from the inputs history
        inputs.remove(inputs.size() - 1);
      }
      // if user inputs a command
      else {
        attemptExecute((ArrayList) commands);
      }
    }
  }

  /**
   * Takes input commands and attempts to execute the commands. If the command
   * does not exist, print out an error message
   * 
   * @param commands is the ArrayList containing the parsed input from the user
   */
  public static void attemptExecute(ArrayList commands) {
    // attempt to execute the command
    try {
      Class<?> x =
          Class.forName("driver." + ((String) commands.get(0)).toUpperCase());
      Method y = x.getMethod("execute", commands.getClass());
      y.invoke(y, commands);
    }

    // if the command does not exist print an error
    catch (NoSuchMethodException | ClassNotFoundException
        | InvocationTargetException | IllegalAccessException e) {
      Command.printGenericError("Invalid command, for help type: man CMD");
    }
  }

  /**
   * Takes a raw version of a user's input and returns a refined, edited input
   * to be executed
   * 
   * @param rawCommands is the unedited input
   * @return commands is the fully edited input
   */
  public static ArrayList parseInput(ArrayList rawCommands) {
    ArrayList commands = rawCommands;

    commands = stringEdit(commands);

    // remove empty inputs
    while (commands.contains("")) {
      commands.remove("");
    }
    commands = numberEdit(commands);
    return commands;
  }

  /**
   * Takes a raw version of a user's input and returns a refined version that
   * converts parsed a string to a full string. NOTE: all commands currently
   * take only one string, so this is designed to only handle one string.
   * 
   * @param rawCommands is the unedited input
   * @return commands is the edited input
   */
  private static ArrayList stringEdit(ArrayList rawCommands) {
    ArrayList commands = rawCommands;
    ArrayList range = findRange(commands);

    // if a range of inputs exist between the start and end add them together
    String newObject = "";
    if ((int) range.get(0) < (int) range.get(1)) {
      for (int p = (int) range.get(1); p > (int) range.get(0); p--) {
        // add the pieces together
        newObject = " " + commands.get(p) + newObject;
        // remove the pieces from the commands
        commands.remove(p);
      }
      // add the final piece and overwrite
      commands.set((int) range.get(0),
          (commands.get((int) range.get(0)) + newObject).substring(1,
              (commands.get((int) range.get(0)) + newObject).length() - 1));
    }
    return commands;
  }

  /**
   * Helper method for stringEdit that returns the range of objects that the
   * string spans
   * 
   * @param rawCommands is the unedited input
   * @return range is the ArrayList containing the integer start and end
   *         coordinates
   */
  private static ArrayList findRange(ArrayList rawCommands) {
    ArrayList commands = rawCommands;
    // search the command parameters for parameters starting and ending
    // with "
    int start = 0;
    int end = 0;
    boolean foundStart = false;
    for (int k = 0; k < commands.size(); k++) {
      if (!commands.get(k).equals("")) {
        // record the start index if a starting " is found
        if ((((String) commands.get(k)).charAt(0) == "\"".charAt(0))
            & !foundStart) {
          start = k;
          foundStart = true;
        }
        // record the end index if an end " is found
        if ((((String) commands.get(k))
            .charAt(((String) commands.get(k)).length() - 1) == "\"".charAt(0))
            & foundStart) {
          end = k;
        }
      }
    }
    ArrayList range = new ArrayList();
    range.add(start);
    range.add(end);
    return range;
  }

  /**
   * Takes a raw version of a user's input and returns a refined version that
   * converts !# inputs into number # for execution purposes
   * 
   * @param rawCommands is the unedited input
   * @return commands is the edited input
   */
  private static ArrayList numberEdit(ArrayList rawCommands) {
    ArrayList commands = rawCommands;
    // If the first parameter starts with ! it is a number command, convert it
    if (commands.size() > 0) {
      if (((String) commands.get(0)).charAt(0) == "!".charAt(0)) {
        String number = ((String) commands.get(0)).substring(1);
        commands.set(0, "number");
        commands.add(number);
      }
    }
    return commands;
  }


  /**
   * A getter method to return working directory
   * 
   * @return workingDir is the working directory
   */
  public static Dir getWorkingDir() {
    return workingDir;
  }

  /**
   * A setter method to change the working directory
   * 
   * @param workingDir is the dir you want to set as JShell's workingDir
   */
  public static void setWorkingDir(Dir workingDir) {
    JShell.workingDir = workingDir;
  }

  /**
   * A getter method to return the input history
   * 
   * @return inputs is the input history
   */
  public static ArrayList getInputs() {
    return inputs;
  }

  /**
   * A setter method to change JShell's input history
   * 
   * @param inputs is the ArrayList you want to set as the input history
   */
  public static void setInputs(ArrayList inputs) {
    JShell.inputs = inputs;
  }

  /**
   * A getter method to return directory history
   * 
   * @return dirHistory is the history of directories
   */
  public static ArrayList getDirHistory() {
    return dirHistory;
  }

  /**
   * A setter method to change JShell's directory history
   * 
   * @param dirHistory is the ArrayList you want to set as the directory history
   */
  public static void setDirHistory(ArrayList dirHistory) {
    JShell.dirHistory = dirHistory;
  }

  /**
   * A getter method to return JShell's running state
   * 
   * @return running indicates if JShell is running or terminated
   */
  public static boolean isRunning() {
    return running;
  }

  /**
   * A setter method to change the running state of JShell
   * 
   * @param running is the boolean you want to set JShell's running to
   */
  public static void setRunning(boolean running) {
    JShell.running = running;
  }

  /**
   * A getter method to return the manual for all commands
   * 
   * @return manual is the string containing all the manuals
   */
  public static String getManual() {
    return manual;
  }

}
