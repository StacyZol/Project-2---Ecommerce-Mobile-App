package com.example.stacyzolnikov.project2shoppinglist2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by stacyzolnikov on 7/28/16.
 */
public class RecyclerViewCartAdapter extends RecyclerView.Adapter<CartObjectViewHolder> {
    List<CartObject> cartObjectList1;

    public RecyclerViewCartAdapter(List<CartObject> cartObjectList) {
        cartObjectList1 = cartObjectList;
    }

    @Override
    public CartObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_cart_object_view, parent, false);
        CartObjectViewHolder viewHolder = new CartObjectViewHolder(parentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CartObjectViewHolder holder, int position) {
        holder.mShirtName.setText(cartObjectList1.get(position).getShirtName());
        holder.mPrice.setText(cartObjectList1.get(position).getPrice());
       // holder.mShirtPhotosID.setText(cartObjectList1.get(position).getShirtPhotosID());


    }

    @Override
    public int getItemCount() {
        if (cartObjectList1 == null) {
            return 0;
        } else {
            return cartObjectList1.size();
        }
    }
}
