package driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CP class takes command input and copies one directory into another
 * 
 * It also has a manual that can be displayed
 *
 */
public class CP extends Command {
  /**
   * If the two paths are valid, copy the directory indicated by the first path
   * into the directory indicated by the second path. If invalid, print errors.
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    // cp has at 3 inputs, print an error otherwise
    if (commands.get(0).equals("cp") & commands.size() == 3) {
      // obtain the Dirs to work on
      Dir oldDir = navigatePath((String) commands.get(1));
      Dir newDir = navigatePath((String) commands.get(2));
      // verify that the Dirs are valid
      boolean verified = verifyDirs(oldDir, newDir);
      // if valid, execute
      if (verified) {
        newDir.storeDir(clone(oldDir));
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
   * Recursively generates a clone of oldDir with all its cloned files and
   * cloned directories.
   * 
   * @param oldDir is the Dir to be copied into newDir
   * @return clone is the clone of oldDir
   */
  private static Dir clone(Dir oldDir) {
    // generate a clone dir
    Dir clone = Dir.createChildDir(oldDir.toString());
    // generate and store all the cloned files of the dir
    for (Object file : oldDir.getDirContents()) {
      File fileClone = new File(((File) file).toString());
      fileClone.setContents(((File) file).returnContents());
      clone.storeFile((File) fileClone);
    }
    // create clones of subdirectories recursively
    for (Object subDir : oldDir.getDirChildren()) {
      clone.storeDir(clone((Dir) subDir));
    }
    return clone;
  }


  /**
   * Returns a boolean indicating whether or not oldDir is a viable Dir to
   * transfer into newDir. Checks for duplication and invalid paths.
   * 
   * @param oldDir is the Dir to be copied into newDir
   * @param newDir is the Dir to accept the copy
   * @return flag is the boolean verifying the compatibility
   */
  private static boolean verifyDirs(Dir oldDir, Dir newDir) {
    boolean flag = true;
    // check that the Dirs exist
    if ((oldDir == null) | (newDir == null)) {
      flag = false;
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
   * @param oldDir is the Dir to be copied into newDir
   * @param newDir is the Dir to accept the copy
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
    System.out.println("cp OLDPATH NEWPATH: \nLike mv, "
        + "but don’t remove OLDPATH. \nIf OLDPATH is a directory, recursively "
        + "copy the contents.");
  }
}
