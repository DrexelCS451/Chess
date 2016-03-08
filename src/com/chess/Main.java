package com.chess;
import com.chess.Controllers.MainMenuController;
import com.chess.Networking.RequestUtil;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(500, 550));
        //Add the ubiquitous "Hello World" label.
        MainMenuController menu = new MainMenuController();
        menu.createView(frame);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

            }
        });*/
        createAndShowGUI();

    }
}
