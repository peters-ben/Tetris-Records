package com.example.tetrisrecords;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class addViewActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DataBase dataBase = new DataBase(addViewActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_view);
        final Button submitBtn = findViewById(R.id.submitBtn);
        final ArrayList<GameStat> gameList = dataBase.getAll();
        Spinner sortSpinner = findViewById(R.id.sortSpinner);
        ArrayAdapter<String> spinnerAdapter =new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sort));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);
        final EditText scoreEntry = findViewById(R.id.scoreEntry);
        final EditText dateEntry = findViewById(R.id.dateEntry);
        final EditText levelEntry = findViewById(R.id.levelEntry);
        final ImageView xView = findViewById(R.id.xView);
        final ImageView editView = findViewById(R.id.editView);
        listView = findViewById(R.id.listView);
        adapter = new CustomAdapter(this, gameList);
        listView.setAdapter(adapter);
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
                    gameList.add(0, newGame);
                    scoreEntry.setText("");
                    levelEntry.setText("");
                    dateEntry.setText("");
                    dataBase.addGame(newGame);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item) {
                    case "Date":
                        Collections.sort(gameList, new GameStat.GameDateCompare());
                        adapter.notifyDataSetChanged();
                        break;
                    case "Score":
                        Collections.sort(gameList, new GameStat.GameScoreCompare());
                        adapter.notifyDataSetChanged();
                        break;
                    case "Level":
                        Collections.sort(gameList, new GameStat.GameLevelCompare());
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
