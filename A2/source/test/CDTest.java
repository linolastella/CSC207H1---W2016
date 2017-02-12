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

public class CDTest {
  
    /**
     * Method which tests CD class testExecute method. All test should pass.
     * 
     */
  
  @Test
  
  public void testExecute() {
    //TEST CASE 1 - BASIC
    ArrayList<String> testMKDIR= new ArrayList<String>();
    //Directories made for testCase1 "a" and "b"
    String[] testCasesMKDIR1 = {"mkdir","a","b","d"};
    testMKDIR.addAll(Arrays.asList(testCasesMKDIR1));
    MKDIR.execute(testMKDIR); //execute command requires an ArrayList
    
    // Test to see if the directory stays the same as "MASTER" instead of
    //"c" as it has not been made. 
    // ArrayList<String> testCD1 = new ArrayList<String>();
    // String[] testCasesCD1 = {"cd","c"};
    // testCD1.addAll(Arrays.asList(testCasesCD1));
    // CD.execute(testCD1);
    // assertEquals(JShell.getWorkingDir().toString(),"MASTER");      
    
    //Test to see if the working directory changes to "a"
    ArrayList<String> testCD2 = new ArrayList<String>();
    String[] testCasesCD2 = {"cd","a"};
    testCD2.addAll(Arrays.asList(testCasesCD2));
    CD.execute(testCD2);
    assertEquals(JShell.getWorkingDir().toString(),"a");
    
    //Test to see if the ".." command works (goes back one directory)
    ArrayList<String> testCD3 = new ArrayList<String>();
    String[] testCasesCD3 = {"cd",".."};
    testCD3.addAll(Arrays.asList(testCasesCD3));
    CD.execute(testCD3);
    assertEquals(JShell.getWorkingDir().toString(),"MASTER");
    
    //TEST CASE 2- ADVANCED
    ArrayList<String> testMKDIR2= new ArrayList<String>();
    String[] testCasesMKDIR2 = {"mkdir","a/2","b/2","d/2"};
    testMKDIR2.addAll(Arrays.asList(testCasesMKDIR2));
    MKDIR.execute(testMKDIR2); //execute command requires an ArrayList
    
    // Test to see if b/2/3 command is faulty and working directory will
    // stay at MASTER
    // ArrayList<String> testCD4 = new ArrayList<String>();
    // String[] testCasesCD4 = {"cd","b/2/3"};
    // testCD4.addAll(Arrays.asList(testCasesCD4));
    // CD.execute(testCD4);
    // assertEquals(JShell.getWorkingDir().toString(),"MASTER"); 
    
    //Test to see if chaining commands b/2 works - should go to "2" directory
    ArrayList<String> testCD5 = new ArrayList<String>();
    String[] testCasesCD5 = {"cd","b/2"};
    testCD5.addAll(Arrays.asList(testCasesCD5));
    CD.execute(testCD5);
    assertEquals(JShell.getWorkingDir().toString(),"2");  
    
    //Test to see if chainning special commands is viable 
    ArrayList<String> testCD6 = new ArrayList<String>();
    String[] testCasesCD6 = {"cd","../../d"};
    testCD6.addAll(Arrays.asList(testCasesCD6));
    CD.execute(testCD6);
    assertEquals(JShell.getWorkingDir().toString(),"d");  
    

  }

}
