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

import driver.CD;
import driver.JShell;
import driver.MKDIR;
import driver.POPD;
import driver.PUSHD;

public class PUSHDTest {

  /**
   * Method which tests the Execute method of the PUSHD class. All tests
   * should pass.
   */

  @Test
  public void testExecute() {
    
    // Creates a directory named b 
    ArrayList<String> testMKDIR= new ArrayList<String>();
    String[] testCasesMKDIR1 = {"mkdir", "b"};
    testMKDIR.addAll(Arrays.asList(testCasesMKDIR1));
    MKDIR.execute(testMKDIR);     
    
    // Performs command PUSHD and adds the pushed directory to a list
    ArrayList<String> testPUSHD = new ArrayList<String>();
    String[] testCasePUSHD = {"pushd","b"};
    testPUSHD.addAll(Arrays.asList(testCasePUSHD));
    PUSHD.execute(testPUSHD);
    ArrayList<String> answer = new ArrayList<String>();
    String[] answerCase = {"MASTER"};
    answer.addAll(Arrays.asList(answerCase));
    
    // Checks to see if directory has been successfully been pushed
    assertEquals(JShell.getWorkingDir().toString(),"b");
    assertEquals(JShell.getDirHistory().toString(), answer.toString());
  }
}
