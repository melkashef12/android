package com.hfad.chapter7_fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity implements WorkoutListListener {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override public void itemClicked(long id) {
    WorkoutDetailFragment fragment = new WorkoutDetailFragment();
    fragment.setWorkoukId(id);
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.fragment_container,fragment);
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    transaction.addToBackStack(null);
    transaction.commit();
  }
}
