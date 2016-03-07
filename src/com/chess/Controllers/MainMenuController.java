package com.chess.Controllers;

import com.chess.Layouts.MainMenuView;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.omg.CORBA.NameValuePair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomer on 2/8/16.
 */
public class MainMenuController {
    public MainMenuController()
    {

    }

    public void createView(final JFrame frame)
    {
        ActionListener newGame = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                checkFirstUse(frame);
                ChessScreenController c = new ChessScreenController();
                c.createView(frame);
            }
        };

        ActionListener stats = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ViewStatsController c = new ViewStatsController();
                c.createView(frame);
            }
        };

        final ActionListener exit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        };

        frame.setContentPane(MainMenuView.getMainMenu(newGame, stats, exit));
        frame.revalidate();
    }

    private boolean checkFirstUse(JFrame frame)
    {
        File varTmpDir = new File("user");
        if(varTmpDir.exists())
        {
            return false;
        }

        String s = (String)JOptionPane.showInputDialog(
                frame,
                "Enter new Username",
                "Username",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");

        return true;
    }

}
