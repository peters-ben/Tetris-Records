package com.example.tetrisrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Button addViewBtn = findViewById(R.id.AddViewBtn);
        Button statsBtn = findViewById(R.id.StatsBtn);
        addViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addViewIntent = new Intent(getApplicationContext(), addViewActivity.class);
                startActivity(addViewIntent);
            }
        });
        statsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent statsIntent = new Intent(getApplicationContext(), StatsActivity.class);
                startActivity(statsIntent);
            }
        });
    }
}
