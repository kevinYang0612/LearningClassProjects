package Critter;

/**
 * This is a sub class called "Wolf" that extends abstract class
 * "AbstractCritter". Wolf moves with a pattern and never stays 
 *   
 * @author Bohan Yang
 * @version April 11th 2021
 */
public class Wolf extends AbstractCritter
{
   // Instance fields
   /** This is a private int field to indicate the direction */
   private int myDirection;
   
   /** This is a private int field to count the movements */   
   private int myCount;
   
   /** This is a private boolean field toogle that changes direction */
   private boolean myFirst;
   
   /** This is a private int field to compare with myCount and 
    *  set things up
    */
   private int myTarget;
   
   // Constructor 
   /**
    * Constructs Wolf by giving its initial to super class
    * initialize the fields by given instruction. 
    */
   public Wolf()
   {
      super('W');
      myDirection = 4;
      myCount = 0;
      myFirst = true;
      myTarget = 1;
   }
   
  /**
   * Wolf moves consistent and in parallel with all wolves.
   * They tend to travel in straight directions for some time.
   *
   * @param theInfo from Interface CritterInfo 
   * is defined and used by CritterModel class
   *
   * @return an int, which is defined at the Critter constants
   * the integer that returns is between 1-4 inclusively represents
   * the direction of Wolf going.
   */
   public int getMove(CritterInfo theInfo)
   {
      int direction = myDirection;
      myCount++;
      if (myCount == myTarget)
      {
         if (!myFirst)
         {
            myTarget++;
         }
         myFirst = !myFirst;
         myCount = 0;
         myDirection = myDirection % EAST + 1;
         direction = myDirection;
      }
      return direction;
   }
}