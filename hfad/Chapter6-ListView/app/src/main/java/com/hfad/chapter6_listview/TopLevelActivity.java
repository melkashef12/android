package com.hfad.chapter6_listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.facebook.stetho.Stetho;

public class TopLevelActivity extends Activity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_top_level);
    Stetho.initializeWithDefaults(this);
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
