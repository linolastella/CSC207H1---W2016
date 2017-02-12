// **********************************************************
// Assignment3:
// CDF user_name: c5lastel
//
// Author: Lino Lastella
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// *********************************************************
package driver;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringEscapeUtils;

public class MyParser {

  /**
   * @param args
   */
  public static void main(String[] args) {
    StringBuilder output = new StringBuilder();
    StringBuilder coAuthors = new StringBuilder();
    TreeSet<String> finalcoAuthorsTree = new TreeSet<String>();
    String inputFiles[] = args[0].split(",");

    for (String inputFile : inputFiles) {
      WebPage wb = new WebPage(inputFile);
      output.append("--------------------------------------------------------"
          + "---------------\n1. Name of Author:\n  " + wb.getAuthor() + "\n2."
          + " Number of All Citations:\n  " + wb.getNumberOfCitations()
          + "\n3. Number of i10-index after 2009:\n  "
          + wb.getNumberOfi10Index()
          + "\n4. Title of the first 3 publications:\n"
          + wb.getPublicationsTitle(3)
          + "5. Total paper citation (first 5 papers):\n  "
          + wb.getSumFirstNCitations(5) + "\n6. Total Co-Authors:\n  "
          + wb.getTotalNumberCoAuthors() + "\n");
      for (String person : wb.getCo_authors().peopleTree) {
        finalcoAuthorsTree.add(person);
      }
    }

    for (String name : finalcoAuthorsTree) {
      coAuthors.append(name + "\n");
    }

    if (args.length == 2) {
      FileWriter.writeFile(OutputFormatter.formatString(output,
          finalcoAuthorsTree.size(), coAuthors), args[1]);
    } else {
      System.out.println(OutputFormatter.formatString(output,
          finalcoAuthorsTree.size(), coAuthors));
    }
    System.out.println(OutputFormatter.formatString(output,
        finalcoAuthorsTree.size(), coAuthors));
  }


  /*
   * This is a helper method. It extracts the string given an html string input,
   * a regular expression and the group input.
   * 
   * @param url: the given url for the web page.
   * 
   * @param re: a regular expression to extract the requested substring.
   * 
   * @param numOfFinds: the number of times we need to call .find()
   * 
   * @param g: the group number.
   */
  public static String extract(String url, String re, int numOfFinds, int g) {

    String match = "";
    try {
      String rawHTMLString =
          StringEscapeUtils.unescapeHtml3(MyParser.getHTML(url));
      Pattern patternObject = Pattern.compile(re);
      Matcher matcherObject = patternObject.matcher(rawHTMLString);
      int counter = 1;
      while (counter < numOfFinds) {
        counter++;
        matcherObject.find();
      }
      if (matcherObject.find()) {
        match = matcherObject.group(g);
      }
    } catch (Exception e) {
      System.out
          .println("malformed URL or cannot open connection to given URL");
    }
    return match;
  }


  /*
   * This is an helper method. Given a valid url it returns the html string.
   */
  public static String getHTML(String urlString) throws Exception {
    // create object to store html source text as it is being collected
    StringBuilder html = new StringBuilder();
    // open connection to given url
    URL url = new File(urlString).toURI().toURL();
    // create BufferedReader to buffer the given url's HTML source
    BufferedReader htmlbr =
        new BufferedReader(new InputStreamReader(url.openStream()));
    String line;
    // read each line of HTML code and store in StringBuilder
    while ((line = htmlbr.readLine()) != null) {
      html.append(line);
    }
    htmlbr.close();
    // convert StringBuilder into a String and return it
    return html.toString();
  }
}
