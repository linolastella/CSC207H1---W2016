package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import driver.CD;
import driver.HISTORY;
import driver.JShell;
import driver.MKDIR;
import driver.MV;

public class MVTest {
  /**
   * Test to see if move class works.
   **/


  @Test
  public void testExecute() {
    // Test 1 - Check if invalid test cases are put in - then should'nt show
    // in history
    // Make directories a and b
    ArrayList<String> mkdir1 = new ArrayList<String>();
    String[] testMkdir1 = {"mkdir", "a", "b"};
    mkdir1.addAll(Arrays.asList(testMkdir1));
    MKDIR.execute(mkdir1);
    // Add them into our history
    ArrayList<String> history1 = new ArrayList<String>();
    String[] testHistory1 = {"mkdir a b", "history"};
    history1.addAll(Arrays.asList(testHistory1));
    JShell.setInputs(history1);
    ArrayList<String> testHISEXC = new ArrayList<String>();
    testHISEXC.add("history");
    HISTORY.execute(testHISEXC);
    // Test Move 1
    ArrayList<String> move1 = new ArrayList<String>();
    String[] testMove1 = {"mv", "c", "a"};
    move1.addAll(Arrays.asList(testMove1));
    // the directory c doesn't exist - invalid path - thus will not execute
    MV.execute(move1);
    // Invalid path - thus doesn't add it to history
    assertEquals(JShell.getInputs(), history1);
    
    // Test 2 - check to see if move class works
    // Test to see if a valid path exists - does it move it and removes it from
    // its previous location
    ArrayList<String> history2 = new ArrayList<String>();
    String[] testHistory2 = {"mkdir a b", "mv b a", "history"};
    history2.addAll(Arrays.asList(testHistory2));
    JShell.setInputs(history2);
    ArrayList<String> testHISEXC2 = new ArrayList<String>();
    testHISEXC2.add("history");
    // Move b in to a
    ArrayList<String> move2 = new ArrayList<String>();
    String[] testMove2 = {"mv", "b", "a"};
    move2.addAll(Arrays.asList(testMove2));
    MV.execute(move2);
    HISTORY.execute(testHISEXC2);
    // Checks to see if move has been executed
    assertEquals(JShell.getInputs(), history2);
    // check to see if b is no longer in our current directory - as it was moved
    assertEquals(JShell.getWorkingDir().getDirChildren().get(0).toString(),
        "a");
    // Go to the directory a - then see if b is in it
    ArrayList<String> testCD1 = new ArrayList<String>();
    String[] testCasesCD1 = {"cd", "a"};
    testCD1.addAll(Arrays.asList(testCasesCD1));
    CD.execute(testCD1);
    // Check to see if b is in a
    assertEquals(JShell.getWorkingDir().getDirChildren().get(0).toString(),
        "b");
  }

}
