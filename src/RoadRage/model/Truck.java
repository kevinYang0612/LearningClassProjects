package model;

import java.util.Map;
/**
 *  This is a class called "Truck" that extends AbstractVehicle class
 *  It is a subclass for moving object "Truck" that behaves as required
 *
 * @author Kevin Yang
 * @version Dec 12th, 2021
 * */
public class Truck extends AbstractVehicle
{
    /** death time (number of moves) for Truck */
    private static final int deathTimeForTruck = 0;

    /** a direction for Truck.*/
    private Direction truckDirection;

    /**
     * Constructs a Truck object with x, y coordinates to keep tracking Truck location on map
     * and a direction for the Truck
     *
     * @param theX, the x coordinate for Truck
     * @param theY, the y coordinate for Truck
     * @param theDirection, direction for Truck
     * */
    public Truck(final int theX, final int theY, final Direction theDirection)
    {
        super(theX, theY, theDirection, deathTimeForTruck);
        this.truckDirection = theDirection;
    }

    /**
     * check if Truck can pass the terrain
     * @param theTerrain, Truck is trying to pass a Terrain
     * @param theLight, Truck will behave the light color as required
     * @return a boolean value to determine if a Car should pass or not
     * */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight)
    {
        boolean passOK = false;
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT)
        {
            passOK = true;
        }
        else if (theTerrain == Terrain.CROSSWALK && theLight != Light.RED)
        {
            passOK = true;
        }
        return passOK;
    }

    /**
     * Truck will choose the preference terrain and direction as required
     * @param theNeighbors, neighbor terrain on the map
     * @return a direction the Truck prefers to go
     * */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors)
    {
        this.truckDirection = Direction.random();
        if (canPass(Terrain.STREET, Light.GREEN))
        {
            if (canTruckReverse(theNeighbors))
            {
                this.truckDirection = getDirection().reverse();
            }
            else
            {
                while (theNeighbors.get(this.truckDirection) != Terrain.STREET
                        && theNeighbors.get(this.truckDirection) != Terrain.CROSSWALK
                        && theNeighbors.get(this.truckDirection) != Terrain.LIGHT
                        || this.truckDirection == getDirection().reverse())
                {
                    this.truckDirection = Direction.random();
                }
            }
        }
        return this.truckDirection;
    }

    /**
     * This is a private helper method for chooseDirection method, it deals with Truck reverse
     * @param theNeighbors, neighbor terrain on the map
     * @return a direction the Truck prefers to go
     * */
    private boolean canTruckReverse(Map<Direction, Terrain> theNeighbors)
    {
        return theNeighbors.get(super.getDirection()) != Terrain.STREET &&
               theNeighbors.get(super.getDirection()) != Terrain.LIGHT &&
               theNeighbors.get(super.getDirection().right()) != Terrain.LIGHT &&
               theNeighbors.get(super.getDirection().left()) != Terrain.LIGHT &&
               theNeighbors.get(super.getDirection()) != Terrain.CROSSWALK &&
               theNeighbors.get(super.getDirection().right()) != Terrain.CROSSWALK &&
               theNeighbors.get(super.getDirection().left()) != Terrain.CROSSWALK &&
               theNeighbors.get(super.getDirection().right()) != Terrain.STREET &&
               theNeighbors.get(super.getDirection().left()) != Terrain.STREET;
    }
}
