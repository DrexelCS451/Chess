package com.chess.Controllers;

import com.chess.Layouts.MainMenuView;
import com.chess.Models.User;
import com.chess.Networking.Listener;
import com.chess.Networking.RequestUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.media.jfxmedia.logging.Logger;
import org.omg.CORBA.NameValuePair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    private boolean checkFirstUse(final JFrame frame)
    {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                RequestUtil.leaveLobby();
            }
        });


        File varTmpDir = new File("user.txt");
        if(varTmpDir.exists())
        {
            try{
                User u = new User();
                Scanner scanner = new Scanner(varTmpDir);
                u.id = scanner.nextLine();
                scanner.close();
                //u.id = "389";
                LobbyController c = new LobbyController();
                c.createView(frame);
            }catch (Exception e){
                Logger.logMsg(Logger.WARNING, e.getStackTrace().toString());
            }


            return false;
        }

        final String s = (String)JOptionPane.showInputDialog(
                frame,
                "Enter new Username",
                "Username",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "");
        if(s!=null && !s.isEmpty())
        {
            RequestUtil.lookupUser(s, new Listener() {
                @Override
                public void responce(JsonElement e) {
                    if(e.getAsJsonObject().get("status").getAsBoolean())
                    {
                        JOptionPane.showMessageDialog(frame, "Error: Username already taken.");
                        checkFirstUse(frame);
                    }
                    else
                    {
                        RequestUtil.CreateUser(s, new Listener() {
                            @Override
                            public void responce(JsonElement e) {
                                try{
                                    PrintWriter writer = new PrintWriter("user.txt", "UTF-8");
                                    writer.println(Integer.toString(e.getAsJsonObject().get("userId").getAsInt()));
                                    writer.close();
                                    User.id =Integer.toString(e.getAsJsonObject().get("userId").getAsInt());
                                    LobbyController c = new LobbyController();
                                    c.createView(frame);
                                }catch (Exception e1){
                                    Logger.logMsg(Logger.WARNING, e1.getStackTrace().toString());
                                }
                            }
                        });


                    }
                }
            });

        }

        return true;
    }

}
