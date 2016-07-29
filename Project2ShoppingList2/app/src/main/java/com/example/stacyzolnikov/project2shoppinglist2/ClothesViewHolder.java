package com.example.stacyzolnikov.project2shoppinglist2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by stacyzolnikov on 7/26/16.
 */
public class ClothesViewHolder extends RecyclerView.ViewHolder {
    public TextView mShirtName;
    public ImageView mHeart;
    public TextView mPrice;
    public TextView mMoreColors;
    public ImageView mShirtPhotos;
    public CardView mCardView;

    public ClothesViewHolder(View itemView) {
        super(itemView);
        mShirtName = (TextView) itemView.findViewById(R.id.ItemNameID);
        mHeart = (ImageView) itemView.findViewById(R.id.HeartID);
        mMoreColors = (TextView) itemView.findViewById(R.id.DescriptionsID);
        mPrice = (TextView) itemView.findViewById(R.id.PriceID);
        mShirtPhotos = (ImageView) itemView.findViewById(R.id.ItemPhotosID);
        mCardView = (CardView) itemView.findViewById(R.id.CustomItemView);
    }


    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
    }
}
