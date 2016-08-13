package com.example.stacyzolnikov.project2shoppinglist2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

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







    }
}
