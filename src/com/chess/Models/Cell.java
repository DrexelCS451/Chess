package com.chess.Models;

/**
 * Created by tomer on 3/4/16.
 */
public class Cell {
    private Coordinate pos;
    private Chess.Pieces cellState;
    private boolean hasMoved;

    public Cell(Coordinate pos, Chess.Pieces cellState) {
        this.pos = pos;
        this.cellState = cellState;
        this.hasMoved = false;
    }

    public Cell copyCell()
    {
        return new Cell(new Coordinate(pos.getX(), pos.getY()), cellState);
    }

    public Coordinate getPos() {
        return pos;
    }

    public void setPos(Coordinate pos) {
        this.pos = pos;
    }

    public Chess.Pieces getCellState() {
        return cellState;
    }

    public boolean getHasMoved() { return hasMoved; }

    public void setCellState(Chess.Pieces cellState) {
        this.cellState = cellState;
        this.hasMoved = true;
    }
}
