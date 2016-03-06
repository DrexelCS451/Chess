package com.chess.Tests;

import com.chess.Models.Coordinate;
import junit.framework.TestCase;

/**
 * Created by AlexMarion on 3/6/16.
 */
public class CoordinateTest extends TestCase {

    public void testGetX() throws Exception {
        Coordinate c = new Coordinate(1, 2);

        assertTrue(c.getX() == 1);
        assertFalse(c.getX() == 2);
    }

    public void testGetY() throws Exception {
        Coordinate c = new Coordinate(1, 2);

        assertTrue(c.getY() == 2);
        assertFalse(c.getY() == 1);
    }
}