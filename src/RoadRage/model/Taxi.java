package model;

import java.util.Map;
/**
 *  This is a class called "Taxi" that extends AbstractVehicle class
 *  It is a subclass for moving object "Taxi" that behaves as required
 *
 * @author Kevin Yang
 * @version Dec 12th, 2021
 * */
public class Taxi extends AbstractVehicle
{
    /** death time (number of moves) for Taxi */
    private static final int deathTimeForTaxi = 15;

    /** a clock cycle that is set to be 3*/
    private static final int CLOCK_CYCLE = 3;

    /** a direction for Human.*/
    private Direction taxiDirection;

    /**
     * Constructs a Taxi object with x, y coordinates to keep tracking Taxi location on map
     * and a direction for the Taxi
     *
     * @param theX, the x coordinate for Taxi
     * @param theY, the y coordinate for Taxi
     * @param theDirection, direction for Taxi
     * */
    public Taxi(final int theX, final int theY, final Direction theDirection)
    {
        super(theX, theY, theDirection, deathTimeForTaxi);
        this.taxiDirection = theDirection;
    }

    /**
     * check if Taxi can pass the terrain
     * @param theTerrain, Taxi is trying to pass a Terrain
     * @param theLight, Taxi will behave the light color as required
     * @return a boolean value to determine if a Car should pass or not
     * */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight)
    {
        boolean passOK = false;
        int counter = 0;
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN)
        {
            passOK = true;
        }
        else if (theTerrain == Terrain.LIGHT && theLight != Light.RED)
        {
            passOK = true;
        }
        else if (theTerrain == Terrain.STREET)
        {
            passOK = true;
        }
        else if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED)
        {
            if (counter == CLOCK_CYCLE)
            {
                passOK = true;
                counter = 0;
            }
            else
            {
                counter++;
            }
        }
        return passOK;
    }

    /**
     * Taxi will choose the preference terrain and direction as required
     * @param theNeighbors, neighbor terrain on the map
     * @return a direction the Taxi prefers to go
     * */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors)
    {
        if (theNeighbors.get(super.getDirection()) == Terrain.CROSSWALK ||
            theNeighbors.get(super.getDirection()) == Terrain.STREET ||
            theNeighbors.get(super.getDirection()) == Terrain.LIGHT)
        {
            this.taxiDirection = super.getDirection();
        }
        else if (theNeighbors.get(super.getDirection().left()) == Terrain.CROSSWALK ||
                 theNeighbors.get(super.getDirection().left()) == Terrain.STREET ||
                 theNeighbors.get(super.getDirection().left()) == Terrain.LIGHT)
        {
            this.taxiDirection = super.getDirection().left();
        }
        else if (theNeighbors.get(super.getDirection().right()) == Terrain.CROSSWALK ||
                 theNeighbors.get(super.getDirection().right()) == Terrain.STREET ||
                 theNeighbors.get(super.getDirection().right()) == Terrain.LIGHT)
        {
            this.taxiDirection = super.getDirection().right();
        }
        else
        {
            this.taxiDirection = super.getDirection().reverse();
        }
        return this.taxiDirection;
    }
}
