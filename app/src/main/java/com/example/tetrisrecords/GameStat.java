package com.example.tetrisrecords;

import java.util.Comparator;

public class GameStat {
    public GameStat() {
        score = 0;
        level = 0;
        date = "";
    }
    static class GameDateCompare implements Comparator<GameStat> {
        @Override
        public int compare(GameStat o1, GameStat o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }
    static class GameScoreCompare implements Comparator<GameStat> {
        @Override
        public int compare(GameStat o1, GameStat o2) {
            int returnValue = 0;
            if(o1.getScore() < o2.getScore())
                returnValue = 1;
            else if(o1.getScore() > o2.getScore())
                returnValue = -1;
            return returnValue;
        }
    }
    static class GameLevelCompare implements  Comparator<GameStat> {
        @Override
        public int compare(GameStat o1, GameStat o2) {
            int lvlCompare = 0, returnValue = 0;
            if(o1.getLevel() < o2.getLevel())
                lvlCompare = 1;
            else if(o1.getLevel() > o2.getLevel())
                lvlCompare = -1;
            if(lvlCompare == 0) {
                if(o1.getScore() < o2.getScore())
                    returnValue = 1;
                else if(o1.getScore() > o2.getScore())
                    returnValue = -1;
            } else
                returnValue = lvlCompare;
            return returnValue;
        }
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
