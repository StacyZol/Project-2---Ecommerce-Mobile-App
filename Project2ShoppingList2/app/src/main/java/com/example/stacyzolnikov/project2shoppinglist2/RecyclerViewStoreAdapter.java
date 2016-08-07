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
    List<Store> mStores, filterList;
    Context context;
     LayoutInflater mInflater;


    public RecyclerViewStoreAdapter(List<Store> stores, Context context) {
        //this.mStores = stores;
        mStores = new ArrayList<>(stores);
        this.context = context;
        this.filterList = new ArrayList<Store>();
        this.filterList.addAll(this.mStores);
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
        Store storesNew = filterList.get(position);
        holder.mStoreName.setText(mStores.get(position).getStoreName());
        holder.mReviews.setText(mStores.get(position).getReviews());
        int imageResource2 = context.getResources().getIdentifier(mStores.get(position).getStars().replace(".png", ""), "drawable", context.getPackageName());
        holder.mStars.setImageResource(imageResource2);
        int imageResource = context.getResources().getIdentifier(mStores.get(position).getPhotos().replace(".png", ""), "drawable", context.getPackageName());
        holder.mPhotos.setImageResource(imageResource);

        // holder.mReviews.setOnClickListener


        //This is for search


        //final Store storesNew = mStores.get(position);
        holder.bind(storesNew);


        holder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClothesActivity.class);
                intent.putExtra("position", DatabaseHelper.COL_STORE_LOGO);
                view.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        if (mStores == null) {
            return 0;
        } else {
            return mStores.size();
        }
    }
    public void setStore (List<Store> storesNew) {
        mStores = new ArrayList<>(storesNew);
    }


    public Store removeItem(int position) {
        Store storesNew = mStores.remove(position);
        notifyItemRemoved(position);
        return storesNew;
    }
    public void addItem (int position, Store storesNew) {
        mStores.add(position, storesNew);
        notifyItemInserted(position);
    }


    public void moveItem (int fromPosition, int toPosition) {
        final Store storesNew = mStores.remove(fromPosition);
        mStores.add(toPosition, storesNew);
        notifyItemMoved(fromPosition, toPosition);

    }

    public void animateTo(List<Store> storesNew) {
        applyAndAnimateRemovals(storesNew);
        applyAndAnimateAdditions(storesNew);
        applyAndAnimateMovedItems(storesNew);
    }

    private void applyAndAnimateRemovals(List<Store> newStores) {
        for (int i = mStores.size() - 1; i>=0; i--){
            Store storesNew = mStores.get(i);
            if(!newStores.contains(storesNew)){
                removeItem(i);
            }
        }
    }
    private void applyAndAnimateAdditions (List<Store> newStores) {
       for (int i = 0, count = newStores.size(); i<count; i++){
           Store storesNew = newStores.get(i);
           if(!mStores.contains(storesNew)) {
               addItem(i, storesNew);
           }

        }
    }

    private void applyAndAnimateMovedItems (List<Store> newStores) {
        for (int toPosition = newStores.size() - 1; toPosition >= 0; toPosition--) {
            Store storesNew = newStores.get(toPosition);
            final int fromPosition = mStores.indexOf(storesNew);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }


        public void filter(final String text) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    filterList.clear();
                    if(TextUtils.isEmpty(text)){
                        filterList.addAll(mStores);
                    }
                    else {
                        for(Store stores2: mStores){
                            if(stores2.storeName.toLowerCase().contains(text.toLowerCase())){
                                filterList.add(stores2);
                            }
                        }
                        filterList.clear();
                        filterList.addAll(mStores);
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
    }


