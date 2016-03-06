package com.chess.Models;

/**
 * Created by tomer on 3/4/16.
 */
public class Cell {
    private Coordinate pos;
    private Chess.Pieces cellState;

    public Cell(Coordinate pos, Chess.Pieces cellState) {
        this.pos = pos;
        this.cellState = cellState;
    }

    public Cell copyCell()
    {
        return new Cell(new Coordinate(pos.getX(), pos.getY()), cellState);
    }

    public Coordinate getPos() {
        return pos;
    }

    public Chess.Pieces getCellState() {
        return cellState;
    }

    public void setCellState(Chess.Pieces cellState) {
        this.cellState = cellState;
    }
}
