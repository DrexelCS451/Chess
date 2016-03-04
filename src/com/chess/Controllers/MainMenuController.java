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
        try{
            PrintWriter writer1 = new PrintWriter("user", "UTF-8");
            writer1.println(s);
            writer1.close();

            URL url = new URL("http://localhost:8080/myapp/user");
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("POST");
            request.setDoOutput(true);
            String postData = "{\"username\"=\"" + s + "\"}";
            OutputStream os = request.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(postData);
            writer.flush();
            writer.close();
            os.close();


            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootobj = root.getAsJsonObject(); // may be Json Array if it's an array, or other type if a primitive



        }catch (Exception e){
            int test  =1;
            test++;
        }







        return true;
    }

}
