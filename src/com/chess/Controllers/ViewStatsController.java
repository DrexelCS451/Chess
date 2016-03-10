package com.chess.Controllers;

import com.chess.Layouts.ViewStatsView;
import com.chess.Models.Stats;
import com.chess.Models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;

/**
 * Created by Tomer on 2/9/16.
 */
public class ViewStatsController {

    public static final String STAT_FILE_NAME = "stats.csv";

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
        frame.setContentPane(ViewStatsView.getviewStatsView(getStats(),returnAction));
        frame.revalidate();
    }


    public static void saveStats(Stats stats){
        try {
            PrintWriter writer = new PrintWriter(STAT_FILE_NAME, "UTF-8");
            writer.print(stats.getWins() + "," + stats.getLosses() + "," + stats.getForfiet() + "," + stats.getTimePlayed());
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Stats getStats()  {

        try {
            File statFile = new File(STAT_FILE_NAME);
            FileInputStream fileReader = null;
            fileReader = new FileInputStream(statFile);
            byte[] data = new byte[(int) statFile.length()];
            fileReader.read(data);
            fileReader.close();
            String fileData = new String(data);
            String[] splitFile = fileData.split(",");
            return new Stats(Integer.parseInt(splitFile[0]), Integer.parseInt(splitFile[1]), Integer.parseInt(splitFile[2]), Integer.parseInt(splitFile[3]));
        } catch (Exception e) {
            return new Stats(0,0,0,0);
        }
    }

    public static void updateTime()
    {
        int mins = (int)(new Date().getTime() - User.timeStarted) / 1000 / 60;
        Stats s = getStats();
        s.setTimePlayed(s.getTimePlayed() + mins);
        saveStats(s);

    }

}
