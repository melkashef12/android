package com.hfad.chapter6_listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDatabaseHelper extends SQLiteOpenHelper {

  private static final String DB_NAME = "starbuzz";
  private static final int DB_VERSION  = 2;

  public StarbuzzDatabaseHelper(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    updateMyDatabase(db, 0, DB_VERSION);

  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    updateMyDatabase(db, oldVersion, newVersion);
  }

  private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
    if (oldVersion < 1) {
      createTable(db);
      populateTable(db);
    }

    if(oldVersion < 2){
      addFavoriteColumnToDrinkTable(db);
    }
  }

  private void createTable(SQLiteDatabase db) {
    db.execSQL(buildCreateStatement());
  }

  private String buildCreateStatement() {
    return "CREATE TABLE DRINK("
        + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "NAME TEXT, "
        + "DESCRIPTION TEXT, "
        + "IMAGE_RESOURCE_ID INTEGER)";
  }

  private void populateTable(SQLiteDatabase db) {
    insertDrink(db,"Latte","A couple of espresso shots with steamed milk", R.drawable.latte);
    insertDrink(db,"Cappuccino","Espresso, hot milk, and a steamed milk foam",R.drawable.cappuccino);
    insertDrink(db,"Filter","Highest quality beans roasted and brewed fresh",R.drawable.filter);
  }

  public void insertDrink(SQLiteDatabase db, String name, String description, int resourceId){
    ContentValues contentValues = new ContentValues();
    contentValues.put("NAME",name);
    contentValues.put("DESCRIPTION",description);
    contentValues.put("IMAGE_RESOURCE_ID",resourceId);
    db.insert("DRINK",null,contentValues);
  }

  private void addFavoriteColumnToDrinkTable(SQLiteDatabase db) {
    db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC");
  }
}
