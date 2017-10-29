package com.hfad.chapter6_listview;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.facebook.stetho.Stetho;

import static com.hfad.chapter6_listview.SqlUtils.showException;

public class TopLevelActivity extends Activity {

  private SQLiteDatabase db;
  private Cursor favoriteCursor;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_top_level);
    Stetho.initializeWithDefaults(this);
    setListViewBehaviour();
    setFavoriteListView();
  }

  @Override protected void onRestart() {
    super.onRestart();
    ListView favorites_list = findViewById(R.id.list_favorites);

    try{
      db = openRedeableDb();
      Cursor newCursor = getFavoriteCursor();

      CursorAdapter adapter = (CursorAdapter) favorites_list.getAdapter();
      adapter.changeCursor(newCursor);
      this.favoriteCursor = newCursor;

    } catch (SQLiteException ex) {
      showException(this,ex);
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    favoriteCursor.close();
    db.close();
  }

  private void setListViewBehaviour() {
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(l == 0){
          Intent intent = new Intent(TopLevelActivity.this,DrinkCategoryActivity.class);
          startActivity(intent);
        }
      }
    };

    ListView listView =  findViewById(R.id.list_options);
    listView.setOnItemClickListener(onItemClickListener);
  }

  private void setFavoriteListView() {
    ListView favorites_list = findViewById(R.id.list_favorites);
    try{
      db = openRedeableDb();
      favoriteCursor = getFavoriteCursor();
      setFavoriteCursorAdapter(favoriteCursor,favorites_list);
    } catch (SQLiteException ex) {
      showException(this,ex);
    }

    favorites_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(TopLevelActivity.this,DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINKNO,(int)id);
        startActivity(intent);
      }
    });
  }

  private SQLiteDatabase openRedeableDb(){
    SQLiteOpenHelper helper = new StarbuzzDatabaseHelper(this);
    return helper.getReadableDatabase();
  }

  @NonNull private void setFavoriteCursorAdapter(Cursor favoriteCursor, ListView favorites_list) {
    SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_1,
            favoriteCursor,
            new String[] { "NAME" },
            new int[] { android.R.id.text1 },
            0
        );

    favorites_list.setAdapter(adapter);
  }

  private Cursor getFavoriteCursor(){
    Cursor cursor = db.query(
        "DRINK",
        new String[]{"_id", "NAME"},
        "FAVORITE = ?",
        new String[]{"1"},
        null,null,null
    );
    return cursor;
  }
}
