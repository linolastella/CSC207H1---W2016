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
 * All complex commands shall inherit from this class
 * 
 * Command classes take parsed input and execute if appropriate
 */
public class Command {
  /**
   * Executes desired function based on input or Prints errors
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  public static void execute(ArrayList commands) {

  }

  /**
   * Prints out the manual for this command
   */
  public static void manual() {

  }

  /**
   * Prints out the error message and removes the invalid command from the input
   * history
   * 
   * @param errorMessage is the error message to be displayed
   */
  public static void printGenericError(String errorMessage) {
    // print error message
    System.out.println(errorMessage);
    // remove invalid input from inputs history
    ArrayList mod = JShell.getInputs();
    mod.remove(mod.size() - 1);
    JShell.setInputs(mod);
  }

  /**
   * Returns whether or not the give name is a valid name for a Dir or File
   * 
   * @param names is the name being verified
   * @return valid is the boolean indicating whether or not the name is valid
   */
  public static boolean validName(String name) {
    // set a flag to be lowered
    boolean valid = true;
    // if the name has any invalid characters, lower the flag
    if (name.contains(".") | name.contains("/") | name.contains("\\")
        | name.contains("\"") | name.contains("\'") | name.contains(",")
        | name.contains("!") | name.contains("@") | name.contains("$")
        | name.contains("&") | name.contains("*") | name.contains("()")
        | name.contains("?") | name.contains(":") | name.contains("[]")
        | name.contains(">") | name.contains("<") | name.contains("|")
        | name.contains("=") | name.contains("{}") | name.contains(";")
        | name.contains(" ")) {
      valid = false;
    }
    return valid;
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
      // the path is invalid, print error and return null
      printGenericError("Path does not exist");
      return null;
    }
  }


  /**
   * Returns the directory the path navigates to. This method was designed to
   * fail to trigger errors in navigatePath
   * 
   * @param manipulateDir is the starting dir of navigation
   * @param path is the ArrayList version of the path to navigate
   * @return manipulatedDir is the Dir at the end of the path
   */
  public static Dir followTrail(Dir manipulatedDir, ArrayList path) {
    // cycle through each dir in the path
    for (int i = 0; i < path.size(); i++) {
      // .. refers to parent, . refers to self
      if (path.get(i).equals("..") & !(manipulatedDir.getParent() == null)) {
        manipulatedDir = manipulatedDir.getParent();
      } else if (path.get(i).equals(".")) {
      } else {
        int j = 0;
        // scan children for match
        while (!path.get(i)
            .equals(manipulatedDir.getDirChildren().get(j).toString())) {
          j++;
        }
        // path dir is found, change the workingDir to its selected child
        manipulatedDir = (Dir) manipulatedDir.getDirChildren().get(j);
      }
    }
    // at this point the path is verified return the dir
    return manipulatedDir;
  }


  /**
   * Takes a Dir and searches it for a File with the string fileName.
   * If the Dir contains a file corresponding to the fileName, return the file.
   * If not, return null
   * 
   * @param fileNams is the string representation of the file desired
   * @param dir is the directory to have its contents searched
   * 
   * @return (File) dir.getDirContents().get(j) is the desired file
   * @return null indicates the file doesn't exist in the Dir's contents
   *       
   */
  public static File getFileFromDir(String fileName, Dir dir) {
    try{
      // increment j until a file corresponding to the index is found
      int j = 0;
      while (!dir.getDirContents().get(j).toString().equals(fileName)){
        j ++;
      }
      // return once found
      return (File) dir.getDirContents().get(j);
    }  catch (IndexOutOfBoundsException e) {
      // if j surpasses the maximum index of the contents, the file doesn't
      // exist. Return null
      return null;
    }
  }
  
  public static ArrayList outFileParameters(ArrayList commands){
    ArrayList parameters = new ArrayList(3);
    // set the parameters for outFiling to disabled so specialPrint just prints
    boolean enable = false;
    String manipulation = null;
    String outFilePath = null;
    // if the commands has outfile enabled, enable outfiling, record the file
    // name, and record the manipulation method
    if (commands.size() > 2){
      if (commands.get(commands.size() - 2).equals(">") |
          commands.get(commands.size() - 2).equals(">>")){
        enable = true;
        outFilePath = (String) commands.get(commands.size() - 1);
        manipulation = (String) commands.get(commands.size() - 2);
        commands.remove(commands.size() - 1);
        commands.remove(commands.size() - 1);
      }
    }
    // store the parameters and return
    parameters.add(enable);
    parameters.add(manipulation);
    parameters.add(outFilePath);
    return parameters;
  }
  
  public static void specialPrint(String message, ArrayList parameters){
    // if outfiling is enabled
    if ((boolean) parameters.get(0)){
      String path = subPath((String) parameters.get(2));
      String stringFile = target((String) parameters.get(2));
      Dir manipulatedDir = navigatePath(path);
      if (!(manipulatedDir == null)){
        File file = findOrMakeFile(manipulatedDir, stringFile);
        if (parameters.get(1).equals(">")){
          overWriteContentsOnFile("", file);
          parameters.set(1, ">>");
        }
        appendContentsToFile(message, file);
      }
    }
    else {
      System.out.print(message);
    }
  }
  
  /**
   * Searches the current workingDir for the file in commands. If found return
   * it. If not found, make the file, and return it.
   * 
   * @param commands is an ArrayList of parsed inputs from JShell
   */
  private static File findOrMakeFile(Dir mod, String outFile ) {
    // index will track the file's location in the directory
    int index = 0;
    // scan the contents until an existing file is found, then return it
    try {
      while (!outFile.equals(mod.getDirContents().get(index).toString())) {
        index++;
      }
      return (File) mod.getDirContents().get(index);
    }
    // if the file does not exist in the contents, create one
    catch (IndexOutOfBoundsException e) {
      // create and return the file
      mod.storeFile(new File((String) outFile));
      return (File) mod.getDirContents().get(mod.getDirContents().size() - 1);
    }
  }
  
  /**
   * A method which appends a String to a specific target file.
   * 
   * @param content: the information which the user wants to append to the file
   * @param target: The target file which the changes will be made to
   */
  private static void appendContentsToFile(String content, File target) {
    // Access the Previous contents of a file
    String newContents = target.returnContents();
    // Append the new contents
    newContents = newContents + content;
    // Overwrite the target's contents
    target.setContents(newContents);

  }

  /**
   * A method which over writes a given File and all of its contents with a new
   * set of String values
   * 
   * @param content: the information which the user wants to append to the file
   * @param target: The target file which the changes will be made to
   */
  private static void overWriteContentsOnFile(String content, File target) {
    // Over write contents of the target File
    target.setContents(content);
  }
  
  /**
   * Takes a path and returns the target file of the path
   * 
   * @param path is the given path
   * @param target is the target file of the path
   */
  public static String target(String path) {
    // splice the parts of the path into separate objects
    List<String> parts =
        new ArrayList<String>(Arrays.asList(path.split("/")));
    // return the final part of the path, which is the target
    return parts.get(parts.size() - 1);
  }
  /**
   * Takes a path and returns the sub path leading to the target file
   * 
   * @param path is the given path
   * @param subPath is the path leading to the Dir containing the target file
   */
  public static String subPath(String path) {
    // splice the parts of the path into separate objects
    List<String> parts =
        new ArrayList<String>(Arrays.asList(path.split("/")));
    // rebuild the path excluding the last object
    parts.remove(parts.size() - 1);  
    String subPath = null;
    if (parts.size() > 0){
      if (parts.get(0).equals("")){
        subPath = "/.";
      }
    }
    
    
    if (parts.size() == 0){
      subPath = ".";
    }
    else if (subPath == null){
      subPath = (String) parts.get(0);
    }
    
    
    for (int i = 1; i < parts.size(); i++){
      subPath = subPath + "/" + parts.get(i);
    }
    return subPath;  
  }
}
