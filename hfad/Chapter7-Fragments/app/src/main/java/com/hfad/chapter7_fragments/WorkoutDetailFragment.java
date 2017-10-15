package com.hfad.chapter7_fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {

  private long workoukId;

  public WorkoutDetailFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    retrieveWorkoutId(savedInstanceState);
    return inflater.inflate(R.layout.fragment_workout_detail, container, false);
  }


  @Override public void onStart() {
    super.onStart();
    View view = getView();
    if(view != null){
      TextView titleView = view.findViewById(R.id.textTitle);
      titleView.setText(Workout.workouts[(int) workoukId].getName());

      TextView descriptionView = view.findViewById(R.id.textDescription);
      descriptionView.setText(Workout.workouts[(int) workoukId].getDescription());
    }
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putLong("workoutId",workoukId);
  }


  private void retrieveWorkoutId(Bundle savedInstanceState) {
    if(savedInstanceState != null){
      this.workoukId = savedInstanceState.getLong("workoutId");
    }
  }

  public void setWorkoukId(long workoukId) {
    this.workoukId = workoukId;
  }
}
