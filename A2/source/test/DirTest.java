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

import java.util.*;
import org.junit.Assert;
import org.junit.Test;

import driver.Dir;

import driver.File;

public class DirTest {
    
    /**
     * Method which test the Dir class createChildDir constructor.
     * All tests should pass.
     * 
     */
    @Test
    public void testCreateChildDir(){
      Dir test = Dir.createChildDir("Test");
      Assert.assertTrue(test instanceof Dir);
      }
   
    
    /**
     * Method which test the Dir class's constructor createMasterDir. 
     * All tests should pass.
     */
   
    @Test
    public void testCreateMasterDir(){
      Dir test = Dir.createMasterDir();
      Assert.assertTrue(test instanceof Dir);
      assertEquals(test.getParent(),null);
      }
    
    /**
     * Method which tests the setDirParent method of the Dir class.
     * All tests should pass.
     * 
     */
    
    @Test
    public void testSetDirParent(){
      Dir testDir = Dir.createChildDir("tester");
      Dir testParentDir = Dir.createChildDir("parent");
      testDir.setDirParent(testParentDir);
      assertEquals(testDir.getParent(), testParentDir);
    }
    
    /**
     * Method which tests the Dir class toString Method. All tests
     * should pass. 
     */
    @Test
    public void testToString(){
      Dir test = Dir.createChildDir("test");
      String name = "test";
      String actual = test.toString();
      assertEquals(name, actual);
    }
    
    
    /**Method which tests the Dir Class getParent Method. ALl tests should pass.
     * 
     */
    @Test
    public void testGetParent(){
      Dir tester = Dir.createChildDir("test");
      Dir testparent = Dir.createChildDir("parent");
      tester.setDirParent(testparent);
      assertEquals(tester.getParent(), testparent);
    }
    
    
    /**
     * Method which test the Dir class getDirChildren method. All tests
     * should pass.
     */
    @Test
    public void testGetDirChildren(){
      Dir test1 = Dir.createChildDir("d1");
      Dir test2 = Dir.createChildDir("d2");
      Dir test3 = Dir.createChildDir("d3");
      Dir test4 = Dir.createChildDir("d4");
      test1.storeDir(test2);
      test1.storeDir(test3);
      test1.storeDir(test4);
      ArrayList<Dir> children = new ArrayList<Dir>();
      children.add(test2);
      children.add(test3);
      children.add(test4);
      assertEquals(test1.getDirChildren(),children);
      }
    
     /**
     * Method which test the Dir class getDirContents method. All tests should
     * pass.
     */
    
    @Test
    public void testGetDirContents(){
      Dir test1 = Dir.createChildDir("test1");
      File a = new File("tester");
      test1.storeFile(a);
      ArrayList<File> childFiles = new ArrayList<File>();
      childFiles.add(a);
      assertEquals(test1.getDirContents(), childFiles);
    }
    
    
    /**
     * Method which test the Dir class storeDir method. All tests should pass.
     */
    @Test
    public void testStoreDir(){
      Dir test1 = Dir.createChildDir("test1");
      Dir test2 = Dir.createChildDir("test2");
      test1.storeDir(test2);
      ArrayList<Dir> children = new ArrayList<Dir>();
      children.add(test2);
      assertEquals(test1.getDirChildren(),children);
    }
    
    /**
     * Method which test the Dir class storeFile method. All tests should pass.
     */
    @Test
    public void testStoreFile(){
      Dir test1 = Dir.createChildDir("test1");
      File a = new File("tester");
      test1.storeFile(a);
      ArrayList<File> childFiles = new ArrayList<File>();
      childFiles.add(a);
      assertEquals(test1.getDirContents(),childFiles);
    }
}    