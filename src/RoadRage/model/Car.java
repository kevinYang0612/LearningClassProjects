package model;

import java.util.Map;
/**
 *  This is a class called "Car" that extends AbstractVehicle class
 *  It is a subclass for moving object "Car" that behaves as required
 *
 * @author Kevin Yang
 * @version Dec 12th, 2021
 * */
public class Car extends AbstractVehicle
{
    /** death time (number of moves) for Car */
    private static final int deathTimeForCar = 15;

    /** a direction for Car.*/
    private Direction carDirection;

    /**
     * Constructs a Car object with x, y coordinates to keep tracking Car location on map
     * and a direction for the Car
     *
     * @param theX, the x coordinate for Car
     * @param theY, the y coordinate for Car
     * @param theDirection, direction for Car
     * */
    public Car(final int theX, final int theY, final Direction theDirection)
    {
        super(theX, theY, theDirection, deathTimeForCar);
        this.carDirection = theDirection;
    }

    /**
     * check if Car can pass the terrain
     * @param theTerrain, Car is trying to pass a Terrain
     * @param theLight, Car will stop for the red light
     * @return a boolean value to determine if a Car should pass or not
     * */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight)
    {
        boolean passOK = false;
        if (theTerrain == Terrain.STREET)
        {
            passOK = true;
        }
        else if (theTerrain == Terrain.LIGHT && theLight == Light.GREEN)
        {
            passOK = true;
        }
        else if (theTerrain == Terrain.LIGHT && theLight == Light.YELLOW)
        {
            passOK = true;
        }
        return passOK;
    }

    /**
     * Car will choose the preference terrain and direction as required
     * @param theNeighbors, neighbor terrain on the map
     * @return a direction the Car prefers to go
     * */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors)
    {
        if (theNeighbors.get(super.getDirection()) == Terrain.STREET ||
                theNeighbors.get(super.getDirection()) == Terrain.LIGHT)
        {
            this.carDirection = super.getDirection();
        }
        else if (theNeighbors.get(super.getDirection().left()) == Terrain.STREET ||
                theNeighbors.get(super.getDirection().left()) == Terrain.LIGHT)
        {
            this.carDirection = super.getDirection().left();
        }
        else if (theNeighbors.get(super.getDirection().right()) == Terrain.STREET ||
                theNeighbors.get(super.getDirection().right()) == Terrain.LIGHT)
        {
            this.carDirection = super.getDirection().right();
        }
        else
        {
            this.carDirection = super.getDirection().reverse();
        }
        return this.carDirection;
    }
}
