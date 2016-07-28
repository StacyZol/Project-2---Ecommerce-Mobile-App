package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by stacyzolnikov on 7/26/16.
 */
public class RecyclerViewClothesAdapter extends RecyclerView.Adapter<ClothesViewHolder> {
    List<Shirt> shirts;
    Context mContext;
    int mItemXML;


    public RecyclerViewClothesAdapter() {

    }


    public RecyclerViewClothesAdapter(List<Shirt> shirts, int itemLayout) {
        this.shirts = shirts;
        this.mItemXML = itemLayout;
    }
    public RecyclerViewClothesAdapter(List<Shirt> shirts, Context context) {
        this.shirts = shirts;
        this.mContext = context;
    }

    @Override
    public ClothesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_items_view, parent, false);
        ClothesViewHolder viewHolder = new ClothesViewHolder(parentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClothesViewHolder holder, int position) {
        Shirt shirt = shirts.get(position);
        holder.mShirtName.setText(shirts.get(position).getShirtName());
        holder.mPrice.setText(shirts.get(position).getPrice());
//
        int imageResource =mContext.getResources().getIdentifier(shirts.get(position).getHeart().replace(".png", ""), "drawable", mContext.getPackageName());
        holder.mHeart.setImageResource(imageResource);
        int imageResource2 = mContext.getResources().getIdentifier(shirts.get(position).getShirtPhotosID().replace(".png", ""), "drawable", mContext.getPackageName());
        holder.mShirtPhotos.setImageResource(imageResource2);
    }


    @Override
    public int getItemCount() {
        if (shirts == null) {
            return 0;
        } else {
            return shirts.size();

        }
    }
}
