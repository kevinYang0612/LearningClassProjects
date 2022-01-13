package Assassin;

/**
  * This is a class called "AssassinManager". 
  * includes a nested helper class "AssassinNode" that helps us to
  * create a Circular LinkedList that will contain a series of 
  * player names. 
  * The "AssassinManager" shows player is targeting another player
  * Terminated players will be displayed in graveyard when playing
  * The game repeats until there is only one assassin remaining.
  *   
  * @author Bohan Yang
  * @version May 23th 2021
  */
public class AssassinManager 
{
  /**
   * This is a helper class called AssassinNode that helps us to 
   * create a Circular LinkedList that will contain  
   * a series of player names.
   */
   private static class AssassinNode 
   {
      // fields for AssassinNode
      /** myName that stores players name*/
      public String myName;   
      
      /** myKiller that indicate player kills another player */
      public String myKiller;
      
      /** myNext is the next node in the LinkedList*/ 
      public AssassinNode myNext;
      
      // constructor
     /**
      * constructs a node of LinkedList with given name
      * without a node
      *
      * @param theName, a player name that is going to be stored
      */
      public AssassinNode(String theName)
      {
         this(theName, null);
      }
      
     /**
      * constructs a node of LinkedList with given names
      * and a node to next name
      *
      * @param theName, a player name that is going to be stored
      * @param theNext, a node to the next player name
      */
      public AssassinNode(String theName, AssassinNode theNext)
      {
         myName = theName;
         myKiller = null;
         myNext = theNext;
      }
   }
   // fields for AssassinManager
   
   /** a LinkedList has players name in the kill ring */
   private AssassinNode myFront;
   
   /** a LinkedList has the previous players name. */
   private AssassinNode myLast;
   
   /** a LinkedList has the current players name. */
   private AssassinNode myCurrent;
   
   /** a LinkedList has the dead players name.*/
   private AssassinNode myGraveYard;
   
 /**
   * constructs AssassinManager object that takes an array of names
   * and going to link the names together to form the kill ring
   *
   * @param theNames, a series of players name are going to be stored
   * @throws IllegalArgumentException if theNames is empty.
   */
   public AssassinManager(String[] theNames)
   {
      if (theNames == null || theNames.length == 0)
      {
         throw new IllegalArgumentException();
      }
      for (int i = theNames.length - 1; i >= 0; i--)
      {
         myFront = new AssassinNode(theNames[i], myFront);
      }
   }
   
  /**
   * This method is printing the players name in the kill ring
   * and also indicating who is targeting who
   */
   public void printKillRing()
   {
      myCurrent = myFront;
      while (myCurrent.myNext != null)
      {
         System.out.println(myCurrent.myName + 
                         " is targetting " + myCurrent.myNext.myName);
         myCurrent = myCurrent.myNext;
      }
      System.out.println(myCurrent.myName + 
                         " is targetting " + myFront.myName);
   }
   
  /**
   * This method is printing the players name if the player is killed
   * and also printing out who killed this dead player.
   * If more than one player get killed, it will stack up in 
   * reverse chronologic to display. 
   * When no one got killed, it will not display anything
   */ 
   public void printGraveyard()
   {
      myCurrent = myGraveYard;
      while (myCurrent != null)
      {
         System.out.println(myCurrent.myName + " was killed by " + 
                            myCurrent.myKiller);
         myCurrent = myCurrent.myNext;
      }
   }
   
  /**
   * This method return a boolean value
   * true if the player is still in the kill ring(alive), vice versa
   * 
   * @param theName, a player name we are going to check
   * @return a boolean to indicate theName is still in the kill ring
   */    
   public boolean killRingContains(String theName)
   {
      return doesContain(theName, myFront);
   }
   
  /**
   * This method return a boolean value
   * true if the player is dead (killed), vice versa
   * 
   * @param theName, a player name we are going to check
   * @return a boolean to indicate theName is dead or not. 
   */    
   public boolean graveyardContains(String theName)
   {
      return doesContain(theName, myGraveYard);
   }
   
  /**
   * This method is a helper method for checking a player's name
   * true if the player's name is checked, vice versa
   * 
   * @param theName, a player name we are going to check
   * @param theCurrent, it actually is the current LinkedList that 
   * we are checking depending on what kind of thing is passing in
   * It could be the kill ring or it could be the graveyard
   * @return a true to indicate if theName is checked in a particular
   * LinkedList, vice versa
   */     
   private boolean doesContain(String theName, AssassinNode theCurrent)
   {
      boolean res = false;
      while (theCurrent != null)
      {
         if (theCurrent.myName.equalsIgnoreCase(theName))
         {
            res = true;
         }
         theCurrent = theCurrent.myNext;
      }
      return res;
   }
   
  /**
   * This method return a boolean value
   * if there is only one player left in the game, the game is over
   * vice versa
   *
   * @return a true if only one player left in game, vice versa.
   */ 
   public boolean isGameOver()
   {
      return myFront.myNext == null;
   }
   
  /**
   * This method return a String to indicate who won the game
   *
   * @return string representation of the winner's name
   */    
   public String getWinner()
   {
      String res = null;
      if (isGameOver())
      {
         res = myFront.myName;
      }
      else
      {
         res = null;
      }
      return res;
   }
   
  /**
   * This method is going to remove the player with the given name
   * and move given name player from kill ring to graveyard because 
   * the given name player is killed by the player that was targeting
   * And it will retarget and link properly of the players that still
   * in the game
   * @param theName, a name that is the victim's name
   * @throws IllegalArgumentException 
   * if kill ring doesn't have this name.
   * @throws IllegalStateException
   * if the game is over. 
   */    
   public void kill(String theName)
   {
      myCurrent = myFront;
      myLast = myGraveYard;
      if (!killRingContains(theName))
      {
         throw new IllegalArgumentException();
      }
      else if (isGameOver())
      {
         throw new IllegalStateException();
      }
      if (myCurrent.myName.equalsIgnoreCase(theName))
      {
         myLast = myCurrent;
         while (myCurrent.myNext != null)
         {
            myCurrent = myCurrent.myNext;
         }
         myFront = myFront.myNext;
      }
      else
      {
         while (!myCurrent.myNext.myName.equalsIgnoreCase(theName))
         {
            myCurrent = myCurrent.myNext;
         }
         myLast = myCurrent.myNext;
         myCurrent.myNext = myCurrent.myNext.myNext;
      }
      myLast.myKiller = myCurrent.myName;
      myLast.myNext = myGraveYard;
      myGraveYard = myLast;
   }   
}