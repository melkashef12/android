package com.hfad.chapter7_fragments;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class WorkoutListFragment extends ListFragment {

  private WorkoutListListener listener;

  public WorkoutListFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View layout = super.onCreateView(inflater,container,savedInstanceState);
    String[] names = extractNamesFromWorkouts();
    ArrayAdapter<String> adapter = initAdapter(inflater, names);
    setListAdapter(adapter);
    return layout;
  }

  @Override public void onAttach(Context activity) {
    super.onAttach(activity);
    this.listener = (WorkoutListListener) activity;
  }

  @Override public void onListItemClick(ListView l, View v, int position, long id) {
    if(listener != null){
      listener.itemClicked(id);
    }
  }

  @NonNull private ArrayAdapter<String> initAdapter(LayoutInflater inflater, String[] names) {
    return new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, names);
  }

  private String[] extractNamesFromWorkouts() {
    int length = Workout.workouts.length;
    String[] names = new String[length];
    for(int i=0 ; i <length ; i++){
      names[i] = Workout.workouts[i].getName();
    }
    return names;
  }
}
