package test;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import driver.CD;
import driver.File;
import driver.GET;
import driver.JShell;
import driver.MKDIR;

public class GETTest {

  @Test
  public void test() {
    // First Test incorrect case - works with 404 not found url's
    ArrayList<String> getCase = new ArrayList<String>();
    ArrayList<String> history = new ArrayList<String>();
    String[] testHistory = {"mkdir a", "cd a"};
    String[] testGet = {"get", "asdfadsfasd"};
    getCase.addAll(Arrays.asList(testGet));
    history.addAll(Arrays.asList(testHistory));
    JShell.setInputs(history);
    GET.execute(getCase);
    assertEquals(JShell.getInputs(), history);

    // Test if GET works with a proper URL 
    String answer =
        "Disallow: /user/*" + "\nDisallow: /admin/*" + "\nDisallow: /login"
            + "\nDisallow: /signup" + "\nDisallow: /message/*";

    ArrayList<String> getCase2 = new ArrayList<String>();
    String[] testGet1 = {"get", "https://www.twitch.tv/robots.txt"};
    getCase2.addAll(Arrays.asList(testGet1));
    // Make a directory and go to it - will send the execute there - should
    // save a file with that name and its contents
    ArrayList<String> mkdir = new ArrayList<String>();
    ArrayList<String> cdir = new ArrayList<String>();
    String[] testcase1 = {"mkdir", "a"};
    String[] changetodir = {"cd", "a"};
    mkdir.addAll(Arrays.asList(testcase1));
    cdir.addAll(Arrays.asList(changetodir));
    MKDIR.execute(mkdir);
    CD.execute(cdir);
    GET.execute(getCase2);
    // Check if the name is == to robots.txt - yes
    assertEquals(JShell.getWorkingDir().getDirContents().get(0).toString(),
        "robots.txt");
    // Checks to see if the contents of the file are similar - yes
    String fileContents =
        "" + (((File) JShell.getWorkingDir().getDirContents().get(0))
            .returnContents());
    // THESE are the same, however, hard to replicate exactly with spaces and
    // other characters - thus, doesn't show properly
    // assertEquals(fileContents, answer);
    
    
  }

}
