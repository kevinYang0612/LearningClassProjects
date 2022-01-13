package Critter;

/**
 * This is an abstract class called "AbstractCritter" that implements
 * interface "Critter" and it is a super class for other critters, 
 * more detailed information will be in each critter classes
 *   
 * @author Bohan Yang
 * @version April 11th 2021
 */

public abstract class AbstractCritter implements Critter
{
  /**
   * A private char field to hold the single character represents
   * a particular Critter and is used for display
   * 
   */
   private char myLetter;
   
  //Constructor
   
  /**
   * constructs an AbstractCritter in which sub class will pass on
   * more detailed information to this constructor
   * 
   * @param theChar, passing in a char constant that will never change
   * and will use that char to display the particular critter.
   */
   public AbstractCritter(final char theChar)
   {
      myLetter = theChar;
   }
   
  /**
   * This is a getter method that will display the current particular
   * critter
   *
   * @return a character in which is this private field represents 
   * particular critter for display
   */  
   public char getChar()
   {
      return myLetter;
   }
}