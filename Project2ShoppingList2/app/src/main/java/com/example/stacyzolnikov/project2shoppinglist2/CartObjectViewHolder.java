package com.example.stacyzolnikov.project2shoppinglist2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by stacyzolnikov on 7/28/16.
 */
public class  CartObjectViewHolder extends RecyclerView.ViewHolder {

    public TextView mShirtName;
    public TextView mPrice;
    public TextView mShirtPhotos;
    public CardView mCardView;
    ImageView mChangeQuantity;
    TextView mItemQuantity;
    TextView mItemTotal;


    public CartObjectViewHolder(View itemView) {
        super(itemView);
        mShirtName = (TextView) itemView.findViewById(R.id.ItemNameID);
        mPrice = (TextView) itemView.findViewById(R.id.cartPerUnitPrice);
        mCardView = (CardView) itemView.findViewById(R.id.CustomCartItemView);
        mChangeQuantity = (ImageView) itemView.findViewById(R.id.cartItemsPlus_Minus);
        mItemQuantity = (TextView) itemView.findViewById(R.id.cartItemQuantity);
        mItemTotal = (TextView) itemView.findViewById(R.id.cartTotalPrice);
      //  mShirtName = (TextView) itemView.findViewById(R.id.ItemPhotosID)
    }
    
}
