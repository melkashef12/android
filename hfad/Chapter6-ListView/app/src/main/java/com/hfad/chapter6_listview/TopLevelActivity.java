package com.hfad.chapter6_listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TopLevelActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_top_level);
    setListViewBehaviour();
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

    ListView listView = (ListView) findViewById(R.id.list_options);
    listView.setOnItemClickListener(onItemClickListener);
  }


}
