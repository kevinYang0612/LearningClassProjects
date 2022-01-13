
import java.util.Random;
import java.util.Scanner;


public class LoneSurvivorPlay{

  /**
   * Driver program to play LoneSurvivor.
   *
   * @param theArgs may contain file names in an array of type String
   */
  public static void main(String[] theArgs){
    Scanner s = new Scanner(System.in);
    LoneSurvivor game = null;
    findThatSurvivor(s, game);
  }

  /**
   * Allows the users to locate a survivor in destroyed buildings.
   *
   * @param theS to the console.
   * @param theGame LoneSurvivor variable set to null.
   */
  public static void findThatSurvivor(Scanner theS,
                                      LoneSurvivor theGame) {
    int floor;
    int room;
    char[] players = {'1', '2'};
    int playerIndex;
    boolean found = false;
    Random rand = new Random();
    do {
      // Start the game: Create a LoneSurvivor object:
      theGame = new LoneSurvivor(getTotalFloors(theS),
                                 getTotalRooms(theS));
      // Pick starting player
      playerIndex = rand.nextInt(2);
      System.out.println("\nFloor and room numbers start at zero '0'");

      do {
        //  Start Searching:
        do {
          System.out.println("\nPlayer " + players[playerIndex] +
                             ", enter floor and room to search " +
                             "separated by a space: ");
          floor = theS.nextInt();
          room = theS.nextInt();
          //for testing, use random generation of floor and room
          //floor = rand.nextInt(totalFloors);
          //room = rand.nextInt(totalRooms);
        } while (!theGame.indicesOK(floor, room)
                 || theGame.roomSearchedAlready(floor, room));

        found = theGame.searchRoom(floor, room, players[playerIndex]);
        playerIndex = (playerIndex + 1) % 2;
        displayCurrentSearch(theS, theGame, floor, room);
      } while (!found);
      playerIndex = (playerIndex + 1) % 2;
    } while (playAnother(theS, players[playerIndex]));
  }

  /**
   * Displays the current player and room search in the building.
   *
   * @param theS Scanner to the console.
   * @param theGame LoneSurvivor object at its current state.
   * @param theFloor integer representing the floor searched.
   * @param theRoom integer representing the room searched.
   */
  public static void displayCurrentSearch(Scanner theS,
                                          LoneSurvivor theGame,
                                          int theFloor,
                                          int theRoom) {
    System.out.println("\n[" + theFloor + "], [" + theRoom + "]");
    System.out.println(theGame.toString());
    theS.nextLine();
  }

  /**
   * Prompts, reads, and returns how many floors are in the
   * earthquake destroyed building.
   *
   * @param theS Scanner to the console.
   */
  public static int getTotalFloors(Scanner theS) {
    System.out.print("To find the lone survivor, we need to know:\n"
                     + "\tHow many floors are in the building\n"
                     + "\tHow many rooms are on the floors\n\n"
                     + "             Please enter the number of floors: ");
    return theS.nextInt();                 
  }

  /**
   * Prompts, reads, and returns how many rooms are on each floor in the
   * earthquake destroyed building.
   *
   * @param theS Scanner to the console.
   */
  public static int getTotalRooms(Scanner theS) {
    System.out.print("Please enter the number of rooms on the floors: ");
    int totalRooms = theS.nextInt();
    theS.nextLine();    // Consume previous newline character
    return totalRooms;
  }

  /**
   * Displays the person who located the lone survivor, prompts the
   * player(s) for another go at locating a lone survivor in another
   * building, and returns the users response as true or false.
   *
   * @param theS Scanner to the console.
   * @param theWinner char representing the person who found the lone survivor.
   */
  public static boolean playAnother(Scanner theS, char theWinner) {
    System.out.println("Great job player " + theWinner +"!");
    System.out.println("Would you like to find another lone survivor[Y/N]? ");
    return theS.nextLine().equalsIgnoreCase("Y");
  }
}
