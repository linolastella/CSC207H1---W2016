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

public class Dir {

  // A Dir object has a parent Dir and multiple children of Dir
  private Dir parent;
  private ArrayList children;
  // A Dir has Files stored as contents
  private ArrayList contents;
  // name is the name of the Dir
  private String name;

  /**
   * Default Constructor. Creates a Dir object with no parent and no children.
   * 
   * @param name is the name of the Dir
   */
  private Dir(String name) {
    // set an empty parent to be assigned
    this.parent = null;
    // create an empty list of children for the new Dir
    this.children = new ArrayList();
    // create an empty list of contents for the new Dir
    this.contents = new ArrayList();
    // store the name of the Dir
    this.name = name;

  }

  /**
   * Factory Method. Creates and returns Dir object with a parent and no
   * children.
   * 
   * @param name is the name of the Dir
   */
  public static Dir createChildDir(String name) {
    // create a Dir with a parent Dir
    return new Dir(name);
  }

  /**
   * Factory method. Creates and returns a master directory with no parents or
   * children.
   */
  public static Dir createMasterDir() {
    // create a Dir with a parent null (has no parent)
    return new Dir("");

  }

  /**
   * A setter method to change a directory's parent
   * 
   * @param parent is the dir you want to set as the parent
   */
  public void setDirParent(Dir parent) {
    this.parent = parent;

  }

  /**
   * Returns the String representation of the Dir Getter method to return the
   * name of the Dir
   * 
   * @return the string name
   */
  public String toString() {
    // returns the name of the Dir
    return this.name;
  }

  /**
   * A getter method to return a directory's parent
   * 
   * @return parent is the parent of this dir
   */
  public Dir getParent() {
    // return the parent
    return this.parent;
  }

  /**
   * A getter method to return a directory's children as an ArrayList
   * 
   * @return children is the ArrayList containing all child Dirs
   */
  public ArrayList getDirChildren() {
    // return the children
    return this.children;
  }

  /**
   * A getter method to return a directory's contents as an ArrayList
   * 
   * @return contents is the ArrayList containing all Files
   */
  public ArrayList getDirContents() {
    // return the contents
    return this.contents;
  }

  /**
   * A method to store a directory in directory's children
   * 
   * @param directory is the Dir to be stored in this Dir
   */
  public void storeDir(Dir directory) {
    // add the child Dir directory in the children
    this.children.add(directory);
    // set the child Dir parent to the new parent
    directory.setDirParent(this);
  }

  /**
   * A method to store a File in directory's children
   * 
   * @param file is the File to be stored in this Dir
   */
  public void storeFile(File file) {
    // add the child to the Dir directory in the children
    this.contents.add(file);
  }
}
