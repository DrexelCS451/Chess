package com.chess.Tests;

import com.chess.Helpers.BoardHelper;
import com.chess.Models.Board;
import com.chess.Models.Cell;
import com.chess.Models.Chess;
import com.chess.Models.Coordinate;
import junit.framework.TestCase;

/**
 * Created by AlexMarion on 3/6/16.
 */
public class BoardTest extends TestCase {

    public void testGetCell() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
        Cell cell = new Cell(new Coordinate(0, 0), Chess.Pieces.BLACK_ROOK);
        assertTrue(board.getCell(0, 0).getPos().getX() == cell.getPos().getX());
        assertTrue(board.getCell(0, 0).getPos().getY() == cell.getPos().getY());
        assertTrue(board.getCell(0, 0).getCellState().toString().equals(cell.getCellState().toString()));

        assertFalse(board.getCell(0, 0).getPos().getX() == 1);
        assertFalse(board.getCell(0, 0).getPos().getY() == 1);
        assertFalse(board.getCell(0, 0).getCellState().toString().equals(Chess.Pieces.BLACK_PAWN));
    }

    public void testSetCell() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
        Cell cell = new Cell(new Coordinate(0, 0), Chess.Pieces.EMPTY);
        board.setCell(0, 0, cell);

        assertTrue(board.getCell(0, 0).getPos().getX() == cell.getPos().getX());
        assertTrue(board.getCell(0, 0).getPos().getY() == cell.getPos().getY());
        assertTrue(board.getCell(0, 0).getCellState().toString().equals(cell.getCellState().toString()));

        assertFalse(board.getCell(0, 0).getPos().getX() == 1);
        assertFalse(board.getCell(0, 0).getPos().getY() == 1);
        assertFalse(board.getCell(0, 0).getCellState().toString().equals(Chess.Pieces.BLACK_PAWN));
    }

    public void testGetBoardState() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
        assertTrue(board.getBoardState() == null);
        assertFalse(board.getBoardState() == Chess.BoardState.BLACK_TURN);
    }

    public void testSetBoardState() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
        board.setBoardState(Chess.BoardState.BLACK_TURN);
        assertTrue(board.getBoardState().toString().equals(Chess.BoardState.BLACK_TURN.toString()));
        assertFalse(board.getBoardState().toString().equals(Chess.BoardState.WHITE_TURN.toString()));
    }

    public void testToString() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
        assertTrue(board.toString().equals("0,0,BLACK_ROOK\n" +
                "0,1,BLACK_PAWN\n" +
                "0,2,EMPTY\n" +
                "0,3,EMPTY\n" +
                "0,4,EMPTY\n" +
                "0,5,EMPTY\n" +
                "0,6,WHITE_PAWN\n" +
                "0,7,WHITE_ROOK\n" +
                "1,0,BLACK_KNIGHT\n" +
                "1,1,BLACK_PAWN\n" +
                "1,2,EMPTY\n" +
                "1,3,EMPTY\n" +
                "1,4,EMPTY\n" +
                "1,5,EMPTY\n" +
                "1,6,WHITE_PAWN\n" +
                "1,7,WHITE_KNIGHT\n" +
                "2,0,BLACK_BISHOP\n" +
                "2,1,BLACK_PAWN\n" +
                "2,2,EMPTY\n" +
                "2,3,EMPTY\n" +
                "2,4,EMPTY\n" +
                "2,5,EMPTY\n" +
                "2,6,WHITE_PAWN\n" +
                "2,7,WHITE_BISHOP\n" +
                "3,0,BLACK_QUEEN\n" +
                "3,1,BLACK_PAWN\n" +
                "3,2,EMPTY\n" +
                "3,3,EMPTY\n" +
                "3,4,EMPTY\n" +
                "3,5,EMPTY\n" +
                "3,6,WHITE_PAWN\n" +
                "3,7,WHITE_QUEEN\n" +
                "4,0,BLACK_KING\n" +
                "4,1,BLACK_PAWN\n" +
                "4,2,EMPTY\n" +
                "4,3,EMPTY\n" +
                "4,4,EMPTY\n" +
                "4,5,EMPTY\n" +
                "4,6,WHITE_PAWN\n" +
                "4,7,WHITE_KING\n" +
                "5,0,BLACK_BISHOP\n" +
                "5,1,BLACK_PAWN\n" +
                "5,2,EMPTY\n" +
                "5,3,EMPTY\n" +
                "5,4,EMPTY\n" +
                "5,5,EMPTY\n" +
                "5,6,WHITE_PAWN\n" +
                "5,7,WHITE_BISHOP\n" +
                "6,0,BLACK_KNIGHT\n" +
                "6,1,BLACK_PAWN\n" +
                "6,2,EMPTY\n" +
                "6,3,EMPTY\n" +
                "6,4,EMPTY\n" +
                "6,5,EMPTY\n" +
                "6,6,WHITE_PAWN\n" +
                "6,7,WHITE_KNIGHT\n" +
                "7,0,BLACK_ROOK\n" +
                "7,1,BLACK_PAWN\n" +
                "7,2,EMPTY\n" +
                "7,3,EMPTY\n" +
                "7,4,EMPTY\n" +
                "7,5,EMPTY\n" +
                "7,6,WHITE_PAWN\n" +
                "7,7,WHITE_ROOK"));
        assertFalse(board.toString().equals(""));

    }

    public void testFindKing() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
        Cell kingCell = new Cell(new Coordinate(4, 7), Chess.Pieces.WHITE_KING);
        assertTrue(board.findKing(Chess.Pieces.WHITE_KING).getX() == kingCell.getPos().getX());
        assertTrue(board.findKing(Chess.Pieces.WHITE_KING).getY() == kingCell.getPos().getY());

        assertFalse(board.findKing(Chess.Pieces.WHITE_KING).getX() == 0);
        assertFalse(board.findKing(Chess.Pieces.WHITE_KING).getY() == 0);
    }
}