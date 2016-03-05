package com.chess.Controllers;

import com.chess.Helpers.BoardHelper;
import com.chess.Helpers.CellListener;
import com.chess.Helpers.ImageHelper;
import com.chess.Layouts.ChessView;
import com.chess.Layouts.MainMenuView;
import com.chess.Models.Board;
import com.chess.Models.Coordinate;

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

       final Board b =  BoardHelper.CreateBoard(true);
       final ChessView view = new ChessView();
        view.setCellClickListner(new CellListener() {
            @Override
            public void actionPerformed(int i, int j) {
                view.cellClicked(i,j);
            }
        });


        view.setBoard(b);
        frame.setContentPane(view.getView());
        frame.revalidate();
    }
}
