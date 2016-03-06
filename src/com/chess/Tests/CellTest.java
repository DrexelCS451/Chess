package com.chess.Tests;

import com.chess.Models.Cell;
import com.chess.Models.Chess;
import com.chess.Models.Coordinate;
import junit.framework.TestCase;

/**
 * Created by AlexMarion on 3/6/16.
 */
public class CellTest extends TestCase {

    public void testGetPos() throws Exception {
        Cell cell = new Cell(new Coordinate(0, 0), Chess.Pieces.EMPTY);
        assertTrue(cell.getPos().getX() == 0);
        assertTrue(cell.getPos().getX() != 1);
        assertTrue(cell.getPos().getY() == 0);
        assertTrue(cell.getPos().getY() != 1);
    }

    public void testGetCellState() throws Exception {
        Cell cell = new Cell(new Coordinate(0, 0), Chess.Pieces.EMPTY);
        assertTrue(cell.getCellState().toString().equals(Chess.Pieces.EMPTY.toString()));
        assertFalse(cell.getCellState().toString().equals(Chess.Pieces.BLACK_KING.toString()));

    }

    public void testSetCellState() throws Exception {
        Cell cell = new Cell(new Coordinate(0, 0), Chess.Pieces.EMPTY);
        cell.setCellState(Chess.Pieces.BLACK_KING);
        assertFalse(cell.getCellState().toString().equals(Chess.Pieces.EMPTY.toString()));
        assertTrue(cell.getCellState().toString().equals(Chess.Pieces.BLACK_KING.toString()));
    }
}