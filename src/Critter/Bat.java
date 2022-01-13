package Critter;

import java.util.Random;
/**
 * This is a sub class called "Bat" that extends abstract class
 * "AbstractCritter". Bat moves erratically and never stays
 *   
 * @author Bohan Yang
 * @version April 11th 2021
 */
public class Bat extends AbstractCritter
{
   // Constructor
   
   /**
    * Constructs Bat by giving 'B' to its super class constructor
    *
    */
   public Bat()
   {
      super('B');
   }
   
  /**
   * Bat's movement is random and do not stay 
   *
   * @param theInfo from Interface CritterInfo 
   * is defined and used by CritterModel class
   *
   * @return an int, which is defined at the Critter constants
   * the integer that returns is between 1-4 inclusively represents 
   * the direction of Bat going.
   */
   public int getMove(CritterInfo theInfo)
   {
      Random rand = new Random();
      int i = rand.nextInt(4) + 1;
      return i;
   }
}