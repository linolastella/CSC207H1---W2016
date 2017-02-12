<<<<<<< .mine
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
// *********************************************************/**
=======
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

>>>>>>> .r179
package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import driver.CD;
import driver.JShell;
import driver.MKDIR;

import java.util.ArrayList;
import java.util.Arrays;



public class MKDIRTest {
    /**
     * Method which tests the execute method of the MKDIR class.
     */
  @Test
  public void testExecute() {
    // Case 1: creating one new directory
    // make a directory named "a" and change into that directory
    ArrayList<String> makdir = new ArrayList<String>();
    ArrayList<String> chdir = new ArrayList<String>();
    String[] testcase1 = {"mkdir", "a"};
    String[] changetodir = {"cd", "a"};
    makdir.addAll(Arrays.asList(testcase1));
    chdir.addAll(Arrays.asList(changetodir));
    
    // see if the directory made is the current directory
    MKDIR.execute(makdir);
    CD.execute(chdir);
    assertEquals(JShell.getWorkingDir().toString(),"a");
    
    }

}
