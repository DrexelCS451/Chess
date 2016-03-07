package com.chess.Tests;

import com.chess.Helpers.BoardHelper;
import com.chess.Helpers.MoveValidator;
import com.chess.Models.*;
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
        Game game = new Game();
        game.startGame();
        Board board = game.getBoard();

        Cell pawn1 = board.getCell(0, 6);
        Cell pawn2 = board.getCell(1, 6);

        // Test a normal pawn move
        ChessMove moveBy1 = new ChessMove(pawn1, board.getCell(0, 5));
        assertTrue(MoveValidator.isValidMove(moveBy1, board));
        board = game.makeMove(moveBy1);
        pawn1 = board.getCell(0, 5);

        // Test that the pawn cannot move by 2 after it has moved
        ChessMove falseMoveBy2 = new ChessMove(pawn1, board.getCell(0, 3));
        assertFalse(MoveValidator.isValidMove(falseMoveBy2, board));

        // Test that the pawn can move by 2 if it hasn't moved
        ChessMove trueMoveBy2 = new ChessMove(pawn2, board.getCell(1, 4));
        assertTrue(MoveValidator.isValidMove(trueMoveBy2, board));
        board = game.makeMove(trueMoveBy2);
        pawn2 = board.getCell(1, 4);

        // Test that no pawn can move backward
        ChessMove moveBack1 = new ChessMove(pawn1, board.getCell(0, 6));
        assertFalse(MoveValidator.isValidMove(moveBack1, board));

        // Test that pawns cant move sideways
        ChessMove falseSideMove = new ChessMove(pawn1, board.getCell(1, 5));
        assertFalse(MoveValidator.isValidMove(falseSideMove, board));

        // Test that pawns cant attack White pieces
        ChessMove falseAttackMoveFriend = new ChessMove(pawn1, pawn2);
        assertFalse(MoveValidator.isValidMove(falseAttackMoveFriend, board));

        // Test that pawns cant attack Empty spaces
        ChessMove falseAttackMoveEmpty1 = new ChessMove(pawn2, board.getCell(0, 3));
        assertFalse(MoveValidator.isValidMove(falseAttackMoveEmpty1, board));

        ChessMove falseAttackMoveEmpty2 = new ChessMove(pawn2, board.getCell(2, 3));
        assertFalse(MoveValidator.isValidMove(falseAttackMoveEmpty2, board));

        // Test that pawns can attack Black pieces
        ChessMove moveToAttack1 = new ChessMove(pawn2, board.getCell(1, 3));
        assertTrue(MoveValidator.isValidMove(moveToAttack1, board));
        board = game.makeMove(moveToAttack1);
        pawn2 = board.getCell(1, 3);

        ChessMove moveToAttack2 = new ChessMove(pawn2, board.getCell(1, 2));
        assertTrue(MoveValidator.isValidMove(moveToAttack2, board));
        board = game.makeMove(moveToAttack2);
        pawn2 = board.getCell(1, 2);

        ChessMove trueAttackMove = new ChessMove(pawn2, board.getCell(2, 1));
        assertTrue(MoveValidator.isValidMove(trueAttackMove, board));
        board = game.makeMove(trueAttackMove);
        pawn2 = board.getCell(2, 1);

        ChessMove trueAttackMove2 = new ChessMove(pawn2, board.getCell(1, 0));
        assertTrue(MoveValidator.isValidMove(trueAttackMove2, board));
        board = game.makeMove(trueAttackMove2);
        pawn2 = board.getCell(1, 0);

        // Test that pawns cannot move through other pieces
        // This board is initialized with a WHITE_KNIGHT in front of the WHITE_PAWN at position (0, 6)
        Board board2 = new Board("0,0,BLACK_ROOK\n" +
                "0,1,BLACK_PAWN\n" +
                "0,2,EMPTY\n" +
                "0,3,EMPTY\n" +
                "0,4,EMPTY\n" +
                "0,5,WHITE_KNIGHT\n" + // Moved white knight to here
                "0,6,WHITE_PAWN\n" + // This pawn is blocked from moving twice
                "0,7,WHITE_ROOK\n" +
                "1,0,BLACK_KNIGHT\n" +
                "1,1,BLACK_PAWN\n" +
                "1,2,EMPTY\n" +
                "1,3,EMPTY\n" +
                "1,4,EMPTY\n" +
                "1,5,EMPTY\n" +
                "1,6,WHITE_PAWN\n" +
                "1,7,EMPTY\n" + // Moved white knight from here
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
        Cell blockedPawn = board2.getCell(0, 6);
        ChessMove blockedMoveBy2 = new ChessMove(blockedPawn, board.getCell(0, 4));
        assertFalse(MoveValidator.isValidMove(blockedMoveBy2, board2));
    }

    public void testKingMoves() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
        Game game = new Game();
        game.startGame();

        Cell king = board.getCell(4, 7);

        // Test that king can't move onto another piece
        ChessMove blockedLeftMove = new ChessMove(king, board.getCell(3, 7));
        assertFalse(MoveValidator.isValidMove(blockedLeftMove, board));

        ChessMove blockedRightMove = new ChessMove(king, board.getCell(5, 7));
        assertFalse(MoveValidator.isValidMove(blockedRightMove, board));

        ChessMove blockedForwardMove = new ChessMove(king, board.getCell(4, 6));
        assertFalse(MoveValidator.isValidMove(blockedForwardMove, board));

        // Create a new board with the king in the center to test moves
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
                "4,4,WHITE_KING\n" + // Moved king to here
                "4,5,EMPTY\n" +
                "4,6,WHITE_PAWN\n" +
                "4,7,EMPTY\n" + // Moved white king from here
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
        Cell centerKing = board2.getCell(4, 4);

        // Test all valid moves
        // TODO: *NOTE* testing for check and checkMate may break this
        ChessMove forwardMove = new ChessMove(centerKing, board2.getCell(4, 3));
        ChessMove backwardMove = new ChessMove(centerKing, board2.getCell(4, 5));
        ChessMove leftMove = new ChessMove(centerKing, board2.getCell(3, 4));
        ChessMove rightMove = new ChessMove(centerKing, board2.getCell(5, 4));

        ChessMove forwardLeftMove = new ChessMove(centerKing, board2.getCell(3, 3));
        ChessMove forwardRightMove = new ChessMove(centerKing, board2.getCell(5, 3));
        ChessMove backwardLeftMove = new ChessMove(centerKing, board2.getCell(3, 5));
        ChessMove backwardRightMove = new ChessMove(centerKing, board2.getCell(5, 5));

        assertTrue(MoveValidator.isValidMove(forwardMove, board2));
        assertTrue(MoveValidator.isValidMove(backwardMove, board2));
        assertTrue(MoveValidator.isValidMove(leftMove, board2));
        assertTrue(MoveValidator.isValidMove(rightMove, board2));

        assertTrue(MoveValidator.isValidMove(forwardLeftMove, board2));
        assertTrue(MoveValidator.isValidMove(forwardRightMove, board2));
        assertTrue(MoveValidator.isValidMove(backwardLeftMove, board2));
        assertTrue(MoveValidator.isValidMove(backwardRightMove, board2));

        // Test that the king cannot move by more than 1
        ChessMove falseMoveBy2 = new ChessMove(centerKing, board2.getCell(4, 2));
        assertFalse(MoveValidator.isValidMove(falseMoveBy2, board2));

        // Test that a king cannot move into check
        game.setBoard(board2);
        board2 = game.makeMove(forwardMove);
        centerKing = board2.getCell(4, 3);

        // TODO: this won't work until isCheck is in king move validator
        ChessMove falseMoveToCheck = new ChessMove(centerKing, board2.getCell(4, 2));
        // TODO: assertFalse(MoveValidator.isValidMove(falseMoveToCheck, board2));

        // Test king attack
        // Create a new board with the king in the center and a piece to attack
        Board board3 = new Board("0,0,BLACK_ROOK\n" +
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
                "4,1,EMPTY\n" + // Moved black pawn from here
                "4,2,EMPTY\n" +
                "4,3,BLACK_PAWN\n" + // Moved black pawn to here
                "4,4,WHITE_KING\n" + // Moved king to here
                "4,5,EMPTY\n" +
                "4,6,WHITE_PAWN\n" +
                "4,7,EMPTY\n" + // Moved white king from here
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
        game.setBoard(board3);
        Cell attackKing = board3.getCell(4, 4);
        Cell victemPawn = board3.getCell(4, 3);
        ChessMove validAttack = new ChessMove(attackKing, victemPawn);
        assertTrue(MoveValidator.isValidMove(validAttack, board3));
        board3 = game.makeMove(validAttack);
        attackKing = board3.getCell(victemPawn.getPos());
    }

    public void testQueenMoves() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
        Game game = new Game();
        game.startGame();
    }

    public void testRookMoves() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
        Game game = new Game();
        game.startGame();
    }

    public void testKnightMoves() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
        Game game = new Game();
        game.startGame();
    }

    public void testBishopMoves() throws Exception {
        Board board = BoardHelper.CreateBoard(true);
        Game game = new Game();
        game.startGame();
    }
}