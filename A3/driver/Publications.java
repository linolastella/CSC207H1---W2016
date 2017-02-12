package driver;

import java.util.ArrayList;

public class Publications {

  public ArrayList<String> publicationsList = new ArrayList<String>();
  public int totalPublications;  // I need this in the citation class.

  // Regular Expression for finding an author in an html string.
  private static final String PUBLICATIONSREGEX =
      "<a href=\"(.*?)\" class=" + "\"cit-dark-large-link\">(.*?)</a>";
  
  // Regular Expression for the total number of publications.
  private static final String TOTALPUBLICATIONS = "<span style=\"font-weight: "
      + "bold; margin: 0 0.5em 0 0.5em;\">\\d+?-(\\d+?)</span>";
  
  
  // Constructor for Publications given an url.
  public Publications(String url) {
    totalPublications = Integer.parseInt(getTotalPublications(url));
    this.setPublicationsList(url, totalPublications);
  }

  
  private void setPublicationsList(String url, int n) {
    String name = "";
    try {
      int counter = 1;
      while (counter != n + 2) {
        name = MyParser.extract(url, PUBLICATIONSREGEX, counter, 2);
        this.publicationsList.add(name);
        counter++;
      }
      while (publicationsList.contains("")) {
        publicationsList.remove("");
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  private String getTotalPublications(String url) {
    String num = "";
    try {
      num = MyParser.extract(url, TOTALPUBLICATIONS, 1, 1);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return num;
  }
}
