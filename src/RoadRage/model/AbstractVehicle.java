package model;

import java.util.Map;
/**
 *  This is an abstract class called "AbstractVehicle" that implements Vehicle interface
 *  It is a superclass for all move objects in this programming assignment
 *
 * @author Kevin Yang
 * @version Dec 12th, 2021
 * */
public abstract class AbstractVehicle implements Vehicle
{
    /** an x coordinate for the moving object */
    private int x;

    /** a y coordinate for the moving object */
    private int y;

    /** an initial x coordinate for the moving object */
    private final int startX;

    /** an initial y coordinate for the moving object */
    private final int startY;

    /** a direction for the moving object */
    private Direction direction;

    /** an initial direction for the moving object */
    private final Direction startDirection;

    /**
     * assign a dead time (in number of moves) of staying dead for a moving object
     * if collided with another moving object
     * */
    private int deathTime;

    /** a timer to count down for a moving object after collided */
    private int deathTimer;

    /**
     * Constructs an AbstractVehicle object and initialize the fields accordingly
     *
     * @param theX to set the moving object x coordinate
     * @param theY to set the moving object y coordinate
     * @param theDirection to set the moving direction for moving object
     * @param theDeathTime to set the number of moves of being dead after collision
     * */
    public AbstractVehicle(final int theX, final int theY, final Direction theDirection, final int theDeathTime)
    {
        this.x = theX;
        this.y = theY;
        this.direction = theDirection;
        this.startX = theX;
        this.startY = theY;
        this.startDirection = theDirection;
        this.deathTime = theDeathTime;
        this.deathTimer = theDeathTime;
    }

    /**
     * Check if a moving object should make a pass on a terrain
     * @param theTerrain, a moving object trying to pass a Terrain object
     * @param theLight, color of the traffic will determine if a moving object should pass
     * @return boolean value to make the pass or not
     * */
    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) { return false;}

    /**
     * determine a direction of a moving object that is going to move base on the map terrain
     * and neighbor terrain
     * @param theNeighbors neighbor terrain on the map
     * @return a direction of this moving object
     * */
    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        return null;
    }

    /**
     * Called when this Vehicle collides with the specified other Vehicle.
     *
     * @param theOther The other object.
     */
    @Override
    public void collide(Vehicle theOther)
    {
        if ((isAlive() && theOther.isAlive()) && (this.deathTime > theOther.getDeathTime()))
        {
            this.deathTime = 0;
        }
    }

    /**
     * Returns the number of updates between this vehicle's death and when it
     * should be revived.
     *
     * @return the number of updates.
     */
    @Override
    public int getDeathTime() {
        return this.deathTime;
    }

    /**
     * Returns the file name of the image for this Vehicle object, such as
     * "car.gif".
     *
     * @return the file name.
     */
    @Override
    public String getImageFileName()
    {
        String file = this.getClass().getSimpleName().toLowerCase();
        if (isAlive())
        {
            file += ".gif";
        }
        else
        {
            file += "_dead.gif";
        }
        return file;
    }

    /**
     * Returns this Vehicle object's direction.
     *
     * @return the direction.
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Returns this Vehicle object's x-coordinate.
     *
     * @return the x-coordinate.
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * Returns this Vehicle object's y-coordinate.
     *
     * @return the y-coordinate.
     */
    @Override
    public int getY() {
        return this.y;
    }

    /**
     * Returns whether this Vehicle object is alive and should move on the map.
     *
     * @return true if the object is alive, false otherwise.
     */
    @Override
    public boolean isAlive()
    {
        return this.deathTime == this.deathTimer;
    }

    /**
     * Called by the UI to notify a dead vehicle that 1 movement round has
     * passed, so that it will become 1 move closer to revival.
     */
    @Override
    public void poke()
    {
        if (isAlive())
        {
            this.direction = Direction.random();
        }
        else
        {
            deathTime++;
        }
    }

    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset()
    {
        this.x = this.startX;
        this.y = this.startY;
        this.deathTimer = this.deathTime;
        this.direction = this.startDirection;
    }

    /**
     * Sets this object's facing direction to the given value.
     *
     * @param theDir The new direction.
     */
    @Override
    public void setDirection(Direction theDir)
    {
        this.direction = theDir;
    }

    /**
     * Sets this object's x-coordinate to the given value.
     *
     * @param theX The new x-coordinate.
     */
    @Override
    public void setX(int theX)
    {
        this.x = theX;
    }

    /**
     * Sets this object's y-coordinate to the given value.
     *
     * @param theY The new y-coordinate.
     */
    @Override
    public void setY(int theY)
    {
        this.y = theY;
    }
}
