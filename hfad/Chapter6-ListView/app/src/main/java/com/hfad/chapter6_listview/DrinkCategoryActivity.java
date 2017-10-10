package com.hfad.chapter6_listview;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends ListActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setListViewBehaviour();
  }

  @Override protected void onListItemClick(ListView l, View v, int position, long id) {
    Intent intent = new Intent(this, DrinkActivity.class);
    intent.putExtra(DrinkActivity.EXTRA_DRINKNO,(int)id);
    startActivity(intent);
  }

  private void setListViewBehaviour() {
    ListView listDrinks = getListView();
    ArrayAdapter<Drink> adapter = new ArrayAdapter<Drink>(
        this,
        android.R.layout.simple_list_item_1,
        Drink.drinks
    );
    listDrinks.setAdapter(adapter);
  }
}
