package com.example.stacyzolnikov.project2final.recycler;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.fragments.AddToCartDialogFragment_Herb;
import com.example.stacyzolnikov.project2final.fragments.AddToCartDialogFragment_Tree;
import com.example.stacyzolnikov.project2final.objects.FavoritesObject;
import com.example.stacyzolnikov.project2final.objects.Herb;
import com.example.stacyzolnikov.project2final.objects.Tree;
import com.example.stacyzolnikov.project2final.setup.FavoritesSingleton;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/22/16.
 */
public class RecyclerViewHerbAdapter extends RecyclerView.Adapter<HerbsViewHolder> {
    List<Herb> herbs;
    Context mContext;
    int mItemXML;
    private static final String TAG = "RecyclerViewHerbAdapter";

    public RecyclerViewHerbAdapter(List<Herb> herbs, int itemLayout, Context context) {
        this.herbs = herbs;
        this.mItemXML = itemLayout;
        this.mContext = context;
    }
    public RecyclerViewHerbAdapter(List<Herb> herbs, Context context) {
        this.herbs = herbs;
        this.mContext = context;
    }

    @Override
    public HerbsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_view_items, parent, false);
        HerbsViewHolder viewHolder = new HerbsViewHolder(parentView);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final HerbsViewHolder holder, final int position) {
        final Herb herb = herbs.get(position);
        holder.mHerbName.setText(herbs.get(position).getHerbName());
        holder.mDescription.setText(herbs.get(position).getHerbDescription());
        holder.mPrice.setText("$" + herbs.get(position).getHerbPrice());
        int imageResource2 = mContext.getResources().getIdentifier(herbs.get(position).getHerbPhoto().replace(".jpg", ""), "drawable", mContext.getPackageName());
        Log.i("yo2", imageResource2+"");

        holder.mPhoto.setImageResource(imageResource2);
        holder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                AddToCartDialogFragment_Herb addToCart = new AddToCartDialogFragment_Herb(herbs, position);
                Log.i(TAG, "onClick: " + herbs.get(position).getHerbName());
                addToCart.show(fm, "add_to_cart");

            }
        });
        //Same concept below as the Flower & tree
        holder.mAddedHeart.setVisibility(View.INVISIBLE);
        holder.mHeart.setEnabled(true);
        holder.mHeart.setVisibility(View.VISIBLE);
        holder.mHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Added To Favorites", Toast.LENGTH_LONG).show();

                final FavoritesSingleton favoritesSingleton = FavoritesSingleton.getInstance();
                favoritesSingleton.addFavoritesObject(new FavoritesObject(herbs.get(position).getHerbName(), herbs.get(position).getHerbDescription(), herbs.get(position).getHerbPrice(), herbs.get(position).getHerbPhoto()));
                Log.i(TAG, "onClick: photo: " + herbs.get(position).getHerbPhoto());
                holder.mAddedHeart.setVisibility(View.VISIBLE);
                holder.mHeart.setEnabled(false);
                holder.mHeart.setVisibility(View.GONE);
                Log.i(TAG, "Test");
            }
        });
    }
    @Override
    public int getItemCount() {
        if (herbs == null) {
            return 0;
        } else {
            return herbs.size();
        }
    }
}
