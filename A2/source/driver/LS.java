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
 * LS class takes command input and prints out Dir contents or a File name
 * 
 * It also has a manual that can be displayed
 *
 */
public class LS extends Command {
  /**
   * If the second parameter is a path to a Dir, LS will print out the Dir's
   * contents or If the second parameter is a path to a File, LS will print out
   * the File's name or If the path does not exist, an error message will appear
   * 
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // ls always works as long as its input isn't an empty array
    if (commands.get(0).equals("ls") & commands.size() == 1) {
      printDirContentsAndChildren(JShell.getWorkingDir());
    } else if ((commands.get(0).equals("ls")) & commands.size() > 1) {
      // if ls -R is only given, recursively print all subdirs in workingDir
      if ((commands.size() == 2)
          & ((String) commands.get(1)).toUpperCase().equals("-R")) {
        recursivePrint(JShell.getWorkingDir());
      } // paths given, recursivePrint the dirs or print the files
      else if ((commands.size() > 2)
          & ((String) commands.get(1)).toUpperCase().equals("-R")) {
        for (int i = 2; i < commands.size(); i++) {
          Dir manipulatedDir = navigatePath((String) commands.get(i));
          if (manipulatedDir == null) { // path DNE, print the file
            printFileOrDirContents((String) commands.get(i));
          } else { // path exists, recursively print its subdirectories
            recursivePrint(manipulatedDir);
          }
        }
      } // if ls is given with paths, print each path's contents or file name
      else {
        for (int i = 1; i < commands.size(); i++) {
          printFileOrDirContents((String) commands.get(i));
        }
      }
    } else {
      printGenericError("Invalid command, for help type: man CMD");
    }
  }

  /**
   * Take a directory and recursively prints out its contents and children, and
   * the contents and children of all subdirectories
   * 
   * @param directory is the dir to have it's and it's children's contents and
   *        children printed out
   */
  private static void recursivePrint(Dir directory) {
    // print the directory's contents and children
    printDirContentsAndChildren(directory);
    for (Object subDirectory : directory.getDirChildren()) {
      // do the same for all children
      recursivePrint((Dir) subDirectory);
    }
  }


  /**
   * Either prints the name of the file indicated by the path OR prints the
   * contents and children of the dir indicated by the path
   * 
   * @param stringPath is the path to the Dir or File
   */
  private static void printFileOrDirContents(String stringPath) {
    // remember the final object in the given path
    List<String> pathObjects =
        new ArrayList<String>(Arrays.asList((stringPath).split("/")));
    String target = pathObjects.get(pathObjects.size() - 1);
    // create a path that excludes the last object in the given path
    String path = "";
    for (int l = 0; l < pathObjects.size() - 1; l++) {
      path = path + pathObjects.get(l);
    }
    // navigate to just before the final object in the path
    Dir manipulatedDir = JShell.getWorkingDir();
    if (!(path.length() == 0)) {
      manipulatedDir = navigatePath(path);
    }
    if (!(manipulatedDir == null)) {
      searchAndPrintTarget(manipulatedDir, target);
    }
  }


  /**
   * Returns the directory the path navigates to if valid.
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
    if (parameter.charAt(0) == "/".charAt(0)) {
      while (!(manipulatedDir.getParent() == null)) {
        manipulatedDir = manipulatedDir.getParent();
      }
      if (path.size() > 0) {
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
   * Searches a given directory's children for a Dir matching the target. If not
   * in the children, searches the given directory's contents for a File
   * matching the target. If not in the contents, print an error
   * 
   * @param manipulatedDir is the Dir to be searched
   * @param target is the string representation of a File or Dir
   */
  private static void searchAndPrintTarget(Dir manipulatedDir, String target) {
    // search the manipulatedDir to see if the target is a child
    try {
      int i = 0;
      while (!manipulatedDir.getDirChildren().get(i).toString()
          .equals(target)) {
        i++;
      }
      printDirContentsAndChildren((Dir) manipulatedDir.getDirChildren().get(i));
    } catch (IndexOutOfBoundsException e) {
      // search the manipulatedDir to see if the target is a file
      try {
        int i = 0;
        while (!manipulatedDir.getDirContents().get(i).toString()
            .equals(target)) {
          i++;
        }
        System.out.println(target);
        // the target does not exist
      } catch (IndexOutOfBoundsException r) {
        System.out.println("Target does not exist");
      }
    }
  }

  /**
   * Prints the contents and children of a given directory
   * 
   * @param mod is the Dir to have its contents and children printed
   */

  private static void printDirContentsAndChildren(Dir mod) {
    // create the dirContents string
    String dirContents = mod.toString() + ": Directories: ";
    // add all the Dirs
    for (int k = 0; k < mod.getDirChildren().size(); k++) {
      dirContents = dirContents + mod.getDirChildren().get(k).toString() + " ";
    }
    // add all the Files
    dirContents = dirContents + " Files: ";
    for (int c = 0; c < mod.getDirContents().size(); c++) {
      dirContents = dirContents + mod.getDirContents().get(c).toString() + " ";
    }
    // print the contents
    System.out.println(dirContents);
  }


  /**
   * Prints out the manual for this command
   */
  public static void manual() {
    System.out.println("ls [-R] [PATH]..:\nIf [-R] is present,"
        + " recursively list all subdirectories.\nIf no paths are given,"
        + "prints the contents(file or directories) of the"
        + "\ncurrent directory. Otherwise, for PATH, prints the contents\n"
        + "(file or directories).");
  }
}
