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

    public Coordinate findKing(Chess.Pieces piece) {
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getCell(i, j).getCellState() == piece)
                    return new Coordinate(i, j);
            }
        }
        return new Coordinate(-1, -1);
    }

    public ArrayList<ChessMove> findQueenMoves(Chess.Pieces queen, Coordinate queenCell) {
        ArrayList<ChessMove> queenMoves = new ArrayList<ChessMove>();
        ArrayList<ChessMove> rookMoves = findRookMoves(queen, queenCell);
        ArrayList<ChessMove> bishopMoves = findRookMoves(queen, queenCell);
        queenMoves.addAll(rookMoves);
        queenMoves.addAll(bishopMoves);
        return queenMoves;
    }

    public ArrayList<ChessMove> findPawnMoves(Chess.Pieces pawn, Coordinate pawnCell) {
        ArrayList<ChessMove> pawnMoves = new ArrayList<ChessMove>();
        int pawnX = pawnCell.getX();
        int pawnY = pawnCell.getY();

        Chess.Pieces otherPawn;
        Chess.Pieces otherRook;
        Chess.Pieces otherBishop;
        Chess.Pieces otherKnight;
        Chess.Pieces otherQueen;
        Chess.Pieces otherKing;

        if(pawn == Chess.Pieces.BLACK_PAWN) {
            otherPawn = Chess.Pieces.WHITE_PAWN;
            otherRook = Chess.Pieces.WHITE_ROOK;
            otherBishop = Chess.Pieces.WHITE_BISHOP;
            otherKnight = Chess.Pieces.WHITE_KNIGHT;
            otherQueen = Chess.Pieces.WHITE_QUEEN;
            otherKing = Chess.Pieces.WHITE_KING;
        }
        else {
            otherPawn = Chess.Pieces.BLACK_PAWN;
            otherRook = Chess.Pieces.BLACK_ROOK;
            otherBishop = Chess.Pieces.BLACK_BISHOP;
            otherKnight = Chess.Pieces.BLACK_KNIGHT;
            otherQueen = Chess.Pieces.BLACK_QUEEN;
            otherKing = Chess.Pieces.BLACK_KING;
        }
        // two move headstart
        if (pawnY == 6 && getCell(pawnX, pawnY - 2).getCellState() == Chess.Pieces.EMPTY) {
            Cell pawnTo = new Cell(new Coordinate(pawnX, pawnY - 2), pawn);
            Cell pawnFrom = new Cell(new Coordinate(pawnX, pawnY), pawn);
            ChessMove move = new ChessMove(pawnFrom, pawnTo);
            pawnMoves.add(move);
        }
        // regular moving foward
        if (pawnY - 1 > -1 && getCell(pawnX, pawnY - 1).getCellState() == Chess.Pieces.EMPTY) {
            Cell pawnTo = new Cell(new Coordinate(pawnX, pawnY - 1), pawn);
            Cell pawnFrom = new Cell(new Coordinate(pawnX, pawnY), pawn);
            ChessMove move = new ChessMove(pawnFrom, pawnTo);
            pawnMoves.add(move);
        }

        // attacking diagonally left
        if (pawnY - 1 > -1 && pawnX - 1 > -1 && (getCell(pawnX - 1, pawnY - 1).getCellState() == otherPawn ||
                                                 getCell(pawnX - 1, pawnY - 1).getCellState() == otherBishop ||
                                                 getCell(pawnX - 1, pawnY - 1).getCellState() == otherKing ||
                                                 getCell(pawnX - 1, pawnY - 1).getCellState() == otherKnight ||
                                                 getCell(pawnX - 1, pawnY - 1).getCellState() == otherQueen ||
                                                 getCell(pawnX - 1, pawnY - 1).getCellState() == otherRook)) {
            Cell pawnTo = new Cell(new Coordinate(pawnX - 1, pawnY - 1), pawn);
            Cell pawnFrom = new Cell (new Coordinate(pawnX, pawnY), pawn);
            ChessMove move = new ChessMove(pawnFrom, pawnTo);
            pawnMoves.add(move);
        }
        // attacking diagonally right
        if (pawnX + 1 < 8 && pawnY - 1 > -1 && (getCell(pawnX + 1, pawnY - 1).getCellState() == otherPawn ||
                                                getCell(pawnX + 1, pawnY - 1).getCellState() == otherBishop ||
                                                getCell(pawnX + 1, pawnY - 1).getCellState() == otherKing ||
                                                getCell(pawnX + 1, pawnY - 1).getCellState() == otherKnight ||
                                                getCell(pawnX + 1, pawnY - 1).getCellState() == otherQueen ||
                                                getCell(pawnX + 1, pawnY - 1).getCellState() == otherRook)) {
            Cell pawnTo = new Cell(new Coordinate(pawnX + 1, pawnY - 1), pawn);
            Cell pawnFrom = new Cell(new Coordinate(pawnX, pawnY), pawn);
            ChessMove move = new ChessMove(pawnFrom, pawnTo);
            pawnMoves.add(move);
        }
        return pawnMoves;
    }

    public ArrayList<ChessMove> findKnightMoves (Chess.Pieces knight, Coordinate knightCell) {
        // 8 possibilities
        ArrayList<ChessMove> knightMoves = new ArrayList<ChessMove>();

        int knightX = knightCell.getX();
        int knightY = knightCell.getY();

        Chess.Pieces otherPawn;
        Chess.Pieces otherRook;
        Chess.Pieces otherBishop;
        Chess.Pieces otherKnight;
        Chess.Pieces otherQueen;
        Chess.Pieces otherKing;

        if(knight == Chess.Pieces.BLACK_KNIGHT) {
            otherPawn = Chess.Pieces.WHITE_PAWN;
            otherRook = Chess.Pieces.WHITE_ROOK;
            otherBishop = Chess.Pieces.WHITE_BISHOP;
            otherKnight = Chess.Pieces.WHITE_KNIGHT;
            otherQueen = Chess.Pieces.WHITE_QUEEN;
            otherKing = Chess.Pieces.WHITE_KING;
        }
        else {
            otherPawn = Chess.Pieces.BLACK_PAWN;
            otherRook = Chess.Pieces.BLACK_ROOK;
            otherBishop = Chess.Pieces.BLACK_BISHOP;
            otherKnight = Chess.Pieces.BLACK_KNIGHT;
            otherQueen = Chess.Pieces.BLACK_QUEEN;
            otherKing = Chess.Pieces.BLACK_KING;
        }

        // up and right
        Coordinate up_and_right = new Coordinate(knightX + 1, knightY - 2);
        if (knightX + 1 < 8 && knightY - 2 > -1) {
            if (getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherPawn ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherRook ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherBishop ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherKnight ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherQueen ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherKing) {
                Cell knightFrom = new Cell(knightCell, knight);
                Cell knightTo = new Cell(up_and_right, knight);
                ChessMove move = new ChessMove(knightFrom, knightTo);
                knightMoves.add(move);
            }
        }

        // right and up
        Coordinate right_and_up = new Coordinate(knightX + 2, knightY - 1);
        if (knightX + 2 < 8 && knightY - 1 > -1) {
            if (getCell(right_and_up.getX(), right_and_up.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(right_and_up.getX(), right_and_up.getY()).getCellState() == otherPawn ||
                    getCell(right_and_up.getX(), right_and_up.getY()).getCellState() == otherRook ||
                    getCell(right_and_up.getX(), right_and_up.getY()).getCellState() == otherBishop ||
                    getCell(right_and_up.getX(), right_and_up.getY()).getCellState() == otherKnight ||
                    getCell(right_and_up.getX(), right_and_up.getY()).getCellState() == otherQueen ||
                    getCell(right_and_up.getX(), right_and_up.getY()).getCellState() == otherKing) {
                Cell knightFrom = new Cell(knightCell, knight);
                Cell knightTo = new Cell(right_and_up, knight);
                ChessMove move = new ChessMove(knightFrom, knightTo);
                knightMoves.add(move);
            }
        }

        //right and down
        Coordinate right_and_down = new Coordinate(knightX + 2, knightY + 1);
        if (knightX + 2 < 8 && knightY + 1 < 8) {
            if (getCell(right_and_down.getX(), right_and_down.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(right_and_down.getX(), right_and_down.getY()).getCellState() == otherPawn ||
                    getCell(right_and_down.getX(), right_and_down.getY()).getCellState() == otherRook ||
                    getCell(right_and_down.getX(), right_and_down.getY()).getCellState() == otherBishop ||
                    getCell(right_and_down.getX(), right_and_down.getY()).getCellState() == otherKnight ||
                    getCell(right_and_down.getX(), right_and_down.getY()).getCellState() == otherQueen ||
                    getCell(right_and_down.getX(), right_and_down.getY()).getCellState() == otherKing) {
                Cell knightFrom = new Cell(knightCell, knight);
                Cell knightTo = new Cell(right_and_down, knight);
                ChessMove move = new ChessMove(knightFrom, knightTo);
                knightMoves.add(move);
            }
        }

        //down and right
        Coordinate down_and_right = new Coordinate(knightX + 1, knightY + 2);
        if (knightX + 1 < 8 && knightY + 2 < 8) {
            if (getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherPawn ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherRook ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherBishop ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherKnight ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherQueen ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherKing) {
                Cell knightFrom = new Cell(knightCell, knight);
                Cell knightTo = new Cell(down_and_right, knight);
                ChessMove move = new ChessMove(knightFrom, knightTo);
                knightMoves.add(move);
            }
        }

        // down and left
        Coordinate down_and_left = new Coordinate(knightX - 1, knightY + 2);
        if (knightX - 1 > -1 && knightY + 2 < 8) {
            if (getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherPawn ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherRook ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherBishop ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherKnight ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherQueen ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherKing) {
                Cell knightFrom = new Cell(knightCell, knight);
                Cell knightTo = new Cell(down_and_left, knight);
                ChessMove move = new ChessMove(knightFrom, knightTo);
                knightMoves.add(move);
            }
        }

        // left and down
        Coordinate left_and_down = new Coordinate(knightX - 2, knightY + 1);
        if (knightX - 2 > -1 && knightY + 1 < 8) {
            if (getCell(left_and_down.getX(), left_and_down.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(left_and_down.getX(), left_and_down.getY()).getCellState() == otherPawn ||
                    getCell(left_and_down.getX(), left_and_down.getY()).getCellState() == otherRook ||
                    getCell(left_and_down.getX(), left_and_down.getY()).getCellState() == otherBishop ||
                    getCell(left_and_down.getX(), left_and_down.getY()).getCellState() == otherKnight ||
                    getCell(left_and_down.getX(), left_and_down.getY()).getCellState() == otherQueen ||
                    getCell(left_and_down.getX(), left_and_down.getY()).getCellState() == otherKing) {
                Cell knightFrom = new Cell(knightCell, knight);
                Cell knightTo = new Cell(left_and_down, knight);
                ChessMove move = new ChessMove(knightFrom, knightTo);
                knightMoves.add(move);
            }
        }

        // left and up
        Coordinate left_and_up = new Coordinate(knightX - 2, knightY - 1);
        if (knightX - 2 > -1 && knightY - 1 > -1) {
            if (getCell(left_and_up.getX(), left_and_up.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(left_and_up.getX(), left_and_up.getY()).getCellState() == otherPawn ||
                    getCell(left_and_up.getX(), left_and_up.getY()).getCellState() == otherRook ||
                    getCell(left_and_up.getX(), left_and_up.getY()).getCellState() == otherBishop ||
                    getCell(left_and_up.getX(), left_and_up.getY()).getCellState() == otherKnight ||
                    getCell(left_and_up.getX(), left_and_up.getY()).getCellState() == otherQueen ||
                    getCell(left_and_up.getX(), left_and_up.getY()).getCellState() == otherKing) {
                Cell knightFrom = new Cell(knightCell, knight);
                Cell knightTo = new Cell(left_and_up, knight);
                ChessMove move = new ChessMove(knightFrom, knightTo);
                knightMoves.add(move);
            }
        }

        // up and left
        Coordinate up_and_left = new Coordinate(knightX - 1, knightY - 2);
        if (knightX - 1 > -1 && knightY - 2 > -1) {
            if (getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherPawn ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherRook ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherBishop ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherKnight ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherQueen ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherKing) {
                Cell knightFrom = new Cell(knightCell, knight);
                Cell knightTo = new Cell(up_and_left, knight);
                ChessMove move = new ChessMove(knightFrom, knightTo);
                knightMoves.add(move);
            }
        }
        return knightMoves;
    }

    public ArrayList<ChessMove> findBishopMoves (Chess.Pieces bishop, Coordinate bishopCell) {
        ArrayList<ChessMove> bishopMoves = new ArrayList<ChessMove>();
        int bishopX = bishopCell.getX();
        int bishopY = bishopCell.getY();

        Chess.Pieces otherPawn;
        Chess.Pieces otherRook;
        Chess.Pieces otherBishop;
        Chess.Pieces otherKnight;
        Chess.Pieces otherQueen;
        Chess.Pieces otherKing;

        if(bishop == Chess.Pieces.BLACK_BISHOP || bishop == Chess.Pieces.BLACK_QUEEN) {
            otherPawn = Chess.Pieces.WHITE_PAWN;
            otherRook = Chess.Pieces.WHITE_ROOK;
            otherBishop = Chess.Pieces.WHITE_BISHOP;
            otherKnight = Chess.Pieces.WHITE_KNIGHT;
            otherQueen = Chess.Pieces.WHITE_QUEEN;
            otherKing = Chess.Pieces.WHITE_KING;
        }
        else {
            otherPawn = Chess.Pieces.BLACK_PAWN;
            otherRook = Chess.Pieces.BLACK_ROOK;
            otherBishop = Chess.Pieces.BLACK_BISHOP;
            otherKnight = Chess.Pieces.BLACK_KNIGHT;
            otherQueen = Chess.Pieces.BLACK_QUEEN;
            otherKing = Chess.Pieces.BLACK_KING;
        }

        // diagonal up and right- each time X goes up by 1 and y goes down by 1
        if (bishopX + 1 < 8 && bishopY - 1 > -1) {
            for (int i = bishopX + 1, j = bishopY - 1; i < 8 && j >= 0; i++, j--) {
                if (getCell(i, j).getCellState() == Chess.Pieces.EMPTY ||
                        getCell(i, j).getCellState() == otherPawn ||
                        getCell(i, j).getCellState() == otherRook ||
                        getCell(i, j).getCellState() ==  otherBishop ||
                        getCell(i, j).getCellState() ==  otherKnight ||
                        getCell(i, j).getCellState() ==  otherQueen ||
                        getCell(i, j).getCellState() ==  otherKing) {
                    Cell bishopFrom = new Cell(bishopCell, bishop);
                    Cell bishopTo = new Cell(new Coordinate(i, j), bishop);
                    ChessMove move = new ChessMove(bishopFrom, bishopTo);
                    bishopMoves.add(move);
                }
            }
        }

        // diagonal up and left - X goes down by 1 and Y goes down by 1
        if (bishopX - 1 > -1 && bishopY - 1 > -1) {
            for (int i = bishopX -1, j = bishopY - 1; i  >= 0 && j >= 0; i--, j--) {
                if (getCell(i,j).getCellState() == Chess.Pieces.EMPTY ||
                        getCell(i,j).getCellState() == otherPawn ||
                        getCell(i,j).getCellState() == otherRook ||
                        getCell(i,j).getCellState() == otherBishop ||
                        getCell(i,j).getCellState() == otherKnight ||
                        getCell(i,j).getCellState() == otherQueen ||
                        getCell(i,j).getCellState() == otherKing) {
                    Cell bishopFrom = new Cell(bishopCell, bishop);
                    Cell bishopTo = new Cell(new Coordinate(i, j), bishop);
                    ChessMove move = new ChessMove(bishopFrom, bishopTo);
                    bishopMoves.add(move);
                }
            }
        }

        // diagonal down and left - X goes down by 1 and Y goes up by 1
        if (bishopX - 1 > -1 && bishopY + 1 < 8) {
            for (int i = bishopX - 1, j = bishopY + 1; i >= 0 && j < 8; i--, j++) {
                if (getCell(i, j).getCellState() == Chess.Pieces.EMPTY ||
                        getCell(i, j).getCellState() == otherPawn ||
                        getCell(i, j).getCellState() == otherRook ||
                        getCell(i, j).getCellState() == otherBishop ||
                        getCell(i, j).getCellState() == otherKnight ||
                        getCell(i, j).getCellState() == otherQueen ||
                        getCell(i, j).getCellState() == otherKing) {
                    Cell bishopFrom = new Cell(bishopCell, bishop);
                    Cell bishopTo = new Cell(new Coordinate(i, j),bishop);
                    ChessMove move = new ChessMove(bishopFrom, bishopTo);
                    bishopMoves.add(move);
                }
            }
        }
        // diagonal down and right - X goes up by 1 and Y goes up by 1
        if (bishopX + 1 < 8 && bishopY < 8) {
            for (int i = bishopX + 1, j = bishopY + 1; i < 8 && j < 8; i++, j++) {
                if (getCell(i, j).getCellState() == Chess.Pieces.EMPTY ||
                        getCell(i, j).getCellState() == otherPawn ||
                        getCell(i, j).getCellState() == otherRook ||
                        getCell(i, j).getCellState() == otherBishop ||
                        getCell(i, j).getCellState() == otherKnight ||
                        getCell(i, j).getCellState() == otherQueen ||
                        getCell(i, j).getCellState() == otherKing) {
                    Cell bishopFrom = new Cell(bishopCell, bishop);
                    Cell bishopTo = new Cell(new Coordinate(i, j),bishop);
                    ChessMove move = new ChessMove(bishopFrom, bishopTo);
                    bishopMoves.add(move);
                }
            }
        }
        return bishopMoves;
    }

    public ArrayList<ChessMove> findRookMoves (Chess.Pieces rook, Coordinate rookCell) {
        ArrayList<ChessMove> rookMoves = new ArrayList<ChessMove>();
        int rookX = rookCell.getX();
        int rookY = rookCell.getY();

        Chess.Pieces otherPawn;
        Chess.Pieces otherRook;
        Chess.Pieces otherBishop;
        Chess.Pieces otherKnight;
        Chess.Pieces otherQueen;
        Chess.Pieces otherKing;

        if(rook == Chess.Pieces.BLACK_ROOK || rook == Chess.Pieces.BLACK_QUEEN) {
            otherPawn = Chess.Pieces.WHITE_PAWN;
            otherRook = Chess.Pieces.WHITE_ROOK;
            otherBishop = Chess.Pieces.WHITE_BISHOP;
            otherKnight = Chess.Pieces.WHITE_KNIGHT;
            otherQueen = Chess.Pieces.WHITE_QUEEN;
            otherKing = Chess.Pieces.WHITE_KING;
        }
        else {
            otherPawn = Chess.Pieces.BLACK_PAWN;
            otherRook = Chess.Pieces.BLACK_ROOK;
            otherBishop = Chess.Pieces.BLACK_BISHOP;
            otherKnight = Chess.Pieces.BLACK_KNIGHT;
            otherQueen = Chess.Pieces.BLACK_QUEEN;
            otherKing = Chess.Pieces.BLACK_KING;
        }
        //up (x stays the same, y decreases)
        if (rookY - 1 > -1) {
            for (int i = rookY - 1; i >= 0; i--) {
                if (getCell(rookX, i).getCellState() == Chess.Pieces.EMPTY ||
                        getCell(rookX, i).getCellState() == otherPawn ||
                        getCell(rookX, i).getCellState() == otherRook ||
                        getCell(rookX, i).getCellState() == otherBishop ||
                        getCell(rookX, i).getCellState() == otherKnight ||
                        getCell(rookX, i).getCellState() == otherQueen ||
                        getCell(rookX, i).getCellState() == otherKing) {
                    Cell rookFrom = new Cell(rookCell, rook);
                    Cell rookTo = new Cell(new Coordinate(rookX, i), rook);
                    ChessMove move = new ChessMove(rookFrom, rookTo);
                    rookMoves.add(move);
                }
            }
        }

        //down (x stays the same, y increases)
        if (rookY + 1 < 8) {
            for (int i = rookY + 1; i < 8; i++) {
                if (getCell(rookX, i).getCellState() == Chess.Pieces.EMPTY ||
                        getCell(rookX, i).getCellState() == otherPawn ||
                        getCell(rookX, i).getCellState() == otherRook ||
                        getCell(rookX, i).getCellState() == otherBishop ||
                        getCell(rookX, i).getCellState() == otherKnight ||
                        getCell(rookX, i).getCellState() == otherQueen ||
                        getCell(rookX, i).getCellState() == otherKing) {
                    Cell rookFrom = new Cell(rookCell, rook);
                    Cell rookTo = new Cell(new Coordinate(rookX, i), rook);
                    ChessMove move = new ChessMove(rookFrom, rookTo);
                    rookMoves.add(move);
                }
            }
        }
        //left (x decreases, y stays the same)
        if (rookX - 1 > -1) {
            for (int i = rookX - 1; i >= 0; i--) {
                if (getCell(i, rookY).getCellState() == Chess.Pieces.EMPTY ||
                        getCell(i, rookY).getCellState() == otherPawn ||
                        getCell(i, rookY).getCellState() == otherRook ||
                        getCell(i, rookY).getCellState() == otherBishop ||
                        getCell(i, rookY).getCellState() == otherKnight ||
                        getCell(i, rookY).getCellState() == otherQueen ||
                        getCell(i, rookY).getCellState() == otherKing) {
                    Cell rookFrom = new Cell(rookCell, rook);
                    Cell rookTo = new Cell(new Coordinate(i, rookY), rook);
                    ChessMove move = new ChessMove(rookFrom, rookTo);
                    rookMoves.add(move);
                }
            }
        }
        //right (x increases, y stays the same)
        if (rookX + 1 < 8) {
            for (int i = rookX + 1; i < 8; i++) {
                if (getCell(i, rookY).getCellState() == Chess.Pieces.EMPTY ||
                        getCell(i, rookY).getCellState() == otherPawn ||
                        getCell(i, rookY).getCellState() == otherRook ||
                        getCell(i, rookY).getCellState() == otherBishop ||
                        getCell(i, rookY).getCellState() == otherKnight ||
                        getCell(i, rookY).getCellState() == otherQueen ||
                        getCell(i, rookY).getCellState() == otherKing) {
                    Cell rookFrom = new Cell(rookCell, rook);
                    Cell rookTo = new Cell(new Coordinate(i, rookY), rook);
                    ChessMove move = new ChessMove(rookFrom, rookTo);
                    rookMoves.add(move);
                }
            }
        }

        return rookMoves;
    }
    // Kelly Shiptoski 3/6
    public ArrayList<ChessMove> findKingMoves(Chess.Pieces king, Coordinate kingCell) {
        ArrayList<ChessMove> kingMoves = new ArrayList<ChessMove>();

        int kingX = kingCell.getX();
        int kingY = kingCell.getY();

        Chess.Pieces otherPawn;
        Chess.Pieces otherRook;
        Chess.Pieces otherBishop;
        Chess.Pieces otherKnight;
        Chess.Pieces otherQueen;
        Chess.Pieces otherKing;

        if(king == Chess.Pieces.BLACK_KING) {
            otherPawn = Chess.Pieces.WHITE_PAWN;
            otherRook = Chess.Pieces.WHITE_ROOK;
            otherBishop = Chess.Pieces.WHITE_BISHOP;
            otherKnight = Chess.Pieces.WHITE_KNIGHT;
            otherQueen = Chess.Pieces.WHITE_QUEEN;
            otherKing = Chess.Pieces.WHITE_KING;
        }
        else {
            otherPawn = Chess.Pieces.BLACK_PAWN;
            otherRook = Chess.Pieces.BLACK_ROOK;
            otherBishop = Chess.Pieces.BLACK_BISHOP;
            otherKnight = Chess.Pieces.BLACK_KNIGHT;
            otherQueen = Chess.Pieces.BLACK_QUEEN;
            otherKing = Chess.Pieces.BLACK_KING;
        }

        //up
        Coordinate up = new Coordinate(kingX, kingY - 1);
        if (kingY - 1> -1) {
            if (getCell(up.getX(), up.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(up.getX(), up.getY()).getCellState() == otherPawn ||
                    getCell(up.getX(), up.getY()).getCellState() == otherRook ||
                    getCell(up.getX(), up.getY()).getCellState() == otherBishop ||
                    getCell(up.getX(), up.getY()).getCellState() == otherKnight ||
                    getCell(up.getX(), up.getY()).getCellState() == otherQueen ||
                    getCell(up.getX(), up.getY()).getCellState() == otherKing) {
                Cell kingFrom = new Cell(kingCell, king);
                Cell kingTo = new Cell(up, king);
                ChessMove move = new ChessMove(kingFrom, kingTo);
                kingMoves.add(move);
            }
        }

        //down
        Coordinate down = new Coordinate(kingX, kingY + 1);
        if (kingY + 1 < 8) {
            if (getCell(down.getX(), down.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(down.getX(), down.getY()).getCellState() == otherPawn ||
                    getCell(down.getX(), down.getY()).getCellState() == otherRook ||
                    getCell(down.getX(), down.getY()).getCellState() == otherBishop ||
                    getCell(down.getX(), down.getY()).getCellState() == otherKnight ||
                    getCell(down.getX(), down.getY()).getCellState() == otherQueen ||
                    getCell(down.getX(), down.getY()).getCellState() == otherKing) {
                Cell kingFrom = new Cell(kingCell, king);
                Cell kingTo = new Cell(down, king);
                ChessMove move = new ChessMove(kingFrom, kingTo);
                kingMoves.add(move);
            }
        }

        //left
        Coordinate left = new Coordinate(kingX - 1, kingY);
        if (kingX - 1 > -1) {
            if (getCell(left.getX(), left.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(left.getX(), left.getY()).getCellState() == otherPawn ||
                    getCell(left.getX(), left.getY()).getCellState() == otherRook ||
                    getCell(left.getX(), left.getY()).getCellState() == otherBishop ||
                    getCell(left.getX(), left.getY()).getCellState() == otherKnight ||
                    getCell(left.getX(), left.getY()).getCellState() == otherQueen ||
                    getCell(left.getX(), left.getY()).getCellState() == otherKing) {
                Cell kingFrom = new Cell(kingCell, king);
                Cell kingTo = new Cell (left, king);
                ChessMove move = new ChessMove(kingFrom, kingTo);
                kingMoves.add(move);
            }
        }

        //right
        Coordinate right = new Coordinate(kingX + 1, kingY);
        if (kingX + 1 < 8) {
            if (getCell(right.getX(), right.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(right.getX(), right.getY()).getCellState() == otherPawn ||
                    getCell(right.getX(), right.getY()).getCellState() == otherRook ||
                    getCell(right.getX(), right.getY()).getCellState() == otherBishop ||
                    getCell(right.getX(), right.getY()).getCellState() == otherKnight ||
                    getCell(right.getX(), right.getY()).getCellState() == otherQueen ||
                    getCell(right.getX(), right.getY()).getCellState() == otherKing) {
                Cell kingFrom = new Cell(kingCell, king);
                Cell kingTo = new Cell(right, king);
                ChessMove move = new ChessMove(kingFrom, kingTo);
                kingMoves.add(move);
            }
        }

        //up and right
        Coordinate up_and_right = new Coordinate(kingX + 1, kingY - 1);
        if (kingX + 1 < 8 && kingY - 1 > -1) {
            if (getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherPawn ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherRook ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherBishop ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherKnight ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherQueen ||
                    getCell(up_and_right.getX(), up_and_right.getY()).getCellState() == otherKing) {
                Cell kingFrom = new Cell(kingCell, king);
                Cell kingTo = new Cell(up_and_right, king);
                ChessMove move = new ChessMove(kingFrom, kingTo);
                kingMoves.add(move);
            }
        }

        //down and right
        Coordinate down_and_right = new Coordinate(kingX + 1, kingY + 1);
        if (kingX  + 1 < 8 && kingY + 1 < 8) {
            if (getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherPawn ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherRook ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherBishop ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherKnight ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherQueen ||
                    getCell(down_and_right.getX(), down_and_right.getY()).getCellState() == otherKing) {
                Cell kingFrom = new Cell(kingCell, king);
                Cell kingTo = new Cell (down_and_right, king);
                ChessMove move = new ChessMove(kingFrom, kingTo);
                kingMoves.add(move);
            }
        }

        //down and left
        Coordinate down_and_left = new Coordinate(kingX - 1, kingY + 1);
        if (kingX - 1 > -1 && kingY + 1 < 8) {
            if (getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherPawn ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherRook ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherBishop ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherKnight ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherQueen ||
                    getCell(down_and_left.getX(), down_and_left.getY()).getCellState() == otherKing) {
                Cell kingFrom = new Cell(kingCell, king);
                Cell kingTo = new Cell(down_and_left, king);
                ChessMove move = new ChessMove(kingFrom, kingTo);
                kingMoves.add(move);
            }
        }

        //up and left
        Coordinate up_and_left = new Coordinate(kingX - 1, kingY - 1);
        if (kingX - 1 > -1 && kingY - 1 > -1) {
            if (getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == Chess.Pieces.EMPTY ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherPawn ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherRook ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherBishop ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherKnight ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherQueen ||
                    getCell(up_and_left.getX(), up_and_left.getY()).getCellState() == otherKing) {
                Cell kingFrom = new Cell(kingCell, king);
                Cell kingTo = new Cell(up_and_left, king);
                ChessMove move = new ChessMove(kingFrom, kingTo);
                kingMoves.add(move);
            }
        }
        return kingMoves;
    }

    // Walk the board to find the pieces that are left
    // Generate moves as you go

    // Kelly Shiptoski 3/6
    // Get all possible moves of all pieces
    // Temp apply each move
    // See if after each temp move king is still in check
    public boolean savableKing(Chess.Pieces king, Coordinate kingCell) {
        if (king == Chess.Pieces.BLACK_KING) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_QUEEN) {
                        ArrayList<ChessMove> blackQueenMoves = findQueenMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < blackQueenMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(blackQueenMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_BISHOP) {
                        ArrayList<ChessMove> blackBishopMoves = findBishopMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < blackBishopMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(blackBishopMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_PAWN) {
                        ArrayList<ChessMove> blackPawnMoves = findPawnMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < blackPawnMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(blackPawnMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_ROOK) {
                        ArrayList<ChessMove> blackRookMoves = findRookMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < blackRookMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(blackRookMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_KNIGHT) {
                        ArrayList<ChessMove> blackKnightMoves = findKnightMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < blackKnightMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(blackKnightMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.BLACK_KING) {
                        ArrayList<ChessMove> blackKingMoves = findKingMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < blackKingMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(blackKingMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                }
            }
        }
        else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_QUEEN) {
                        ArrayList<ChessMove> whiteQueenMoves = findQueenMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < whiteQueenMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(whiteQueenMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_BISHOP) {
                        ArrayList<ChessMove> whiteBishopMoves = findBishopMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < whiteBishopMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(whiteBishopMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_PAWN) {
                        ArrayList<ChessMove> whitePawnMoves = findPawnMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < whitePawnMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(whitePawnMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_ROOK) {
                        ArrayList<ChessMove> whiteRookMoves = findRookMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < whiteRookMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(whiteRookMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_KNIGHT) {
                        ArrayList<ChessMove> whiteKnightMoves = findKnightMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < whiteKnightMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(whiteKnightMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                    else if (getCell(i,j).getCellState() == Chess.Pieces.WHITE_KING) {
                        ArrayList<ChessMove> whiteKingMoves = findKingMoves(getCell(i,j).getCellState(), new Coordinate(i,j));
                        for (int k = 0; k < whiteKingMoves.size(); k++) {
                            Game tempGame = new Game();
                            Board tempBoard = copyBoard();
                            tempGame.setBoard(tempBoard);
                            tempGame.makeMove(whiteKingMoves.get(k));
                            if (!MoveValidator.isCheck(tempBoard, king, kingCell))
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
