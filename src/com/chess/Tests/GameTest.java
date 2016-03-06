package com.chess.Tests;

import com.chess.Helpers.BoardHelper;
import com.chess.Models.*;
import junit.framework.TestCase;

/**
 * Created by AlexMarion on 3/6/16.
 */
public class GameTest extends TestCase {

    public void testGetBoard() throws Exception {
        Game game = new Game();
        game.startGame();
        Board board = BoardHelper.CreateBoard(true);

        assertTrue(game.getBoard().toString().equals(board.toString()));
    }

    public void testSetBoard() throws Exception {
        Game game = new Game();
        game.startGame();
        Board board = new Board("0,0,BLACK_ROOK\n" +
                "0,1,EMPTY\n" + // Changed this to empty
                "0,2,EMPTY\n" +
                "0,3,EMPTY\n" +
                "0,4,EMPTY\n" +
                "0,5,EMPTY\n" +
                "0,6,WHITE_PAWN\n" +
                "0,7,WHITE_ROOK\n" +
                "1,0,BLACK_KNIGHT\n" +
                "1,1,BLACK_PAWN\n" +
                "1,2,EMPTY\n" +
                "1,3,BLACK_PAWN\n" + // Changed this to black pawn
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
                "7,7,WHITE_ROOK\n");
        game.setBoard(board);
        assertTrue(game.getBoard().toString().equals(board.toString()));
    }

    public void testStartGame() throws Exception {
        Game game = new Game();
        game.startGame();
        Board board = BoardHelper.CreateBoard(true);

        assertTrue(game.getBoard().toString().equals(board.toString()));
    }

    public void testMakeMove() throws Exception {
        Game game = new Game();
        game.startGame();
        Board board = BoardHelper.CreateBoard(true);

        ChessMove movePawn = new ChessMove(board.getCell(0, 6), board.getCell(0, 5));
        game.makeMove(movePawn);

        Cell moved1 = new Cell(new Coordinate(0, 6), Chess.Pieces.EMPTY);
        Cell moved2 = new Cell(new Coordinate(0, 5), Chess.Pieces.WHITE_PAWN);
        board.setCell(0, 6, moved1);
        board.setCell(0, 5, moved2);

        assertTrue(game.getBoard().toString().equals(board.toString()));

        Board board2 = BoardHelper.CreateBoard(true);
        assertFalse(game.getBoard().toString().equals(board2.toString()));
    }

    public void testEndGame() throws Exception {
        // TODO: implement this method
    }
}