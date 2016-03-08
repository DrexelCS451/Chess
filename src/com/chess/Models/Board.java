package com.chess.Models;

import com.chess.Helpers.MoveValidator;

import java.util.ArrayList;

/**
 * Created by tomer on 3/4/16.
 */
public class Board {
    Cell[][] cells = new Cell[8][8];
    Chess.BoardState state;

    public Board() {}

    public Board copyBoard()
    {
        Board board = new Board();
        for (int i = 0; i < cells.length; i++)
        {
            for (int j = 0; j < cells[i].length; j++)
            {
                board.cells[i][j] = cells[i][j].copyCell();
            }
        }
        return board;
    }

    public Board(String boardString) {
        String[] cellStrings = boardString.split("\n");
        String[][] cellLists = new String[cellStrings.length][3];

        for(int i = 0; i < cellStrings.length; i++) {
            cellLists[i] = cellStrings[i].split(",");
        }

        int index;
        int x;
        int y;
        for(int i = 0; i < cells.length; i++) {
            for(int j = 0; j < cells[i].length; j++) {
                index = (i * cells.length) + j;
                x = Integer.parseInt(cellLists[index][0]);
                y = Integer.parseInt(cellLists[index][1]);
                cells[i][j] = new Cell(new Coordinate(x, y), Chess.Pieces.valueOf(cellLists[index][2]));
            }
        }


        if(!User.isWhite)
        {
            Cell row;
            for(int j = 0; j < 8; j++)
            {
                for (int i =0; i <4; i++)
                {
                    row = cells[j][i];
                    cells[j][i] = cells[j][7-i];
                    cells[j][i].setPos(new Coordinate(j,i));
                    cells[j][7-i] = row;
                    cells[j][7-i].setPos(new Coordinate(j,7-i));
                }
            }
        }
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
        if(!User.isWhite) {
            Cell[][] cells = this.cells;
            Cell row;
            for(int j = 0; j < 8; j++) {
                for (int i =0; i <4; i++) {
                    row = cells[j][i];
                    cells[j][i] = cells[j][7-i];
                    cells[j][i].setPos(new Coordinate(j,i));
                    cells[j][7-i] = row;
                    cells[j][7-i].setPos(new Coordinate(j,7-i));
                }
            }
        }

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

    public Coordinate findKing(Chess.Pieces piece) {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getCell(i, j).getCellState() == piece)
                    return new Coordinate(i, j);
            }
        }
        return new Coordinate(-1, -1);
    }


    public boolean savableKingAux(ArrayList<ChessMove> validMoves, Cell piece) {
        for (int k = 0; k < validMoves.size(); k++) {
            Game tempGame = new Game();
            Board tempBoard = copyBoard();
            tempGame.setBoard(tempBoard);
            tempGame.makeMove(validMoves.get(k));
            if (!MoveValidator.isCheck(tempBoard, piece.getCellState(), piece.getPos()))
                return true;
        }
        return false;
    }

    public boolean savableKing(Cell king) {
        if (king.getCellState() == Chess.Pieces.BLACK_KING) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_QUEEN) {
                        ArrayList<ChessMove> blackQueenMoves = MoveValidator.findQueenMoves(getCell(i, j), this);
                        return savableKingAux(blackQueenMoves, king);
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_BISHOP) {
                        ArrayList<ChessMove> blackBishopMoves = MoveValidator.findBishopMoves(getCell(i, j), this);
                        return savableKingAux(blackBishopMoves, king);
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_PAWN) {
                        ArrayList<ChessMove> blackPawnMoves = MoveValidator.findPawnMoves(getCell(i, j), this);
                        return savableKingAux(blackPawnMoves, king);
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_ROOK) {
                        ArrayList<ChessMove> blackRookMoves = MoveValidator.findRookMoves(getCell(i, j), this);
                        return savableKingAux(blackRookMoves, king);
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_KNIGHT) {
                        ArrayList<ChessMove> blackKnightMoves = MoveValidator.findKnightMoves(getCell(i, j), this);
                        return savableKingAux(blackKnightMoves, king);
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_KING) {
                        ArrayList<ChessMove> blackKingMoves = MoveValidator.findKingMoves(getCell(i, j), this);
                        return savableKingAux(blackKingMoves, king);
                    }
                }
            }
        }
        else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_QUEEN) {
                        ArrayList<ChessMove> whiteQueenMoves = MoveValidator.findQueenMoves(getCell(i, j), this);
                        return savableKingAux(whiteQueenMoves, king);
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_BISHOP) {
                        ArrayList<ChessMove> whiteBishopMoves = MoveValidator.findBishopMoves(getCell(i, j), this);
                        return savableKingAux(whiteBishopMoves, king);
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_PAWN) {
                        ArrayList<ChessMove> whitePawnMoves = MoveValidator.findPawnMoves(getCell(i, j), this);
                        return savableKingAux(whitePawnMoves, king);
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_ROOK) {
                        ArrayList<ChessMove> whiteRookMoves = MoveValidator.findRookMoves(getCell(i, j), this);
                        return savableKingAux(whiteRookMoves, king);
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_KNIGHT) {
                        ArrayList<ChessMove> whiteKnightMoves = MoveValidator.findKnightMoves(getCell(i, j), this);
                        return savableKingAux(whiteKnightMoves, king);
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_KING) {
                        ArrayList<ChessMove> whiteKingMoves = MoveValidator.findKingMoves(getCell(i, j), this);
                        return savableKingAux(whiteKingMoves, king);
                    }
                }
            }
        }
        return false;
    }
}
