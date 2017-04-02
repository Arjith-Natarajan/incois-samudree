package com.example.vickssarv.homescreenf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Button btn3 = (Button) findViewById(R.id.button3);
        Button btn4 = (Button) findViewById(R.id.button4);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent puzzleintent = new Intent(GameActivity.this, PuzzleActivity.class);
            startActivity(puzzleintent);}
        });
btn4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent boatzintent = new Intent(GameActivity.this, FishingActivity.class);
        startActivity(boatzintent);
    }
});

    }
}
