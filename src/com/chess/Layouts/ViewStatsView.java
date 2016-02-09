package com.chess.Layouts;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Tomer on 2/9/16.
 */
public class ViewStatsView {
    public static JPanel getviewStatsView()
    {

        JPanel rootpane = new JPanel();
        rootpane.setLayout(new BoxLayout(rootpane, BoxLayout.Y_AXIS));
        rootpane.setAlignmentY(Component.CENTER_ALIGNMENT);
        rootpane.add(Box.createRigidArea(new Dimension(0, 100)));

        Label l = new Label("Statistics",SwingConstants.VERTICAL);
        rootpane.add(l);



        JPanel pane = new JPanel();
        pane.setLayout(new GridLayout(4,2));
        pane.add(new Label("Wins",SwingConstants.VERTICAL));
        pane.add(new Label("0"));

        pane.add(new Label("Loses",SwingConstants.VERTICAL));
        pane.add(new Label("0"));

        pane.add(new Label("Forfiegts",SwingConstants.VERTICAL));
        pane.add(new Label("0"));

        pane.add(new Label("Time Played",SwingConstants.VERTICAL));
        pane.add(new Label("0"));
        pane.setBorder( BorderFactory.createLineBorder(Color.black));
        rootpane.add(pane);

        return rootpane;
    }
    
}
