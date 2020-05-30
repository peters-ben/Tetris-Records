package com.example.tetrisrecords;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class addViewActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_view);
        Button submitBtn = findViewById(R.id.submitBtn);
        final ArrayList<GameStat> gameList = new ArrayList<>();
        final EditText scoreEntry = findViewById(R.id.scoreEntry);
        final EditText dateEntry = findViewById(R.id.dateEntry);
        final EditText levelEntry = findViewById(R.id.levelEntry);
        final ImageView xView = findViewById(R.id.xView);
        final ImageView editView = findViewById(R.id.editView);
        listView = findViewById(R.id.listView);
        adapter = new CustomAdapter(this, gameList);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setAdapter(adapter);
                GameStat newGame = new GameStat();
                if(!scoreEntry.getText().toString().equals("")) {
                    newGame.setScore(Integer.parseInt(scoreEntry.getText().toString()));
                    newGame.setDate(dateEntry.getText().toString());
                    if(levelEntry.getText().toString().equals(""))
                        newGame.setLevel(0);
                    else
                        newGame.setLevel(Integer.parseInt(levelEntry.getText().toString()));
                    gameList.add(0, newGame);
                    scoreEntry.setText("");
                    levelEntry.setText("");
                    dateEntry.setText("");
                }
            }
        });
    }
}
