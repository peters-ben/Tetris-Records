package com.example.tetrisrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
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
        final ArrayList<GameStat> games = new ArrayList<>();
        final ArrayList<TextView> scoreDisplays = new ArrayList<>();
        final EditText scoreEntry = findViewById(R.id.scoreEntry);
        final EditText dateEntry = findViewById(R.id.dateEntry);
        final EditText levelEntry = findViewById(R.id.levelEntry);
        CustomAdapter adapter = new CustomAdapter(this, R.layout.adapter_view_layout, games);
        final ListView mListView = findViewById(R.id.listView);
        mListView.setAdapter(adapter);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameStat newGame = new GameStat();
                if(!scoreEntry.getText().toString().equals("")) {
                    newGame.setScore(Integer.parseInt(scoreEntry.getText().toString()));
                    newGame.setDate(dateEntry.getText().toString());
                    if(levelEntry.getText().toString().equals(""))
                        newGame.setLevel(0);
                    else
                        newGame.setLevel(Integer.parseInt(levelEntry.getText().toString()));
                    games.add(newGame);
                    scoreEntry.setText("");
                    levelEntry.setText("");
                    dateEntry.setText("");
                    final TextView newTextView = new TextView(getApplicationContext());
                    newTextView.setText(String.format(getResources().getString(R.string.display),
                            games.get(games.size() - 1).getDate(), games.get(games.size() - 1).getScore(),
                            games.get(games.size() - 1).getLevel()));
                    newTextView.setTextColor(Color.WHITE);
                    newTextView.setTextSize(40);
                    newTextView.setGravity(Gravity.CENTER);
                  //  mListView.addHeaderView(newTextView);
                    scoreDisplays.add(newTextView);

                }
            }
        });
    }
}
