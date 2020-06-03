package com.example.tetrisrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Stats stats = new Stats();
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stats);
        final DataBase dataBase = new DataBase(StatsActivity.this);
        final ArrayList<GameStat> gameList = dataBase.getAll();
        TextView highScore = findViewById(R.id.highScore);
        TextView avgScore = findViewById(R.id.avgScore);
        TextView medianScore = findViewById(R.id.medianScore);
        TextView numScores = findViewById(R.id.numScores);

        if(gameList.size() != 0) {
            String avg = Float.toString(stats.avgScore(gameList));
            String median = Float.toString(stats.medianScore(gameList));
            highScore.setText(String.format(getResources().getString(R.string.high_score), stats.highScore(gameList)));
            avgScore.setText(String.format(getResources().getString(R.string.average_score), avg));
            medianScore.setText(String.format(getResources().getString(R.string.median_score), median));
        }
        numScores.setText(String.format(getResources().getString(R.string.number_of_scores), gameList.size()));

    }
}
