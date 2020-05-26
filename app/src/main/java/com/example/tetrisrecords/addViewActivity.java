package com.example.tetrisrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class addViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_view);
        Button submitBtn = findViewById(R.id.submitBtn);
        final LinearLayout linearLayout = findViewById(R.id.linearLayout);
        final ArrayList<GameStat> games = new ArrayList<>();
        final ArrayList<TextView> scoreDisplays = new ArrayList<>();
        final EditText scoreEntry = findViewById(R.id.scoreEntry);
        final EditText dateEntry = findViewById(R.id.dateEntry);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameStat newGame = new GameStat();
                newGame.setScore(Integer.parseInt(scoreEntry.getText().toString()));
                newGame.setDate(dateEntry.getText().toString());
                games.add(newGame);
                scoreEntry.setText("");
                dateEntry.setText("");
                final TextView newTextView = new TextView(getApplicationContext());
                newTextView.setText(String.format(getResources().getString(R.string.display),
                        games.get(games.size() - 1).getDate(), games.get(games.size() - 1).getScore()));
                newTextView.setTextColor(Color.WHITE);
                newTextView.setTextSize(40);
                newTextView.setGravity(Gravity.CENTER);
                linearLayout.addView(newTextView);
                scoreDisplays.add(newTextView);
            }
        });
    }
}
