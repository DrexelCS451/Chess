package com.chess.Controllers;

import com.chess.Layouts.ViewStatsView;

import javax.swing.*;

/**
 * Created by Tomer on 2/9/16.
 */
public class ViewStatsController {
    public ViewStatsController()
    {

    }

    public void createView(JFrame frame)
    {
        frame.setContentPane(ViewStatsView.getviewStatsView());
        frame.revalidate();
    }

}
