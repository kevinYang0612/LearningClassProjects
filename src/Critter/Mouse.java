package Critter;

import java.util.Random;
/**
 * This is a sub class called "Mouse" that extends abstract class
 * "AbstractCritter". Mouse moves zigzag motion and stay for some time
 *   
 * @author Bohan Yang
 * @version April 11th 2021
 */
public class Mouse extends AbstractCritter
{
   // Instance fields
   
   /** This field indicates the small step is taking */
   private int myDirection;
   
   /** This field indicates which quadrant is going */
   private int myGeneralDirection;
   
   /** This field is counting the steps*/
   private int myCount;
   
   /** This field is a goal for myCount to catch up */   
   private int myTarget;
   
   // Constructor
   /**
    * Constructs Mouse by giving its initial to super class
    * initialize the fields by given instruction. 
    */
   public Mouse()
   {
      super('M');
      myDirection = 1;
      myGeneralDirection = 1; // diagonally move, 1 is at first quadrant
      myCount = 0;
      myTarget = 5;           // taking 5 steps then change direction
   }
   
   /**
    * Mouse moves in a zigzag, this method will make mouse to move
    * in zigzag motion in general direction for a some time and change
    * direction later
    *
    * @param theInfo from Interface CritterInfo 
    * is defined and used by CritterModel class
    *
    * @return an int, which is defined at the Critter constants
    * the integer that returns is between 1-4 inclusively represents
    * the direction of Mouse going.
    */
   public int getMove(CritterInfo theInfo)
   {
      Random rand = new Random();
      myCount++;
      
      if (myCount == myTarget) // if count steps catching up target
      {
         myCount = 0;
         myGeneralDirection = rand.nextInt(4) + 1; 
         myTarget = rand.nextInt(6) + 1; // change steps assigns to target
      }
      
      if (myGeneralDirection == 1)// if going first quadrant 
      {
         if (myCount % 2 != 0)
         {
            myDirection = 4; // go east
         }
         else 
         {
            myDirection = 1; // go north
         }
      }
      else if (myGeneralDirection == 2) // if going second quadrant
      {
         if (myCount % 2 != 0)
         {
            myDirection = 1;        // go north
         }
         else 
         {
            myDirection = 2;        // go west
         }
      }
      else if (myGeneralDirection == 3)// if going third quadrant
      {
         if (myCount % 2 != 0)
         {
            myDirection = 2;          // go west
         }
         else 
         {
            myDirection = 3;           // go south
         }
      }
      else if (myGeneralDirection == 4) // if going fourth quadrant
      {
         if (myCount % 2 != 0)
         {
            myDirection = 3;        // go south 
         }
         else 
         {
            myDirection = 4;        // go east
         }
      }
      return myDirection;
              
 
   }
}