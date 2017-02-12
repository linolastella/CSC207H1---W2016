package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import driver.Publications;

public class PublicationsTest {
  
  
  @Test
  public void testSetPublicationsList() {
    String url = "rs.html";
    Publications publ = new Publications(url);
    ArrayList<String> actual = publ.publicationsList;
    ArrayList<String> expected = new ArrayList<String>();
    expected.add("On fast exploration in 2D and 3D terrains with multiple "
        + "robots");
    expected.add("Sonic Grid: an auditory interface for the visually impaired "
        + "to navigate GUI-based environments");
    expected.add("Multi robotic exploration with communication requirement to"
        + " a fixed base station");
    expected.add("Covering hostile terrains with partial and complete "
        + "visibilities: On minimum distance paths");
    expected.add("On reduced time fault tolerant paths for multiple UAVs "
        + "covering a hostile terrain");
    assertArrayEquals(expected.toArray(), actual.toArray());
  }
  
  
  @Test
  public void testGetTotalPublications(){
    String url = "rs.html";
    Publications publ = new Publications(url);
    int totalNumberOfPublications = 5;
    int actual = publ.totalPublications;
    assertEquals(actual, totalNumberOfPublications);
  }
}
