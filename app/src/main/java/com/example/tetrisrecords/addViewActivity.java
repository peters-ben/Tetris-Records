package com.example.tetrisrecords;

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

import com.vicmikhailau.maskededittext.MaskedFormatter;
import com.vicmikhailau.maskededittext.MaskedWatcher;

import java.util.ArrayList;
import java.util.Collections;

/*
    Description: This activity is launched whenever the user clicks the "Add/View Games" button on
    the main activity. This activity is intended to allow the user to enter game scores, view
    all previously entered scores, sort how previously entered scores are display, and edit or delete
    individual scores.
 */

public class addViewActivity extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DataBase dataBase = new DataBase(addViewActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // sets activity to Full Screen
        setContentView(R.layout.activity_add_view);
        final Button submitBtn = findViewById(R.id.submitBtn);
        final ArrayList<GameStat> gameList = dataBase.getAll();
        Spinner sortSpinner = findViewById(R.id.sortSpinner);
        ArrayAdapter<String> spinnerAdapter =new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sort)); // dropdown menu for sort
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(spinnerAdapter);
        final EditText scoreEntry = findViewById(R.id.scoreEntry);
        final EditText dateEntry = findViewById(R.id.dateEntry);
        final EditText levelEntry = findViewById(R.id.levelEntry);
        listView = findViewById(R.id.listView);
        adapter = new CustomAdapter(this, gameList);
        listView.setAdapter(adapter); // set adapter that the view is using to a custom one
        submitBtn.setOnClickListener(new View.OnClickListener() { // if user clicks submit button
            @Override
            public void onClick(View v) {
                GameStat newGame = new GameStat();
                if(!scoreEntry.getText().toString().equals("")) { // if user did enter a score
                    newGame.setScore(Integer.parseInt(scoreEntry.getText().toString()));
                    newGame.setDate(dateEntry.getText().toString());
                    if(levelEntry.getText().toString().equals("")) // if user didn't enter a level
                        newGame.setLevel(0);
                    else
                        newGame.setLevel(Integer.parseInt(levelEntry.getText().toString()));
                    gameList.add(0, newGame);
                    scoreEntry.setText(""); // reset text fields to empty
                    levelEntry.setText("");
                    dateEntry.setText("");
                    dataBase.addGame(newGame);
                    adapter.notifyDataSetChanged(); // update adapter
                }
            }
        });
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch (item) {
                    case "Date":
                        Collections.sort(gameList, new GameStat.GameDateCompare()); // sort by date, oldest at top of list
                        adapter.notifyDataSetChanged();
                        break;
                    case "Score":
                        Collections.sort(gameList, new GameStat.GameScoreCompare()); // sort by score, highest at top of list
                        adapter.notifyDataSetChanged();
                        break;
                    case "Level":
                        Collections.sort(gameList, new GameStat.GameLevelCompare()); // sort by level, highest level at top (sorts by score if levels are same)
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { // if user didn't select anything
            }
        });
    }
}
