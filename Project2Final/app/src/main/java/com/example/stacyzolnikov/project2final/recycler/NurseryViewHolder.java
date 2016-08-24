package com.example.stacyzolnikov.project2final.recycler;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.objects.Nursery;

/**
 * Created by stacyzolnikov on 8/20/16.
 */
public class NurseryViewHolder extends RecyclerView.ViewHolder {
    public TextView mNurseryName;
    public ImageView mStars;
    public TextView mReviews;
    public ImageView mPhotos;
    public CardView mCardView;

    public NurseryViewHolder(View itemView) {
        super(itemView);
        mNurseryName = (TextView) itemView.findViewById(R.id.nurseryName);
        mStars = (ImageView) itemView.findViewById(R.id.nurseryStars);
        mReviews = (TextView) itemView.findViewById(R.id.nurseryReviews);
        mPhotos = (ImageView) itemView.findViewById(R.id.nurseryPhoto);
    }

    public void bind(Nursery nursery) {
        mNurseryName.setText(nursery.getNurseryName());
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        itemView.setOnClickListener(onClickListener);
    }


}
