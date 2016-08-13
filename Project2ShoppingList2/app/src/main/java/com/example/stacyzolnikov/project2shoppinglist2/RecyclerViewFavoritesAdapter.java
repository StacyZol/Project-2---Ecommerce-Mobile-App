package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by stacyzolnikov on 8/11/16.
 */
public class RecyclerViewFavoritesAdapter extends RecyclerView.Adapter<FavoriteObjectsViewHolder> {
    private Context mContext;
    List<FavoritesObject> mFavoritesList;
    FavoritesObject mFavoritesObject;

    public RecyclerViewFavoritesAdapter(List<FavoritesObject> favoritesList) {
        mFavoritesList = favoritesList;
    }
    public RecyclerViewFavoritesAdapter(FavoritesObject item){
        mFavoritesObject = item;
    }
    public RecyclerViewFavoritesAdapter(){

    }

    public void setmFavoritesObject(FavoritesObject mFavoritesObject) {
        this.mFavoritesObject = mFavoritesObject;
    }

    public void setmFavoritesList(List<FavoritesObject> mFavoritesList) {
        this.mFavoritesList = mFavoritesList;
    }

    @Override
    public FavoriteObjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_favorites_object, parent, false);
        FavoriteObjectsViewHolder viewHolder = new FavoriteObjectsViewHolder(parentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteObjectsViewHolder holder, int position) {
        holder.mItemDescription.setText(mFavoritesList.get(position).getItemDescription());
    holder.mItemName.setText(mFavoritesList.get(position).getItemName());
    }

    @Override
    public int getItemCount() {
        if(mFavoritesList == null) {
            return 0;
        } else {
            return mFavoritesList.size();
        }
    }


}
