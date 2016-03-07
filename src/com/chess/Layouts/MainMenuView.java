package com.chess.Layouts;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by tomer on 2/8/16.
 */
public class MainMenuView {
    public static JPanel getMainMenu(ActionListener newGame, ActionListener stats, ActionListener exit)
    {
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.setAlignmentY(Component.CENTER_ALIGNMENT);

        pane.add(Box.createRigidArea(new Dimension(0, 100)));


        JButton button = new JButton("New Game");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMargin(new Insets(10, 10, 10, 10));
        button.addActionListener(newGame);
        pane.add(button);
        pane.add(Box.createRigidArea(new Dimension(0,30)));


        button = new JButton("View Stats");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMargin(new Insets(10, 10, 10, 10));
        button.addActionListener(stats);
        pane.add(button);
        pane.add(Box.createRigidArea(new Dimension(0,30)));

        button = new JButton("Exit");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMargin(new Insets(10, 10, 10, 10));
        button.addActionListener(exit);
        pane.add(button);
        return pane;
    }
}
