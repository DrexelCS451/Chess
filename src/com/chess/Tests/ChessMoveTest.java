package com.chess.Tests;

import com.chess.Models.Cell;
import com.chess.Models.Chess;
import com.chess.Models.ChessMove;
import com.chess.Models.Coordinate;
import junit.framework.TestCase;

/**
 * Created by AlexMarion on 3/6/16.
 */
public class ChessMoveTest extends TestCase {

    public void testGetFrom() throws Exception {
        Cell from  = new Cell(new Coordinate(0, 0), Chess.Pieces.BLACK_PAWN);
        Cell to  = new Cell(new Coordinate(1, 2), Chess.Pieces.EMPTY);
        ChessMove move = new ChessMove(from, to);

        assertTrue(move.getFrom().getPos().getX() == from.getPos().getX());
        assertTrue(move.getFrom().getPos().getY() == from.getPos().getY());
        assertTrue(move.getFrom().getCellState().toString().equals(from.getCellState().toString()));

        assertFalse(move.getFrom().getPos().getX() == to.getPos().getX());
        assertFalse(move.getFrom().getPos().getY() == to.getPos().getY());
        assertFalse(move.getFrom().getCellState().toString().equals(to.getCellState().toString()));
    }

    public void testGetTo() throws Exception {
        Cell from  = new Cell(new Coordinate(0, 0), Chess.Pieces.BLACK_PAWN);
        Cell to  = new Cell(new Coordinate(1, 2), Chess.Pieces.EMPTY);
        ChessMove move = new ChessMove(from, to);

        assertTrue(move.getTo().getPos().getX() == to.getPos().getX());
        assertTrue(move.getTo().getPos().getY() == to.getPos().getY());
        assertTrue(move.getTo().getCellState().toString().equals(to.getCellState().toString()));

        assertFalse(move.getTo().getPos().getX() == from.getPos().getX());
        assertFalse(move.getTo().getPos().getY() == from.getPos().getY());
        assertFalse(move.getTo().getCellState().toString().equals(from.getCellState().toString()));
    }
}