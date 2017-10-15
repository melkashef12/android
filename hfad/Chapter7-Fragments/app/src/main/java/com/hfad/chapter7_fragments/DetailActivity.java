package com.hfad.chapter7_fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DetailActivity extends Activity {

  public static final String EXTRA_WORKOUT_ID = "workoutId";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    WorkoutDetailFragment workoutDetailFragment = (WorkoutDetailFragment) getFragmentManager().findFragmentById(R.id.detail_frag);
    long workoutId = getIntent().getExtras().getLong(EXTRA_WORKOUT_ID);
    workoutDetailFragment.setWorkoukId(workoutId);
  }
}
