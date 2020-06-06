package com.example.tetrisrecords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
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
        final TextView highScore = findViewById(R.id.highScore);
        final TextView avgScore = findViewById(R.id.avgScore);
        final TextView medianScore = findViewById(R.id.medianScore);
        final TextView numScores = findViewById(R.id.numScores);
        Button deleteAllButton = findViewById(R.id.deleteAllButton);
        if(gameList.size() != 0) {
            String avg = Float.toString(stats.avgScore(gameList));
            String median = Float.toString(stats.medianScore(gameList));
            highScore.setText(String.format(getResources().getString(R.string.high_score), stats.highScore(gameList)));
            avgScore.setText(String.format(getResources().getString(R.string.average_score), avg));
            medianScore.setText(String.format(getResources().getString(R.string.median_score), median));
        }
        numScores.setText(String.format(getResources().getString(R.string.number_of_scores), gameList.size()));
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder delete = new AlertDialog.Builder(StatsActivity.this);
                delete.setTitle("Delete All Scores");
                LinearLayout layout = new LinearLayout(StatsActivity.this);
                TextView verify = new TextView(StatsActivity.this);
               // verify.setGravity(Gravity.LEFT);
                verify.setPadding(33,0,0,0);
                verify.setText(R.string.verify);
                layout.addView(verify);
                delete.setView(layout);
                delete.setPositiveButton("Delete ALL Scores", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataBase.deleteAll();
                        gameList.clear();
                        highScore.setText(R.string.high_score_NA);
                        avgScore.setText(R.string.average_score_NA);
                        medianScore.setText(R.string.median_score_NA);
                        numScores.setText(String.format(getResources().getString(R.string.number_of_scores), gameList.size()));
                    }
                });
                delete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                delete.show();
            }
        });
    }
}
