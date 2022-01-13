
import java.util.Random;
/*
   This is a class called "LoneSurvivor" which represents a survivor
   of an earthquake destruction of a building who is lost in a multi-
   -floor building that contains the same number of rooms on each floor.
    
   @Bohan Yang
   @version April 4th 2021
*/



public class LoneSurvivor
{
   // This field represents a 2D array, location interprets as [floor][room]
   private final char[][] mySurvivorLocations;

   // This field represents a character will be assigned to player's character
   private char myWinner;
   
   // This field represents true when the survivor, vice versa
   private boolean myFound;
   
   // Constructs a LoneSurvivor using the provided floor and room.
   // Parameters are non-negative integers
   // @para theFloor where the survivor is at this floor/row
   // @para theRoom where the survivor is at this room/column
   
   public LoneSurvivor(int theFloor, int theRoom)
   {
      mySurvivorLocations = new char[theFloor][theRoom];
      Random rand = new Random();
      // This field represents the row/floor where the survivor is lost
      int myFloorLocation = rand.nextInt(theFloor);
      // This field represents the column/room where the survivor is lost
      int myRoomLocation = rand.nextInt(theRoom);

      for(int i = 0; i < theFloor; i++)
      {
         for(int j = 0; j < theRoom; j++)
         {
            mySurvivorLocations[i][j] = ' ';
         }
      }
      mySurvivorLocations[myFloorLocation][myRoomLocation] = 'S';
      myWinner = ' ';
      myFound = false;
   }
   
   // which room is being searched?
   // @param theFloor and theRoom are integers indicating the floor and room to be searched.
   // @return a boolean value determined by the room has been searched or not
   
   public boolean roomSearchedAlready(int theFloor, int theRoom)
   {
      boolean searched = false;
      searched = mySurvivorLocations[numberOfFloors() - theFloor - 1][numberOfRooms() - theRoom - 1] != ' ' &&
              mySurvivorLocations[numberOfFloors() - theFloor - 1][numberOfRooms() - theRoom - 1] != 'S';
      return searched;
   }
   // which room is survivor located?
   // @param theFloor and theRoom are integers indicating the floor and room to be searched.
   // @return a boolean value determined by the room has been searched and survivor exists or not
   public boolean survivorLocation(int theFloor, int theRoom)
   {
      boolean survivorLost = false;
      if(mySurvivorLocations[theFloor][theRoom] == 'S')
      {
         survivorLost = true;
      }
      return survivorLost;
   }
   // Making sure the the values that passing in are in the range
   // @param theFloor and theRoom are integers indicating the floor and room to be searched.
   // @return a boolean value determined the floor or room is out of range of this building 
   public boolean indicesOK(int theFloor, int theRoom)
   {
      return theFloor < mySurvivorLocations.length && theRoom < mySurvivorLocations[theFloor].length;
   }
   // return total floors of this building
   public int numberOfFloors()
   {
      return mySurvivorLocations.length;
   }
   // return total rooms of this building
   public int numberOfRooms()
   {
      return mySurvivorLocations[0].length;
   }
   // How are we setting the player on the room when the room is searched by current player?
   // if survivor is not found by current player after a player searched a room, 
   // the current player will be marked on that just searched room.
   // @param theFloor, theRoom, and current player as a char type
   // @return a true value determined by survivor is found 
   public boolean searchRoom(int theFloor, int theRoom, char theChar)
   {
      if(mySurvivorLocations[numberOfFloors()-theFloor-1][numberOfRooms()-theRoom-1] == 'S')
      {
         myWinner = theChar;
         myFound = true;
      }
      else
      {
         mySurvivorLocations[numberOfFloors()-theFloor-1][numberOfRooms()-theRoom-1] = theChar;
         myFound = false;
      }
      return myFound;
   }
   // return a current state of this class
   public String toString()
   {
      int floors = numberOfFloors();
      int rooms = numberOfRooms();
      char value = ' ';                 // a marker
      StringBuilder building = new StringBuilder();
      for(int i = 0; i < rooms; i++)
      {
         building.append(" ");
      }
      building.append("/");
      for(int i = 0; i < floors; i++)
      {
         building.append("____");
      }
      building.append("\n");
      int cnt = rooms * 2;  // a tool that symmetry the building in horizonally 
      for(int i = rooms - 1; i >= 0; i--)
      {
         for(int j = 0; j < rooms; j++) 
         {
            if(cnt > rooms)
            {
               if(j + rooms != cnt - 1)
               {
                  building.append(" ");
               }
               else
               {
                  building.append("/");
               }
            }
            else
            {
               if(j == rooms - cnt)
               {
                  building.append("\\");
               }
               else
               {
                  building.append(" ");
               }
            }                      
         }
         for(int k = floors - 1; k >= 0; k--)
         {
            value = mySurvivorLocations[floors - k - 1][rooms - i - 1];  
            if(value == 'S' && myFound)        // checking the marker with the survivor location
            {                                          // and checking if the current player found survivor
               building.append("|").append(myWinner).append("S ");
            }
            else
            {
               if(value == 'S')
               {
                  value = ' ';
               }
               building.append("| ").append(value).append(" ");
            }
         }
         building.append("|\n");
         cnt--;
         for(int p = 0; p < rooms; p++)
         {
            if(cnt > rooms)
            {
               if(p != cnt - rooms - 1)
               {
                  building.append(" ");
               }
               else
               {
                  building.append("/");
               }
            }
            else
            {
               if(p != rooms - cnt)
               {
                  building.append(" ");
               }
               else
               {
                  building.append("\\");
               }
            }
         }
         for(int q = 0; q < floors; q++)
         {
            building.append("|___");
         }
         building.append("|\n");
         cnt--;    
      }
      for(int o = 0; o < rooms; o++)
      {
         building.append(" ");
      }
      building.append("\\");
      return building.toString();
   }
}