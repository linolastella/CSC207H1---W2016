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

import driver.CD;
import driver.Dir;
import driver.JShell;
import driver.MKDIR;

import org.junit.Test;

public class JShellTest {

  @Test
  public void test() {
    // Test getWorkingDirectory - have seen that it works in other JUnit tests
    assertEquals(JShell.getWorkingDir().toString(), "MASTER");

    // Test setWorkingDir
    ArrayList<String> testMKDIR = new ArrayList<String>();
    // Directories made for testCase1 "a"
    String[] testCasesMKDIR1 = {"mkdir", "a"};
    testMKDIR.addAll(Arrays.asList(testCasesMKDIR1));
    MKDIR.execute(testMKDIR);
    // CD changes sets the working directory to "a"
    ArrayList<String> testCD2 = new ArrayList<String>();
    String[] testCasesCD2 = {"cd", "a"};
    testCD2.addAll(Arrays.asList(testCasesCD2));
    CD.execute(testCD2);
    // See if the directory has been changed
    assertEquals(JShell.getWorkingDir().toString(), "a");

    // Test getInputs and setInputs is shown in HISTORYTest - redundant code
    // Test getDirHistory and setDirHistory is shown in POPDTest and PUSHDTest -
    // redundant code
  }

}
