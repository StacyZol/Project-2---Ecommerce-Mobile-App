package com.example.stacyzolnikov.project2final.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.objects.Master;
import com.example.stacyzolnikov.project2final.objects.Tree;
import com.example.stacyzolnikov.project2final.recycler.RecyclerViewSearchAdapter;
import com.example.stacyzolnikov.project2final.setup.DatabaseHelper;
import com.example.stacyzolnikov.project2final.setup.NurseryManager;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    RecyclerViewSearchAdapter mAdapter;
    List<Master> masterList;
    DatabaseHelper databaseHelper;
    RecyclerView mRecyclerView;
    ImageView mImageViewBack, mImageViewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);


        databaseHelper = DatabaseHelper.getInstance(SearchActivity.this);
        masterList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearch);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new RecyclerViewSearchAdapter(masterList);
        mRecyclerView.setAdapter(mAdapter);

        handleIntent(getIntent());
        mAdapter.notifyDataSetChanged();
        Log.i(TAG, "SearchActivity1: " );


     mImageViewBack = (ImageView) findViewById(R.id.backButton);
     mImageViewBack.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(SearchActivity.this, ItemsActivity.class);
             startActivity(intent);
         }
     });
        mImageViewCart = (ImageView) findViewById(R.id.cartButton);
        mImageViewCart.setOnClickListener(new View.OnClickListener()   {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = getIntent().getStringExtra(SearchManager.QUERY);
            Log.i(TAG, "handleIntent: search: " + query);
            Log.i(TAG, "handleIntent: Size: " + databaseHelper.getTrees().size());
            //MasterList was created because Trees,Flowers, & Herbs are all separate tables. Because I used placeholder fragments to display the list of the three items, I had to make the search results go to a separate activity and therefore needed to add all the items into one "Master" list so user can search the entire database of items.
            masterList.addAll(DatabaseHelper.getInstance(SearchActivity.this).searchTreeList(query));
            masterList.addAll(DatabaseHelper.getInstance(SearchActivity.this).searchFlowerList(query));
            masterList.addAll(DatabaseHelper.getInstance(SearchActivity.this).searchHerbList(query));
            //Logging for different tests: not important to accomplish search functionality
            Log.i(TAG, "handleIntent: size master: " + masterList.size());
            for (int i = 0; i<masterList.size();i++) {
                Log.i("bah", "handleIntent: " + masterList.get(i).getItemName());
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
