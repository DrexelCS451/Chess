package com.chess.Tests;

import com.chess.Helpers.BoardHelper;
import com.chess.Models.Board;
import junit.framework.TestCase;

/**
 * Created by AlexMarion on 3/6/16.
 */
public class BoardHelperTest extends TestCase {

    public void testCreateBoard() throws Exception {
        Board board1 = BoardHelper.CreateBoard(true);
        Board board2 = new Board("0,0,BLACK_ROOK\n" +
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
                "7,7,WHITE_ROOK\n");
        assertTrue(board1.toString().equals(board2.toString()));

        board2 = new Board("0,0,BLACK_ROOK\n" +
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
        assertFalse(board1.toString().equals(board2.toString()));
    }

    public void testSquareIsUsers() throws Exception {
        // Create a board where the user is white
        Board board1 = BoardHelper.CreateBoard(true);

        // WHITE_ROOK square
        assertTrue(BoardHelper.SquareIsUsers(7, 7, board1));
        // WHITE_KING square
        assertTrue(BoardHelper.SquareIsUsers(4, 7, board1));

        // EMPTY square
        assertFalse(BoardHelper.SquareIsUsers(0, 1, board1));
        // BLACK_KING square
        assertFalse(BoardHelper.SquareIsUsers(4, 0, board1));
    }
}