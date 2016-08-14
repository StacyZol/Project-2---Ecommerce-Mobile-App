package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;


public class FavoritesActivity extends AppCompatActivity {
    ImageView mImageView;
    RecyclerView mRecyclerViewFavorites;
    RecyclerViewFavoritesAdapter mAdapter;
    List<FavoritesObject> favoritesObjectList;


    public FavoritesActivity() {
    }

    public FavoritesActivity(List<Shirt> shirts, int position) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);


        Toolbar toolbar = (Toolbar) findViewById(R.id.favoritesToolbar);
        setSupportActionBar(toolbar);




        FavoritesSingleton favoritesSingleton = FavoritesSingleton.getInstance();
       // int x = getIntent().getExtras().getInt("position");
        //FavoritesObject item = favoritesSingleton.getFavoritesList().get(x);

        mRecyclerViewFavorites = (RecyclerView) findViewById(R.id.recyclerviewFavorites);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewFavorites.setLayoutManager(linearLayoutManager);

        favoritesObjectList = (List<FavoritesObject>) favoritesSingleton.getFavoritesList();

        mAdapter = new RecyclerViewFavoritesAdapter(favoritesSingleton.getFavoritesList());
        //final RecyclerViewFavoritesAdapter mAdapter = new RecyclerViewFavoritesAdapter();
        mRecyclerViewFavorites.setAdapter(mAdapter);
mImageView = (ImageView) findViewById(R.id.BackButton);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavoritesActivity.this, StoreActivity.class);
                startActivity(intent);
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
            case R.id.ShareButton:
                Toast.makeText(this, "Share this", Toast.LENGTH_LONG).show();
               // Intent intent2 = new Intent(FavoritesActivity.this,StoreActivity.class);
               // startActivity(intent2);
                return true;
            case R.id.ShoppingCart:
                Intent intent = new Intent(FavoritesActivity.this, ShoppingCartActivity2.class);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
