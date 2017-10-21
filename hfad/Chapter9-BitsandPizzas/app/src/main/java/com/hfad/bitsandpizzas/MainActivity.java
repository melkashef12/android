package com.hfad.bitsandpizzas;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;

public class MainActivity extends Activity {

  private ShareActionProvider shareActionProvider;
  private DrawerLayout drawerLayout;
  private String[] titles;
  private ListView drawerList;
  private ActionBarDrawerToggle drawerToggle;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    this.titles = getResources().getStringArray(R.array.titles);
    this.drawerList = findViewById(R.id.drawer);
    this.drawerLayout = findViewById(R.id.drawer_layout);
    //Init and set adapter for the drawer
    ArrayAdapter<String> drawerAdapter = initDrawerAdapter();
    drawerList.setAdapter(drawerAdapter);
    //On Click
    drawerList.setOnItemClickListener(new DrawerItemClickListener());
    if(savedInstanceState == null){
      selectItem(0);
    }
    //Drawer Listner
    this.drawerToggle = initActionBarDrawableToggle();
    drawerLayout.addDrawerListener(drawerToggle);
    //Enable the drawer to open and close
    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setHomeButtonEnabled(true);
  }

  @NonNull private ArrayAdapter<String> initDrawerAdapter() {
    return new ArrayAdapter<>(this,android.R.layout.simple_list_item_activated_1,titles);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main,menu);
    MenuItem menuItem = menu.findItem(R.id.action_share);
    this.shareActionProvider = (ShareActionProvider) menuItem.getActionProvider();
    setIntent("This is a example text");
    return super.onCreateOptionsMenu(menu);
  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    drawerToggle.syncState();
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    drawerToggle.onConfigurationChanged(newConfig);
  }

  private void setIntent(String text) {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_TEXT,text);
    shareActionProvider.setShareIntent(intent);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    //Handle ActionBarDrawerToggle being clicked
    if(drawerToggle.onOptionsItemSelected(item)){
      return true;
    }

    switch (item.getItemId()){
      case R.id.action_create_order :
        Intent intent = new Intent(this,OrderActivity.class);
        startActivity(intent);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    boolean drawerOpen = drawerLayout.isDrawerOpen(drawerList);
    menu.findItem(R.id.action_share).setVisible(!drawerOpen);
    return super.onPrepareOptionsMenu(menu);
  }

  private void selectItem(int position) {
    replaceFragment(position);
    setActionBarTitle(position);
    closeDrawer();
  }


  private void replaceFragment(int position) {
    Fragment fragment;
    switch (position){
      case 1 :
        fragment = new PizzaFragment();
        break;
      case 2 :
        fragment = new PastaFragment();
        break;
      case 3 :
        fragment = new StoreFragment();
        break;
      default :
        fragment = new TopFragment();
    }

    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.content_frame,fragment);
    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
    transaction.addToBackStack(null);
    transaction.commit();
  }

  private void setActionBarTitle(int position) {
    String title;
    if(position == 0) {
      title = getResources().getString(R.string.app_name);
    } else {
      title = titles[position];
    }
    getActionBar().setTitle(title);
  }

  private void closeDrawer() {

    drawerLayout.closeDrawer(drawerList);
  }

  private ActionBarDrawerToggle initActionBarDrawableToggle(){
    return new ActionBarDrawerToggle(
        this,
        this.drawerLayout,
        R.string.open_drawer,
        R.string.close_drawer
    ){
      @Override public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        invalidateOptionsMenu();
      }

      @Override public void onDrawerClosed(View drawerView) {
        super.onDrawerClosed(drawerView);
        invalidateOptionsMenu();
      }
    };
  }

  private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
    @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }
  }

}
