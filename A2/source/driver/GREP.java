package driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GREP command takes input and searches files, or a directory's files for a
 * given string REGEX.
 * 
 * It also has a manual that can be displayed
 *
 */
public class GREP extends Command {
  /**
   * If the second parameter is not -R, the execute will assume all parameters
   * after the regex are paths to files. It then goes into each file's contents
   * and searches for a line containing regex, which it reports. or If the
   * second parameter is -R it assumes that all parameters after the regex can
   * be paths to files, or paths to dirs. It then goes into each file's
   * contents, or each contents in all the files of a dir, and searches for a
   * line containing regex, which it reports.
   * 
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {
    if (commands.get(0).equals("grep") & commands.size() > 2) {
      // set the print parameters
      ArrayList parameters = outFileParameters(commands);
      // if -R is provided search the Dir indicated
      if (((String) commands.get(1)).toUpperCase().equals("-R")
          & commands.size() > 3) {
        for (int i = 3; i < commands.size(); i++) {
          searchDir((String) commands.get(i), (String) commands.get(2),
              parameters);
        }
      }
      // if not, do a standard file search
      else {
        for (int j = 2; j < commands.size(); j++) {
          searchFile((String) commands.get(j), (String) commands.get(1),
              parameters);
        }
      }
    } else {
      printGenericError("Invalid command, for help type: man CMD");
    }
  }

  /**
   * Navigates to a Dir by a given path. If the path is valid, search each file
   * in the dir for regex. If the path is invalid, pass the path and regex to
   * searchFile to be assessed if it is a file path.
   * 
   * @param path is the path to a Dir or File
   * @param regex is the string to be searched for
   * @param parameters is the outfile/ display parameters
   */
  private static void searchDir(String path, String regex,
      ArrayList parameters) {
    // navigate the path
    Dir manipulatedDir = navigatePath(path);
    // if the path doesn't exist, it may be a file path, pass it on
    if (manipulatedDir == null) {
      searchFile(path, regex, parameters);
    }
    // the path exists
    else {
      // for each file in the dir, search its contents
      for (Object file : manipulatedDir.getDirContents()) {
        searchContents((File) file, regex, parameters);
      }
    }

  }

  /**
   * Navigate to a file by a given path. If the path is valid, search the file
   * for regex. If invalid, return a message indicating the file does not exist.
   * 
   * @param path is the path to a File
   * @param regex is the string to be searched for
   * @param parameters is the outfile/ display parameters
   */
  private static void searchFile(String path, String regex,
      ArrayList parameters) {
    // get the subpath
    String subPath = subPath(path);
    // get the target
    String target = target(path);
    // navigate to the subPath
    Dir manipulatedDir = JShell.getWorkingDir();
    if (!(subPath.equals(target))) {
      manipulatedDir = navigatePath(subPath);
    }
    // if the path does not exist, print an error for the path
    if (manipulatedDir == null) {
      specialPrint("Path is invalid\n", parameters);
    }
    // otherwise, find the file and search it for regex
    else {
      try {
        int k = 0;
        while (!manipulatedDir.getDirContents().get(k).toString()
            .equals(target)) {
          k++;
        }
        searchContents((File) manipulatedDir.getDirContents().get(k), regex,
            parameters);
      } catch (IndexOutOfBoundsException e) {
        // file doesn't exist, print error
        specialPrint("File does not exist\n", parameters);
      }
    }

  }

  /**
   * Search a file's contents for any occurrences of regex. Prints the file,
   * line number, and line.
   * 
   * @param file is the file to be searched
   * @param regex is the string to be searched for
   * @param parameters is the outfile/ display parameters
   */
  private static void searchContents(File file, String regex,
      ArrayList parameters) {
    // splice the contents of the file at the newlines
    List<String> pieces =
        new ArrayList<String>(Arrays.asList(file.returnContents().split("\n")));
    // search each line of the file
    for (int m = 0; m < pieces.size(); m++) {
      // if the line contains regex print out the file, line number, and line
      if (pieces.get(m).contains(regex)) {
        specialPrint(
            file.toString() + "-" + (m + 1) + ":" + pieces.get(m) + "\n",
            parameters);
      }
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

  public static void manual() {
    System.out.println("ngrep [-R] REGEX PATH ... \n"
        + "If [-R] is not supplied, print any lines containing REGEX in PATH, "
        + "which\nmust be a file. If [-R] is supplied, and PATH is a directory,"
        + " recursively \ntraverse the directory and, for all lines in all "
        + "files that contain REGEX, \nprint the path to the file (including "
        + "the filename), then a colon, \nthen the line that contained REGEX.");
  }
}
