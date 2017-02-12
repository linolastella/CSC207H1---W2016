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
package test;

import static org.junit.Assert.*;
import org.junit.Test;
import driver.File;

public class FileTest {
	
  /**
   * Method which test the File class returnContents method. All test
   * should pass
   */
  @Test
  public void testReturnContents() {
    File file1 = new File("File 1");
    assertEquals("", file1.returnContents());
  }

  /**
   * Method which test the File class toString method. All test
   * should pass
   */
  @Test
  public void testToString() {
    File file1 = new File("I really like this file");
    assertEquals("I really like this file", file1.toString());
  }

  /**
   * Method which test the File class setContents method. All test
   * should pass
   */
  @Test
  public void testSetContents() {
    File file1 = new File("I don't like this file");
    file1.setContents("New Content here");
    assertEquals("New Content here", file1.returnContents());
  }
}