package com.chess.Models;

import com.chess.Helpers.MoveValidator;
import com.chess.Models.Board;

/**
 * Created by AlexMarion on 3/4/16.
 */
public class Game {
    private MoveValidator validator;
    private Board board;

    public Game() {}

    public void startGame() {}

    public boolean makeMove() {
        return false;
    }

    private boolean validateMove() {
        return false;
    }

    private Board initBoard() {
        return null;
    }

    public void endGame() {}
}
