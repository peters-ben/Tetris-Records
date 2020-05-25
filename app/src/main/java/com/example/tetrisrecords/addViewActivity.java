package com.example.tetrisrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_view);
        Button submitBtn = findViewById(R.id.submitBtn);
        final EditText scoreEntry = findViewById(R.id.scoreEntry);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameStat game = new GameStat();
                game.setScore(Integer.parseInt(scoreEntry.getText().toString()));
                scoreEntry.setText("");

            }
        });
    }
}
