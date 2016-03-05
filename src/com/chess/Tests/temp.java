package com.chess.Tests;

import com.chess.Models.Board;
import com.chess.Models.Cell;
import com.chess.Models.Chess;
import com.chess.Models.ChessMove;

/**
 * Created by kellyshiptoski on 3/4/16.
 */
public class temp {

    //================================---------------------================================
    //================================ Validate ROOK moves ================================
    //================================---------------------================================
    private static boolean validate_ROOK_MOVE(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();
        // rook can move in the column and row
        // any distance as long as it doesn't
        // run into another piece

        int moveRow = toCell.getPos().getX();
        int moveCol = toCell.getPos().getY();
        int fromRow = fromCell.getPos().getX();
        int fromCol = fromCell.getPos().getY();

        if (fromCell == toCell)
            return false;
        else if (moveRow == fromRow) {
            int row = fromRow;
            for(int i = fromCol; i <= moveCol;i--) {
                if (board.getCell(moveRow, moveCol - 1).getCellState() == Chess.Pieces.EMPTY)
                    return false;
            }
            return true;
        }
        else if (moveCol == fromCol) {
            for(int i = fromRow; i <= moveRow;i--) {
                if (board.getCell(moveRow - 1, moveCol).getCellState() == Chess.Pieces.EMPTY)
                    return false;
            }
            return true;
        }

        return false;

    }

    private static boolean validate_BLACK_ROOK(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();

        int moveRow = toCell.getPos().getX();
        int moveCol = toCell.getPos().getY();
        int fromRow = fromCell.getPos().getX();
        int fromCol = fromCell.getPos().getY();

        if (fromCell == toCell)
            return false;
        else if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_ROOK_MOVE(move, board);
        }
        else if(toCell.getCellState() == Chess.Pieces.WHITE_PAWN
                || toCell.getCellState() == Chess.Pieces.WHITE_KING
                || toCell.getCellState() == Chess.Pieces.WHITE_QUEEN
                || toCell.getCellState() == Chess.Pieces.WHITE_ROOK
                || toCell.getCellState() == Chess.Pieces.WHITE_KNIGHT
                || toCell.getCellState() == Chess.Pieces.WHITE_BISHOP) {
            if (moveRow == fromRow) {
                int row = fromRow;
                for(int i = fromCol; i <= moveCol;i--) {
                    if (board.getCell(moveRow, moveCol - 1).getCellState() == Chess.Pieces.EMPTY)
                        return false;
                }
                return true;
            }
            else if (moveCol == fromCol) {
                for(int i = fromRow; i <= moveRow;i--) {
                    if (board.getCell(moveRow - 1, moveCol).getCellState() == Chess.Pieces.EMPTY)
                        return false;
                }
                return true;
            }
        }

        return false;

    }

    private static boolean validate_WHITE_ROOK(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();

        int moveRow = toCell.getPos().getX();
        int moveCol = toCell.getPos().getY();
        int fromRow = fromCell.getPos().getX();
        int fromCol = fromCell.getPos().getY();

        if (fromCell == toCell)
            return false;

        else if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_ROOK_MOVE(move, board);
        }

        else if (toCell.getCellState() == Chess.Pieces.BLACK_PAWN
                || toCell.getCellState() == Chess.Pieces.BLACK_KING
                || toCell.getCellState() == Chess.Pieces.BLACK_QUEEN
                || toCell.getCellState() == Chess.Pieces.BLACK_ROOK
                || toCell.getCellState() == Chess.Pieces.BLACK_KNIGHT
                || toCell.getCellState() == Chess.Pieces.BLACK_BISHOP) {
            if (moveRow == fromRow) {
                int row = fromRow;
                for(int i = fromCol; i <= moveCol;i--) {
                    if (board.getCell(moveRow, moveCol - 1).getCellState() == Chess.Pieces.EMPTY)
                        return false;
                }
                return true;
            }
            else if (moveCol == fromCol) {
                for(int i = fromRow; i <= moveRow;i--) {
                    if (board.getCell(moveRow - 1, moveCol).getCellState() == Chess.Pieces.EMPTY)
                        return false;
                }
                return true;
            }
        }
        return false;
    }


    //================================-----------------------================================
    //================================ Validate KNIGHT moves ================================
    //================================-----------------------================================
    private static boolean validate_KNIGHT_MOVE(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();
        // rook can move in the column and row
        // any distance as long as it doesn't
        // run into another piece

        int toRow = toCell.getPos().getX();
        int toCol = toCell.getPos().getY();
        int fromRow = fromCell.getPos().getX();
        int fromCol = fromCell.getPos().getY();

        if (fromCell == toCell)
            return false;
        else if (toRow > 8 || toRow < -1 || toCol > 8 || toCol < -1)
            return false;
        else if ((fromCol - 2) == toCol && (fromRow - 1) == toRow)
            return true;
        else if ((fromCol - 1) == toCol && (fromRow - 2) == toRow)
            return true;
        else if ((fromCol - 2) == toCol && (fromRow + 1) == toRow)
            return true;
        else if ((fromRow + 2) == toRow && (fromCol - 1) == toCol)
            return true;
        else if ((fromRow - 1) == toRow && (fromCol + 2) == toCol)
            return true;
        else if ((fromRow + 1) == toRow && (fromCol + 2) == toCol)
            return true;
        else if ((fromRow - 2) == toRow && (fromCol + 1) == toCol)
            return true;
        else if ((fromRow + 2) == toRow && (fromCol + 1) == toCol)
            return true;
        else
            return false;
    }

    private static boolean validate_BLACK_KNIGHT(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();

        int toRow = toCell.getPos().getX();
        int toCol = toCell.getPos().getY();
        int fromRow = fromCell.getPos().getX();
        int fromCol = fromCell.getPos().getY();

        if (fromCell == toCell)
            return false;
        else if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_KNIGHT_MOVE(move, board);
        }
        else if (toCell.getCellState() == Chess.Pieces.WHITE_PAWN
                || toCell.getCellState() == Chess.Pieces.WHITE_KING
                || toCell.getCellState() == Chess.Pieces.WHITE_QUEEN
                || toCell.getCellState() == Chess.Pieces.WHITE_ROOK
                || toCell.getCellState() == Chess.Pieces.WHITE_KNIGHT
                || toCell.getCellState() == Chess.Pieces.WHITE_BISHOP) {
            if (toRow > 8 || toRow < -1 || toCol > 8 || toCol < -1)
                return false;
            else if ((fromCol - 2) == toCol && (fromRow - 1) == toRow)
                return true;
            else if ((fromCol - 1) == toCol && (fromRow - 2) == toRow)
                return true;
            else if ((fromCol - 2) == toCol && (fromRow + 1) == toRow)
                return true;
            else if ((fromRow + 2) == toRow && (fromCol - 1) == toCol)
                return true;
            else if ((fromRow - 1) == toRow && (fromCol + 2) == toCol)
                return true;
            else if ((fromRow + 1) == toRow && (fromCol + 2) == toCol)
                return true;
            else if ((fromRow - 2) == toRow && (fromCol + 1) == toCol)
                return true;
            else if ((fromRow + 2) == toRow && (fromCol + 1) == toCol)
                return true;
        }
        return false;
    }

    private static boolean validate_WHITE_KNIGHT(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();

        int toRow = toCell.getPos().getX();
        int toCol = toCell.getPos().getY();
        int fromRow = fromCell.getPos().getX();
        int fromCol = fromCell.getPos().getY();

        if (fromCell == toCell)
            return false;
        else if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_KNIGHT_MOVE(move, board);
        }
        else if (toCell.getCellState() == Chess.Pieces.BLACK_PAWN
                || toCell.getCellState() == Chess.Pieces.BLACK_KING
                || toCell.getCellState() == Chess.Pieces.BLACK_QUEEN
                || toCell.getCellState() == Chess.Pieces.BLACK_ROOK
                || toCell.getCellState() == Chess.Pieces.BLACK_KNIGHT
                || toCell.getCellState() == Chess.Pieces.BLACK_BISHOP) {
            if (toRow > 8 || toRow < -1 || toCol > 8 || toCol < -1)
                return false;
            else if ((fromCol - 2) == toCol && (fromRow - 1) == toRow)
                return true;
            else if ((fromCol - 1) == toCol && (fromRow - 2) == toRow)
                return true;
            else if ((fromCol - 2) == toCol && (fromRow + 1) == toRow)
                return true;
            else if ((fromRow + 2) == toRow && (fromCol - 1) == toCol)
                return true;
            else if ((fromRow - 1) == toRow && (fromCol + 2) == toCol)
                return true;
            else if ((fromRow + 1) == toRow && (fromCol + 2) == toCol)
                return true;
            else if ((fromRow - 2) == toRow && (fromCol + 1) == toCol)
                return true;
            else if ((fromRow + 2) == toRow && (fromCol + 1) == toCol)
                return true;
        }
        return false;
    }

    //================================-----------------------================================
    //================================ Validate BISHOP moves ================================
    //================================-----------------------================================
    private static boolean validate_BISHOP_MOVE(ChessMove move, Board board) {
        return false;
    }

    private static boolean validate_BLACK_BISHOP(ChessMove move, Board board) {
        return false;
    }

    private static boolean validate_WHITE_BISHOP(ChessMove move, Board board) { return false; }
}
}
