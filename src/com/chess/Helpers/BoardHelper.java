package com.chess.Helpers;

import com.chess.Models.*;

/**
 * Created by tomer on 3/4/16.
 */
public class BoardHelper {

    public static Board CreateBoard(boolean userIsWhite)
    {
        Board b = new Board();
        for (int i=0;i < 8;i++ )
        {
            for (int j=0;j < 8;j++ ) {
                Coordinate c = new Coordinate(i,j);
                Cell cell = new Cell(c, Chess.Pieces.EMPTY);
                b.setCell(i,j,cell);
            }
        }
        int row1,row2,row3,row4;

        row1 = (userIsWhite)?7:0;
        row2 = (userIsWhite)?6:1;
        row3 = (userIsWhite)?1:6;
        row4 = (userIsWhite)?0:7;

        Coordinate c = new Coordinate(0,row1);
        Cell cell = new Cell(c, Chess.Pieces.WHITE_ROOK);
        b.setCell(0,row1,cell);

        c = new Coordinate(1,row1);
        cell = new Cell(c, Chess.Pieces.WHITE_KNIGHT);
        b.setCell(1,row1,cell);

        c = new Coordinate(2,row1);
        cell = new Cell(c, Chess.Pieces.WHITE_BISHOP);
        b.setCell(2,row1,cell);

        c = new Coordinate(3,row1);
        cell = new Cell(c, Chess.Pieces.WHITE_QUEEN);
        b.setCell(3,row1,cell);

        c = new Coordinate(4,row1);
        cell = new Cell(c, Chess.Pieces.WHITE_KING);
        b.setCell(4,row1,cell);

        c = new Coordinate(5,row1);
        cell = new Cell(c, Chess.Pieces.WHITE_BISHOP);
        b.setCell(5,row1,cell);

        c = new Coordinate(6,row1);
        cell = new Cell(c, Chess.Pieces.WHITE_KNIGHT);
        b.setCell(6,row1,cell);

        c = new Coordinate(7,row1);
        cell = new Cell(c, Chess.Pieces.WHITE_ROOK);
        b.setCell(7,row1,cell);

        for (int i=0; i <8; i++)
        {
            c = new Coordinate(i,row2);
            cell = new Cell(c, Chess.Pieces.WHITE_PAWN);
            b.setCell(i,row2,cell);
        }

        c = new Coordinate(0,row4);
        cell = new Cell(c, Chess.Pieces.BLACK_ROOK);
        b.setCell(0,row4,cell);

        c = new Coordinate(1,row4);
        cell = new Cell(c, Chess.Pieces.BLACK_KNIGHT);
        b.setCell(1,row4,cell);

        c = new Coordinate(2,row4);
        cell = new Cell(c, Chess.Pieces.BLACK_BISHOP);
        b.setCell(2,row4,cell);

        c = new Coordinate(3,row4);
        cell = new Cell(c, Chess.Pieces.BLACK_QUEEN);
        b.setCell(3,row4,cell);

        c = new Coordinate(4,row4);
        cell = new Cell(c, Chess.Pieces.BLACK_KING);
        b.setCell(4,row4,cell);

        c = new Coordinate(5,row4);
        cell = new Cell(c, Chess.Pieces.BLACK_BISHOP);
        b.setCell(5,row4,cell);

        c = new Coordinate(6,row4);
        cell = new Cell(c, Chess.Pieces.BLACK_KNIGHT);
        b.setCell(6,row4,cell);

        c = new Coordinate(7,row4);
        cell = new Cell(c, Chess.Pieces.BLACK_ROOK);
        b.setCell(7,row4,cell);

        for (int i=0; i <8; i++)
        {
            c = new Coordinate(i,row3);
            cell = new Cell(c, Chess.Pieces.BLACK_PAWN);
            b.setCell(i,row3,cell);
        }
        b.setBoardState(Chess.BoardState.WHITE_TURN);
        return b;
    }


    public static boolean SquareIsUsers(int i, int j, Board b)
    {
        Chess.Pieces p = b.getCell(i,j).getCellState();
        return (User.isWhite &&  p.name().startsWith("WHITE") || (!User.isWhite &&  p.name().startsWith("BLACK")));
    }
}
