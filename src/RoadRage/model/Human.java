package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 *  This is a class called "Human" that extends AbstractVehicle class
 *  It is a subclass for moving object "Human" that behaves as required
 *
 * @author Kevin Yang
 * @version Dec 12th, 2021
 * */
public class Human extends AbstractVehicle
{
    /** death time (number of moves) for Human */
    private static final int deathTimeForHuman = 45;

    /** a direction for Human.*/
    private Direction directionForHuman;

    /**
     * Constructs a Human object with x, y coordinates to keep tracking Human location on map
     * and a direction for the Human
     *
     * @param theX, the x coordinate for Human
     * @param theY, the y coordinate for Human
     * @param theDirection, direction for Human
     * */
    public Human(final int theX, final int theY, final Direction theDirection)
    {
        super(theX, theY, theDirection, deathTimeForHuman);
        this.directionForHuman = theDirection;
    }

    /**
     * check if Human can pass the terrain
     * @param theTerrain, Human is trying to pass a Terrain
     * @param theLight, Human will travel through crosswalks when crosswalk light is yellow or red
     * @return a boolean value to determine if a Car should pass or not
     * */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight)
    {
        boolean passOK = false;
        if (theTerrain == Terrain.GRASS) {
            passOK = true;
        } else if (theTerrain == Terrain.CROSSWALK && (theLight != Light.GREEN)) {
            passOK = true;
        }
        return passOK;
    }

    /**
     * Human will choose the preference terrain and direction as required
     * @param theNeighbors, neighbor terrain on the map
     * @return a direction the Human prefers to go
     * */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors)
    {
        final Random random = new Random();
        final List<Direction> directionList = new ArrayList<Direction>();
        if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK)
        {
            this.directionForHuman = super.getDirection();
        }
        else if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK)
        {
            this.directionForHuman = super.getDirection().left();
        }
        else if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK)
        {
            this.directionForHuman = super.getDirection().right();
        }
        else
        {
            if (theNeighbors.get(getDirection()) == Terrain.GRASS)
            {
                directionList.add(super.getDirection());
            }
            if (theNeighbors.get(getDirection().left()) == Terrain.GRASS)
            {
                directionList.add(super.getDirection().left());
            }
            if (theNeighbors.get(super.getDirection().right()) == Terrain.GRASS)
            {
                directionList.add(super.getDirection().right());
            }
            if (directionList.isEmpty())
            {
                this.directionForHuman = super.getDirection().reverse();
            }
            else
            {
                final int rand = random.nextInt(directionList.size());
                this.directionForHuman = directionList.get(rand);
            }
        }
        return this.directionForHuman;
    }
}
