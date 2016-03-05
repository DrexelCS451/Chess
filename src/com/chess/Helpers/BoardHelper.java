package com.chess.Helpers;

import com.chess.Models.Board;
import com.chess.Models.Cell;
import com.chess.Models.Chess;
import com.chess.Models.Coordinate;

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

        row1 = (userIsWhite)?0:7;
        row2 = (userIsWhite)?1:6;
        row3 = (userIsWhite)?6:1;
        row4 = (userIsWhite)?7:0;

        Coordinate c = new Coordinate(row4,0);
        Cell cell = new Cell(c, Chess.Pieces.WHITE_ROOK);
        b.setCell(row4,0,cell);

        c = new Coordinate(row4,1);
        cell = new Cell(c, Chess.Pieces.WHITE_KNIGHT);
        b.setCell(row4,1,cell);

        c = new Coordinate(row4,2);
        cell = new Cell(c, Chess.Pieces.WHITE_BISHOP);
        b.setCell(row4,2,cell);

        c = new Coordinate(row4,3);
        cell = new Cell(c, Chess.Pieces.WHITE_QUEEN);
        b.setCell(row4,3,cell);

        c = new Coordinate(row4,4);
        cell = new Cell(c, Chess.Pieces.WHITE_KING);
        b.setCell(row4,4,cell);

        c = new Coordinate(row4,5);
        cell = new Cell(c, Chess.Pieces.WHITE_BISHOP);
        b.setCell(row4,5,cell);

        c = new Coordinate(row4,6);
        cell = new Cell(c, Chess.Pieces.WHITE_KNIGHT);
        b.setCell(row4,6,cell);

        c = new Coordinate(row4,7);
        cell = new Cell(c, Chess.Pieces.WHITE_ROOK);
        b.setCell(row4,7,cell);

        for (int i=0; i <8; i++)
        {
            c = new Coordinate(row3,i);
            cell = new Cell(c, Chess.Pieces.WHITE_PAWN);
            b.setCell(row3,i,cell);
        }

        c = new Coordinate(row1,0);
        cell = new Cell(c, Chess.Pieces.BLACK_ROOK);
        b.setCell(row1,0,cell);

        c = new Coordinate(row1,1);
        cell = new Cell(c, Chess.Pieces.BLACK_KNIGHT);
        b.setCell(row1,1,cell);

        c = new Coordinate(row1,2);
        cell = new Cell(c, Chess.Pieces.BLACK_BISHOP);
        b.setCell(row1,2,cell);

        c = new Coordinate(row1,3);
        cell = new Cell(c, Chess.Pieces.BLACK_QUEEN);
        b.setCell(row1,3,cell);

        c = new Coordinate(row1,4);
        cell = new Cell(c, Chess.Pieces.BLACK_KING);
        b.setCell(row1,4,cell);

        c = new Coordinate(row1,5);
        cell = new Cell(c, Chess.Pieces.BLACK_BISHOP);
        b.setCell(row1,5,cell);

        c = new Coordinate(row1,6);
        cell = new Cell(c, Chess.Pieces.BLACK_KNIGHT);
        b.setCell(row1,6,cell);

        c = new Coordinate(row1,7);
        cell = new Cell(c, Chess.Pieces.BLACK_ROOK);
        b.setCell(row1,7,cell);

        for (int i=0; i <8; i++)
        {
            c = new Coordinate(row2,i);
            cell = new Cell(c, Chess.Pieces.BLACK_PAWN);
            b.setCell(row2,i,cell);
        }
        return b;
    }
}
