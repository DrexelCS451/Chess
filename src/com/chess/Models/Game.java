package com.chess.Models;

import com.chess.Helpers.BoardHelper;
import com.chess.Helpers.MoveValidator;
import com.chess.Models.Board;

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

    public void startGame() {}

    public Board makeMove(ChessMove m) {
        Coordinate c1 = m.getFrom().getPos();
        Coordinate c2 = m.getTo().getPos();
        board.getCell(c2).setCellState(board.getCell(c1).getCellState());
        board.getCell(c1).setCellState(Chess.Pieces.EMPTY);
        return board;
    }

    private void initBoard() {
        board = BoardHelper.CreateBoard(true);
    }

    public void endGame() {}
}
