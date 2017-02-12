package driver;

public class OutputFormatter {

  public static String formatString(StringBuilder output, int Total,
      StringBuilder coAuthors) {
    
    return output + "\n-----------------------------------"
        + "------------------------------------\n7. Co-Author list sorted "
        + "(Total: " + Total + "):\n" + coAuthors;
  }
}
