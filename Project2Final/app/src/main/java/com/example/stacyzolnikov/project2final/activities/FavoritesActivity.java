package com.example.stacyzolnikov.project2final.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.objects.FavoritesObject;
import com.example.stacyzolnikov.project2final.recycler.RecyclerViewFavoritesAdapter;
import com.example.stacyzolnikov.project2final.setup.FavoritesSingleton;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    ImageView mCartButton, mBackButton;
    RecyclerView mRecyclerViewFavorites;
    RecyclerViewFavoritesAdapter mAdapter;
    List<FavoritesObject> favoritesObjectList;


    public FavoritesActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar toolbar = (Toolbar) findViewById(R.id.favoritesToolbar);
        setSupportActionBar(toolbar);
        FavoritesSingleton favoritesSingleton = FavoritesSingleton.getInstance();


        mRecyclerViewFavorites = (RecyclerView) findViewById(R.id.recyclerviewFavorites);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewFavorites.setLayoutManager(linearLayoutManager);

        favoritesObjectList = (List<FavoritesObject>) favoritesSingleton.getFavoritesList();

        mAdapter = new RecyclerViewFavoritesAdapter(favoritesSingleton.getFavoritesList());
        //final RecyclerViewFavoritesAdapter mAdapter = new RecyclerViewFavoritesAdapter();
        mRecyclerViewFavorites.setAdapter(mAdapter);
        mBackButton = (ImageView) findViewById(R.id.BackButton);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_two, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ShoppingCart:
                Intent intent = new Intent(FavoritesActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
