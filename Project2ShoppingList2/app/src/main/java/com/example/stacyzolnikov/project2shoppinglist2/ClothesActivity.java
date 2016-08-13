package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.util.List;

public class ClothesActivity extends AppCompatActivity implements  PlaceHolderFragment.OnListItemClickListener, NavigationView.OnNavigationItemSelectedListener {

    List<Shirt> arrayList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_two);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content_container,
                        MainFragment.newInstance(this)).commit();



    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SearchStores:
                return true;
            case R.id.ShoppingCart:
                Intent intent = new Intent(ClothesActivity.this, ShoppingCartActivity2.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//  @Override
//   public void onListItemClicked() {


//   }

   @Override
   public void OnListItemClicked(int tabPosition, int listPosition) {

   }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.draw_home){
            //handle the home action

        }
        else if (id == R.id.draw_cart) {
            //handle cart action
            Intent intent = new Intent (ClothesActivity.this, ShoppingCartActivity2.class);
            startActivity(intent);
        }
        else if (id == R.id.draw_addresses){
            //handle address intent
        }
        else if (id == R.id.draw_favorites){
            //hanlde favorites intent
            Intent intent = new Intent (ClothesActivity.this, FavoritesActivity.class);
            startActivity(intent);

        }
        //add more
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_two);
        drawer.closeDrawer(GravityCompat.START);

        return false;
    }


}
