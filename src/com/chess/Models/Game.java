package com.chess.Models;

import com.chess.Helpers.BoardHelper;
import com.chess.Helpers.MoveValidator;

/**
 * Created by AlexMarion on 3/4/16.
 */
public class Game {
    private Board board;

    public Game() {
        initBoard();
    }

    public Board getBoard()
    {
        return board;
    }
    public void setBoard(Board board) { this.board = board; }


    public void startGame() { initBoard(); }

    public Board makeMove(ChessMove m) {
        Coordinate c1 = m.getFrom().getPos();
        Coordinate c2 = m.getTo().getPos();
        // Promoting a white pawn to a white queen
        if (board.getCell(c1).getCellState() == Chess.Pieces.WHITE_PAWN && m.getTo().getPos().getY() == 0) {
            board.getCell(c2).setCellState(Chess.Pieces.WHITE_QUEEN);
            board.getCell(c1).setCellState(Chess.Pieces.EMPTY);
        }
        // Promoting a black pawn to a black queen
        else if (board.getCell(c1).getCellState() == Chess.Pieces.BLACK_PAWN && m.getTo().getPos().getY() == 7) {
            board.getCell(c2).setCellState(Chess.Pieces.BLACK_QUEEN);
            board.getCell(c1).setCellState(Chess.Pieces.EMPTY);
        }

        // Castling
        else if ((board.getCell(c1).getCellState() == Chess.Pieces.BLACK_KING ||
                 board.getCell(c1).getCellState() == Chess.Pieces.WHITE_KING) &&
                ((board.getCell(0,7).getCellState() == Chess.Pieces.BLACK_ROOK ||
                 board.getCell(0,7).getCellState() == Chess.Pieces.WHITE_ROOK) ||
                (board.getCell(7,7).getCellState() == Chess.Pieces.WHITE_ROOK ||
                 board.getCell(7,7).getCellState() == Chess.Pieces.BLACK_ROOK))) {
            if (m.getFrom().getPos().getX() == 4 && m.getFrom().getPos().getY() == 7
                    && m.getTo().getPos().getX() == 2 && m.getTo().getPos().getY() == 7 &&
                    m.getFrom().getCellState() == Chess.Pieces.WHITE_KING) {
                if (MoveValidator.canCastle(board, m.getFrom(), new Cell(new Coordinate(0,7), Chess.Pieces.WHITE_ROOK))) {
                    board.getCell(2,7).setCellState(Chess.Pieces.WHITE_KING);
                    board.getCell(4,7).setCellState(Chess.Pieces.EMPTY);
                    board.getCell(3,7).setCellState(Chess.Pieces.WHITE_ROOK);
                    board.getCell(0,7).setCellState(Chess.Pieces.EMPTY);
                }
            }
            else if (m.getFrom().getPos().getX() == 4 && m.getFrom().getPos().getY() == 7
                    && m.getTo().getPos().getX() == 2 && m.getTo().getPos().getY() == 7 &&
                    m.getFrom().getCellState() == Chess.Pieces.BLACK_KING) {
                if (MoveValidator.canCastle(board, m.getFrom(), new Cell(new Coordinate(0,7), Chess.Pieces.BLACK_ROOK))) {
                    board.getCell(2,7).setCellState(Chess.Pieces.BLACK_KING);
                    board.getCell(4,7).setCellState(Chess.Pieces.EMPTY);
                    board.getCell(3,7).setCellState(Chess.Pieces.BLACK_ROOK);
                    board.getCell(0,7).setCellState(Chess.Pieces.EMPTY);
                }
            }
            else if (m.getFrom().getPos().getX() == 4 && m.getFrom().getPos().getY() == 7
                    && m.getTo().getPos().getX() == 6 && m.getTo().getPos().getY() == 7 &&
                    m.getFrom().getCellState() == Chess.Pieces.WHITE_KING) {
                if (MoveValidator.canCastle(board, m.getFrom(), new Cell(new Coordinate(7,7), Chess.Pieces.WHITE_ROOK))) {
                    board.getCell(6,7).setCellState(Chess.Pieces.WHITE_KING);
                    board.getCell(4,7).setCellState(Chess.Pieces.EMPTY);
                    board.getCell(5,7).setCellState(Chess.Pieces.WHITE_ROOK);
                    board.getCell(7,7).setCellState(Chess.Pieces.EMPTY);
                }
            }
            else if (m.getFrom().getPos().getX() == 4 && m.getFrom().getPos().getY() == 7
                    && m.getTo().getPos().getX() == 6 && m.getTo().getPos().getY() == 7 &&
                    m.getFrom().getCellState() == Chess.Pieces.BLACK_KING) {
                if (MoveValidator.canCastle(board, m.getFrom(), new Cell(new Coordinate(7,7), Chess.Pieces.BLACK_ROOK))) {
                    board.getCell(6,7).setCellState(Chess.Pieces.BLACK_KING);
                    board.getCell(4,7).setCellState(Chess.Pieces.EMPTY);
                    board.getCell(5,7).setCellState(Chess.Pieces.BLACK_ROOK);
                    board.getCell(7,7).setCellState(Chess.Pieces.EMPTY);
                }
            }
            else {
                board.getCell(c2).setCellState(board.getCell(c1).getCellState());
                board.getCell(c1).setCellState(Chess.Pieces.EMPTY);
            }
        }
        // Normal moves
        else {
            board.getCell(c2).setCellState(board.getCell(c1).getCellState());
            board.getCell(c1).setCellState(Chess.Pieces.EMPTY);
        }

        return board;
    }

    private void initBoard() {
        board = BoardHelper.CreateBoard(true);
    }

    public void endGame() {}
}
