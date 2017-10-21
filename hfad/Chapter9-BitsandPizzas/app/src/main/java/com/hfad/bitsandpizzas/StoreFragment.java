package com.hfad.bitsandpizzas;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class StoreFragment extends ListFragment {

  public StoreFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    ArrayAdapter<String> adapter = initAdapter(inflater);
    setListAdapter(adapter);
    return view;
  }

  @NonNull private ArrayAdapter<String> initAdapter(LayoutInflater inflater) {
    return new ArrayAdapter<>(
          inflater.getContext(),
          android.R.layout.simple_list_item_1,
          getResources().getStringArray(R.array.stores)
      );
  }
}
