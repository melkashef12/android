package com.hfad.chapter6_listview;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DrinkCategoryActivity extends ListActivity {

  private SQLiteDatabase db;
  private Cursor cursor;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setListViewBehaviour();
  }

  @Override protected void onListItemClick(ListView l, View v, int position, long id) {
    Intent intent = new Intent(this, DrinkActivity.class);
    intent.putExtra(DrinkActivity.EXTRA_DRINKNO,(int)id);
    startActivity(intent);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    cursor.close();
    db.close();
  }

  private void setListViewBehaviour() {
    ListView listDrinks = getListView();

    try{
      StarbuzzDatabaseHelper helper = new StarbuzzDatabaseHelper(this);
      db = helper.getReadableDatabase();
      cursor = db.query(
          "DRINK",
          new String[]{"_id", "NAME"},
          null,null,null,null,null
      );

      CursorAdapter adapter = new SimpleCursorAdapter(
          this,
          android.R.layout.simple_list_item_1,
          cursor,
          new String[]{"NAME"},
          new int[]{android.R.id.text1},
          0
      );
      listDrinks.setAdapter(adapter);

    } catch(SQLiteException ex){
      showException(ex);
    }


  }

  private void showException(SQLiteException e) {
    Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
    toast.show();
  }
}
