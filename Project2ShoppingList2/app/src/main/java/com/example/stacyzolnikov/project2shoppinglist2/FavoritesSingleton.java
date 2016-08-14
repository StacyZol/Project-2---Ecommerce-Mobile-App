package com.example.stacyzolnikov.project2shoppinglist2;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 8/11/16.
 */
public class FavoritesSingleton {
    private static final String TAG = "FavoritesSingleton";
    List<FavoritesObject> mFavoritesList;

    private FavoritesSingleton() {
        if (mFavoritesList == null) {
            mFavoritesList = new ArrayList<>();
        }
    }

    public static FavoritesSingleton favoritesSingleton;

    public static FavoritesSingleton getInstance() {
        if (favoritesSingleton == null) {
            favoritesSingleton = new FavoritesSingleton();
        }
        return favoritesSingleton;
    }

    public List<FavoritesObject> getFavoritesList() {
        return mFavoritesList;
    }
    public int getFavoritesCount() {
        int count = 0;
        for (FavoritesObject favoritesObject:mFavoritesList){
                count = favoritesObject.getNumInFavoritesList();
            Log.i(TAG, "getFavoritesCount: " + count);

        }
        return count;
    }

    public void setCustomFavoritesList(List<FavoritesObject> customFavoritesList) {
        this.mFavoritesList = customFavoritesList;
    }

    public void addFavoritesObject (FavoritesObject object) {
        mFavoritesList.add(object);
    }

}
