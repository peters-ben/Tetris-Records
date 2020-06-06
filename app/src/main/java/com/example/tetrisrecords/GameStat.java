package com.example.tetrisrecords;

import java.util.Comparator;

/*
    Description: A class for an individual game, where each game has a score, level, date, and id.
    This class includes methods for comparing two games based on the various aspects of the games.
 */

class GameStat{
    GameStat() { // constructor sets default values
        score = 0;
        level = 0;
        date = "";
        this.id = counter++; // increases the id of every instance of this class by 1, starting at 1
    }
    static class GameDateCompare implements Comparator<GameStat> { // compare two games by date
        @Override
        public int compare(GameStat o1, GameStat o2) {
            int returnValue;
            if(o1.getDate().isEmpty()) // if first date has no value
                returnValue = 1; // positive value means date1 is after date2
            else if (o2.getDate().isEmpty()) // if second date has no value
                returnValue = -1; // negative value means date1 is before date2
            else
                returnValue = o1.getDate().compareTo(o2.getDate()); // compare the two date values
            return returnValue;
        }
    }
    static class GameScoreCompare implements Comparator<GameStat> { // compare two games by score
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
    static class GameLevelCompare implements  Comparator<GameStat> { // compare two games by level
        @Override
        public int compare(GameStat o1, GameStat o2) {
            int lvlCompare = 0, returnValue = 0;
            if(o1.getLevel() < o2.getLevel())
                lvlCompare = 1;
            else if(o1.getLevel() > o2.getLevel())
                lvlCompare = -1;
            if(lvlCompare == 0) { // if they have the same level, compare by score
                if(o1.getScore() < o2.getScore())
                    returnValue = 1;
                else if(o1.getScore() > o2.getScore())
                    returnValue = -1;
            } else
                returnValue = lvlCompare;
            return returnValue;
        }
    }
    // setters and getters
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
    // data members
    private int score;
    private int level;
    private String date;
    private int id;
    private static int counter = 1;
}
