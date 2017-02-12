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

import driver.ECHO;
import driver.File;
import driver.JShell;

public class ECHOTest {

  @Test
  public void testExecute() {
    
    // echo abc > d
    ArrayList<String> testECHO1 = new ArrayList<String>();
    String[] testCasesECHO1 = {"echo","abc", ">", "d"};
    testECHO1.addAll(Arrays.asList(testCasesECHO1));
    ECHO.execute(testECHO1);
    
    // obtain file d
    File file1 = (File) JShell.getWorkingDir().getDirContents().get(0);
    
    // check that file contents and name are properly assigned
    assertEquals(file1.toString(), "d");
    assertEquals(file1.returnContents(), "abc");
    
    // echo abc >> d
    ArrayList<String> testECHO2 = new ArrayList<String>();
    String[] testCasesECHO2 = {"echo","abc", ">>", "d"};
    testECHO2.addAll(Arrays.asList(testCasesECHO2));
    ECHO.execute(testECHO2);
    
    // obtain file d
    file1 = (File) JShell.getWorkingDir().getDirContents().get(0);
   
    // check that the file contents are appended
    assertEquals(file1.toString(), "d");
    assertEquals(file1.returnContents(), "abcabc");
    
    // echo def > d
    ArrayList<String> testECHO3 = new ArrayList<String>();
    String[] testCasesECHO3 = {"echo","def", ">", "d"};
    testECHO3.addAll(Arrays.asList(testCasesECHO3));
    ECHO.execute(testECHO3);
    
    // echo abc > e
    ArrayList<String> testECHO4 = new ArrayList<String>();
    String[] testCasesECHO4 = {"echo","abc", ">", "e"};
    testECHO4.addAll(Arrays.asList(testCasesECHO4));
    ECHO.execute(testECHO4);
    
    // obtain files d and e
    file1= (File) JShell.getWorkingDir().getDirContents().get(0);
    File file2 = (File) JShell.getWorkingDir().getDirContents().get(1);
    
    // check that d's contents are overwritten
    assertEquals(file1.toString(), "d");
    assertEquals(file1.returnContents(), "def");
    // check that the previous echo's to d did not make a new file and that
    // e's contents and name are properly assigned
    assertEquals(file2.toString(), "e");
    assertEquals(file2.returnContents(), "abc");
  }

}
