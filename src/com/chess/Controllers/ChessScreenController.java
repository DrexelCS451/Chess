package com.chess.Controllers;

import com.chess.Helpers.BoardHelper;
import com.chess.Helpers.CellListener;
import com.chess.Helpers.ImageHelper;
import com.chess.Helpers.MoveValidator;
import com.chess.Layouts.ChessView;
import com.chess.Layouts.MainMenuView;
import com.chess.Models.*;
import com.chess.Networking.Listener;
import com.chess.Networking.RequestUtil;
import com.google.gson.JsonElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tomer on 2/10/16.
 */
public class ChessScreenController {
    JsonElement e;
    public ChessScreenController(JsonElement e)
    {
        this.e = e;
    }

    Coordinate click1;
    Coordinate click2;
    Board b;
    boolean inCheck = false;
    public void createView(final JFrame frame)
    {
        ActionListener forfeit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to forfeit? This will count as a loss.", "Forfeit?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    new MainMenuController().createView(frame);
                }
            }
        };

        final Game g = new Game();
        b = g.getBoard();
       final ChessView view = new ChessView();

        ActionListener b1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    b = g.makeMove(new ChessMove(b.getCell(click1),b.getCell(click2)));
                    view.setBoard(b);
                    view.setLabel("Opponent's Turn");

                    view.resetCellColor();
                    view.remove2Buttons();
                    click1 = null;
                    click2 = null;
                    RequestUtil.makeMove(e.getAsJsonObject().get("id").getAsInt(), b, new Listener() {
                        @Override
                        public void responce(JsonElement e) {
                            Coordinate king = b.findKing( (!User.isWhite) ? Chess.Pieces.WHITE_KING: Chess.Pieces.BLACK_KING);

                            if(MoveValidator.isCheckMate(b.getCell(king),b))
                            {

                                JOptionPane.showMessageDialog(frame, "You Won!");
                                MainMenuController m = new MainMenuController();
                                m.createView(frame);
                                RequestUtil.stopCheckingForMoves();
                                return;
                            }




                            RequestUtil.startCheckingForMoves(new Listener() {
                                @Override
                                public void responce(JsonElement e) {
                                    b = new Board(e.getAsJsonObject().get("encodedGameBoard").getAsString());
                                    Coordinate king = b.findKing( (User.isWhite) ? Chess.Pieces.WHITE_KING: Chess.Pieces.BLACK_KING);

                                    if(MoveValidator.isCheckMate(b.getCell(king),b))
                                    {
                                        RequestUtil.stopCheckingForMoves();
                                        JOptionPane.showMessageDialog(frame, "You Lost!");
                                        MainMenuController m = new MainMenuController();
                                        m.createView(frame);
                                    }



                                    view.setLabel("Your Turn");
                                    b.setBoardState(Chess.BoardState.valueOf(e.getAsJsonObject().get("state").getAsString()));
                                    g.setBoard(b);
                                    inCheck = MoveValidator.isCheck(b, (User.isWhite) ? Chess.Pieces.WHITE_KING: Chess.Pieces.BLACK_KING,b.findKing( (User.isWhite) ? Chess.Pieces.WHITE_KING: Chess.Pieces.BLACK_KING));

                                    view.setBoard(b);
                                    frame.setContentPane(view.getView());
                                    frame.revalidate();
                                    RequestUtil.stopCheckingForMoves();
                                }
                            });
                        }
                    });

            }
        };

        ActionListener b2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.resetCellColor();
                view.remove2Buttons();
                click1 = null;
                click2 = null;
            }
        };

        if(!((e.getAsJsonObject().get("state").getAsString().equals(Chess.BoardState.BLACK_TURN.name()) && !User.isWhite) || (e.getAsJsonObject().get("state").getAsString().equals(Chess.BoardState.WHITE_TURN.name()) && User.isWhite)))
        {
            RequestUtil.startCheckingForMoves(new Listener() {
                @Override
                public void responce(JsonElement e) {
                    b = new Board(e.getAsJsonObject().get("encodedGameBoard").getAsString());
                    b.setBoardState(Chess.BoardState.valueOf(e.getAsJsonObject().get("state").getAsString()));
                    g.setBoard(b);
                    view.setBoard(b);
                    frame.setContentPane(view.getView());
                    frame.revalidate();
                    RequestUtil.stopCheckingForMoves();
                }
            });
        }

            view.setB1Listener(b1);
        view.setB2Listener(b2);
        view.setForfeitListener(forfeit);
        view.setCellClickListner(new CellListener() {
             @Override
             public void actionPerformed(int i, int j) {
                 if ((g.getBoard().getBoardState() == Chess.BoardState.BLACK_TURN && !User.isWhite) || (g.getBoard().getBoardState() == Chess.BoardState.WHITE_TURN && User.isWhite)) {

                     if (BoardHelper.SquareIsUsers(i, j, b)) {
                         view.resetCellColor();
                         click1 = new Coordinate(i, j);
                         click2 = null;
                         view.cellClicked(i, j);
                         view.remove2Buttons();
                     } else if (click1 != null && MoveValidator.isValidMove(new ChessMove(b.getCell(click1.getX(), click1.getY()), b.getCell(i, j)), b)) {

                         view.resetCellColor();
                         click2 = new Coordinate(i, j);
                         view.cellClicked(click1.getX(), click1.getY());
                         view.cellClicked(i, j);
                         view.show2Buttons("Confirm", "Cancel");
                     }
                 }

             }
         }
        );


            view.setBoard(b);
            frame.setContentPane(view.getView());
            frame.revalidate();
        }
    }
