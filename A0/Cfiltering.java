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
package a0;

import java.text.DecimalFormat;
import java.util.*;

public class Cfiltering {
  // this is a 2d matrix i.e. user*movie
  private int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*movie
  private float userUserMatrix[][];
  private int numOfUsers;
  private int numOfMovies;

  /**
   * Default Constructor.
   */
  public Cfiltering() {
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new float[1][1];
    numOfMovies = 1;
    numOfUsers = 1;
  }

  /**
   * Constructs an object which contains two 2d matrices, one of size
   * users*movies which will store integer movie ratings and one of size
   * users*users which will store float similarity scores between pairs of
   * users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
    userMovieMatrix = new int[numberOfUsers][numberOfMovies];
    userUserMatrix = new float[numberOfUsers][numberOfUsers];
    numOfUsers = numberOfUsers;
    numOfMovies = numberOfMovies;
  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input
   * parameters it takes in a rowNumber, columnNumber and a rating value. The
   * rating value is then inserted in the UserMovieMatrix at the specified
   * rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
   */
  public void populateUserMovieMatrix(int rowNumber, int columnNumber,
      int ratingValue) {
    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   */
  public void calculateSimilarityScore() {
    for (int j = 0; j < numOfUsers; j++) {
      for (int i = 0; i < numOfUsers; i++) {
        float similarityScore =
            calculateDistance(userMovieMatrix[j], userMovieMatrix[i]);
        float value = 1 / (similarityScore + 1);
        userUserMatrix[j][i] = value;
      }
    }
  }

  /**
   * Calculate the "distance" of a series of ratings.
   * 
   * @param ratings1 The first array of ratings
   * @param ratings2 The second array of ratings
   * @return float
   **/
  private float calculateDistance(int[] ratings1, int[] ratings2) {
    float distance = 0;
    for (int i = 0; i < numOfMovies; i++) {
      distance = distance + (float) Math.pow(ratings1[i] - ratings2[i], 2);
    }
    return (float) Math.sqrt(distance);
  }

  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   */
  public void printUserUserMatrix() {
    for (int i = 0; i < numOfUsers; i++) {
      StringBuilder stringToPrint = new StringBuilder("[");
      for (int j = 0; j < numOfUsers; j++) {
        DecimalFormat df = new DecimalFormat("0.0000");
        stringToPrint.append(df.format(userUserMatrix[i][j])).append(", ");
      }
      stringToPrint.toString();
      System.out.println(
          stringToPrint.substring(0, stringToPrint.length() - 2) + "]");
    }
  }

  /**
   * Helper function: mathematical definition of n factorial
   * 
   * @param n the number we are calculating the factorial of
   */
  private static int factorial(int n) {
    int result = 1;
    for (int i = 1; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  /**
   * This function finds and prints the most similar pair of users in the
   * userUserMatrix.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */
  public void findAndprintMostSimilarPairOfUsers() {
    // loop over the upper half of the matrix and identify the largest
    // entry
    int numberOfEntries = factorial(numOfUsers - 1);
    ArrayList<Float> upperElements = new ArrayList<Float>(numberOfEntries);
    for (int i = 0; i < numOfUsers - 1; i++) {
      for (int j = i + 1; j < numOfUsers; j++) {
        upperElements.add(userUserMatrix[i][j]);
      }
    }
    float maximum = Collections.max(upperElements);

    // loop over the upper half of the matrix once again and print all the
    // pairs whose value is equal to the largest one found above
    for (int i = 0; i < numOfUsers - 1; i++) {
      for (int j = i + 1; j < numOfUsers; j++) {
        if (userUserMatrix[i][j] == maximum) {
          String firstPiece = String.format("User%s and User%s", i + 1, j + 1);
          System.out.println(firstPiece);
        }
      }
    }
    DecimalFormat df = new DecimalFormat("0.0000");
    String secondPiece =
        String.format("with similarity score of %s", df.format(maximum));
    System.out.println(secondPiece);

  }

  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   * 
   * @param COMPLETE THIS IF NEEDED
   * @param COMPLETE THIS IF NEEDED
   * @return COMPLETE THIS IF NEEDED
   */
  public void findAndprintMostDissimilarPairOfUsers() {
    // loop over the upper half of the matrix and identify the smallest
    // entry
    int numberOfEntries = factorial(numOfUsers - 1);
    ArrayList<Float> upperElements = new ArrayList<Float>(numberOfEntries);
    for (int i = 0; i < numOfUsers - 1; i++) {
      for (int j = i + 1; j < numOfUsers; j++) {
        upperElements.add(userUserMatrix[i][j]);
      }
    }
    float maximum = Collections.min(upperElements);

    // loop over the upper half of the matrix once again and print all the
    // pairs whose value is equal to the smallest one found above
    for (int i = 0; i < numOfUsers - 1; i++) {
      for (int j = i + 1; j < numOfUsers; j++) {
        if (userUserMatrix[i][j] == maximum) {
          String firstPiece = String.format("User%s and User%s", i + 1, j + 1);
          System.out.println(firstPiece);
        }
      }
    }
    DecimalFormat df = new DecimalFormat("0.0000");
    String secondPiece =
        String.format("with similarity score of %s", df.format(maximum));
    System.out.println(secondPiece);

  }
}
