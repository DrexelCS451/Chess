package com.chess.Helpers;

import com.chess.Models.*;

import java.util.ArrayList;

/**
 * Created by AlexMarion on 3/4/16.
 */
public class MoveValidator {
    public MoveValidator() {}

    public static ArrayList<ChessMove> findPawnMoves(Cell pawn, Board board){
        int multiplier = -1;
        if ((pawn.getCellState() == Chess.Pieces.BLACK_PAWN && User.isWhite)
                || (pawn.getCellState() == Chess.Pieces.WHITE_PAWN && !User.isWhite)) {
            multiplier = 1;
        }
        ArrayList<ChessMove> moveList = new ArrayList<ChessMove>();
        int pawnX = pawn.getPos().getX();
        int pawnY = pawn.getPos().getY();

        // If the pawn hasn't moved it can move (0, -2)
        if(!pawn.getHasMoved()) {
            if ((pawnY == 1 && multiplier == 1) || (pawnY == 6 && multiplier == -1)) {
                ChessMove move = new ChessMove(pawn, board.getCell(pawnX, pawnY + 2 * multiplier));
                if (MoveValidator.isValidMove(move, board)) {
                    moveList.add(move);
                }
            }
        }


        // Test if the pawn can move (0, -1)
        ChessMove move = new ChessMove(pawn, board.getCell(pawnX, pawnY + 1*multiplier));
        if(isValidMove(move, board)) {
            moveList.add(move);
        }

        // Test if the pawn can attack (-1, -1) or (+1, -1)
        if(pawnX + 1 < 8) {
            move = new ChessMove(pawn, board.getCell(pawnX + 1, pawnY + 1*multiplier));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }
        if(pawnX - 1 > -1) {
            move = new ChessMove(pawn, board.getCell(pawnX - 1, pawnY + 1*multiplier));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }


        return moveList;
    }

    public static ArrayList<ChessMove> findKingMoves(Cell king, Board board) {
        ArrayList<ChessMove> moveList = new ArrayList<ChessMove>();
        int kingX = king.getPos().getX();
        int kingY = king.getPos().getY();
        ChessMove move;

        // Test up, down, left, and right
        if(kingY - 1 > -1) {
            move = new ChessMove(king, board.getCell(kingX, kingY - 1));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }
        if(kingY + 1 < 8) {
            move = new ChessMove(king, board.getCell(kingX, kingY + 1));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }
        if(kingX - 1 > -1) {
            move = new ChessMove(king, board.getCell(kingX - 1, kingY));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }
        if(kingX + 1 < 8) {
            move = new ChessMove(king, board.getCell(kingX + 1, kingY));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }

        // Test diagonals
        if(kingX - 1 > -1) {
            if(kingY - 1 > -1) {
                move = new ChessMove(king, board.getCell(kingX - 1, kingY - 1));
                if(isValidMove(move, board)) {
                    moveList.add(move);
                }
            }
            if(kingY + 1 < 8) {
                move = new ChessMove(king, board.getCell(kingX - 1, kingY + 1));
                if(isValidMove(move, board)) {
                    moveList.add(move);
                }
            }
        }
        if(kingX + 1 < 8) {
            if(kingY - 1 > -1) {
                move = new ChessMove(king, board.getCell(kingX + 1, kingY - 1));
                if(isValidMove(move, board)) {
                    moveList.add(move);
                }
            }
            if(kingY + 1 < 8) {
                move = new ChessMove(king, board.getCell(kingX + 1, kingY + 1));
                if(isValidMove(move, board)) {
                    moveList.add(move);
                }
            }
        }
        return moveList;
    }

    public static ArrayList<ChessMove> findQueenMoves(Cell queen, Board board) {
        ArrayList<ChessMove> moveList = new ArrayList<ChessMove>();
        moveList.addAll(findRookMoves(queen, board));
        moveList.addAll(findBishopMoves(queen, board));
        return moveList;
    }

    public static ArrayList<ChessMove> findRookMoves(Cell rook, Board board) {
        ArrayList<ChessMove> moveList = new ArrayList<ChessMove>();
        int rookX = rook.getPos().getX();
        int rookY = rook.getPos().getY();
        ChessMove move;

        // Test left moves
        int tempRookX = rookX;
        while(tempRookX > -1) {
            move = new ChessMove(rook, board.getCell(tempRookX, rookY));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
            tempRookX--;
        }
        // Test right moves
        tempRookX = rookX;
        while(tempRookX < 8) {
            move = new ChessMove(rook, board.getCell(tempRookX, rookY));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
            tempRookX++;
        }

        // Test forward moves
        int tempRookY = rookY;
        while(tempRookY > -1) {
            move = new ChessMove(rook, board.getCell(rookX, tempRookY));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
            tempRookY--;
        }
        // Test backward moves
        tempRookY = rookY;
        while(tempRookY < 8) {
            move = new ChessMove(rook, board.getCell(rookX, tempRookY));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
            tempRookY++;
        }
        return moveList;
    }

    public static ArrayList<ChessMove> findKnightMoves(Cell knight, Board board) {
        ArrayList<ChessMove> moveList = new ArrayList<ChessMove>();
        int knightX = knight.getPos().getX();
        int knightY = knight.getPos().getY();
        ChessMove move;

        if(knightX - 1 < -1 && knightY - 2 < -1) {
            move = new ChessMove(knight, board.getCell(knightX - 1, knightY - 2));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }
        if(knightX - 1 < -1 && knightY + 2 > 8) {
            move = new ChessMove(knight, board.getCell(knightX - 1, knightY + 2));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }
        if(knightX + 1 > 8 && knightY - 2 < -1) {
            move = new ChessMove(knight, board.getCell(knightX + 1, knightY - 2));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }
        if(knightX + 1 > 8 && knightY + 2 > 8) {
            move = new ChessMove(knight, board.getCell(knightX + 1, knightY + 2));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }

        if(knightX - 2 < -1 && knightY - 1 < -1) {
            move = new ChessMove(knight, board.getCell(knightX - 2, knightY - 1));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }
        if(knightX - 2 < -1 && knightY + 1 > 8) {
            move = new ChessMove(knight, board.getCell(knightX - 2, knightY + 1));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }
        if(knightX + 2 > 8 && knightY - 1 < -1) {
            move = new ChessMove(knight, board.getCell(knightX + 2, knightY - 1));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }
        if(knightX + 2 > 8 && knightY + 1 > 8) {
            move = new ChessMove(knight, board.getCell(knightX + 2, knightY + 1));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
        }

        return moveList;
    }

    public static ArrayList<ChessMove> findBishopMoves(Cell bishop, Board board) {
        ArrayList<ChessMove> moveList = new ArrayList<ChessMove>();
        int bishopX = bishop.getPos().getX();
        int bishopY = bishop.getPos().getY();
        ChessMove move;

        int tempBishopX = bishopX;
        int tempBishopY = bishopY;

        // Test forwardLeft diagonal
        while(tempBishopX > -1 && tempBishopY > -1) {
            move = new ChessMove(bishop, board.getCell(tempBishopX, tempBishopY));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
            tempBishopX--;
            tempBishopY--;
        }

        // Test forwardRight diagonal
        tempBishopX = bishopX;
        tempBishopY = bishopY;
        while(tempBishopX < 8 && tempBishopY > -1) {
            move = new ChessMove(bishop, board.getCell(tempBishopX, tempBishopY));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
            tempBishopX++;
            tempBishopY--;
        }

        // Test backwardLeft diagonal
        tempBishopX = bishopX;
        tempBishopY = bishopY;
        while(tempBishopX > -1 && tempBishopY < 8) {
            move = new ChessMove(bishop, board.getCell(tempBishopX, tempBishopY));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
            tempBishopX--;
            tempBishopY++;
        }

        // Test backwardRight diagonal
        tempBishopX = bishopX;
        tempBishopY = bishopY;
        while(tempBishopX < 8 && tempBishopY < 8) {
            move = new ChessMove(bishop, board.getCell(tempBishopX, tempBishopY));
            if(isValidMove(move, board)) {
                moveList.add(move);
            }
            tempBishopX++;
            tempBishopY++;
        }
        return moveList;
    }

    // Castling
    // The king and the chosen rook are on the player's first rank (Y == 7) -- DONE
    // Neither the king nor the chosen rook has previously moved. -- DONE
    // There are no pieces between the king and the chosen rook. -- DONE
    // The king is not currently in check. -- DONE
    // The king does not pass through a square that is attacked by an enemy piece
    // The king does not end up in check. (True of any legal move.) -- DONE
    public static boolean canCastle(Board board, Cell kingCell, Cell rookCell) {
    int kingX = kingCell.getPos().getX();
    int kingY = kingCell.getPos().getY();
    int rookX = rookCell.getPos().getX();
    int rookY = rookCell.getPos().getY();

    Coordinate kingCoord = new Coordinate(kingX, kingY);

    if (kingCell.getPos().getY() == 7 && rookCell.getPos().getY() == 7 && !kingCell.getHasMoved() &&
                !rookCell.getHasMoved() && !isCheck(board, kingCell.getCellState(), kingCoord)) {
            if (rookX == 0) {
                if (board.getCell(1,7).getCellState() == Chess.Pieces.EMPTY &&
                        board.getCell(2,7).getCellState() == Chess.Pieces.EMPTY &&
                        board.getCell(3,7).getCellState() == Chess.Pieces.EMPTY) {

                        //move king from (4,7) to (3,7)

                        Board tempBoard1 = board.copyBoard();
                        Chess.Pieces king = tempBoard1.getCell(4,7).getCellState();
                        tempBoard1.getCell(4,7).setCellState(Chess.Pieces.EMPTY);
                        tempBoard1.getCell(3,7).setCellState(king);

                        Board tempBoard2 = board.copyBoard();
                        Chess.Pieces king2 = tempBoard2.getCell(4,7).getCellState();
                        tempBoard2.getCell(4,7).setCellState(Chess.Pieces.EMPTY);
                        tempBoard2.getCell(2,7).setCellState(king2);

                        Board tempBoard3 = board.copyBoard();
                        Chess.Pieces king3 = tempBoard3.getCell(4,7).getCellState();
                        tempBoard3.getCell(4,7).setCellState(Chess.Pieces.EMPTY);
                        tempBoard3.getCell(1,7).setCellState(king3);
                        if (!MoveValidator.isCheck(tempBoard1, kingCell.getCellState(), new Coordinate(3,7)) &&
                             !MoveValidator.isCheck(tempBoard2, kingCell.getCellState(), new Coordinate(2,7)) &&
                             !MoveValidator.isCheck(tempBoard3, kingCell.getCellState(), new Coordinate(1,7)))
                                return true;
                        return false;
                }
            }
            else if (rookX == 7) {
                if (board.getCell(5,7).getCellState() == Chess.Pieces.EMPTY &&
                        board.getCell(6,7).getCellState() == Chess.Pieces.EMPTY){

                    //move king from (4,7) to (5,7)
                    Board tempBoard1 = board.copyBoard();
                    Chess.Pieces king = tempBoard1.getCell(4,7).getCellState();
                    tempBoard1.getCell(4,7).setCellState(Chess.Pieces.EMPTY);
                    tempBoard1.getCell(5,7).setCellState(king);

                    //move king from (4,7) to (6,7)
                    Board tempBoard2 = board.copyBoard();
                    Chess.Pieces king2 = tempBoard2.getCell(4,7).getCellState();
                    tempBoard2.getCell(4,7).setCellState(Chess.Pieces.EMPTY);
                    tempBoard2.getCell(6,7).setCellState(king2);

                     if (!MoveValidator.isCheck(tempBoard1, kingCell.getCellState(), new Coordinate(5,7)) &&
                            !MoveValidator.isCheck(tempBoard2, kingCell.getCellState(), new Coordinate(6,7)))
                         return true;
                     return false;
                }
            }
        }
        return false;
     }

    public static boolean isCheck(Board board, Chess.Pieces king, Coordinate kingCell) {
        int kingX = kingCell.getX();
        int kingY = kingCell.getY();
        Chess.Pieces otherKing;
        Chess.Pieces otherQueen;
        Chess.Pieces otherRook;
        Chess.Pieces otherPawn;
        Chess.Pieces otherBishop;
        Chess.Pieces otherKnight;

        if (king == Chess.Pieces.BLACK_KING) {
            otherKing = Chess.Pieces.WHITE_KING;
            otherQueen = Chess.Pieces.WHITE_QUEEN;
            otherRook = Chess.Pieces.WHITE_ROOK;
            otherPawn = Chess.Pieces.WHITE_PAWN;
            otherBishop = Chess.Pieces.WHITE_BISHOP;
            otherKnight = Chess.Pieces.WHITE_KNIGHT;
        }
        else {
            otherKing = Chess.Pieces.BLACK_KING;
            otherQueen = Chess.Pieces.BLACK_QUEEN;
            otherRook = Chess.Pieces.BLACK_ROOK;
            otherPawn = Chess.Pieces.BLACK_PAWN;
            otherBishop = Chess.Pieces.BLACK_BISHOP;
            otherKnight = Chess.Pieces.BLACK_KNIGHT;
        }

        // Case of king: left, right, in front, behind, or diagonals
        // Check front
        if(kingX - 1 > -1 && board.getCell(kingX-1, kingY).getCellState() == otherKing)
            return true;
        // Check behind
        if(kingX + 1 < 8 && board.getCell(kingX+1, kingY).getCellState() == otherKing)
            return true;
        // Check left
        if(kingY - 1 > -1 && board.getCell(kingX, kingY-1).getCellState() == otherKing)
            return true;
        // Check right
        if(kingY + 1 < 8 && board.getCell(kingX, kingY+1).getCellState() == otherKing)
            return true;
        // Check frontLeft
        if(kingX - 1 > -1 && kingY - 1 > -1 && board.getCell(kingX-1, kingY-1).getCellState() == otherKing)
            return true;
        // Check frontRight
        if(kingX - 1 > -1 && kingY + 1 < 8 && board.getCell(kingX-1, kingY+1).getCellState() == otherKing)
            return true;
        // Check behindLeft
        if(kingX + 1 < 8 && kingY - 1 > -1 && board.getCell(kingX+1, kingY-1).getCellState() == otherKing)
            return true;
        // Check behindRight
        if(kingX + 1 < 8 && kingY + 1 < 8 && board.getCell(kingX+1, kingY+1).getCellState() == otherKing)
            return true;

        // vertical or horizontal attack = queen or rook
        // from above for white, below for black (vertical attacks)
        for (int i = (kingY - 1); i >= 0; i--) {
            if (board.getCell(kingX, i).getCellState() != Chess.Pieces.EMPTY) {
                if (board.getCell(kingX, i).getCellState() == otherQueen ||
                    board.getCell(kingX, i).getCellState() == otherRook) {
                    return true;
                }
                else
                    break;
            }
        }
        for (int i = (kingY + 1); i < 8; i++) {
            if (board.getCell(kingX, i).getCellState() != Chess.Pieces.EMPTY) {
                if (board.getCell(kingX, i).getCellState() == otherQueen ||
                        board.getCell(kingX, i).getCellState() == otherRook) {
                    return true;
                }
                else
                    break;
            }
        }
        // left and right attacks (horizontal attacks) (rooks and queens)
        for (int i = (kingX + 1); i < 8; i++) {
            if (board.getCell(i, kingY).getCellState() != Chess.Pieces.EMPTY) {
                if (board.getCell(i, kingY).getCellState() == otherQueen ||
                        board.getCell(i, kingY).getCellState() == otherRook) {
                    return true;
                }
                else
                    break;
            }
        }
        for (int i = (kingX - 1); i >= 0; i--) {
            if(board.getCell(i, kingY).getCellState() != Chess.Pieces.EMPTY) {
                if (board.getCell(i, kingY).getCellState() == otherQueen ||
                    board.getCell(i, kingY).getCellState() == otherRook) {
                    return true;
                }
                else
                    break;
            }
        }

        // Diagonal attacks = pawns and bishops
        // down and right
        for (int i = kingX + 1, j = kingY + 1; i < 8 && j < 8; i++, j++) {
            if (board.getCell(i, j).getCellState() != Chess.Pieces.EMPTY) {
                if (board.getCell(i, j).getCellState() == otherBishop ||
                        board.getCell(i, j).getCellState() == otherQueen) {
                    return true;
                } else
                    break;
            }
        }

        // up and left
        for (int i = kingX - 1, j = kingY - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.getCell(i, j).getCellState() != Chess.Pieces.EMPTY) {
                if (board.getCell(i, j).getCellState() == otherBishop ||
                        board.getCell(i, j).getCellState() == otherQueen) {
                    return true;
                } else
                    break;
            }
        }

        // up and right
        for (int i = kingX + 1, j = kingY - 1; i < 8 && j >= 0; i++, j--) {
            if (board.getCell(i, j).getCellState() != Chess.Pieces.EMPTY) {
                if (board.getCell(i, j).getCellState() == otherBishop ||
                        board.getCell(i, j).getCellState() == otherQueen) {
                    return true;
                } else
                    break;
            }
        }

        // down and left
        for (int i = kingX - 1, j = kingY + 1; i >= 0 && j < 8; i--, j++) {
            if (board.getCell(i, j).getCellState() != Chess.Pieces.EMPTY) {
                if (board.getCell(i, j).getCellState() == otherBishop ||
                        board.getCell(i, j).getCellState() == otherQueen) {
                    return true;
                } else
                    break;
            }
        }

        // case of pawns - down and left from king and down and right from king
        // down and left - (X - 1, Y - 1)
        int multiplier = -1;
        if ((king == Chess.Pieces.BLACK_KING && User.isWhite)
            || (king == Chess.Pieces.WHITE_KING && !User.isWhite))
            multiplier = 1;

        int newKingY = kingY + 1 * multiplier;
        if ((kingX - 1 > -1) && (newKingY > -1) && (newKingY < 8) && board.getCell(kingX - 1, newKingY).getCellState() == otherPawn)
            return true;
        // down and right - (X + 1, Y - 1)
        if ((kingX + 1 < 8) && (newKingY > -1) && (newKingY < 8) && board.getCell(kingX + 1, newKingY).getCellState() == otherPawn)
            return true;

        // case of knights
        if ((kingX + 1 < 8) && (kingY - 2 > -1) &&(board.getCell(kingX + 1, kingY - 2).getCellState() == otherKnight)) {
            return true;
        }
        if ((kingX + 2 < 8) && (kingY - 1 > -1) && (board.getCell(kingX + 2, kingY - 1).getCellState() == otherKnight)) {
            return true;
        }
        if ((kingX + 2 < 8) && (kingY + 1 < 8) && (board.getCell(kingX + 2, kingY + 1).getCellState() == otherKnight)) {
            return true;
        }
        if ((kingX + 1 < 8) && (kingY + 2 < 8) && (board.getCell(kingX + 1, kingY + 2).getCellState() == otherKnight)) {
            return true;
        }
        if ((kingX - 1 > -1) && (kingY + 2 < 8) && (board.getCell(kingX - 1, kingY + 2).getCellState() == otherKnight)) {
            return true;
        }
        if ((kingX - 2 > -1) && (kingY + 1 < 8) && (board.getCell(kingX - 2, kingY + 1).getCellState() == otherKnight)) {
            return true;
        }
        if ((kingX - 2 > -1) && (kingY - 1 > -1) && (board.getCell(kingX - 2, kingY - 1).getCellState() == otherKnight)) {
            return true;
        }
        if ((kingX - 1 > -1) && (kingY - 2 > - 1) && (board.getCell(kingX - 1, kingY - 2).getCellState() == otherKnight)) {
            return true;
        }
        return false;
    }

    // The king cannot move out of check.
    // No one can move in front of him to save him.
    public static boolean isCheckMate(Cell king, Board board) {
        if (isCheck(board, king.getCellState(), king.getPos()) && !board.savableKing(king))
            return true;
        return false;
        }

    // Not in check but has no legal move.
    public static boolean isStaleMate(Cell king, Board board) {
        if (!isCheck(board, king.getCellState(), king.getPos()) && !board.savableKing(king))
            return true;
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
                return validate_BLACK_BISHOP(move, board);
            case WHITE_BISHOP:
                return validate_WHITE_BISHOP(move, board);
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

        // Create a temporary board to see if check
        Game tempGame = new Game();
        Board tempBoard = new Board(board.toString());
        tempGame.setBoard(tempBoard);
        tempGame.makeMove(move);
        Coordinate kingPos = board.findKing(Chess.Pieces.BLACK_KING);
        if(isCheck(tempBoard, Chess.Pieces.BLACK_KING, kingPos)) {
            return false;
        }


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

        // Create a temporary board to see if check
        Game tempGame = new Game();
        Board tempBoard = new Board(board.toString());
        tempGame.setBoard(tempBoard);
        tempGame.makeMove(move);
        Coordinate kingPos = board.findKing(Chess.Pieces.WHITE_KING);
        if(isCheck(tempBoard, Chess.Pieces.WHITE_KING, kingPos)) {
            return false;
        }

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

        int toX = toCell.getPos().getX();
        int toY = toCell.getPos().getY();
        Chess.Pieces king = fromCell.getCellState();

        // Validate castling
        if (toX == 6 && toY == 7 && king == Chess.Pieces.WHITE_KING) {
            Cell rookCell = new Cell(new Coordinate(7,7), Chess.Pieces.WHITE_ROOK);
            if (canCastle(board, fromCell, rookCell))
                return true;
        }

        else if (toX == 2 && toY == 7 && king == Chess.Pieces.WHITE_KING) {
            Cell rookCell = new Cell(new Coordinate(0,7), Chess.Pieces.WHITE_ROOK);
            if (canCastle(board, fromCell, rookCell))
                return true;
            }

        else if (toX == 6 && toY == 7 && king == Chess.Pieces.BLACK_KING) {
            Cell rookCell = new Cell(new Coordinate(7,7), Chess.Pieces.BLACK_ROOK);
            if (canCastle(board, fromCell, rookCell))
                return true;
            }

        else if (toX == 2 && toY == 7 && king == Chess.Pieces.BLACK_KING) {
            Cell rookCell = new Cell(new Coordinate(0,7), Chess.Pieces.BLACK_ROOK);
            if (canCastle(board, fromCell, rookCell))
                return true;
            }
        // Moves where the X doesn't change
        else if(toCell.getPos().getX() == fromCell.getPos().getX()) {
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
        Cell toCell = move.getTo();

        if(isCheck(board, Chess.Pieces.BLACK_KING, toCell.getPos()))
            return false;
        // If cell is empty, move is not an attack
        else if(toCell.getCellState() == Chess.Pieces.EMPTY) {
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
        Cell toCell = move.getTo();

        if(isCheck(board, Chess.Pieces.WHITE_KING, toCell.getPos()))
            return false;
        // If cell is empty, move is not an attack
        else if(toCell.getCellState() == Chess.Pieces.EMPTY) {
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
        Cell fromCell = move.getFrom();
        Cell toCell = move.getTo();

        if (fromCell.getPos().getX() == toCell.getPos().getX() && fromCell.getPos().getY() == toCell.getPos().getY())
            return false;
        else if (validate_ROOK_MOVE(move, board) || validate_BISHOP_MOVE(move, board))
            return true;
        return false;
    }

    private static boolean validate_BLACK_QUEEN(ChessMove move, Board board) {
        Cell toCell = move.getTo();

        // Create a temporary board to see if check
        Game tempGame = new Game();
        Board tempBoard = new Board(board.toString());
        tempGame.setBoard(tempBoard);
        tempGame.makeMove(move);
        Coordinate kingPos = board.findKing(Chess.Pieces.BLACK_KING);
        if(isCheck(tempBoard, Chess.Pieces.BLACK_KING, kingPos)) {
            return false;
        }

        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_QUEEN_MOVE(move, board);
        }

        else if (toCell.getCellState() == Chess.Pieces.WHITE_PAWN
                || toCell.getCellState() == Chess.Pieces.WHITE_KING
                || toCell.getCellState() == Chess.Pieces.WHITE_QUEEN
                || toCell.getCellState() == Chess.Pieces.WHITE_ROOK
                || toCell.getCellState() == Chess.Pieces.WHITE_KNIGHT
                || toCell.getCellState() == Chess.Pieces.WHITE_BISHOP)  {
            return validate_QUEEN_MOVE(move, board);
        }

        return false;
    }

    private static boolean validate_WHITE_QUEEN(ChessMove move, Board board) {
        Cell toCell = move.getTo();

        // Create a temporary board to see if check
        Game tempGame = new Game();
        Board tempBoard = new Board(board.toString());
        tempGame.setBoard(tempBoard);
        tempGame.makeMove(move);
        Coordinate kingPos = board.findKing(Chess.Pieces.WHITE_KING);
        if(isCheck(tempBoard, Chess.Pieces.WHITE_KING, kingPos)) {
            return false;
        }

        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_QUEEN_MOVE(move, board);
        }

        else if (toCell.getCellState() == Chess.Pieces.BLACK_PAWN
                || toCell.getCellState() == Chess.Pieces.BLACK_KING
                || toCell.getCellState() == Chess.Pieces.BLACK_QUEEN
                || toCell.getCellState() == Chess.Pieces.BLACK_ROOK
                || toCell.getCellState() == Chess.Pieces.BLACK_KNIGHT
                || toCell.getCellState() == Chess.Pieces.BLACK_BISHOP)  {
            return validate_QUEEN_MOVE(move, board);
        }

        return false;
    }

    //================================---------------------================================
    //================================ Validate ROOK moves ================================
    //================================---------------------================================

    // created by Kelly Shiptoski on 3/4

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

        if (fromCell.getPos().getX() == toCell.getPos().getX() && fromCell.getPos().getY() == toCell.getPos().getY())
            return false;

        else if (toCol == fromCol) {
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
        Cell toCell = move.getTo();

        // Create a temporary board to see if check
        Game tempGame = new Game();
        Board tempBoard = new Board(board.toString());
        tempGame.setBoard(tempBoard);
        tempGame.makeMove(move);
        Coordinate kingPos = board.findKing(Chess.Pieces.BLACK_KING);
        if(isCheck(tempBoard, Chess.Pieces.BLACK_KING, kingPos)) {
            return false;
        }

        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
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
        Cell toCell = move.getTo();

        // Create a temporary board to see if check
        Game tempGame = new Game();
        Board tempBoard = new Board(board.toString());
        tempGame.setBoard(tempBoard);
        tempGame.makeMove(move);
        Coordinate kingPos = board.findKing(Chess.Pieces.WHITE_KING);
        if(isCheck(tempBoard, Chess.Pieces.WHITE_KING, kingPos)) {
            return false;
        }

        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
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
        Cell toCell = move.getTo();

        // Create a temporary board to see if check
        Game tempGame = new Game();
        Board tempBoard = new Board(board.toString());
        tempGame.setBoard(tempBoard);
        tempGame.makeMove(move);
        Coordinate kingPos = board.findKing(Chess.Pieces.BLACK_KING);
        if(isCheck(tempBoard, Chess.Pieces.BLACK_KING, kingPos)) {
            return false;
        }

        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
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
        Cell toCell = move.getTo();

        // Create a temporary board to see if check
        Game tempGame = new Game();
        Board tempBoard = new Board(board.toString());
        tempGame.setBoard(tempBoard);
        tempGame.makeMove(move);
        Coordinate kingPos = board.findKing(Chess.Pieces.WHITE_KING);
        if(isCheck(tempBoard, Chess.Pieces.WHITE_KING, kingPos)) {
            return false;
        }

        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
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

        if (fromCell.getPos().getX() == toCell.getPos().getX() && fromCell.getPos().getY() == toCell.getPos().getY())
            return false;

        else if(absXDiff == absYDiff) {
            // Check that there is no piece in the move path
            if(xDiff > 0 && yDiff > 0) {
                for(int i = 1; i < xDiff; i++) {
                    if (board.getCell(fromCell.getPos().getX() + i, fromCell.getPos().getY() + i).getCellState() != Chess.Pieces.EMPTY)
                        return false;
                }
                return true;
            } else if(xDiff > 0 && yDiff < 0) {
                for(int i = 1; i < xDiff; i++) {
                    if (board.getCell(fromCell.getPos().getX() + i, fromCell.getPos().getY() - i).getCellState() != Chess.Pieces.EMPTY)
                        return false;
                }
                return true;

            } else if(xDiff < 0 && yDiff > 0) {
                for(int i = 1; i < yDiff; i++) {
                    if (board.getCell(fromCell.getPos().getX() - i, fromCell.getPos().getY() + i).getCellState() != Chess.Pieces.EMPTY)
                        return false;
                }
                return true;

            } else if(xDiff < 0 && yDiff < 0) {
                for(int i = 1; i < absXDiff; i++) {
                    if (board.getCell(fromCell.getPos().getX() - i, fromCell.getPos().getY() - i).getCellState() != Chess.Pieces.EMPTY)
                        return false;
                }
                return true;
            }
        }
        return false;
    }

    private static boolean validate_BLACK_BISHOP(ChessMove move, Board board) {
        Cell toCell = move.getTo();

        // Create a temporary board to see if check
        Game tempGame = new Game();
        Board tempBoard = new Board(board.toString());
        tempGame.setBoard(tempBoard);
        tempGame.makeMove(move);
        Coordinate kingPos = board.findKing(Chess.Pieces.BLACK_KING);
        if(isCheck(tempBoard, Chess.Pieces.BLACK_KING, kingPos)) {
            return false;
        }

        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_BISHOP_MOVE(move, board);
        }

        else if (toCell.getCellState() == Chess.Pieces.WHITE_PAWN
                || toCell.getCellState() == Chess.Pieces.WHITE_KING
                || toCell.getCellState() == Chess.Pieces.WHITE_QUEEN
                || toCell.getCellState() == Chess.Pieces.WHITE_ROOK
                || toCell.getCellState() == Chess.Pieces.WHITE_KNIGHT
                || toCell.getCellState() == Chess.Pieces.WHITE_BISHOP) {
            return validate_BISHOP_MOVE(move, board);
        }
        return false;
    }

    private static boolean validate_WHITE_BISHOP(ChessMove move, Board board) {
        Cell toCell = move.getTo();

        // Create a temporary board to see if check
        Game tempGame = new Game();
        Board tempBoard = new Board(board.toString());
        tempGame.setBoard(tempBoard);
        tempGame.makeMove(move);
        Coordinate kingPos = board.findKing(Chess.Pieces.WHITE_KING);
        if(isCheck(tempBoard, Chess.Pieces.WHITE_KING, kingPos)) {
            return false;
        }

        if(toCell.getCellState() == Chess.Pieces.EMPTY) {
            return validate_BISHOP_MOVE(move, board);
        }

        else if (toCell.getCellState() == Chess.Pieces.BLACK_PAWN
                || toCell.getCellState() == Chess.Pieces.BLACK_KING
                || toCell.getCellState() == Chess.Pieces.BLACK_QUEEN
                || toCell.getCellState() == Chess.Pieces.BLACK_ROOK
                || toCell.getCellState() == Chess.Pieces.BLACK_KNIGHT
                || toCell.getCellState() == Chess.Pieces.BLACK_BISHOP) {
            return validate_BISHOP_MOVE(move, board);
        }
        return false;
    }
}
