package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by stacyzolnikov on 7/28/16.
 */
public class RecyclerViewCartAdapter extends RecyclerView.Adapter<CartObjectViewHolder> {
    private Context mContext;
    List<CartObject> cartObjectList1;
    List<ClothesInCart> mClothesItemList;

    public RecyclerViewCartAdapter(List<CartObject> cartObjectList) {
        cartObjectList1 = cartObjectList;

    }
    public RecyclerViewCartAdapter(List<CartObject> cartObjectList, Context context) {
        this.cartObjectList1 = cartObjectList;
        this.mContext = context;
    }


    //  public RecyclerViewCartAdapter(List<CartObject> mClothesItemList, Context context) {
  //      mClothesItemList = cartObjectList1;
  //      mContext = context;
//
  //  }
    public void setCartObjectList1(List<CartObject> cartObjectList1) {
        this.cartObjectList1 = cartObjectList1;
    }

    @Override
    public CartObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_cart_object_view, parent, false);
        CartObjectViewHolder viewHolder = new CartObjectViewHolder(parentView);
        mContext=parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CartObjectViewHolder holder, int position) {
        holder.mShirtName.setText(cartObjectList1.get(position).getShirtName());
        String cost = "$" + cartObjectList1.get(position).getPrice();
        holder.mPrice.setText(cost);
        holder.mChangeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Change Quantity", Toast.LENGTH_LONG).show();
            }
        });

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
