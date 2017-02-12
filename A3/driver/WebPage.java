package driver;

public class WebPage {

  // This class collaborates with many classes.
  private Author author;
  private Citation citation;
  private CoAuthors co_authors;
  private Publications publications;

  // Create a WebPage object given a valid url.
  public WebPage(String url) {
    this.setAuthor(new Author(url));
    this.setCitation(new Citation(url));
    this.setCo_authors(new CoAuthors(url));
    this.setPublication(new Publications(url));
  }


  private void setAuthor(Author a) {
    this.author = a;
  }


  private void setCitation(Citation cit) {
    this.citation = cit;
  }


  private void setPublication(Publications publ) {
    this.publications = publ;
  }


  public String getAuthor() {
    return this.author.name;
  }


  public String getNumberOfCitations() {
    return this.citation.allCitations;
  }


  public String getNumberOfi10Index() {
    return this.citation.i10Index;
  }


  public int getTotalNumberCoAuthors() {
    return this.getCo_authors().peopleTree.size();
  }


  public CoAuthors getCo_authors() {
    return co_authors;
  }


  public void setCo_authors(CoAuthors co_authors) {
    this.co_authors = co_authors;
  }


  public int getSumFirstNCitations(int N) {
    int totalSum = 0;
    for (String numOfCitations : this.citation.numOfCitationsList.subList(0,
        N)) {
      int num = Integer.parseInt(numOfCitations);
      totalSum = totalSum + num;
    }
    return totalSum;
  }


  public String getPublicationsTitle(int n) {
    StringBuilder finalResult = new StringBuilder();
    for (String publ : this.publications.publicationsList.subList(0, n)) {
      if (publ != null) {
        finalResult.append(publ + "\n");
      }
    }
    return finalResult.toString();
  }
}
