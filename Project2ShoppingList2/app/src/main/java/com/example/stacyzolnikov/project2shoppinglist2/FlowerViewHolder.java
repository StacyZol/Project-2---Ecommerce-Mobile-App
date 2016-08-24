package com.example.stacyzolnikov.project2shoppinglist2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by stacyzolnikov on 8/18/16.
 */
public class FlowerViewHolder extends RecyclerView.ViewHolder {
    public TextView mFlowerName;
    public ImageView mFlowerHeart;
    public TextView mFlowerPrice;
    public TextView mFlowerDescription;
    public ImageView mFlowerPhotos;
    public CardView mFlowerCardView;
    public Button mFlowerAddButton;
    public ImageView mFlowerAddedHeart;


    public FlowerViewHolder(View itemView) {
        super(itemView);
        mFlowerName = (TextView) itemView.findViewById(R.id.FlowerNameID);
        mFlowerPrice = (TextView) itemView.findViewById(R.id.FlowerPriceID);
        mFlowerHeart = (ImageView) itemView.findViewById(R.id.FlowerHeartID);
        mFlowerDescription = (TextView) itemView.findViewById(R.id.FlowerDescriptionsID);
        mFlowerAddButton = (Button) itemView.findViewById(R.id.FlowerQuickAdd);
        mFlowerAddedHeart = (ImageView) itemView.findViewById(R.id.FlowerHeartIDFaded);
        mFlowerCardView = (CardView) itemView.findViewById(R.id.CustomFlowerView);


    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        itemView.setOnClickListener(onClickListener);
    }
}
