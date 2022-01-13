package Critter;

import java.lang.Math;
/**
 * This is a sub class called "Turtle" that extends abstract class
 * "AbstractCritter". Turtle moves slow and can be stay at CENTER
 *   
 * @author Bohan Yang
 * @version April 11th 2021
 */
public class Frog extends AbstractCritter
{
   // Instance fields
   
   /** This is a int field to indicate the direction */
   private int myDirection;
   
   /** This is a private int field to count the movements */
   private int myCount;
   
   // Constructor
   /**
    * Constructs Turtle by giving its initial to super class
    * initialize the fields by given instruction. 
    * 
    */
   public Frog()
   {
      super('F');
      myDirection = 0;
      myCount = 0;
   }
   
   /**
    * This method describes frog movement, frog never jumps onto Stone.
    * When frog next jump is Stone, frog will pause(CENTER) itself.
    *
    * @param theInfo from Interface CritterInfo 
    * is defined and used by CritterModel class
    *
    * @return an int, which is defined at the Critter constants
    * the integer that returns is between 0-4 inclusively to respresents
    * the direction of Frog going.
    */
   public int getMove(CritterInfo theInfo)
   {
      int direction = myDirection;
      double rand = Math.random();
      if (myCount == 0)
      {      
         if (rand < 0.25)
         {
            myDirection = 1;             
         }
         else if (rand < 0.5)
         {
            myDirection = 3; 
         }
         else if (rand < 0.75)
         {
            myDirection = 4;
         }
         else
         {
            myDirection = 2;
         }
         
      }
      myCount++;
      if (myCount == 3)
      {
         myCount = 0;
      }
      if (theInfo.getNeighbor(myDirection) == 'S')
      {
         direction = CENTER;
      }
      else
      {
         direction = myDirection;
      }
      return direction;
      
   }
}