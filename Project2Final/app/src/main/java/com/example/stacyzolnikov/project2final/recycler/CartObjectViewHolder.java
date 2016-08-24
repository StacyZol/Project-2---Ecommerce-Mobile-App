package com.example.stacyzolnikov.project2final.recycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stacyzolnikov.project2final.R;

/**
 * Created by stacyzolnikov on 8/21/16.
 */
public class CartObjectViewHolder extends RecyclerView.ViewHolder {
    public TextView mItemName;
    public TextView mItemPrice;
    public ImageView mItemPhoto;
    public CardView mCardView;
    ImageView mChangeQuantity;
    TextView mItemQuantity;
    TextView mItemTotal;
    ImageView mItemDelete;


    public CartObjectViewHolder(View itemView) {
        super(itemView);
        mItemName = (TextView) itemView.findViewById(R.id.ItemNameID);
        mItemPrice = (TextView) itemView.findViewById(R.id.cartPerUnitPrice);
        mItemPhoto = (ImageView) itemView.findViewById(R.id.ItemPhotosID);
        mCardView = (CardView) itemView.findViewById(R.id.CustomCartItemView);
        mChangeQuantity = (ImageView) itemView.findViewById(R.id.cartItemsPlus_Minus);
        mItemQuantity = (TextView) itemView.findViewById(R.id.cartItemQuantity);
        mItemTotal = (TextView) itemView.findViewById(R.id.cartTotalPrice);
        mItemDelete = (ImageView) itemView.findViewById(R.id.removeFromCart);

    }

}
