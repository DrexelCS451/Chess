package com.chess.Layouts;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Tomer on 2/9/16.
 */
public class ViewStatsView {
    public static JPanel getviewStatsView(int win, int loss, int forfiegt, int time, ActionListener returnAction)
    {

        JPanel rootpane = new JPanel();
        rootpane.setLayout(new BoxLayout(rootpane, BoxLayout.Y_AXIS));
        rootpane.setAlignmentY(Component.CENTER_ALIGNMENT);

        Label l = new Label("Statistics",SwingConstants.VERTICAL);
        rootpane.add(l);
        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(4,2));
        pane.add(new Label("Wins", SwingConstants.VERTICAL));
        pane.add(new Label(Integer.toString(win),SwingConstants.VERTICAL));

        pane.add(new Label("Loses",SwingConstants.VERTICAL));
        pane.add(new Label(Integer.toString(loss),SwingConstants.VERTICAL));

        pane.add(new Label("Forfeits",SwingConstants.VERTICAL));
        pane.add(new Label(Integer.toString(forfiegt),SwingConstants.VERTICAL));

        pane.add(new Label("Time Played", SwingConstants.VERTICAL));
        pane.add(new Label(Integer.toString(time) + " minutes", SwingConstants.VERTICAL));
        pane.setBorder(BorderFactory.createLineBorder(Color.black));
        rootpane.add(pane);

        rootpane.add(Box.createVerticalGlue());

        JPanel newp = new JPanel();
        JButton b = new JButton("Return");

        b.setPreferredSize(new Dimension(100,50));
        b.addActionListener(returnAction);
        newp.add(b);
        rootpane.add(newp);


        return rootpane;
    }
    
}
