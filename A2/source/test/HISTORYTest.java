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

import java.util.ArrayList;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import driver.HISTORY;
import driver.JShell;

public class HISTORYTest {

  /**
   * Method which tests the Execute method of the history class. All tests
   * should pass.
   */
  @Test
  public void testExecute() {
    
    // Checks to see if the beginning History is empty
    ArrayList emptyArrayList = new ArrayList();
    assertEquals(emptyArrayList, JShell.getDirHistory());

    // Test to see if history without any arguments works
    ArrayList testHIS = new ArrayList();
    String[] testCaseHis = {"pwd", "ls", "cd a", "history"};
    testHIS.addAll(Arrays.asList(testCaseHis));
    JShell.setInputs(testHIS);
    ArrayList<String> testHISEXC = new ArrayList<String>();
    testHISEXC.add("history");
    HISTORY.execute(testHISEXC);
    assertEquals(JShell.getInputs(), testHIS);

    // Test to see if history with a number argument works
    ArrayList testHIS2 = new ArrayList();
    String[] testCaseHis2 = {"pwd", "ls", "mkdir a", "cd a", "history"};
    testHIS.addAll(Arrays.asList(testCaseHis));
    JShell.setInputs(testHIS2);
    ArrayList<String> testHISEXC2 = new ArrayList<String>();
    String[] addHis = {"history", "3"};
    testHISEXC2.addAll(Arrays.asList(addHis));
    ArrayList answer = new ArrayList();
    String[] answerString = {"cd a", "history", "history 3"};
    answer.addAll(Arrays.asList(answerString));
    JShell.setInputs(answer);
    HISTORY.execute(testHISEXC2);
    assertEquals(JShell.getInputs(), answer);
  }
}
