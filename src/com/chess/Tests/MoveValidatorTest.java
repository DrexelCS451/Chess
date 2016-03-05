package com.chess.Tests;

import com.chess.Helpers.BoardHelper;
import com.chess.Helpers.MoveValidator;
import com.chess.Models.Board;
import com.chess.Models.ChessMove;
import com.chess.Models.Coordinate;
import com.chess.Models.Game;
import junit.framework.TestCase;

/**
 * Created by AlexMarion on 3/5/16.
 */
public class MoveValidatorTest extends TestCase {

    public void testFindMoves() throws Exception {

    }

    public void testIsCheck() throws Exception {

    }

    public void testIsCheckMate() throws Exception {

    }

    public void testIsValidMove() throws Exception {
        this.testPawnMoves();
        this.testKingMoves();
        this.testQueenMoves();
        this.testRookMoves();
        this.testKnightMoves();
        this.testBishopMoves();
    }

    public void testPawnMoves() throws Exception {
        // TODO: create a game and make the move after each true assertion
        Game game = new Game();
        Board board = BoardHelper.CreateBoard(true);

        // Test a normal pawn move
        ChessMove moveBy1 = new ChessMove(board.getCell(0, 6), board.getCell(0, 5));
        assertTrue(MoveValidator.isValidMove(moveBy1, board));

        // Test that the pawn cannot move by 2 after it has moved
        ChessMove falseMoveBy2 = new ChessMove(board.getCell(0, 5), board.getCell(0, 3));
        assertFalse(MoveValidator.isValidMove(falseMoveBy2, board));

        // Test that the pawn can move by 2 if it hasn't moved
        ChessMove trueMoveBy2 = new ChessMove(board.getCell(1, 6), board.getCell(1, 4));
        assertTrue(MoveValidator.isValidMove(trueMoveBy2, board));

        // Test that no pawn can move backward
        ChessMove moveBack1 = new ChessMove(board.getCell(0, 5), board.getCell(0, 6));
        assertFalse(MoveValidator.isValidMove(moveBack1, board));

    }

    public void testKingMoves() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
    }

    public void testQueenMoves() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
    }

    public void testRookMoves() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
    }

    public void testKnightMoves() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
    }

    public void testBishopMoves() throws Exception {

    }
}