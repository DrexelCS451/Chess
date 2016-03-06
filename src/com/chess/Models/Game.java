package com.chess.Models;

import com.chess.Helpers.BoardHelper;

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
