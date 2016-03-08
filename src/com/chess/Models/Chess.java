package com.chess.Models;

/**
 * Created by tomer on 3/4/16.
 */
public class Chess {
    public static enum Pieces{
        BLACK_KING, BLACK_QUEEN, BLACK_KNIGHT, BLACK_ROOK, BLACK_PAWN, BLACK_BISHOP,WHITE_KING, WHITE_QUEEN, WHITE_KNIGHT, WHITE_ROOK, WHITE_PAWN, WHITE_BISHOP,EMPTY
    }

    public static enum BoardState {
        BLACK_WIN, WHITE_WIN, BLACK_TURN, WHITE_TURN, STALEMATE
    }
}
