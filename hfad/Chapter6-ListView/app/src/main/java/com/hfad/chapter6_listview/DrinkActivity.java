package com.hfad.chapter6_listview;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import static com.hfad.chapter6_listview.SqlUtils.showException;

public class DrinkActivity extends Activity {

  public static final String EXTRA_DRINKNO = "drinkno";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drink);

    int drinkno = getIntent().getExtras().getInt(EXTRA_DRINKNO);
    Drink drink = loadDrink(drinkno);

    ImageView photo = findViewById(R.id.photo);
    photo.setImageResource(drink.getImageResourceId());
    photo.setContentDescription(drink.getName());

    TextView name = findViewById(R.id.name);
    name.setText(drink.getName());

    TextView description = findViewById(R.id.description);
    description.setText(drink.getDescription());

    CheckBox favoriteCheckBox = findViewById(R.id.favorite);
    favoriteCheckBox.setChecked(drink.isFavorite());
  }

  private Drink loadDrink(int drinkno) {
    Drink drink = null;
    try {
      SQLiteDatabase db = openReadableDb();
      Cursor cursor = queryForDrink(drinkno, db);

      if (cursor.moveToFirst()) {
        drink = mapDrink(cursor);
      }

      cursor.close();
      db.close();
    } catch (SQLiteException e) {
      showException(this,e);
    }

    return drink;
  }

  private SQLiteDatabase openReadableDb() {
    StarbuzzDatabaseHelper helper = new StarbuzzDatabaseHelper(this);
    SQLiteDatabase db = helper.getReadableDatabase();
    return db;
  }

  private Cursor queryForDrink(int drinkno, SQLiteDatabase db) {
    return db.query("DRINK",
        new String[] { "NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE" },
        "_id = ?",
        new String[] { Integer.toString(drinkno) }
        , null, null, null);
  }

  private Drink mapDrink(Cursor cursor) {
    Drink drink = new Drink();
    drink.setName(cursor.getString(0));
    drink.setDescription(cursor.getString(1));
    drink.setImageResourceId(cursor.getInt(2));
    drink.setFavorite(cursor.getInt(3) == 1);
    return drink;
  }

  public void onFavoriteClicked(View view) {
    int drinkno = getIntent().getExtras().getInt(EXTRA_DRINKNO);
    CheckBox favorite = findViewById(R.id.favorite);

    ContentValues drinkValues = new ContentValues();
    drinkValues.put("FAVORITE", favorite.isChecked());

    SQLiteOpenHelper helper = new StarbuzzDatabaseHelper(this);
    try {
      SQLiteDatabase db = helper.getWritableDatabase();
      db.update(
          "DRINK",
          drinkValues,
          "_id = ?",
          new String[]{Integer.toString(drinkno)}

      );

      db.close();

    } catch(SQLiteException ex){
      showException(this,ex);
    }
  }
}
