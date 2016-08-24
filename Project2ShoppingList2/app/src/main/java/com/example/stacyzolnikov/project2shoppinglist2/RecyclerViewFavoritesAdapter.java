package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;


/**
 * Created by stacyzolnikov on 8/11/16.
 */
public class RecyclerViewFavoritesAdapter extends RecyclerView.Adapter<FavoriteObjectsViewHolder> {

    List<FavoritesObject> mFavoritesList;
    FavoritesObject mFavoritesObject;
    Context mContext;


    OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onRemoveFavorite();
    }

    public void setItemClickListener (OnItemClickListener item) {
        this.mListener = item;
    }

    public RecyclerViewFavoritesAdapter(List<FavoritesObject> favoritesList, Context context, OnItemClickListener listener) {
        this.mFavoritesList = favoritesList;
        this.mListener = listener;
        this.mContext = context;
    }
    public RecyclerViewFavoritesAdapter(List<FavoritesObject> favoritesList) {
        this.mFavoritesList = favoritesList;

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
        mContext=parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteObjectsViewHolder holder, final int position) {
        holder.mItemDescription.setText(mFavoritesList.get(position).getItemDescription());
    holder.mItemName.setText(mFavoritesList.get(position).getItemName());
        holder.mItemPrice.setText(mFavoritesList.get(position).getItemPrice());
        holder.mRemoveFavorite.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mFavoritesList.remove(position);
                notifyDataSetChanged();

            }
        });
    }
   public void removeFavoriteFromList(final FavoritesObject favoritesObject, final int position){

   }
    @Override
    public int getItemCount() {
        if(mFavoritesList == null) {
            return 0;
        } else {
            return mFavoritesList.size();
        }
    }
    public void removeFromFavoritesList(final FavoritesObject favoritesObject, final int position){

    }


}
