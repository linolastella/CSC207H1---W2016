package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import driver.CD;
import driver.CP;
import driver.HISTORY;
import driver.JShell;
import driver.MKDIR;

public class CPTest {
  /**
   * Test cases to see if the copy command works 
   **/

  @Test
  public void test() {
    // Test 1
    // Test to see if path doesn't exist - if it will not execute - will not
    // show in history
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
    // Test Move 1
    ArrayList<String> cp1 = new ArrayList<String>();
    String[] testCp1 = {"cp", "c", "a"};
    cp1.addAll(Arrays.asList(testCp1));
    // the directory c doesn't exist - invalid path - thus will not execute
    CP.execute(cp1);
    HISTORY.execute(testHISEXC);
    // Invalid path - thus doesn't add it to history
    assertEquals(JShell.getInputs(), history1);

    // Test 2 - check to see if move class works
    // Test to see if a valid path exists - does it move it and removes it from
    // its previous location
    ArrayList<String> history2 = new ArrayList<String>();
    String[] testHistory2 =
        {"mkdir a b", "cd b", "mkdir c", "cp /b /a", "history"};
    history2.addAll(Arrays.asList(testHistory2));
    JShell.setInputs(history2);
    ArrayList<String> testHISEXC2 = new ArrayList<String>();
    testHISEXC2.add("history");

    // Go to the directory b - and add a new directory named c
    ArrayList<String> testCD1 = new ArrayList<String>();
    String[] testCasesCD1 = {"cd", "b"};
    testCD1.addAll(Arrays.asList(testCasesCD1));
    CD.execute(testCD1);
    ArrayList<String> mkdir2 = new ArrayList<String>();
    String[] testMkdir2 = {"mkdir", "c"};
    mkdir2.addAll(Arrays.asList(testMkdir2));
    MKDIR.execute(mkdir2);

    // Copy the /b into /a
    ArrayList<String> copy2 = new ArrayList<String>();
    String[] testCopy2 = {"cp", "/b", "/a"};
    copy2.addAll(Arrays.asList(testCopy2));
    CP.execute(copy2);
    HISTORY.execute(testHISEXC2);
    // Checks to see if move has been executed
    assertEquals(JShell.getInputs(), history2);
    // Check to see if /b and all its contents are not removed
    assertEquals(JShell.getWorkingDir().toString(), "b");
    assertEquals(JShell.getWorkingDir().getDirChildren().get(0).toString(),
        "c");

    // Go and check if /b and all its contents have been copied to /a
    ArrayList<String> testCD2 = new ArrayList<String>();
    String[] testCasesCD2 = {"cd", "/a"};
    testCD2.addAll(Arrays.asList(testCasesCD2));
    CD.execute(testCD2);
    // Check if b is now in a
    assertEquals(JShell.getWorkingDir().getDirChildren().get(0).toString(),
        "b");
    // Check if c is in b
    ArrayList<String> testCD3 = new ArrayList<String>();
    String[] testCasesCD3 = {"cd", "b"};
    testCD3.addAll(Arrays.asList(testCasesCD3));
    CD.execute(testCD3);
    assertEquals(JShell.getWorkingDir().getDirChildren().get(0).toString(),
        "c");
  }

}
