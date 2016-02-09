package com.chess.Controllers;

import com.chess.Layouts.ViewStatsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Tomer on 2/9/16.
 */
public class ViewStatsController {
    public ViewStatsController()
    {

    }

    public void createView(final JFrame frame)
    {
        ActionListener returnAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainMenuController menu = new MainMenuController();
                menu.createView(frame);
            }
        };
        frame.setContentPane(ViewStatsView.getviewStatsView(10,5,2,1004,returnAction));
        frame.revalidate();
    }

}
