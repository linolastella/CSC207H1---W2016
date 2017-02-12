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
 * MV class takes command input and Moves one directory into another
 * 
 * It also has a manual that can be displayed
 *
 */
public class MV extends Command {

  /**
   * If the two paths are valid, move the directory indicated by the first path
   * into the directory indicated by the second path. If invalid, print errors.
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // mv has at 3 inputs, print an error otherwise
    if (commands.get(0).equals("mv") & commands.size() == 3) {
      // obtain the Dirs to work on
      Dir oldDir = navigatePath((String) commands.get(1));
      Dir newDir = navigatePath((String) commands.get(2));
      // verify that the Dirs are valid
      boolean verified = verifyDirs(oldDir, newDir);
      // if valid, execute
      if (verified) {
        cutAndMove(oldDir, newDir);
      }
      // report error if invalid
      else {
        printGenericError("Paths are invalid.");
      }
    } else {
      printGenericError("Invalid commnad: Please refer to man CMD");
    }
  }

  /**
   * Removes oldDir from its parent by cutting all ties, and then stores it in
   * the newDir.
   * 
   * @param oldDir is the Dir to be moved into newDir
   * @param newDir is the Dir to accept oldDir
   */
  private static void cutAndMove(Dir oldDir, Dir newDir) {
    // search for oldDir in its parent and remove it
    Dir parent = oldDir.getParent();
    parent.getDirChildren().remove(oldDir);
    // cut parent relations from oldDir and its parent
    oldDir.setDirParent(null);
    // store oldDir in newDir
    newDir.storeDir(oldDir);
  }

  /**
   * Returns a boolean indicating whether or not oldDir is a viable Dir to
   * transfer into newDir. Checks for duplication, moving into subdirectories,
   * moving into self, and invalid paths.
   * 
   * @param oldDir is the Dir to be moved into newDir
   * @param newDir is the Dir to accept oldDir
   * @return flag is the boolean verifying the compatibility
   */
  private static boolean verifyDirs(Dir oldDir, Dir newDir) {
    boolean flag = true;
    // check that the Dirs exist and are not the same
    if ((oldDir == null) | (newDir == null) | (oldDir == newDir)) {
      flag = false;
    }
    // starting at oldDir, check all of its subdirs to verify you are
    // not moving oldDir into a child
    if (flag) {
      flag = verifySubDirs(oldDir, newDir);
    }
    // check to make sure oldDir isnt the same name as other
    // dir in newDir
    if (flag) {
      flag = verifyDuplicateDirs(oldDir, newDir);
    }
    return flag;
  }

  /**
   * Returns a boolean indicating whether or not oldDir has the same name as
   * other Dirs in newDir.
   * 
   * @param oldDir is the Dir to be moved into newDir
   * @param newDir is the Dir to accept oldDir
   * @return flag is the boolean verifying the compatibility
   */
  private static boolean verifyDuplicateDirs(Dir oldDir, Dir newDir) {
    boolean flag = true;
    for (Object dir : newDir.getDirChildren()) {
      if (dir.toString().equals(oldDir.toString())) {
        flag = false;
      }
    }
    return flag;
  }

  /**
   * Returns a boolean indicating whether or not newDir is a subdirectory of
   * oldDir.
   * 
   * @param oldDir is the Dir to be moved into newDir
   * @param newDir is the Dir to accept oldDir
   * @return flag is the boolean verifying the compatibility
   */
  private static boolean verifySubDirs(Dir currentDir, Dir newDir) {
    // if the sub dir is the same as the newDir, return false
    if (currentDir == newDir) {
      return false;
    }
    // if the sub dir is not the same as newDir, check its children
    else {
      boolean flag = true;
      for (Object subDir : currentDir.getDirChildren()) {
        flag = flag & verifySubDirs((Dir) subDir, newDir);
      }
      return flag;
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
   * Prints out the manual for this command
   */
  public static void manual() {
    System.out.println("mv OLDPATH NEWPATH: \nMove item "
        + "OLDPATH to NEWPATH. Both OLDPATH and NEWPATH may be relative to\nthe "
        + "current directory or may be full paths. If NEWPATH is a directory,\n"
        + "move the item into the directory.");
  }
}
