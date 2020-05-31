package com.example.tetrisrecords;

public class GameStat {
    public GameStat() {
        score = 0;
        level = 0;
        date = "";
    }
    void setScore(int newScore) {
        score = newScore;
    }
    void setDate(String newDate) {
        date = newDate;
    }
    void setLevel(int newLevel) {
        level = newLevel;
    }
    String getDate() {
        return date;
    }
    int getLevel() {
        return level;
    }
    int getScore() {
        return score;
    }
    private int score;
    private int level;
    private String date;
}
