package Critter;

/**
 * This is a sub class called "Stone" that extends abstract class
 * "AbstractCritter". Stone does not move and stays at its place
 *   
 * @author Bohan Yang
 * @version April 11th 2021
 */
public class Stone extends AbstractCritter
{
   // Constructor
   /**
    * Constructs stone by giving 'S' to its super class constructor
    */
   public Stone()
   {
      super('S');
   }
   
   /**
    * Stone's movement is never move
    *
    * @param theInfo from Interface CritterInfo 
    * is defined and used by CritterModel class
    *
    * @return 0 means CENTER is set to be 0 just going to stay center
    */
   public int getMove(CritterInfo theInfo)
   {
      return 0;
   }
}