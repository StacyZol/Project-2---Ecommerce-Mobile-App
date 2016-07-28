package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by stacyzolnikov on 7/20/16.
 */
public class RecyclerViewStoreAdapter extends RecyclerView.Adapter<StoreViewHolder> {
    List<Store> stores;
    Context context;


    public RecyclerViewStoreAdapter(List<Store> stores, Context context) {
        this.stores = stores;
        this.context = context;
    }

    public RecyclerViewStoreAdapter() {
    }


    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_store_view, parent, false);
        StoreViewHolder viewHolder = new StoreViewHolder(parentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, final int position) {
        holder.mStoreName.setText(stores.get(position).getStoreName());
        holder.mReviews.setText(stores.get(position).getReviews());
        int imageResource2 = context.getResources().getIdentifier(stores.get(position).getStars().replace(".png", ""), "drawable", context.getPackageName());
        holder.mStars.setImageResource(imageResource2);
        int imageResource = context.getResources().getIdentifier(stores.get(position).getPhotos().replace(".png", ""), "drawable", context.getPackageName());
        holder.mPhotos.setImageResource(imageResource);

        // holder.mReviews.setOnClickListener
        //Above will pull up an AlertDialog for reviews

        holder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClothesActivity.class);
                intent.putExtra("position", position);
                view.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        if (stores == null) {
            return 0;
        } else {
            return stores.size();
        }
    }
}
