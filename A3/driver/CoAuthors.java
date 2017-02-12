package driver;

import java.util.TreeSet;

public class CoAuthors {

  // This data type implements the SortedSet interface, which is a sub-interface
  // of Collection.
  public TreeSet<String> peopleTree = new TreeSet<String>();

  // Regular expression for finding all the co-authors in a given web page.
  private static final String COAUTHORSREGEX =
      "<a class=\"cit-dark-link\" href=\"(.*?)\" title=\"(.*?)\">\\2</a>";


  public CoAuthors(String url) {

    this.setPeopleTree(url);

  }


  private void setPeopleTree(String url) {
    String name = "";
    try {
      int counter = 1;
      while (true) {
        name = MyParser.extract(url, COAUTHORSREGEX, counter, 2);
        counter++;
        if (name.equals("")) {
          break;
        } else {
          this.peopleTree.add(name);
        }
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
