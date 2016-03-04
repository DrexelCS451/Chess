package com.chess.Models;

/**
 * Created by tomer on 3/4/16.
 */
public class Board {
    Cell[][] cells = new Cell[8][8];
    Chess.BoardState state;

    public Board()
    {

    }

    public Cell getcell(int x, int y)
    {
        return cells[x][y];
    }

    public void setCell(int x, int y, Cell cell)
    {

    }

    public Chess.BoardState getBoardState()
    {
        return state;
    }

    public void setBoardState(Chess.BoardState state)
    {

    }
}
