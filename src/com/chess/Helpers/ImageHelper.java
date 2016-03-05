package com.chess.Helpers;

import com.chess.Models.Board;
import com.chess.Models.Cell;
import com.chess.Models.Chess;
import com.chess.Models.Coordinate;

/**
 * Created by tomer on 3/4/16.
 */
public class ImageHelper {
    public static String getPeiceIcon(Chess.Pieces p)
    {
        String icon;
        switch (p) {
            case WHITE_KING: icon = "♔";
                break;
            case WHITE_QUEEN: icon = "♕";
                break;
            case WHITE_BISHOP: icon = "♗";
                break;
            case WHITE_KNIGHT: icon = "♘";
                break;
            case WHITE_PAWN: icon = "♙";
                break;
            case WHITE_ROOK: icon = "♖";
                break;
            case BLACK_KING: icon = "♚";
                break;
            case BLACK_QUEEN: icon = "♛";
                break;
            case BLACK_BISHOP: icon = "♝";
                break;
            case BLACK_KNIGHT: icon = "♞";
                break;
            case BLACK_PAWN: icon = "♟";
                break;
            case BLACK_ROOK: icon = "♜";
                break;
            case EMPTY: icon = "";
                break;
            default: icon ="";
                break;
        }

        return icon;
    }



}
