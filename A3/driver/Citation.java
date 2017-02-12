package driver;

import java.util.ArrayList;

public class Citation {

  public String allCitations;
  public String i10Index;
  public ArrayList<String> numOfCitationsList = new ArrayList<String>();
  private Publications relatedPublications;

  // Regular Expressions associated with the Citation part of the web page.
  private static final String CITATIONSTABLEREGEX =
      "<td class=\"cit-borderleft" + " cit-data\">([0-9]*?)</td>";

  private static final String TOTALCITATIONREGEX =
      "<a class=\"cit-dark-link\" href=\"(.*?)\">([0-9]*?)</a>";

  // Constructor of Citation given an url.
  public Citation(String url) {
    relatedPublications = new Publications(url);
    this.setAllCitations(url);
    this.seti10Index(url);
    this.setCitationsList(url, relatedPublications.totalPublications);
  }

  private void setCitationsList(String url, int n) {
    String num = "";
    try {
      int counter = 1;
      while (counter != n + 2) {
        num = MyParser.extract(url, TOTALCITATIONREGEX, counter, 2);
        this.numOfCitationsList.add(num);
        counter++;
      }
      while (numOfCitationsList.contains("")) {
        numOfCitationsList.remove("");
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private void seti10Index(String url) {
    String name = "";
    try {
      name = MyParser.extract(url, CITATIONSTABLEREGEX, 6, 1);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.i10Index = name;
  }

  private void setAllCitations(String url) {
    String name = "";
    try {
      name = MyParser.extract(url, CITATIONSTABLEREGEX, 1, 1);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    this.allCitations = name;
  }
}
