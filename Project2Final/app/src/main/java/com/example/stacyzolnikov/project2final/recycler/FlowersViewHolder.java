package com.example.stacyzolnikov.project2final.recycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stacyzolnikov.project2final.R;

/**
 * Created by stacyzolnikov on 8/22/16.
 */
public class FlowersViewHolder extends RecyclerView.ViewHolder {
    public TextView mFlowerName;
    public ImageView mHeart;
    public TextView mPrice;
    public TextView mDescription;
    public ImageView mPhoto;
    public Button mAddButton;
    public ImageView mAddedHeart;
    public CardView mCardView;

    public FlowersViewHolder(View itemView) {
        super(itemView);
        mFlowerName = (TextView) itemView.findViewById(R.id.ItemNameID);
        mHeart = (ImageView) itemView.findViewById(R.id.HeartID);
        mDescription = (TextView) itemView.findViewById(R.id.DescriptionsID);
        mPrice = (TextView) itemView.findViewById(R.id.PriceID);
        mPhoto = (ImageView) itemView.findViewById(R.id.ItemPhotosID);
        mCardView = (CardView) itemView.findViewById(R.id.CustomItemView);
        mAddButton = (Button) itemView.findViewById(R.id.QuickAdd);
        mAddedHeart = (ImageView) itemView.findViewById(R.id.HeartIDFaded);

    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        itemView.setOnClickListener(onClickListener);
    }



}
