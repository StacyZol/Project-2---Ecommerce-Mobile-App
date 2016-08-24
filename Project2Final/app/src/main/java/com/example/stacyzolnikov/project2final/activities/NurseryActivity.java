package com.example.stacyzolnikov.project2final.activities;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.objects.Nursery;
import com.example.stacyzolnikov.project2final.recycler.RecyclerViewNurseryAdapter;
import com.example.stacyzolnikov.project2final.setup.DatabaseHelper;
import com.example.stacyzolnikov.project2final.setup.NurseryManager;

import java.util.ArrayList;
import java.util.List;


public class NurseryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    private static final String TAG = "NurseryActivity";
    RecyclerView mRecyclerViewNursery;
    List<Nursery> mNursery;
    List<Nursery> arrayList;
    String[] filteredList;
    RecyclerViewNurseryAdapter mAdapter;
    DatabaseHelper databaseHelper;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursery);

        //Filtered list is for the new list that automatically gets populated as a user is searching for the name
        filteredList = DatabaseHelper.NURSERY_COLUMNS;
        mNursery = new ArrayList<>();
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //This is for the RecyclerView for the listof nurseries
        mRecyclerViewNursery = (RecyclerView) findViewById(R.id.nurseryRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewNursery.setLayoutManager(linearLayoutManager);

        //Adding list of nurseries from the database
        databaseHelper = DatabaseHelper.getInstance(NurseryActivity.this);
        databaseHelper.addNurseries();
        arrayList = databaseHelper.getNurseries();

        //New adapter for the filtered list
        mAdapter = new RecyclerViewNurseryAdapter(arrayList, this);
        mRecyclerViewNursery.setAdapter(mAdapter);

        //Handling the search intent
        handleIntent(getIntent());
    }

    @Override
    public void onBackPressed() {
        //Handling action for when back is pressed
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Toast.makeText(NurseryActivity.this, "Closed Activity", Toast.LENGTH_SHORT).show();
            finish();
        }
        super.onBackPressed();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        //Setting up what happens on a new search intent
        Log.d(TAG, "onNewIntent: ");
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        //Handling the search intent. Adding the filtered search result to the main filtered list
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = getIntent().getStringExtra(SearchManager.QUERY);
            NurseryManager.getInstance(NurseryActivity.this).updateSearch(query);
            arrayList.clear();
            arrayList.addAll(DatabaseHelper.getInstance(NurseryActivity.this).searchNurseryList(query));
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflating the main menu which has the search, action settings for drawer and shopping cart icon
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        //Searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.NurserySearch).getActionView();
        ComponentName componentName = getComponentName();
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(componentName);
        searchView.setSearchableInfo(searchableInfo);
        //setting on Query Text Listener because the method in the adapter relies on it to update the filtered list dynamically
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(false);
        searchView.setQueryHint("Search Here");
        searchView.setOnCloseListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.NurserySearch:
                return true;
            case R.id.ShoppingCart:
                Intent intent = new Intent(NurseryActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this, "Search was submitted", Toast.LENGTH_SHORT).show();
        return false;
    }


    @Override
    public boolean onQueryTextChange(String query) {
        //Filtered list is configured to dynamically update as user searches using the animateTo.
        //filteredNurseryList is automtically populated depending on what is typed in the search query
        //Would like to handle product-related searches here as well but for the time being, product related searches are only allowed on the Items Activity
        List<Nursery> filteredNurseryList = filter(arrayList, query);
        try {
            mAdapter.animateTo(filteredNurseryList);
            mAdapter.filter(query);
            mRecyclerViewNursery.scrollToPosition(0);
            if (query.isEmpty()) {
                //Toast to alert no nursery found when query is empty.
                //Problem: toast happens everytime the search is being configured, need to change this toast to be on textSubmit
                Toast.makeText(this, "No Nursery found", Toast.LENGTH_SHORT).show();
            }
            if (filteredNurseryList.isEmpty()) {
                arrayList.addAll(DatabaseHelper.getInstance(NurseryActivity.this).searchNurseryList(query));
                mAdapter.notifyDataSetChanged();
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return true;
    }


    private List<Nursery> filter(List<Nursery> nurseries, String query) {
        //Making the query go to lowercase
        //Method loops the filteredNurseryList to see if it exists in databaselist
        query = query.toLowerCase();
        final List<Nursery> filteredNurseryList = new ArrayList<>();
        for (Nursery nursery : nurseries) {
            final String text = nursery.getNurseryName().toLowerCase();
            if (text.contains(query)) {
                filteredNurseryList.add(nursery);
            }
        }
        return filteredNurseryList;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.draw_home) {
            //handle the home action
        } else if (id == R.id.draw_cart) {
            //handle cart action
            Intent intent = new Intent(NurseryActivity.this, ShoppingCartActivity.class);
            startActivity(intent);
        } else if (id == R.id.draw_addresses) {
            //handle address intent
        } else if (id == R.id.draw_favorites) {
            Intent intent = new Intent(NurseryActivity.this, FavoritesActivity.class);
            startActivity(intent);

        }
        //add more
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public boolean onClose() {
        // finish();
        return true;
    }
}


