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
 * MKDIR class takes command input and creates non duplicate dirs
 * 
 * It also has a manual that can be displayed
 *
 */
public class MKDIR extends Command {
  /**
   * Checks for duplicate dirs and creates them at their specified paths or
   * prints errors
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // mkdir has at least 2 inputs
    if (commands.get(0).equals("mkdir") & commands.size() > 1) {
      ArrayList paths = commands;
      paths.remove(0);
      // if any of the parameters are invalid print an error, run otherwise
      if (!verifyAllDirs(paths)) {
        printGenericError("One of the Directories is a duplicate or"
            + " one of the paths does not exist");
      } else {
        // generate a directory for each path
        for (Object givenPath : paths) {
          createDir((String) givenPath);
        }
      }
    } else {
      printGenericError("Invalid command, for help type: man CMD");
    }
  }

  /**
   * Generates a directory at the given path. The final object in the path is
   * the directory to be made.
   * 
   * @param givenPath is the String representation of the path for the new
   *        directory
   */
  private static void createDir(String givenPath) {
    // splice the path at / to form a list
    List<String> path =
        new ArrayList<String>(Arrays.asList(((String) givenPath).split("/")));
    // remember and remove the last object of the path
    String rememberDir = path.get(path.size() - 1);
    path.remove(path.size() - 1);
    // reconstruct the path
    givenPath = "";
    for (String object : path) {
      givenPath = givenPath + object + "/";
    }// if there is no path create a dir in the workingDir
    if (((String) givenPath).length() == 0) {
      Dir manipulatedDir = JShell.getWorkingDir();
      manipulatedDir.storeDir(Dir.createChildDir(rememberDir));
    }
    // if there is a path navigate to it and create a dir there
    else {
      Dir manipulatedDir = navigatePath((String) givenPath);
      manipulatedDir.storeDir(Dir.createChildDir(rememberDir));
    }
  }

  /**
   * Verifies that all given parameters are valid
   * 
   * @param paths is the given ArrayList
   * @return (!duplicate & !invalid & !invalidPath) will return true if every
   *         single parameter will not generate duplicates, have invalid
   *         characters, and provide an invalid path
   */
  private static boolean verifyAllDirs(ArrayList paths) {
    // set flags
    boolean duplicate = duplicateInputs(paths);
    boolean invalid = false;
    boolean invalidPath = false;
    for (Object givenPath : paths) {
      // splice the path at / to form a list
      List<String> path =
          new ArrayList<String>(Arrays.asList(((String) givenPath).split("/")));
      // remember and remove the last object of the path
      String rememberDir = path.get(path.size() - 1);
      path.remove(path.size() - 1);
      givenPath = "";
      for (String object : path) {
        givenPath = givenPath + object + "/";
      }// check the given dir for invalid characters
      if (!validName(rememberDir)) {
        invalid = true;
      } // check if there are duplicates in the contents of the path
      if (path.size() == 0 & !duplicate) {
        duplicate = duplicateContents(JShell.getWorkingDir(), rememberDir);
      } else {
        Dir manipulatedDir = navigatePath((String) givenPath);
        if (manipulatedDir == null) {
          invalidPath = true;
        } else if (!duplicate) {
          duplicate = duplicateContents(manipulatedDir, rememberDir);
        }
      }
    }
    return ((!duplicate & !invalid & !invalidPath));
  }

  /**
   * Checks for potential duplication in the directory
   * 
   * @param manipulateDir is the directory to be checked
   * @return duplicate indicates if paths will generate duplicate Dirs
   */
  private static boolean duplicateContents(Dir manipulatedDir,
      String rememberDir) {
    // set a flag to be raise
    boolean duplicate = false;
    // iterate over each child dir
    for (int m = 0; m < manipulatedDir.getDirChildren().size(); m++) {
      // if child dir exists raise the flag
      if (manipulatedDir.getDirChildren().get(m).toString()
          .equals(rememberDir)) {
        duplicate = true;
      }
    }
    return duplicate;
  }

  /**
   * Checks for duplicates in the given ArrayList of paths
   * 
   * @param paths is the given ArrayList
   * @return duplicate indicates if paths has duplicate objects
   */
  private static boolean duplicateInputs(ArrayList paths) {
    // set flag to check for duplicate directories
    boolean duplicate = false;

    // check for duplicates in the inputs
    for (int p = 0; p < paths.size(); p++) {
      for (int v = 0; v < paths.size(); v++) {
        // if two different objects in the parameters are the same
        if ((p != v) & paths.get(p).equals(paths.get(v))) {
          // flag for duplicates
          duplicate = true;
        }

      }
    }
    return duplicate;
  }

  /**
   * Returns the directory the path navigates to if valid. If invalid, prints
   * error messages.
   * 
   * @param parameter is the string of the path
   * @return manipulatedDir is the Dir at the end of the path
   */
  public static Dir navigatePath(String parameter) {
    // splice the path at / to form a list
    List<String> path =
        new ArrayList<String>(Arrays.asList(parameter.split("/")));
    // remember the old workingDir
    Dir manipulatedDir = JShell.getWorkingDir();
    // if the path is a full path navigate to the MASTER dir first
    if (parameter.charAt(0) == "/".charAt(0)){
      while(!(manipulatedDir.getParent() == null)){
        manipulatedDir = manipulatedDir.getParent();
      }
      if (path.size() > 0){
        path.remove(0);
      }
    }
    try {
      return followTrail(manipulatedDir, (ArrayList) path);
    } catch (IndexOutOfBoundsException e) {
      // the path is invalid, return null
      return null;
    }
  }

  /**
   * Prints out the manual for this command
   */
  public static void manual() {
    System.out.println(
        "mkdir DIR: " + "\nCreate directories, each of which may be relative"
            + "\nto the current directory or may be a full path.");
  }

}
