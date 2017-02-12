package test;

import static org.junit.Assert.*;
import org.junit.Test;
import driver.Author;

public class AuthorTest {

  @Test
  public void testSetName() {
    String url = "sample1.html";
    String guysName = "Ola Spjuth";
    Author author = new Author(url);
    assertEquals(guysName, author.name);
  }
}
