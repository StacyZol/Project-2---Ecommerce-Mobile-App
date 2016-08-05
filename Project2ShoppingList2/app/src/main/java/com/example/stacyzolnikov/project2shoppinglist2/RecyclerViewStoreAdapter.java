package com.example.stacyzolnikov.project2shoppinglist2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 7/20/16.
 */
public class RecyclerViewStoreAdapter extends RecyclerView.Adapter<StoreViewHolder> {
    List<Store> stores, filterList;
    Context context;
     LayoutInflater mInflater;


    public RecyclerViewStoreAdapter(List<Store> stores, Context context) {
        this.stores = stores;
        this.context = context;
        this.filterList = new ArrayList<Store>();
        this.filterList.addAll(this.stores);
        mInflater = LayoutInflater.from(context);
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
        Store store = filterList.get(position);
        holder.mStoreName.setText(stores.get(position).getStoreName());
        holder.mReviews.setText(stores.get(position).getReviews());
        int imageResource2 = context.getResources().getIdentifier(stores.get(position).getStars().replace(".png", ""), "drawable", context.getPackageName());
        holder.mStars.setImageResource(imageResource2);
        int imageResource = context.getResources().getIdentifier(stores.get(position).getPhotos().replace(".png", ""), "drawable", context.getPackageName());
        holder.mPhotos.setImageResource(imageResource);

        // holder.mReviews.setOnClickListener


        //This is for search


        final Store storeNew = stores.get(position);
        holder.bind(storeNew);


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
    public void filter(final String text) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                filterList.clear();
                if(TextUtils.isEmpty(text)){
                    filterList.addAll(stores);
            }
                else {
                    for(Store stores2: stores){
                        if(stores2.storeName.toLowerCase().contains(text.toLowerCase())){
                            filterList.add(stores2);
                        }

                    }
                }
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    public void setStore (List<Store> storesNew) {
        stores = new ArrayList<>(storesNew);
    }

    public Store removeItem(int position) {
        Store store = stores.remove(position);
        notifyItemRemoved(position);
        return store;
    }

    public void addStore (int position, Store storeNew) {
        stores.add(position, storeNew);
        notifyItemInserted(position);
    }

    public void moveItem (int fromPosition, int toPosition) {
        final Store storeNew = stores.remove(fromPosition);
        stores.add(toPosition, storeNew);
        notifyItemMoved(fromPosition, toPosition);

    }

    public void animateTo(List<Store> storeNew) {
        applyAndAnimateRemovals(storeNew);
        applyAndAnimateAdditions(storeNew);
        applyAndAnimateMovedItems(storeNew);
    }

    private void applyAndAnimateRemovals(List<Store> newStores) {
        for (int i = stores.size() - 1; i>=0; i--){
            Store store = stores.get(i);
            if(!newStores.contains(store)){
                removeItem(i);
            }
        }
    }
    private void applyAndAnimateAdditions (List<Store> newStores) {
        for (int i = 0, count = newStores.size(); i<count; i++){
            Store store = stores.get(i);
            if(!stores.contains(store)) {
                addStore(i, store);
            }
        }
    }

    private void applyAndAnimateMovedItems (List<Store> newStores) {
        for (int toPosition = newStores.size() - 1; toPosition >=0; toPosition--){
            Store store = newStores.get(toPosition);
            final int fromPosition = stores.indexOf(store);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

}
