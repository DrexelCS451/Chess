package com.chess.Controllers;

import com.chess.Layouts.ChessView;
import com.chess.Layouts.MainMenuView;

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
        frame.setContentPane(ChessView.getChessView(forfeit));
        frame.revalidate();
    }
}
