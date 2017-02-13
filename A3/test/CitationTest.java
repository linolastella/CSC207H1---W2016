package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import driver.Citation;


public class CitationTest {
  

  @Test
  public void testSetCitationsList() {    
    String url = "sample1.html";
    Citation cit = new Citation(url);
    ArrayList<String> citationsList = new ArrayList<String>();
    citationsList.add("88");
    citationsList.add("41");
    citationsList.add("37");
    citationsList.add("37");
    citationsList.add("36");
    citationsList.add("26");
    citationsList.add("25");
    citationsList.add("24");
    citationsList.add("17");
    citationsList.add("12");
    citationsList.add("11");
    citationsList.add("10");
    citationsList.add("9");
    citationsList.add("8");
    citationsList.add("6");
    citationsList.add("5");
    citationsList.add("5");
    citationsList.add("4");
    citationsList.add("4");
    citationsList.add("4");    
    ArrayList<String> expected = cit.numOfCitationsList;
    assertEquals(citationsList.size(), expected.size());
    }
  
  @Test
  public void testSeti10Index() {
    String url = "sample1.html";
    Citation cit = new Citation(url);
    String i10Index = "12";
    assertEquals(i10Index, cit.i10Index);
  }
  
  @Test
  public void testSetAllCitations() {
    String url = "sample1.html";
    Citation cit = new Citation(url);
    String i10Index = "437";
    assertEquals(i10Index, cit.allCitations);
  }
}
