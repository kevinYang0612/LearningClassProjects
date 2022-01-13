package tests;

import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
/**
 *  This is a class called "ATVTest". It is an unit test class
 *  This class is mainly to test out the ATV behaves like it should be
 *
 * @author Kevin Yang
 * @version Dec 12th, 2021
 * */
public class ATVTest
{
    /** a static final constant for number of time to do the test due to the high random possibilities*/
    private static final int TRIES_FOR_RANDOMNESS = 50;

    /** create an ATV object and set the location on the map */
    private final ATV avt = new ATV(1, 11, Direction.NORTH);

    /** create a Truck object and set the location on the map */
    private final Truck truck = new Truck(1, 11, Direction.NORTH);

    /** create a Human object and set the location on the map*/
    private final Human human = new Human(1, 11, Direction.NORTH);

    /** Test method to test if ATV constructor works.*/
    @Test
    public void testATVConstructor()
    {
        Assertions.assertEquals(1, this.avt.getX(), "ATV x coordinate not initialized correctly!");
        Assertions.assertEquals(11, this.avt.getY(), "ATV y coordinate not initialized correctly!");
        Assertions.assertEquals(Direction.NORTH, this.avt.getDirection(), "ATV direction not initialized correctly!");
        Assertions.assertEquals(25, this.avt.getDeathTime(), "ATV death time not initialized correctly!");
        Assertions.assertTrue(this.avt.isAlive(), "ATV isAlive() fails initially!");
    }

    /** Test method for ATV collide method. */
    @Test
    public void testAtvCollide()
    {
        this.avt.collide(this.truck);
        Assertions.assertFalse(this.avt.isAlive(), "Collision to a truck does not work properly!");
        this.avt.reset();

        this.avt.collide(this.human);
        Assertions.assertTrue(this.avt.isAlive(), "Collision to a human does not work properly!");
        this.avt.reset();

    }

    /** Test method for ATV get image correctly */
    @Test
    public void testATVGetImage()
    {
        Assertions.assertEquals("atv.gif", this.avt.getImageFileName());
        this.avt.collide(this.truck);
        Assertions.assertEquals("atv_dead.gif", this.avt.getImageFileName());
    }

    /** Test method for Atv setters. */
    @Test
    public void testAtvSetters()
    {
        this.avt.setX(12);
        Assertions.assertEquals(12, this.avt.getX(), "ATV setX failed.");
        this.avt.setY(13);
        Assertions.assertEquals(13, this.avt.getY(), "ATV setY failed.");
        this.avt.setDirection(Direction.NORTH);
        Assertions.assertEquals(Direction.NORTH, this.avt.getDirection(), "ATV setDirection failed.");
    }

    /** Test method for ATV poke. */
    @Test
    public void testATVPoke()
    {
        this.avt.collide(this.truck);
        for (int i = 0; i <= this.avt.getDeathTime(); i++)
        {
            this.avt.poke();
        }
    }

    /** Test method for ATV canPass. */
    @Test
    public void testCanPass()
    {
        final ATV atv = new ATV(0, 0, Direction.NORTH);

        for (final Terrain destinationTerrain : Terrain.values())
        {
            for (final Light currentLightCondition : Light.values())
            {
                if (destinationTerrain != Terrain.WALL)
                {
                    Assertions.assertTrue(atv.canPass(destinationTerrain, currentLightCondition),
                            "ATV should be able to pass GRASS" + ", with light " + currentLightCondition);
                }
            }
        }
    }

    /** Test method for ATV choose direction with one wall. */
    @Test
    public void testChooseDirectionOneWall()
    {
        final ATV atv = new ATV(0, 0, Direction.NORTH);
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.GRASS);
        neighbors.put(Direction.EAST, Terrain.GRASS);
        neighbors.put(Direction.SOUTH, Terrain.GRASS);

        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++)
        {
            Assertions.assertNotEquals(Direction.SOUTH, atv.chooseDirection(neighbors));
            Assertions.assertNotEquals(Direction.WEST, atv.chooseDirection(neighbors));
        }
    }
    /** Test method for ATV choose direction with two walls. */
    @Test
    public void testChooseDirectionTwoWalls() {
        final ATV atv = new ATV(0, 0, Direction.NORTH);
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.EAST, Terrain.GRASS);
        neighbors.put(Direction.SOUTH, Terrain.GRASS);

        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++)
        {
            Assertions.assertNotEquals(Direction.SOUTH, atv.chooseDirection(neighbors));
            Assertions.assertNotEquals(Direction.NORTH, atv.chooseDirection(neighbors));
            Assertions.assertNotEquals(Direction.WEST, atv.chooseDirection(neighbors));
        }
    }
}
