package com.chess.Models;

/**
 * Created by AlexMarion on 3/4/16.
 */
public class ChessMove {
    private Cell from;
    private Cell to;

    public ChessMove(Cell from, Cell to) {
        this.from = from;
        this.to = to;
    }

    public Cell getFrom() {
        return this.from;
    }

    public Cell getTo() {
        return this.to;
    }
}
