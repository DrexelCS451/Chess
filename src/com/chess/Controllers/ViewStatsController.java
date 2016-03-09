package com.chess.Controllers;

import com.chess.Layouts.ViewStatsView;
import com.chess.Models.Stats;
import com.chess.Models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
        frame.setContentPane(ViewStatsView.getviewStatsView(10,5,2,1004,returnAction));
        frame.revalidate();
    }


    public static void saveStats(Stats stats){
        File newStatFile = new File(STAT_FILE_NAME);
        try {
            PrintWriter fileWriter = new PrintWriter(newStatFile);
            fileWriter.print(stats.getWins() + ',' + stats.getLosses() + ',' + stats.getForfiet() + ',' + stats.getTimePlayed());
            fileWriter.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Stats getStats() throws IOException {
        File statFile = new File(STAT_FILE_NAME);
        FileInputStream fileReader = new FileInputStream(statFile);
        byte[] data = new byte[(int) statFile.length()];
        fileReader.read(data);
        fileReader.close();
        String fileData = new String(data);
        String[] splitFile = fileData.split(",");
        return new Stats(splitFile[0], splitFile[1], splitFile[2], splitFile[3]);

    }

}
