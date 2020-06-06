package com.example.tetrisrecords;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;

/*
    Description: A class for finding various stats of a given list of games including high score,
    average score, and the median score.
 */

class Stats {
    int highScore(ArrayList<GameStat> games) {
        int high = 0;
        for(GameStat game : games) { // for every game in list
            if (game.getScore() > high) // if higher than current highest seen
                high = game.getScore();
        }
        return high;
    }
    float avgScore(ArrayList<GameStat> games) {
        float avg = 0;
        for(GameStat game : games) // for every game in list
            avg += game.getScore(); // add to sum
        if(games.size() != 0) // ensures no division by 0
            avg /= games.size();
        return avg;
    }
    float medianScore(ArrayList<GameStat> games) {
        Collections.sort(games, new GameStat.GameScoreCompare()); // sort games by score
        float returnValue;
        if(games.size() % 2 != 0) // if there are an odd number of games
            returnValue =  (float) games.get((int) Math.floor((double) games.size() / 2)).getScore();
        else // if there are an even number of games
            returnValue = ((float) (games.get(games.size() / 2 - 1).getScore() +
                    games.get(games.size() / 2).getScore())) / 2;
            return returnValue;
    }
}
