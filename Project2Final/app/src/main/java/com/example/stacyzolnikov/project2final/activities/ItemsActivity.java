package com.example.stacyzolnikov.project2final.activities;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.fragments.MainFragment;
import com.example.stacyzolnikov.project2final.fragments.PlaceHolderFragment;
import com.example.stacyzolnikov.project2final.objects.Flower;
import com.example.stacyzolnikov.project2final.objects.Herb;
import com.example.stacyzolnikov.project2final.objects.Master;
import com.example.stacyzolnikov.project2final.objects.Tree;
import com.example.stacyzolnikov.project2final.recycler.RecyclerViewSearchAdapter;
import com.example.stacyzolnikov.project2final.setup.DatabaseHelper;
import com.example.stacyzolnikov.project2final.setup.NurseryManager;

import java.util.List;


public class ItemsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PlaceHolderFragment.OnListItemClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private static final String TAG = "ItemsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        //Second drawer layout needed because it's a different activity
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_two);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.items_content_container,
                        MainFragment.newInstance(this)).commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflating main menu which has action settings(for drawer), search, and shopping cart
        getMenuInflater().inflate(R.menu.main_menu, menu);
        ComponentName componentName = new ComponentName(this, SearchActivity.class);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.NurserySearch).getActionView();
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(componentName);
        searchView.setSearchableInfo(searchableInfo);
        //Setting queryTextListener but NOT being used at this time. Will want to implement later but because results go to a different activity, it is not needed
        searchView.setOnQueryTextListener(this);
        Log.i(TAG, "Search: ");
        searchView.setQueryHint("Search for items");
        searchView.setOnCloseListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this, "Search was submitted", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onQueryTextSubmit: " + query);
        Intent intent = new Intent(ItemsActivity.this, SearchActivity.class);
        startActivity(intent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // case R.id.SearchStores:
            //    return true;
            case R.id.ShoppingCart:
                Intent intent = new Intent(ItemsActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void OnListItemClicked(int tabPosition, int listPosition) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.draw_home) {
            //handle home intent
        } else if (id == R.id.draw_cart) {
            Intent intent = new Intent(ItemsActivity.this, ShoppingCartActivity.class);
            startActivity(intent);
        } else if (id == R.id.draw_addresses) {
            //handle address intent
        } else if (id == R.id.draw_favorites) {
            Intent intent = new Intent(ItemsActivity.this, FavoritesActivity.class);
            startActivity(intent);

        }
        //add more
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_two);
        drawer.closeDrawer(GravityCompat.START);

        return false;
    }

    @Override
    public boolean onClose() {
        return false;
    }
}

