package com.example.gameulstu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class GameLayout extends AppCompatActivity {

    public static LinkedList<String> word = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawViews(this));
        word.add("кнопка");
        word.add("электрон");
        word.add("два");
        word.add("четыре");
        word.add("четыре");
        Words.word.addAll(word);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        finish();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }
}