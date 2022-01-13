package model;

import java.util.Map;
/**
 *  This is a class called "Bicycle" that extends AbstractVehicle class
 *  It is a subclass for moving object "Bicycle" that behaves as required
 *
 * @author Kevin Yang
 * @version Dec 12th, 2021
 * */
public class Bicycle extends AbstractVehicle
{
    /** death time (number of moves) for bicycle */
    private static final int deathTimeForBicycle = 35;

    /** a direction for bicycle.*/
    private Direction bicycleDirection;

    /**
     * Constructs a bicycle object with x, y coordinates to keep tracking bicycle location on map
     * and a direction for the bicycle
     *
     * @param theX, the x coordinate for bicycle
     * @param theY, the y coordinate for bicycle
     * @param theDirection, direction for bicycle
     * */
    public Bicycle(int theX, int theY, Direction theDirection)
    {
        super(theX, theY, theDirection, deathTimeForBicycle);
        this.bicycleDirection = theDirection;
    }

    /**
     * check if bicycle can pass the terrain
     * @param theTerrain, bicycle is trying to pass a Terrain
     * @param theLight, bicycle will only pass when is green light
     * @return a boolean value to determine if a bicycle should pass or not
     * */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight)
    {
        boolean passOK = false;
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.TRAIL)
        {
            passOK = true;
        }
        else if (theTerrain == Terrain.LIGHT && theLight == Light.GREEN)
        {
            passOK = true;
        }
        else if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN)
        {
            passOK = true;
        }
        return passOK;
    }

    /**
     * Bicycle will choose the preference terrain and direction as required
     * @param theNeighbors, neighbor terrain on the map
     * @return a direction the bicycle prefers to go
     * */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors)
    {
        if (theNeighbors.get(super.getDirection()) == Terrain.TRAIL)
        {
            this.bicycleDirection = super.getDirection();
        }
        else if (theNeighbors.get(super.getDirection().left()) == Terrain.TRAIL)
        {
            this.bicycleDirection = super.getDirection().left();
        }
        else if (theNeighbors.get(super.getDirection().right()) == Terrain.TRAIL)
        {
            this.bicycleDirection = super.getDirection().right();
        }
        else if (theNeighbors.get(super.getDirection()) == Terrain.STREET
                || theNeighbors.get(super.getDirection()) == Terrain.LIGHT)
        {
            this.bicycleDirection = super.getDirection();
        }
        else if (theNeighbors.get(super.getDirection().left()) == Terrain.STREET
                || (theNeighbors.get(super.getDirection().left())) == Terrain.LIGHT)
        {
            this.bicycleDirection = super.getDirection().left();
        }
        else if (theNeighbors.get(super.getDirection().right()) == Terrain.STREET
                || theNeighbors.get(super.getDirection().right()) == Terrain.LIGHT)
        {
            this.bicycleDirection = super.getDirection().right();
        }
        else
        {
            this.bicycleDirection = super.getDirection().reverse();
        }
        return this.bicycleDirection;
    }
}
