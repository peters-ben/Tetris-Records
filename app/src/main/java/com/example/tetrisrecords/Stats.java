package com.example.tetrisrecords;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;

class Stats {
    int highScore(ArrayList<GameStat> games) {
        int high = 0;
        for(GameStat game : games) {
            if (game.getScore() > high) {
                high = game.getScore();
            }
        }
        return high;
    }
    float avgScore(ArrayList<GameStat> games) {
        float avg = 0;
        for(GameStat game : games)
            avg += game.getScore();
        if(games.size() != 0)
            avg /= games.size();
        return avg;
    }
    float medianScore(ArrayList<GameStat> games) {
        Collections.sort(games, new GameStat.GameScoreCompare());
        float returnValue;
        int num1 = games.get((games.size() / 2 - 1)).getScore();
        int num2 = games.get(games.size() / 2).getScore();
        if(games.size() % 2 != 0)
            returnValue =  (float) games.get((int) Math.floor((double) games.size() / 2)).getScore();
        else
            returnValue = ((float) (games.get(games.size() / 2 - 1).getScore() +
                    games.get(games.size() / 2).getScore())) / 2;
            return returnValue;
    }

}
