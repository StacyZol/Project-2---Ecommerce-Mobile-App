package com.example.stacyzolnikov.project2shoppinglist2;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v4.view.MenuItemCompat;
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
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class StoreActivity extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        CardView cardview = (CardView) findViewById(R.id.CustomStoreView);

        //This is to add the ToolBar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //This is for the RecyclerView for the stores
        mRecyclerViewStore = (RecyclerView) findViewById(R.id.StoresRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewStore.setLayoutManager(linearLayoutManager);

        databaseHelper = DatabaseHelper.getInstance(StoreActivity.this);
        databaseHelper.addToDatabase();
        arrayList = databaseHelper.getStores();

        //For progress bar and text view and search
        mProgressBar = (ProgressBar) findViewById(R.id.StoreProgressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        mTextView = (TextView) findViewById(R.id.NumberOfStoresText);
        mSearchView = (SearchView) findViewById(R.id.SearchStoresOptions);

        mAdapter = new RecyclerViewStoreAdapter(arrayList, this);
        mRecyclerViewStore.setAdapter(mAdapter);
        //setupSearchView();

        handleIntent(getIntent());

    }




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

        //       MenuItem searchItem =   menu.findItem(R.id.SearchStoresOptions);
              // MenuItem item = (MenuItem) menu.findItem(R.id.SearchStoresOptions).getActionView(item);
               // SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);


                SearchView searchView = (SearchView) menu.findItem(R.id.SearchStoresOptions).getActionView();

               // ComponentName componentName = new ComponentName(this, SearchActivity.class);

                ComponentName componentName = getComponentName();
                SearchableInfo searchableInfo = searchManager.getSearchableInfo(componentName);
                searchView.setSearchableInfo(searchableInfo);

//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
//        searchView.setOnQueryTextListener(this);

       return super.onCreateOptionsMenu(menu);
               // return true;
            }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SearchStores:
                return true;
            case R.id.ShoppingCart:
                Intent intent = new Intent(StoreActivity.this, ShoppingCartActivity2.class);
                startActivity(intent);
                return true;
            case R.id.NavBar:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
            //Below is to add actions to items in the menu


            //  private void setupSearchView() {
//        mSearchView = (SearchView) findViewById(R.id.SearchStores);
//
//        // mSearchView.setIconifiedByDefault(false);
//        mSearchView.setOnQueryTextListener(this);
//        mSearchView.setSubmitButtonEnabled(true);
//        mSearchView.setQueryHint("search for stores");
        }
//    @Override
//    public boolean onQueryTextSubmit(String query) {
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String query) {
//        List<Store> filteredStoreList = filter(arrayList,query);
//        try {
//            mAdapter.animateTo(filteredStoreList);
//            mRecyclerViewStore.scrollToPosition(0);
//
//
//        }
//        catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//    private List<Store> filter(List<Store> stores, String query) {
//        query = query.toLowerCase();
//        final List<Store> filteredStoreList = new ArrayList<>();
//        for(Store store: stores){
//            final String text = store.getStoreName().toLowerCase();
//            if(text.contains(query)) {
//                filteredStoreList.add(store);
//            }
//        }
//        return filteredStoreList;
//    }





