package com.example.stacyzolnikov.project2shoppinglist2;

import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.stacyzolnikov.project2shoppinglist2.R.id.StarsID;

/**
 * Created by stacyzolnikov on 7/20/16.
 */
public class StoreViewHolder extends RecyclerView.ViewHolder{
    public TextView mStoreName;
    public ImageView mStars;
    public TextView mReviews;
    public ImageView mPhotos;
    public CardView mCardView;


    public StoreViewHolder(View itemView) {
        super(itemView);
        mStoreName = (TextView) itemView.findViewById(R.id.StoreNameID);
        mStars = (ImageView) itemView.findViewById(R.id.StarsID);
        mReviews = (TextView) itemView.findViewById(R.id.NumberOfReviewsID);
        mPhotos = (ImageView) itemView.findViewById(R.id.StoreLogoPictureID);
        mCardView = (CardView) itemView.findViewById(R.id.CustomStoreView);
    }



    //Below is to set on the card item listener
    //Still need to create the function
    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);

    }


}
