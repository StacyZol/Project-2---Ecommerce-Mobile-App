package com.example.stacyzolnikov.project2final.recycler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.activities.ItemsActivity;
import com.example.stacyzolnikov.project2final.objects.Nursery;
import com.example.stacyzolnikov.project2final.setup.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 8/20/16.
 */
public class RecyclerViewNurseryAdapter extends RecyclerView.Adapter<NurseryViewHolder> {
    List<Nursery> mNurseries, filterList;
    Context context;
    LayoutInflater mInflater;

    public RecyclerViewNurseryAdapter(List<Nursery> nursery, Context context) {
        mNurseries = new ArrayList<>(nursery);
        this.context = context;
        this.filterList = new ArrayList<Nursery>();
        this.filterList.addAll(this.mNurseries);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public NurseryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_view_nursery, parent, false);
        NurseryViewHolder viewHolder = new NurseryViewHolder(parentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NurseryViewHolder holder, int position) {
        Nursery nurseryNew = filterList.get(position);
        holder.mNurseryName.setText(mNurseries.get(position).getNurseryName());
        holder.mReviews.setText(mNurseries.get(position).getNurseryReviews());
        int imageResource = context.getResources().getIdentifier(mNurseries.get(position).getNurseryStars().replace(".jpg", ""), "drawable", context.getPackageName());
        holder.mStars.setImageResource(imageResource);
        int imageResource2 = context.getResources().getIdentifier(mNurseries.get(position).getNurseryPhoto().replace(".jpg", ""), "drawable", context.getPackageName());
        holder.mPhotos.setImageResource(imageResource2);
        holder.bind(nurseryNew);
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ItemsActivity.class);
                intent.putExtra("position", DatabaseHelper.COL_NURSERY_LOGO);
                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mNurseries == null) {
            return 0;
        }
        else {
            return mNurseries.size();
        }
    }
    public Nursery removeItem(int position){
        Nursery nurseryNew = mNurseries.remove(position);
        notifyItemRemoved(position);
        return nurseryNew;
    }
    public void addItem (int position, Nursery nurseryNew) {
        mNurseries.add(position, nurseryNew);
        notifyItemInserted(position);
    }


    public void moveItem (int fromPosition, int toPosition) {
        final Nursery nurseryNew = mNurseries.remove(fromPosition);
        mNurseries.add(toPosition, nurseryNew);
        notifyItemMoved(fromPosition, toPosition);

    }

    public void animateTo(List<Nursery> nurseryNew) {
        applyAndAnimateRemovals(nurseryNew);
        applyAndAnimateAdditions(nurseryNew);
        applyAndAnimateMovedItems(nurseryNew);
    }

    private void applyAndAnimateRemovals(List<Nursery> newNursery) {
        for (int i = mNurseries.size() - 1; i>=0; i--){
            Nursery nurseryNew = mNurseries.get(i);
            if(!newNursery.contains(nurseryNew)){
                removeItem(i);
            }
        }
    }
    private void applyAndAnimateAdditions (List<Nursery> newNursery) {
        for (int i = 0, count = newNursery.size(); i<count; i++){
            Nursery nurseryNew = newNursery.get(i);
            if(!mNurseries.contains(nurseryNew)) {
                addItem(i, nurseryNew);
            }

        }
    }

    private void applyAndAnimateMovedItems (List<Nursery> newNursery) {
        for (int toPosition = newNursery.size() - 1; toPosition >= 0; toPosition--) {
            Nursery storesNew = newNursery.get(toPosition);
            final int fromPosition = mNurseries.indexOf(storesNew);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }


    public void filter(final String text) {
        new Thread(new Runnable() {
            //method to return filtered list based on users search
            //If the query is empty, it adds all Nursery objects to the list. Then, as user types in characters, it searches for any nursery that contains the letters dynamically.
            //the only search methods applied would be for Nursery Name. Would like to incorporate search based on location but have not added to database yet.
            @Override
            public void run() {
                filterList.clear();
                if(TextUtils.isEmpty(text)){
                    filterList.addAll(mNurseries);
                }
                else {
                    for(Nursery nursery2: mNurseries){
                        if(nursery2.nurseryName.toLowerCase().contains(text.toLowerCase())){
                            filterList.add(nursery2);
                        }
                    }
                    filterList.clear();
                    filterList.addAll(mNurseries);
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
