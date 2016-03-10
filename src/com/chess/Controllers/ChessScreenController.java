package com.chess.Controllers;

import com.chess.Helpers.BoardHelper;
import com.chess.Helpers.CellListener;
import com.chess.Helpers.MoveValidator;
import com.chess.Layouts.ChessView;
import com.chess.Models.*;
import com.chess.Networking.Listener;
import com.chess.Networking.RequestUtil;
import com.google.gson.JsonElement;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by tomer on 2/10/16.
 *
 */
public class ChessScreenController {
    JsonElement e;
    public ChessScreenController(JsonElement e)
    {
        User.timeStarted = new Date().getTime();
        this.e = e;
    }

    Coordinate click1;
    Coordinate click2;
    Board b;

    public void createView(final JFrame frame)
    {
        ActionListener forfeit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to forfeit? This will count as a loss.", "Forfeit?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    b.setBoardState(User.isWhite ? Chess.BoardState.BLACK_WIN : Chess.BoardState.WHITE_WIN);
                    Stats s = ViewStatsController.getStats();
                    s.setForfiet(s.getForfiet() + 1);
                    ViewStatsController.saveStats(s);
                    ViewStatsController.updateTime();
                    RequestUtil.makeMove(e.getAsJsonObject().get("id").getAsInt(), b, new Listener() {
                        @Override
                        public void responce(JsonElement e) {
                            new MainMenuController().createView(frame);
                        }
                    });
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
                    view.disableForfeit();
                    view.setBoard(b);
                    view.setLabel("Opponent's Turn");

                    view.resetCellColor();
                    view.remove2Buttons();
                    final Coordinate king = b.findKing( (!User.isWhite) ? Chess.Pieces.WHITE_KING: Chess.Pieces.BLACK_KING);

                   /* if(MoveValidator.isStaleMate(b.getCell(king),b))
                    {
                        //b.setBoardState(Chess.BoardState.STALEMATE);
                    }*/

                    click1 = null;
                    click2 = null;
                    RequestUtil.makeMove(e.getAsJsonObject().get("id").getAsInt(), b, new Listener() {
                        @Override
                        public void responce(JsonElement e) {

                            if(MoveValidator.isCheckMate(b.getCell(king),b))
                            {
                                Stats s = ViewStatsController.getStats();
                                s.setWins(s.getWins()+1);
                                ViewStatsController.saveStats(s);
                                ViewStatsController.updateTime();
                                JOptionPane.showMessageDialog(frame, "You Won!");
                                MainMenuController m = new MainMenuController();
                                m.createView(frame);
                                RequestUtil.stopCheckingForMoves();
                                return;
                            }

                          /*  if(MoveValidator.isStaleMate(b.getCell(king),b))
                            {
                                ViewStatsController.updateTime();
                                JOptionPane.showMessageDialog(frame, "Stalemate!");
                                MainMenuController m = new MainMenuController();
                                m.createView(frame);
                                RequestUtil.stopCheckingForMoves();
                                return;
                            }*/



                            RequestUtil.startCheckingForMoves(new Listener() {
                                @Override
                                public void responce(JsonElement e) {
                                    moveMade(frame,view,g,e);
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
            view.disableForfeit();
            RequestUtil.startCheckingForMoves(new Listener() {
                @Override
                public void responce(JsonElement e) {
                    moveMade(frame, view, g, e);
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

    public void moveMade(JFrame frame,ChessView view, Game g, JsonElement e)
    {
        if(e.getAsJsonObject().get("state").getAsString().equals(User.isWhite?Chess.BoardState.WHITE_WIN.name():Chess.BoardState.BLACK_WIN.name()))
        {
            RequestUtil.stopCheckingForMoves();
            Stats s = ViewStatsController.getStats();
            s.setWins(s.getWins()+1);
            ViewStatsController.saveStats(s);
            ViewStatsController.updateTime();
            JOptionPane.showMessageDialog(frame, "You Won! Your opponent forfeited!");
            MainMenuController m = new MainMenuController();
            m.createView(frame);
            return;
        }

        b = new Board(e.getAsJsonObject().get("encodedGameBoard").getAsString());
        Coordinate king = b.findKing( (User.isWhite) ? Chess.Pieces.WHITE_KING: Chess.Pieces.BLACK_KING);

        if(MoveValidator.isCheckMate(b.getCell(king),b))
        {
            RequestUtil.stopCheckingForMoves();
            ViewStatsController.updateTime();
            JOptionPane.showMessageDialog(frame, "Stalemate!");
            MainMenuController m = new MainMenuController();
            m.createView(frame);
            return;
        }

        if(MoveValidator.isStaleMate(b.getCell(king),b)) {
            RequestUtil.stopCheckingForMoves();
            Stats s = ViewStatsController.getStats();
            s.setLosses(s.getLosses() + 1);
            ViewStatsController.saveStats(s);
            ViewStatsController.updateTime();
            JOptionPane.showMessageDialog(frame, "You Lost!");
            MainMenuController m = new MainMenuController();
            m.createView(frame);
            return;
        }

        view.setLabel("Your Turn");
        b.setBoardState(Chess.BoardState.valueOf(e.getAsJsonObject().get("state").getAsString()));
        g.setBoard(b);
        //inCheck = MoveValidator.isCheck(b, (User.isWhite) ? Chess.Pieces.WHITE_KING: Chess.Pieces.BLACK_KING,b.findKing( (User.isWhite) ? Chess.Pieces.WHITE_KING: Chess.Pieces.BLACK_KING));
        view.enableForfeit();
        view.setBoard(b);
        frame.setContentPane(view.getView());
        frame.revalidate();
        RequestUtil.stopCheckingForMoves();
    }
    }
