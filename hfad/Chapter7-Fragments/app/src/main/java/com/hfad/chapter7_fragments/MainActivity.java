package com.hfad.chapter7_fragments;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setWorkoutId();
  }

  private void setWorkoutId() {
    WorkoutDetailFragment frag = (WorkoutDetailFragment) getFragmentManager().findFragmentById(R.id.details_frag);
    frag.setWorkoukId(1l);
  }
}
