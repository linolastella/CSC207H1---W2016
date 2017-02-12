package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import driver.CD;
import driver.JShell;
import driver.MKDIR;
import driver.NUMBER;

public class NUMBERTest {

  @Test
  public void testExecute() {
    // First Test:
    // Checks to see if an invalid number is entered (<1) - it will not show
    // up on history and will not be performed
    ArrayList<String> testNumber = new ArrayList<String>();
    String[] testNumberCase = {"number", "0"};
    testNumber.addAll(Arrays.asList(testNumberCase));
    ArrayList<String> history = new ArrayList<String>();
    String[] testHistoryCase = {"mkdir a", "cd a", "pwd"};
    history.addAll(Arrays.asList(testHistoryCase));
    JShell.setInputs(history);
    // After this is executed - the history should not have this - as it is
    // invalid
    NUMBER.execute(testNumber);
    assertEquals(JShell.getInputs(), history);

    // Second Test:
    // If a valid number is entered, it should be added to the history and
    // it should re-prompt that command
    ArrayList<String> testNumber2 = new ArrayList<String>();
    String[] testNumberCase2 = {"number", "1"};
    testNumber2.addAll(Arrays.asList(testNumberCase2));
    ArrayList<String> history2 = new ArrayList<String>();
    String[] testHistoryCase2 = {"mkdir a", "cd a", "pwd", "!1"};
    history2.addAll(Arrays.asList(testHistoryCase2));
    JShell.setInputs(history2);
    // After this, !1 should be added to the history - as it is a valid command
    NUMBER.execute(testNumber2);
    assertEquals(JShell.getInputs(), history2);

    // Checks after !1 is executes - a new dir named a is made in /a/
    // mkdir a
    // cd a
    // !1
    // Then there would be a dirctory a would be made in /a/
    ArrayList<String> mkdir = new ArrayList<String>();
    ArrayList<String> cdir = new ArrayList<String>();
    String[] testcase1 = {"mkdir", "a"};
    String[] changetodir = {"cd", "a"};
    mkdir.addAll(Arrays.asList(testcase1));
    cdir.addAll(Arrays.asList(changetodir));
    MKDIR.execute(mkdir);
    CD.execute(cdir);

    ArrayList<String> testNumber3 = new ArrayList<String>();
    String[] testNumberCase3 = {"number", "1"};
    testNumber3.addAll(Arrays.asList(testNumberCase3));
    NUMBER.execute(testNumber3);
    // New directory a made in our current working directory /a/
    assertEquals(JShell.getWorkingDir().getDirChildren().get(0).toString(),
        "a");


  }

}
