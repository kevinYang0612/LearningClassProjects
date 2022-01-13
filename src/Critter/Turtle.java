package Critter;

import java.util.Random;
/**
 * This is a sub class called "Turtle" that extends abstract class
 * "AbstractCritter". Turtle moves slow and can be stay at CENTER
 *   
 * @author Bohan Yang
 * @version April 11th 2021
 */
public class Turtle extends AbstractCritter
{
   // Instance fields
   /** A random field that to get a random number */
   private Random myRand;
   
   /** an integer field to count turtle moves and triggers to wait*/
   private int myWait;
   
   // Constructor
   /**
    * Constructs Turtle by giving its initial to super class
    * initialize the fields by given instruction. 
    */
   public Turtle()
   {
      super('T');
      myRand = new Random();
      myWait = 0;
   }
   
   /**
    * Turtle moves slow and can be stopped(CENTER).
    * This method will make Turtle to move as instructed
    * 
    * @param theInfo from Interface CritterInfo 
    * is defined and used by CritterModel class
    *
    * @return an int, which is defined at the Critter constants
    * the integer that returns is between 0-4 inclusively to represents
    * the direction of Turtle going.
    */
   public int getMove(CritterInfo theInfo)
   {
      int direction = 0;
      myWait += 1;
      if (myWait == 3)
      {
         myWait = 0;
         if (myRand.nextBoolean())
         {
            int rn = myRand.nextInt(13);
            if (rn < 2)
            {
               direction = 1;
            }
            else if (rn < 4)
            {
               direction = 4;
            }
            else if (rn < 5)
            {
               direction  = 3;
            }
            else if (rn < 8)
            {
               direction = 2;
            }
            else
            {
               direction = 0;
            }
         }
         else
         {
            int rn = myRand.nextInt(11);
            if (rn < 4)
            {
               direction = 3;
            }
            else if (rn < 6)
            {
               direction = 2;
            }
            else if (rn < 8)
            {
               direction = 1;
            }
            else
            {
               direction = 4;
            }
         }
      }
      return direction;
   }    
}