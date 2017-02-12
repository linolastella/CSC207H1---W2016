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

import driver.EXIT;
import driver.JShell;

public class EXITTest {

  @Test
  public void testExecute() {
    // exit
    ArrayList<String> testEXIT = new ArrayList<String>();
    String[] testCasesEXIT = {"exit"};
    testEXIT.addAll(Arrays.asList(testCasesEXIT));
    EXIT.execute(testEXIT);
    
    // check that the program is not running
    assertEquals(JShell.isRunning(), false);
  }

}
