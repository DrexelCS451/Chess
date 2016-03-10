package com.chess.Models;

/**
 * Created by Matt on 3/9/16.
 */
public class Stats {

    private int wins;
    private int losses;
    private int forfiet;
    private int timePlayed;


    public Stats(int wins, int losses, int forfiet, int timePlayed) {
        this.wins = wins;
        this.losses = losses;
        this.forfiet = forfiet;
        this.timePlayed = timePlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getForfiet() {
        return forfiet;
    }

    public void setForfiet(int forfiet) {
        this.forfiet = forfiet;
    }

    public int getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }
}
