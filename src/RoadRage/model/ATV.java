package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 *  This is a class called "ATV" that extends AbstractVehicle class
 *  It is a subclass for moving object "ATV" that behaves as required
 *
 * @author Kevin Yang
 * @version Dec 12th, 2021
 * */
public class ATV extends AbstractVehicle
{
    /** death time (number of moves) for ATV */
    private static final int deathTimeForATV = 25;

    /** a direction for bicycle.*/
    private Direction ATVDirection;

    /**
     * Constructs an ATV object with x, y coordinates to keep tracking ATV location on map
     * and a direction for the ATV
     *
     * @param theX, the x coordinate for ATV
     * @param theY, the y coordinate for ATV
     * @param theDirection, direction for ATV
     * */
    public ATV(final int theX, final int theY, final Direction theDirection)
    {
        super(theX, theY, theDirection, deathTimeForATV);
        this.ATVDirection = theDirection;
    }

    /**
     * check if ATV can pass the terrain
     * @param theTerrain, ATV is trying to pass a Terrain
     * @param theLight, ATV will ignore the light
     * @return a boolean value to determine if a ATV should pass or not
     * */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight)
    {
        return !(theTerrain == Terrain.WALL);
    }

    /**
     * ATV will choose the preference terrain and direction as required
     * @param theNeighbors, neighbor terrain on the map
     * @return a direction the ATV prefers to go
     * */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors)
    {
        final Random random = new Random();
        final List<Direction> directionList = new ArrayList<Direction>();
        if (theNeighbors.get(super.getDirection()) != Terrain.WALL)
        {
            directionList.add(super.getDirection());
        }
        if (theNeighbors.get(super.getDirection().right()) != Terrain.WALL)
        {
            directionList.add(super.getDirection().right());
        }
        if (theNeighbors.get(super.getDirection().left()) != Terrain.WALL)
        {
            directionList.add(super.getDirection().left());
        }
        final int rand = random.nextInt(directionList.size());
        this.ATVDirection = directionList.get(rand);
        return this.ATVDirection;
    }
}
