package com.example.stacyzolnikov.project2final.recycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stacyzolnikov.project2final.R;

/**
 * Created by stacyzolnikov on 8/21/16.
 */
public class FavoriteObjectsViewHolder extends RecyclerView.ViewHolder {
    public TextView mItemName;
    public ImageView mItemPhoto;
    public Button mAddToCartButton;
    public TextView mItemDescription;
    public ImageView mRemoveFavorite;
    public TextView mItemPrice;

    public FavoriteObjectsViewHolder(View itemView) {
        super(itemView);
        mItemName = (TextView) itemView.findViewById(R.id.ItemNameID);
        mItemPrice = (TextView) itemView.findViewById(R.id.PriceID);
        mRemoveFavorite = (ImageView) itemView.findViewById(R.id.RemoveFavoritesID);
        mAddToCartButton = (Button) itemView.findViewById(R.id.QuickAdd);
        mItemPhoto = (ImageView) itemView.findViewById(R.id.ItemPhotosID);
        mItemDescription = (TextView) itemView.findViewById(R.id.DescriptionsID);
    }

}
