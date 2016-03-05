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
                return validate_BLACK_KING(move, board);
            case WHITE_KING:
                return validate_WHITE_KING(move, board);
            case BLACK_QUEEN:
                return validate_BLACK_QUEEN(move, board);
            case WHITE_QUEEN:
                return validate_WHITE_QUEEN(move, board);
            case BLACK_ROOK:
                return validate_BLACK_ROOK(move, board);
            case WHITE_ROOK:
                return validate_WHITE_ROOK(move, board);
            case BLACK_KNIGHT:
                return validate_BLACK_KNIGHT(move, board);
            case WHITE_KNIGHT:
                return validate_WHITE_KNIGHT(move, board);
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

    //TODO: check that toCell isn't equal to the fromCell
    //TODO: check that the toCell is on the board

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
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();
        // rook can move in the column and row
        // any distance as long as it doesn't
        // run into another piece

        int toRow = toCell.getPos().getY();
        int toCol = toCell.getPos().getX();
        int fromRow = fromCell.getPos().getY();
        int fromCol = fromCell.getPos().getX();

        if (toCol == fromCol) {
            if (toRow - fromRow < 0) {
                for (int i = (fromRow - 1); i > toRow; i--) {
                    if (board.getCell(toCol, i).getCellState() != Chess.Pieces.EMPTY)
                        return false;
                }
                return true;
            }
            else if (toRow - fromRow > 0)  {
                for (int i = (fromRow + 1) ; i < toRow; i++) {
                    if (board.getCell(toCol, i).getCellState() != Chess.Pieces.EMPTY)
                        return false;
                }
                return true;
            }
            return false;
        }
        else if (toRow == fromRow) {
            if (toCol - fromCol > 0) {
                for (int i = (fromCol + 1); i < toCol; i++) {
                    if (board.getCell(i, toRow).getCellState() != Chess.Pieces.EMPTY)
                        return false;
                }
                return true;
            }
            else if (toCol - fromCol < 0) {
                for (int i = (fromCol - 1); i > toCol; i--) {
                    if (board.getCell(i, toRow).getCellState() != Chess.Pieces.EMPTY)
                        return false;
                }
                return true;
            }
            return false;
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

        if (fromCell.getPos().getX() == toCell.getPos().getX() && fromCell.getPos().getY() == toCell.getPos().getY())
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
            return validate_ROOK_MOVE(move, board);
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

        if (fromCell.getPos().getX() == toCell.getPos().getX() && fromCell.getPos().getY() == toCell.getPos().getY())
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
            return validate_ROOK_MOVE(move, board);
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

        if (fromCell.getPos().getX() == toCell.getPos().getX() && fromCell.getPos().getY() == toCell.getPos().getY())
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
            return validate_KNIGHT_MOVE(move, board);
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
            return validate_KNIGHT_MOVE(move, board);
        }
        return false;
    }

    //================================-----------------------================================
    //================================ Validate BISHOP moves ================================
    //================================-----------------------================================
    private static boolean validate_BISHOP_MOVE(ChessMove move, Board board) {
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();

        // The difference between the Xs and Ys must be the same
        int xDiff = toCell.getPos().getX() - fromCell.getPos().getX();
        int yDiff = toCell.getPos().getY() - fromCell.getPos().getY();
        int absXDiff = Math.abs(xDiff);
        int absYDiff = Math.abs(yDiff);
        if(absXDiff == absYDiff) {
            // Check that there is no piece in the move path
            if(xDiff > 0 && yDiff > 0) {
                for(int i = 0; i < xDiff; i++) {

                }
            } else if(xDiff > 0 && yDiff < 0) {

            } else if(xDiff < 0 && yDiff > 0) {

            } else if(xDiff < 0 && yDiff < 0) {

            }
        }
        return false;
    }

    private static boolean validate_BLACK_BISHOP(ChessMove move, Board board) {
        return false;
    }

    private static boolean validate_WHITE_BISHOP(ChessMove move, Board board) {
        return false;
    }
}
