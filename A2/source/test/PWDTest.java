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

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import driver.PWD;
import driver.JShell;
import driver.MKDIR;
import driver.CD;


public class PWDTest {
  
  
  /**
   * Method which tests the execute method in the PWD class.
   * All tests should pass
   */
  @Test
  public void testExecute() {
    // Case 1: Test to see the primary pwd to be MASTER
    ArrayList<String> testPWD1 = new ArrayList<String>();
    String[] testPWDCase1 = {"pwd"};
    testPWD1.addAll(Arrays.asList(testPWDCase1));
    PWD.execute(testPWD1);
    assertSame(JShell.getWorkingDir().toString(), "MASTER");

    // Case 2: Change the working director and see if PWD show case the correct
    // one
    ArrayList<String> testMKDIR = new ArrayList<String>();
    // Directories made for testCase1 "a"
    String[] testCasesMKDIR1 = {"mkdir", "a"};
    testMKDIR.addAll(Arrays.asList(testCasesMKDIR1));
    MKDIR.execute(testMKDIR); // execute command requires an ArrayList

    //Change working directory to a
    ArrayList<String> testCD2 = new ArrayList<String>();
    String[] testCasesCD2 = {"cd", "a"};
    testCD2.addAll(Arrays.asList(testCasesCD2));
    CD.execute(testCD2);
    assertEquals(JShell.getWorkingDir().toString(), "a");

    //Test to see if pwd changes 
    ArrayList<String> testPWD2 = new ArrayList<String>();
    String[] testPWDCase2 = {"pwd"};
    testPWD2.addAll(Arrays.asList(testPWDCase2));
    PWD.execute(testPWD2);
    assertEquals(JShell.getWorkingDir().toString(), "a");

  }
}
