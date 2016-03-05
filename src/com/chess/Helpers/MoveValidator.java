package com.chess.Helpers;

import com.chess.Models.Board;
import com.chess.Models.Cell;
import com.chess.Models.Chess;
import com.chess.Models.ChessMove;

import java.util.ArrayList;

/**
 * Created by AlexMarion on 3/4/16.
 */
public class MoveValidator {
    public MoveValidator() {}

    public static ArrayList<ChessMove> findMoves(Cell cell) {
        return null;
    }

    public static boolean isCheck(Board board) {
        return false;
    }

    public static boolean isCheckMate(Board board) {
        return false;
    }

    public static boolean isValidMove(ChessMove move, Board board) {
        Chess.Pieces piece = move.getFrom().getCellState();
        switch(piece) {
            case EMPTY:
                return false;
            case BLACK_PAWN:
                return validate_BLACK_PAWN(move, board);
            case WHITE_PAWN:
                return validate_WHITE_PAWN(move, board);
            case BLACK_KING:
                break;
            case WHITE_KING:
                break;
            case BLACK_QUEEN:
                break;
            case WHITE_QUEEN:
                break;
            case BLACK_ROOK:
                break;
            case WHITE_ROOK:
                break;
            case BLACK_KNIGHT:
                break;
            case WHITE_KNIGHT:
                break;
            case BLACK_BISHOP:
                break;
            case WHITE_BISHOP:
                break;
        }
        return false;
    }

    /*
    Validator always assumes that pieces are on the bottom of the board (starting at row 7 indexed from 0)
    A move forward is -1
     */

    //================================---------------------================================
    //================================ Validate PAWN moves ================================
    //================================---------------------================================
    private static boolean validate_PAWN_MOVE(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();
        // Check if the move X stays the same
        if(toCell.getPos().getX() == fromCell.getPos().getX()) {
            // Check if the pawn has moved yet
            if(fromCell.getPos().getY() == 6) {
                // If a pawn hasn't moved, it can move its Y -2
                if(toCell.getPos().getY() == fromCell.getPos().getY() - 2) {
                    // Check that there's no piece in the way
                    if(board.getCell(fromCell.getPos().getX(), fromCell.getPos().getY() - 1).getCellState() == Chess.Pieces.EMPTY) {
                        return true;
                    }
                }
            }
            // If the pawn has already moved, Check if the move Y is -1
            if(toCell.getPos().getY() == fromCell.getPos().getY() - 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean validate_BLACK_PAWN(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();

        // If cell is empty, move is not an attack
        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_PAWN_MOVE(move, board);
            // If cell is not empty and other piece is white, move is an attack
        } else if(toCell.getCellState() == Chess.Pieces.WHITE_PAWN
                || toCell.getCellState() == Chess.Pieces.WHITE_KING
                || toCell.getCellState() == Chess.Pieces.WHITE_QUEEN
                || toCell.getCellState() == Chess.Pieces.WHITE_ROOK
                || toCell.getCellState() == Chess.Pieces.WHITE_KNIGHT
                || toCell.getCellState() == Chess.Pieces.WHITE_BISHOP) {
            // Check if the attack X is +1 or -1
            if(toCell.getPos().getX() == fromCell.getPos().getX() + 1 || toCell.getPos().getX() == fromCell.getPos().getX() - 1 ) {
                // Check if the attack Y is -1
                if(toCell.getPos().getY() == fromCell.getPos().getY() - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean validate_WHITE_PAWN(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();

        // If cell is empty, move is not an attack
        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_PAWN_MOVE(move, board);
            // If cell is not empty and other piece is white, move is an attack
        } else if(toCell.getCellState() == Chess.Pieces.BLACK_PAWN
                || toCell.getCellState() == Chess.Pieces.BLACK_KING
                || toCell.getCellState() == Chess.Pieces.BLACK_QUEEN
                || toCell.getCellState() == Chess.Pieces.BLACK_ROOK
                || toCell.getCellState() == Chess.Pieces.BLACK_KNIGHT
                || toCell.getCellState() == Chess.Pieces.BLACK_BISHOP) {
            // Check if the attack X is +1 or -1
            if(toCell.getPos().getX() == fromCell.getPos().getX() + 1 || toCell.getPos().getX() == fromCell.getPos().getX() - 1 ) {
                // Check if the attack Y is -1
                if(toCell.getPos().getY() == fromCell.getPos().getY() - 1) {
                    return true;
                }
            }
        }
        return false;
    }


    //================================---------------------================================
    //================================ Validate KING moves ================================
    //================================---------------------================================
    private static boolean validate_KING_MOVE(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();

        // Moves where the X doesn't change
        if(toCell.getPos().getX() == fromCell.getPos().getX()) {
            if(toCell.getPos().getY() == fromCell.getPos().getY() + 1 || toCell.getPos().getY() == fromCell.getPos().getY() - 1) {
                return true;
            }
            // Moves where Y doesn't change
        } else if(toCell.getPos().getY() == fromCell.getPos().getY()) {
            if(toCell.getPos().getX() == fromCell.getPos().getX() + 1 || toCell.getPos().getX() == fromCell.getPos().getX() - 1) {
                return true;
            }
            // Moves where X changes +1
        } else if(toCell.getPos().getX() == fromCell.getPos().getX() + 1) {
            if(toCell.getPos().getY() == fromCell.getPos().getY()) {
                return true;
            } else if(toCell.getPos().getY() == fromCell.getPos().getY() + 1) {
                return true;
            } else if(toCell.getPos().getY() == fromCell.getPos().getY() - 1) {
                return true;
            }
            // Moves where X changes -1
        } else if(toCell.getPos().getX() == fromCell.getPos().getX() - 1) {
            if(toCell.getPos().getY() == fromCell.getPos().getY()) {
                return true;
            } else if(toCell.getPos().getY() == fromCell.getPos().getY() + 1) {
                return true;
            } else if(toCell.getPos().getY() == fromCell.getPos().getY() - 1) {
                return true;
            }
            // Moves where Y changes +1
        } else if(toCell.getPos().getY() == fromCell.getPos().getY() + 1) {
            if(toCell.getPos().getX() == fromCell.getPos().getX()) {
                return true;
            } else if(toCell.getPos().getX() == fromCell.getPos().getX() + 1) {
                return true;
            } else if(toCell.getPos().getX() == fromCell.getPos().getX() - 1) {
                return true;
            }
            // Moves where Y changes -1
        } else if(toCell.getPos().getY() == fromCell.getPos().getY() - 1) {
            if(toCell.getPos().getX() == fromCell.getPos().getX()) {
                return true;
            } else if(toCell.getPos().getX() == fromCell.getPos().getX() + 1) {
                return true;
            } else if(toCell.getPos().getX() == fromCell.getPos().getX() - 1) {
                return true;
            }
        }
        return false;
    }

    private static boolean validate_BLACK_KING(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();

        // If cell is empty, move is not an attack
        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_KING_MOVE(move, board);
            // If cell is not empty and other piece is white, move is an attack
        } else if(toCell.getCellState() == Chess.Pieces.WHITE_PAWN
                || toCell.getCellState() == Chess.Pieces.WHITE_KING
                || toCell.getCellState() == Chess.Pieces.WHITE_QUEEN
                || toCell.getCellState() == Chess.Pieces.WHITE_ROOK
                || toCell.getCellState() == Chess.Pieces.WHITE_KNIGHT
                || toCell.getCellState() == Chess.Pieces.WHITE_BISHOP) {
            return validate_KING_MOVE(move, board);
        }
        // Cell is occupied by a black piece
        return false;
    }

    private static boolean validate_WHITE_KING(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();

        // If cell is empty, move is not an attack
        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_KING_MOVE(move, board);
            // If cell is not empty and other piece is white, move is an attack
        } else if(toCell.getCellState() == Chess.Pieces.BLACK_PAWN
                || toCell.getCellState() == Chess.Pieces.BLACK_KING
                || toCell.getCellState() == Chess.Pieces.BLACK_QUEEN
                || toCell.getCellState() == Chess.Pieces.BLACK_ROOK
                || toCell.getCellState() == Chess.Pieces.BLACK_KNIGHT
                || toCell.getCellState() == Chess.Pieces.BLACK_BISHOP) {
            return validate_KING_MOVE(move, board);
        }
        // Cell is occupied by a white piece
        return false;
    }


    //================================----------------------================================
    //================================ Validate QUEEN moves ================================
    //================================----------------------================================
    private static boolean validate_QUEEN_MOVE(ChessMove move, Board board) {
        return false;
    }

    private static boolean validate_BLACK_QUEEN(ChessMove move, Board board) {
        return false;
    }

    private static boolean validate_WHITE_QUEEN(ChessMove move, Board board) {
        return false;
    }

    //================================---------------------================================
    //================================ Validate ROOK moves ================================
    //================================---------------------================================
    private static boolean validate_ROOK_MOVE(ChessMove move, Board board) {
        return false;
    }

    private static boolean validate_BLACK_ROOK(ChessMove move, Board board) {
        return false;
    }

    private static boolean validate_WHITE_ROOK(ChessMove move, Board board) {
        return false;
    }


    //================================-----------------------================================
    //================================ Validate KNIGHT moves ================================
    //================================-----------------------================================
    private static boolean validate_KNIGHT_MOVE(ChessMove move, Board board) {
        return false;
    }

    private static boolean validate_BLACK_KNIGHT(ChessMove move, Board board) {
        return false;
    }

    private static boolean validate_WHITE_KNIGHT(ChessMove move, Board board) {
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

    private static boolean validate_WHITE_BISHOP(ChessMove move, Board board) {
        return false;
    }
}
