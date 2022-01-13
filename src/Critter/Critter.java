package Critter;

/**
 * This is a interface called "Critter" that lists constants and
 * and abstract methods to be implemented by various Critter classes.
 * 
 *   
 * @author Bohan Yang
 * @version April 11th 2021
 */

public interface Critter
{
   /**
    * Following constants are 5 int constants for the direction
    * an animal can move, and not moving is CENTER, set to 0
    * moving to NORTH set to 1, moving to WEST set to 2
    * moving ti SOUTH set to 3, moving to EAST set to 4
    */
    public static final int NORTH = 1;
    public static final int WEST = 2;
    public static final int SOUTH = 3;
    public static final int EAST = 4;
    public static final int CENTER = 0;
    
   /**
    * An abstract method, more detailed defined by inner class
    *
    * @return a character represents particular critter for display
    */     
    public char getChar();
    
   /**
    * An abstract method, more detailed defined by inner class
    *
    * @param theInfo is defined and used by CritterModel class
    * @return an integer, represents a move to an direction.
    */   
    public int getMove(CritterInfo theInfo);
}