// **********************************************************
// Assignment0
// CDF user_name: c5lastel
// UT Student #: 1001237654
// Author: Lino Lastella
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************
package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import a0.Cfiltering;

public class CfilteringDriver {

  /**
   * Reads user movie ratings from a text file, calculates similarity scores and
   * prints a score matrix.
   */
  public static void main(String[] args) {
    try {

      // open file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // Read dimensions: number of users and number of movies
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());

      /*
       * create a new Cfiltering object that contains: a) 2d matrix
       * i.e.userMovieMatrix (#users*#movies) b) 2d matrix i.e. userUserMatrix
       * (#users*#users)
       */
      Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);

      // this is a blank line being read
      br.readLine();

      // read each line of movie ratings and populate the
      // userMovieMatrix
      String row;
      int rowNumber = 0;
      while ((row = br.readLine()) != null) {
        // allRatings is a list of all String numbers on one row
        String allRatings[] = row.split(" ");
        int columnNumber = 0;
        for (String singleRating : allRatings) {
          // make the String number into an integer
          // populate userMovieMatrix
          cfObject.populateUserMovieMatrix(rowNumber, columnNumber,
              Integer.valueOf(singleRating));
          columnNumber++;
        }
        rowNumber++;
      }
      // close the file
      fStream.close();

      cfObject.calculateSimilarityScore();

      System.out.println("\n\nuserUserMatrix is:");
      cfObject.printUserUserMatrix();
      System.out.println("\n");

      System.out.println("The most similar pairs of users from above "
          + "userUserMatrix are:");
      cfObject.findAndprintMostSimilarPairOfUsers();
      System.out.println("\n");
      System.out.println("The most dissimilar pairs of users from above "
          + "userUserMatrix are:");
      cfObject.findAndprintMostDissimilarPairOfUsers();

    } catch (FileNotFoundException e) {
      System.err.println("Do you have the input file in the root folder "
          + "of your project?");
      System.err.print(e.getMessage());
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }

}
