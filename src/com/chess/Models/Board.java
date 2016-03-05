package com.chess.Models;

import com.chess.Helpers.BoardHelper;

/**
 * Created by tomer on 3/4/16.
 */
public class Board {
    Cell[][] cells = new Cell[8][8];
    Chess.BoardState state;

    public Board()
    {

    }

    public Cell getCell(int x, int y)
    {
        return cells[x][y];
    }

    public Cell getCell(Coordinate c)
    {
        return cells[c.getX()][c.getY()];
    }

    public void setCell(int x, int y, Cell cell)
    {
        cells[x][y] = cell;
    }

    public Chess.BoardState getBoardState()
    {
        return state;
    }

    public void setBoardState(Chess.BoardState state) {
        this.state = state;
    }

    public String toString() {
        String boardString = "";
        for(int i = 0; i < cells.length; i++) {
            for(int j = 0; j < cells[i].length; j++) {
                boardString += Integer.toString(i)
                        + ","
                        + Integer.toString(j)
                        + ","
                        + cells[i][j].getCellState().toString();
                if(!(i == 7 && j == 7)) {
                    boardString += "\n";
                }
            }
        }
        return boardString;
    }
}
