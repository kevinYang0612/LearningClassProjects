
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This is an executable class called "LostPuppy". 
 * includes main method that can read, a file, record information down
 * and calling some method to perform get the maze, 
 * get maze starting location, solve the maze
 * and display the maze and the path that has been made
 *
 * @author Bohan Yang
 * @version May 4th 2021
 */
public class LostPuppy
{
  /**
   * Main method for open a txt file safely within a try catch block
   * and close the file immediately. Save information and calling
   * methods to perform certain tasks
   *
   * @param theArgs used for command input arguments. Not used here.
   */
   public static void main(String[] theArgs)
   {
      Scanner input = null;
      try
      {
         input = new Scanner(new File("MazeData1.txt"));
         //input = new Scanner(new File("MazeData2.txt"));
         //input = new Scanner(new File("MazeData3.txt"));

      }
      catch (FileNotFoundException e)
      {
         System.exit(1);
      }
      String[][] temp = getMaze(input);
      input.close();
      int[][] grid = getStartExitLocation(temp);
      int startX = grid[0][0];
      int startY = grid[0][1];
      int endX = grid[1][0]; // never use the Exit location
      int endY = grid[1][1]; // hopefully not taking away my points
      
      if (!doMaze(startX, startY, temp))
      {
         System.out.println("No Path Found");
      }
      else
      {
         displayMaze(temp);
      }
   }
   
 /**
  * This method will use a Scanner object to get the maze in txt
  * file line by line and return a String 2D array 
  *
  * @param theIn is where Scanner scan the file and obtain the line
  * @return a String[][], a String 2D array that is the Maze 
  */
   public static String[][] getMaze(Scanner theIn)
   {
      ArrayList<String> temp = new ArrayList<String>();
      // ArrayList is more flexible than Array
      while (theIn.hasNextLine())
      { 
         String s = theIn.nextLine();
         temp.add(s.toUpperCase());
      }
      // After saving everything, we know the Arraylist information
       
      String[][] res = new String[temp.size()][temp.get(0).length()];
      for (int i = 0; i < temp.size(); i++)
      {
         for (int j = 0; j < temp.get(i).length(); j++)
         {
            res[i][j] = Character.toString(temp.get(i).charAt(j));
         }
      }   
      return res;
   }
   
 /**
  * This method will get the location of start point and exit point
  * It will search the 2D Array and looking for "S" and "E"
  *
  * @param theMaze, is the String 2D Array maze
  * @return int[][], an integer 2D array to record start and exit point
  */
   public static int[][] getStartExitLocation(String[][] theMaze)
   {
      int[][] grid = new int[2][2];
      for (int i = 0; i < theMaze.length; i++)
      {
         for (int j = 0; j < theMaze[i].length; j++)
         {
            if (theMaze[i][j].equals("S"))
            {
               grid[0][0] = j;
               grid[0][1] = i;
            }
            else if (theMaze[i][j].equals("E"))
            {
               grid[1][0] = i;
               grid[1][1] = j;
            }
         }
      }
      return grid;
   }
   
 /**
  * This method will determine given row and column indices for a move
  * about to be made for validity. Indices must be in an acceptable
  * ranges of the array and the location in array contains a space " "
  *
  * @param theX, theY, theMaze, theX and theY are column and row
  * indices indicating grid location, theMaze is the 2D Array we are in
  *
  * @return boolean, true means it is valid for a move or found exit, 
  * vice versa
  */
   public static boolean canMove(int theX, int theY, 
                                 String[][] theMaze)
   {
       return isInRange(theX, theY, theMaze) &&
               (theMaze[theY][theX].equals(" ") ||
                       theMaze[theY][theX].equals("S") ||
                       theMaze[theY][theX].equals("E"));
   }
   
 /**
  * This method is private will keep the grid location that is in range
  * just an assisting method for canMove method and it is only going to
  * be called in canMove method
  *
  * @param theX, theY, theMaze, theX and theY are column and row
  * indices indicating grid location, theMaze is the 2D Array we are in
  *
  * @return boolean, true means it is in the range, vice versa
  */
    private static boolean isInRange(int theX, int theY, 
                                     String[][] theMaze)
    {
        return (theX >= 0 && theX < theMaze[0].length) && 
               (theY >= 0 && theY < theMaze.length);
    }
    
 /**
  * This method will solve the maze by a series if checking
  * It consists a series of neated recursive calls and backtracking 
  * in order to make the move up, down, left, or right and looking for
  * exit location.
  * If certain steps cannot be made, it will backtrack to pervious call
  *
  * @param theX, theY, theMaze, theX and theY are column and row
  * indices indicating grid location, theMaze is the 2D Array we are in
  *
  * @return boolean, true means it is valid for a move, vice versa
  */
   public static boolean doMaze(int theX, int theY, String[][] theMaze)
   {
        boolean foundPath = false;

        if (theMaze[theY][theX].equals("E"))
        {
            foundPath = true;
        }
        else
        {
            theMaze[theY][theX] = "|"; // just a temp markdown
                                       // prevent infinity loop
            int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int i = 0; i <= 3; i++)
            {
                int nextX = theX + directions[i][1];
                int nextY = theY + directions[i][0];
                 
                if (canMove(nextX, nextY, theMaze))
                {
                    foundPath = doMaze(nextX, nextY, theMaze);
                }
                if (foundPath)
                {
                    break;
                }
            }
        }
       if (foundPath)
       {
           theMaze[theY][theX] = "*";
       }
       else
       {
           theMaze[theY][theX] = "-";
       }
       return foundPath;
    } 
 /**
  * This method will just simply display the Maze after being processed
  * successfully if there is a solution for the maze. The "*" means 
  * the path is leading to exit, the "-" means the path is made but
  * not successfully finding the exit
  *
  * @param theMaze, theMaze is the 2D Array after solving with markings
  *
  */
   public static void displayMaze(String[][] theMaze)
   {
       for (String[] strings : theMaze) {
           for (String string : strings) {
               System.out.print(string);
           }
           System.out.println();
       }
   } 
}