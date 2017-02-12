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

public class File {
  // contents is the string which File carries
  private String contents;
  // name is the name of the File
  private String name;

  /**
   * Default Constructor. Creates a File object with empty contents.
   * 
   * @param name is the name of the File
   */
  public File(String name) {
    // contents are set to an empty string
    this.contents = "";
    // name is stored
    this.name = name;
  }

  /**
   * Getter method to return the contents of the file.
   * 
   * @return the string contents
   */
  public String returnContents() {
    // returns the contents of the File
    return this.contents;
  }

  /**
   * Returns the String representation of the File Getter method to return the
   * name of the file.
   * 
   * @return the string name
   */
  public String toString() {
    // returns the name of the File
    return this.name;
  }

  /**
   * Setter method to overwrite the contents of the file.
   * 
   * @param newContents is the string replacing contents
   */
  public void setContents(String newContents) {
    // overwrites the contents of the File
    this.contents = newContents;
  }
}
