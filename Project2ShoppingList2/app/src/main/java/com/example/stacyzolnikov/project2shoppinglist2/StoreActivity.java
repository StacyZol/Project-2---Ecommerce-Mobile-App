package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;


import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class StoreActivity extends AppCompatActivity {
    RecyclerView mRecyclerViewStore;
    List<Store> arrayList;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        CardView cardview = (CardView) findViewById(R.id.CustomStoreView);

        //This is to add the ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //This is for the RecyclerView for the stores
          mRecyclerViewStore = (RecyclerView) findViewById(R.id.StoresRecyclerView);
          LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
          mRecyclerViewStore.setLayoutManager(linearLayoutManager);

        databaseHelper = DatabaseHelper.getInstance(StoreActivity.this);
        databaseHelper.addToDatabase();
        arrayList = databaseHelper.getStores();


        RecyclerViewStoreAdapter adapter = new RecyclerViewStoreAdapter(arrayList,this);
        mRecyclerViewStore.setAdapter(adapter);
    }

    //Below is to inflate the tool bar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Below is to add actions to items in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SearchStores:
                return true;
            case R.id.ShoppingCart:
                return true;
            case R.id.NavBar:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }




}
