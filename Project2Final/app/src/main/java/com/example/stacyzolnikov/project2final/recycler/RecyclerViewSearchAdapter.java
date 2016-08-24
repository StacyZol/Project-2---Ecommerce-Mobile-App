package com.example.stacyzolnikov.project2final.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.objects.FavoritesObject;
import com.example.stacyzolnikov.project2final.objects.Master;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 8/22/16.
 */
public class RecyclerViewSearchAdapter extends RecyclerView.Adapter<FavoriteObjectsViewHolder> {
    List<Master> mMasterList;
   Master mMasterObject;
    Context mContext;
    private static final String TAG = "SearchAdapter";

    public RecyclerViewSearchAdapter(List<Master> masterList) {
        mMasterList = masterList; //new ArrayList<>(masterList);

    }
    @Override
    public FavoriteObjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_favorites_object_view, parent, false);
        FavoriteObjectsViewHolder viewHolder = new FavoriteObjectsViewHolder(parentView);
        mContext = parent.getContext();
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(FavoriteObjectsViewHolder holder, final int position) {
        //Using the same view holder as the Favorites Object
        //The search results are based on the MasterList which have Trees, Flowers and Herbs. Inserting the three lists into MasterList is handled in DatabaseHelper
        holder.mItemDescription.setText(mMasterList.get(position).getItemDescription());
        holder.mItemName.setText(mMasterList.get(position).getItemName());
        holder.mItemPrice.setText(mMasterList.get(position).getItemPrice());
        int imageResource = mContext.getResources().getIdentifier(mMasterList.get(position).getItemPhoto().replace(".jpg", ""), "drawable", mContext.getPackageName());
        holder.mItemPhoto.setImageResource(imageResource);
        Log.i(TAG, "searchAdapter: " + mMasterList.get(position).getItemPhoto());
        holder.mRemoveFavorite.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                mMasterList.remove(position);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        if(mMasterList == null) {
            return 0;
        } else {
            return mMasterList.size();
        }
    }

}
