package com.hfad.chapter6_listview;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

public class SqlUtils {

  public static void showException(Context context,SQLiteException e) {
    Toast toast = Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT);
    toast.show();
  }

}
