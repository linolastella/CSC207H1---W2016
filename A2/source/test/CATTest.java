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

import driver.CAT;
import driver.CD;
import driver.ECHO;
import driver.JShell;
import driver.MKDIR;
import driver.File;
import driver.HISTORY;

import org.junit.Test;

public class CATTest {

  /*
   * CATTest test if the CAT command has been called through history. 
   * As it has void methods, there is no way to check the contents which has 
   * been printed, however, those can be found in FileTest and EchoTest
   */

  @Test
  public void testExecute() {
    File a = new File("Hello");
    
    ArrayList<String> testMKDIR= new ArrayList<String>();
    //Directories made for testCase1 "a"
    String[] testCasesMKDIR1 = {"mkdir","a"};
    testMKDIR.addAll(Arrays.asList(testCasesMKDIR1));
    MKDIR.execute(testMKDIR);
   
    //Test to see if the working directory changes to "a"
    ArrayList<String> testCD2 = new ArrayList<String>();
    String[] testCasesCD2 = {"cd","a"};
    testCD2.addAll(Arrays.asList(testCasesCD2));
    CD.execute(testCD2);
    
    ArrayList<String> testECHO = new ArrayList<String>();
    String[] testCaseEcho = {"echo","hello",">","a"};
    testECHO.addAll(Arrays.asList(testCaseEcho));
    ECHO.execute(testECHO);   
    
    ArrayList<String> testCAT = new ArrayList<String>();
    String[] testingCAT = {"cat","a"};
    testCAT.addAll(Arrays.asList(testingCAT));
    CAT.execute(testCAT);
    
    ArrayList testHIS = new ArrayList();
    String[] testCaseHis = {"cat Hello", "history"};
    testHIS.addAll(Arrays.asList(testCaseHis));
    JShell.setInputs(testHIS);
    ArrayList<String> testHISEXC = new ArrayList<String>();
    testHISEXC.add("history");
    HISTORY.execute(testHISEXC);
    assertEquals(JShell.getInputs(), testHIS);
  }

}
