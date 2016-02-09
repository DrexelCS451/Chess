package com.chess.Controllers;

import com.chess.Layouts.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by tomer on 2/8/16.
 */
public class MainMenuController {
    public MainMenuController()
    {

    }

    public void createView(JFrame frame)
    {
        ActionListener newGame = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        };

        ActionListener stats = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        };

        final ActionListener exit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        };

        frame.add(MainMenu.getMainMenu(newGame,stats,exit));
    }
}
