package com.chess.Models;

/**
 * Created by Matt on 3/9/16.
 */
public class Stats {

    private String wins;
    private String losses;
    private String forfiet;
    private String timePlayed;


    public Stats(String wins, String losses, String forfiet, String timePlayed) {
        this.wins = wins;
        this.losses = losses;
        this.forfiet = forfiet;
        this.timePlayed = timePlayed;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getForfiet() {
        return forfiet;
    }

    public void setForfiet(String forfiet) {
        this.forfiet = forfiet;
    }

    public String getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }
}
