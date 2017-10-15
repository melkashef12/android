package com.hfad.chapter7_fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends Activity implements WorkoutListListener {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override public void itemClicked(long id) {
    if (isLargeLayout()) {
      WorkoutDetailFragment fragment = new WorkoutDetailFragment();
      fragment.setWorkoukId(id);
      FragmentTransaction transaction = getFragmentManager().beginTransaction();
      transaction.replace(R.id.fragment_container, fragment);
      transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
      transaction.addToBackStack(null);
      transaction.commit();
    } else {
      Intent intent = new Intent(this, DetailActivity.class);
      intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID,id);
      startActivity(intent);
    }
  }

  private boolean isLargeLayout(){
    View fragmentContainer = findViewById(R.id.fragment_container);
    return fragmentContainer != null;
  }
}
