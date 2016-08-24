package com.example.stacyzolnikov.project2final.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.objects.CartObject;
import com.example.stacyzolnikov.project2final.objects.FavoritesObject;
import com.example.stacyzolnikov.project2final.setup.ShoppingCartSingleton;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/21/16.
 */
public class RecyclerViewFavoritesAdapter extends RecyclerView.Adapter<FavoriteObjectsViewHolder> {
    List<FavoritesObject> mFavoritesList;
    FavoritesObject mFavoritesObject;
    Context mContext;
    private static final String TAG = "FavoritesAdapter";

    public RecyclerViewFavoritesAdapter(List<FavoritesObject> favoritesList) {
        mFavoritesList = favoritesList;
    }

    @Override
    public FavoriteObjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_favorites_object_view, parent, false);
        FavoriteObjectsViewHolder viewHolder = new FavoriteObjectsViewHolder(parentView);
       mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavoriteObjectsViewHolder holder, final int position) {
        holder.mItemDescription.setText(mFavoritesList.get(position).getItemDescription());
        holder.mItemName.setText(mFavoritesList.get(position).getItemName());
        holder.mItemPrice.setText(mFavoritesList.get(position).getItemPrice());
        //Getting the image file from the favorites list and setting it to the photo place holder
        Log.i(TAG, "FavoritesAdapter: " + mFavoritesList.get(position).getItemPhoto());
        int imageResource = mContext.getResources().getIdentifier(mFavoritesList.get(position).getItemPhoto().replace(".jpg", ""), "drawable", mContext.getPackageName());
        holder.mItemPhoto.setImageResource(imageResource);
        holder.mRemoveFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFavoritesList.remove(position);
                notifyDataSetChanged();
            }
        });
        //This adds an item from the favorites list to the shopping cart. It does not remove the item from the favorites list but informs user it has been added to the shopping cart via a Toast
        holder.mAddToCartButton.setOnClickListener(new View.OnClickListener() {
            final ShoppingCartSingleton shoppingCartSingleton = ShoppingCartSingleton.getInstance();

            @Override
            public void onClick(View view) {
                shoppingCartSingleton.addCartObject(new CartObject(mFavoritesList.get(position).getItemName(), mFavoritesList.get(position).getItemPrice(), mFavoritesList.get(position).getItemPhoto()));
                Toast.makeText(mContext, "Added to Cart", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mFavoritesList == null) {
            return 0;
        } else {
            return mFavoritesList.size();
        }
    }
}
