package com.chess.Controllers;

import com.chess.Helpers.BoardHelper;
import com.chess.Helpers.CellListener;
import com.chess.Helpers.ImageHelper;
import com.chess.Helpers.MoveValidator;
import com.chess.Layouts.ChessView;
import com.chess.Layouts.MainMenuView;
import com.chess.Models.Board;
import com.chess.Models.ChessMove;
import com.chess.Models.Coordinate;
import com.chess.Models.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tomer on 2/10/16.
 */
public class ChessScreenController {
    public ChessScreenController()
    {

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
                view.resetCellColor();
                view.remove2Buttons();
                click1 = null;
                click2 = null;
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

        view.setB1Listener(b1);
        view.setB2Listener(b2);
        view.setForfeitListener(forfeit);
        view.setCellClickListner(new CellListener() {
            @Override
            public void actionPerformed(int i, int j) {
               if(BoardHelper.SquareIsUsers(i,j,b))
               {
                   view.resetCellColor();
                   click1 = new Coordinate(i,j);
                   click2 = null;
                   view.cellClicked(i,j);
                   view.remove2Buttons();
               }
               else if(click1 != null && MoveValidator.isValidMove(new ChessMove(b.getCell(click1.getX(), click1.getY()),b.getCell(i, j)),b))
               {
                   view.resetCellColor();
                   click2 = new Coordinate(i,j);
                   view.cellClicked(click1.getX(),click1.getY());
                   view.cellClicked(i,j);
                   view.show2Buttons("Confirm","Cancel");
               }


            }
        });


        view.setBoard(b);
        frame.setContentPane(view.getView());
        frame.revalidate();
    }
}
