package com.example.tetrisrecords;

import java.util.Comparator;

class GameStat{
    GameStat() {
        score = 0;
        level = 0;
        date = "";
        this.id = counter++;
    }
    static class GameDateCompare implements Comparator<GameStat> {
        @Override
        public int compare(GameStat o1, GameStat o2) {
            int returnValue;
            if(o1.getDate().isEmpty())
                returnValue = 1;
            else if (o2.getDate().isEmpty())
                returnValue = -1;
            else
                returnValue = o1.getDate().compareTo(o2.getDate());
            return returnValue;
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
    void setId(int newId) {id = newId;}
    String getDate() {
        return date;
    }
    int getLevel() {
        return level;
    }
    int getScore() {
        return score;
    }
    int getId() {return id;}
    private int score;
    private int level;
    private String date;
    private int id;
    private static int counter = 1;
}
