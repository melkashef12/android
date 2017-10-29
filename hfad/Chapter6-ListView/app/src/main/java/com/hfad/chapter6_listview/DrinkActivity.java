package com.hfad.chapter6_listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends Activity {

  public static final String EXTRA_DRINKNO = "drinkno";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_drink);

    int drinkno = getIntent().getExtras().getInt(EXTRA_DRINKNO);
    Drink drink = Drink.drinks[drinkno];

    ImageView photo = findViewById(R.id.photo);
    photo.setImageResource(drink.getImageResourceId());

    TextView name =  findViewById(R.id.name);
    name.setText(drink.getName());

    TextView description =  findViewById(R.id.description);
    description.setText(drink.getDescription());

  }
}
