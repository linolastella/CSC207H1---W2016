package test;

import static org.junit.Assert.*;
import java.util.TreeSet;
import org.junit.Test;
import driver.CoAuthors;


public class CoAuthorsTest {


  @Test
  public void testSetPeopleTree() {
    String url = "sample1.html";
    CoAuthors ca = new CoAuthors(url);
    TreeSet<String> actual = ca.peopleTree;
    TreeSet<String> expected = new TreeSet<String>();
    expected.add("Egon Willighagen");
    expected.add("Jonathan Alvarsson");
    expected.add("Christoph Steinbeck");
    expected.add("Nina Jeliazkova");
    expected.add("Rajarshi Guha");
    expected.add("Sam Adams");
    expected.add("Janna Hastings");
    expected.add("Samuel Lampa");
    expected.add("Valentin Georgiev");
    expected.add("Adam Ameur");
    expected.add("Komorowski Jan");
    expected.add("gilleain torrance");
    expected.add("Antony John Williams");
    expected.add("Noel M. O'Boyle");
    expected.add("Sean Ekins");
    assertArrayEquals(actual.toArray(), expected.toArray());
  }
}
