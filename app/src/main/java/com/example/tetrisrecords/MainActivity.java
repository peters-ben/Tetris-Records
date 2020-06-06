package com.example.tetrisrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/*
    Description: This activity is launched whenever the app is launched. This activity is responsible
    for displaying the logo of the app, as well as allowing the user to get to the other activities
    in the app, "addViewActivity" and "StatsActivity".
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Button addViewBtn = findViewById(R.id.AddViewBtn);
        Button statsBtn = findViewById(R.id.StatsBtn);
        addViewBtn.setOnClickListener(new View.OnClickListener() { // if user clicks "Add/View Games" button
            @Override
            public void onClick(View v) {
                Intent addViewIntent = new Intent(getApplicationContext(), addViewActivity.class);
                startActivity(addViewIntent); // launch addViewActivity
            }
        });
        statsBtn.setOnClickListener(new View.OnClickListener() { // if user clicks "Overall Stats" button
            @Override
            public void onClick(View v) {
                Intent statsIntent = new Intent(getApplicationContext(), StatsActivity.class);
                startActivity(statsIntent); // launch StatsActivity
            }
        });
    }
}
