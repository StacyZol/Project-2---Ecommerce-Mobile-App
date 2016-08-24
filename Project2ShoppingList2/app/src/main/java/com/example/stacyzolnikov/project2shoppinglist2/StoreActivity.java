package com.example.stacyzolnikov.project2shoppinglist2;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.net.sip.SipAudioCall;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class StoreActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, NavigationView.OnNavigationItemSelectedListener, SearchView.OnCloseListener {
    private static final String TAG = "StoreActivity";

    RecyclerView mRecyclerViewStore;
    List<Store> arrayList;
    DatabaseHelper databaseHelper;
    ProgressBar mProgressBar;
    TextView mTextView;
    AsyncTask<Void, Void, List> task;
    RecyclerViewStoreAdapter mAdapter;
    CursorAdapter mCursorAdapter;
    SearchView mSearchView;
    Toolbar mToolbar;
    String[] filteredList;
    List<Store> mStores;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    private String[] mNavTitles;
    private ListView mDrawerList;
    private ArrayAdapter<String> navAdapter;
    SearchView.OnCloseListener mCloseListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        CardView cardview = (CardView) findViewById(R.id.CustomStoreView);
        filteredList = DatabaseHelper.STORE_COLUMNS;
        //   final String[] STORES = new String[]{
        //           "Macy's", "ZARA"
        //   };
        mStores = new ArrayList<>();
        final Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        // mNavTitles = getResources().getStringArray(R.array.nav_drawer_items);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //  mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        //This is for the RecyclerView for the stores
        mRecyclerViewStore = (RecyclerView) findViewById(R.id.StoresRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewStore.setLayoutManager(linearLayoutManager);

        databaseHelper = DatabaseHelper.getInstance(StoreActivity.this);
        databaseHelper.addToDatabase();
        arrayList = databaseHelper.getStores();


        //For progress bar and text view and search
        mProgressBar = (ProgressBar) findViewById(R.id.StoreProgressBar);
        //will need to make it to visible when add it to tasks
        mProgressBar.setVisibility(View.INVISIBLE);

        mTextView = (TextView) findViewById(R.id.NumberOfStoresText);
        mSearchView = (SearchView) findViewById(R.id.SearchStoresOptions);

        mAdapter = new RecyclerViewStoreAdapter(arrayList, this);
        mRecyclerViewStore.setAdapter(mAdapter);
        //setupSearchView();
        //   mStores = new ArrayList<>();
        //   for (String store : STORES){
        //       mStores.add(new Store(new String[]{store}));
        //   }
        handleIntent(getIntent());


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        SearchView searchView = (SearchView) findViewById(R.id.SearchStores);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else {
            Toast.makeText(StoreActivity.this, "Closed Activity", Toast.LENGTH_SHORT).show();
            finish();


        }
        super.onBackPressed();
    }
    //  if (searchView.)


    //      Adapter searchAdapter = new CursorAdapter(this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER) {


    //     @Override
    //     public View newView(Context context, Cursor cursor, ViewGroup parent) {
    //         return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
    //        // RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(StoreActivity.this);
    //        // mRecyclerViewStore.setLayoutManager(linearLayoutManager);
    //        // return LayoutInflater.from(context).inflate(R.layout.activity_store, parent, false);
//
    //     }
////
    //     @Override
    //     public void bindView(View view, Context context, Cursor cursor) {
    //         CardView cardView = (CardView) view.findViewById(R.id.CustomStoreView);
    //         TextView textView = (TextView) view.findViewById(android.R.id.text1);
    //         RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.StoresRecyclerView);
    //         int store = cursor.getColumnIndex(DatabaseHelper.COL_STORE_NAME);
////
    //         textView.setText(cursor.getString(store));
//

//
    //  }
    //  };

    //       listView.setAdapter(searchAdapter);
    //  }
//

    //  }


    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent: ");
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = getIntent().getStringExtra(SearchManager.QUERY);
            StoreManager.getInstance(StoreActivity.this).updateSearch(query);
            arrayList.clear();
            arrayList.addAll(DatabaseHelper.getInstance(StoreActivity.this).searchStoreList(query));
            mAdapter.notifyDataSetChanged();
            //  List<Store> stores = DatabaseHelper.getInstance(StoreActivity.this).searchStoreList(query);

        }



    }

    //Below is to inflate the tool bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        //Searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.SearchStoresOptions).getActionView();
        // ComponentName componentName = new ComponentName(this, SearchActivity.class);

        ComponentName componentName = getComponentName();
        SearchableInfo searchableInfo = searchManager.getSearchableInfo(componentName);
        searchView.setSearchableInfo(searchableInfo);

//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(false);

        searchView.setQueryHint("Search Here");
        searchView.setOnCloseListener(this);

      // mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      //     @Override
      //     public boolean onQueryTextSubmit(String query) {
      //         Toast.makeText(StoreActivity.this, "Search was submitted", Toast.LENGTH_SHORT).show();
      //         return false;
      //     }

      //     @Override
      //     public boolean onQueryTextChange(String query) {
      //         List<Store> filteredStoreList = filter(arrayList, query);
      //         try {
      //             mAdapter.animateTo(filteredStoreList);
      //             mAdapter.filter(query);
      //             mRecyclerViewStore.scrollToPosition(0);
      //             if (query.isEmpty()) {
      //                // Toast.makeText(this, "No Store found", Toast.LENGTH_SHORT).show();
      //             }
      //             if(filteredStoreList.isEmpty()){
      //                 arrayList.addAll(DatabaseHelper.getInstance(StoreActivity.this).searchStoreList(query));
      //                 mAdapter.notifyDataSetChanged();
      //             }

      //         } catch (NullPointerException e) {
      //             e.printStackTrace();
      //         }
      //        // return true;
      //         return false;
      //     }
      // });




        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                //return true;

            case R.id.SearchStores:
                return true;
            case R.id.ShoppingCart:
                Intent intent = new Intent(StoreActivity.this, ShoppingCartActivity2.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//  @Override
//  public boolean onQueryTextSubmit(String query) {
//     // List<Store> filteredStoreList = filter(arrayList, query);
//     // try {
//     //     mAdapter.animateTo(filteredStoreList);
//     //     mAdapter.filter(query);
//     //     mRecyclerViewStore.scrollToPosition(0);
//     //     if (query.isEmpty()) {
//     //         Toast.makeText(this, "No Store found", Toast.LENGTH_SHORT).show();
//     //     }
//     // } catch (NullPointerException e) {
//     //     e.printStackTrace();
//     // }
//      return true;

//  }


    //Below is to add actions to items in the menu


    //  private void setupSearchView() {
//        mSearchView = (SearchView) findViewById(R.id.SearchStores);
//
//        // mSearchView.setIconifiedByDefault(false);
//        mSearchView.setOnQueryTextListener(this);
//        mSearchView.setSubmitButtonEnabled(true);
//        mSearchView.setQueryHint("search for stores");

  // searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()){

   // }
       @Override
       public boolean onQueryTextSubmit(String query) {
           Toast.makeText(StoreActivity.this, "Search was submitted", Toast.LENGTH_SHORT).show();
           return false;
       }






    @Override
    public boolean onQueryTextChange(String query) {
        List<Store> filteredStoreList = filter(arrayList, query);
        try {
            mAdapter.animateTo(filteredStoreList);
            mAdapter.filter(query);
            mRecyclerViewStore.scrollToPosition(0);
            if (query.isEmpty()) {
                Toast.makeText(this, "No Store found", Toast.LENGTH_SHORT).show();
            }
            if(filteredStoreList.isEmpty()){
                arrayList.addAll(DatabaseHelper.getInstance(StoreActivity.this).searchStoreList(query));
                mAdapter.notifyDataSetChanged();
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return true;
    }


    private List<Store> filter(List<Store> stores, String query) {
        query = query.toLowerCase();
        final List<Store> filteredStoreList = new ArrayList<>();
        for (Store store : stores) {
            final String text = store.getStoreName().toLowerCase();
            if (text.contains(query)) {
                filteredStoreList.add(store);
            }

            //   else if (text.contains(null))
            //   else {
            //       Toast.makeText(StoreActivity.this, "Test2", Toast.LENGTH_SHORT).show();
            //       Log.d(TAG, "TestForNoList");
            //       filteredStoreList.clear();
        }

        return filteredStoreList;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.draw_home) {
            //handle the home action

        } else if (id == R.id.draw_cart) {
            //handle cart action
            Intent intent = new Intent(StoreActivity.this, ShoppingCartActivity2.class);
            startActivity(intent);
        } else if (id == R.id.draw_addresses) {
            //handle address intent
        } else if (id == R.id.draw_favorites) {
            //hanlde favorites intent
            Intent intent = new Intent(StoreActivity.this, FavoritesActivity.class);
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


    //  @Override
    //  public void onBackPressed() {
    //      super.onBackPressed();
    //      StoreManager.getInstance(this).clearCu
    //  }


}


