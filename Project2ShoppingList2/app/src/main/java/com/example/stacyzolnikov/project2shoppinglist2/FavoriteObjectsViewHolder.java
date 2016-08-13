package com.example.stacyzolnikov.project2shoppinglist2;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by stacyzolnikov on 8/11/16.
 */
public class FavoriteObjectsViewHolder extends RecyclerView.ViewHolder{
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
        mAddToCartButton = (Button) itemView.findViewById(R.id.AddToCart);
        mItemPhoto = (ImageView) itemView.findViewById(R.id.ItemPhotosID);
        mItemDescription = (TextView) itemView.findViewById(R.id.DescriptionsID);
    }


}
