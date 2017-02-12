package driver;

public class Author {

  public String name;

  // Regular Expression for finding an author in an html string.
  private static final String AUTHORREGEX = "<span id=\"cit-name-display\" "
      + "class=\"cit-in-place-nohover\">(.*?)</span>";

  public Author(String url) {

    this.setName(url);

  }

  // Extract the name from a given url.
  public void setName(String url) {
    String name = "";
    try {
      name = MyParser.extract(url, AUTHORREGEX, 1, 1);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.name = name;
  }
}
