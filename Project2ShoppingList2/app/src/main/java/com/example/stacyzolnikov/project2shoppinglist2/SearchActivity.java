package com.example.stacyzolnikov.project2shoppinglist2;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       if (Intent.ACTION_SEARCH.equals(getIntent().getAction())){
           String query = getIntent().getStringExtra(SearchManager.QUERY);
           Cursor cursor = (Cursor) DatabaseHelper.getInstance(this).searchStoreList(query);
           Toast.makeText(SearchActivity.this, "Test "+cursor.getCount(), Toast.LENGTH_SHORT).show();
          //  onBackPressed();
       }










    }


}
