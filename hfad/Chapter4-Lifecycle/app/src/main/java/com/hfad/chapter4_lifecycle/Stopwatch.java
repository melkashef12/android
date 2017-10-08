package com.hfad.chapter4_lifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Stopwatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
    }
}
