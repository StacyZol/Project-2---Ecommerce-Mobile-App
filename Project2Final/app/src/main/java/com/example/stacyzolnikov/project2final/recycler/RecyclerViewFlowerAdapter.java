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
import com.example.stacyzolnikov.project2final.fragments.AddToCartDialogFragment_Flower;
import com.example.stacyzolnikov.project2final.objects.FavoritesObject;
import com.example.stacyzolnikov.project2final.objects.Flower;
import com.example.stacyzolnikov.project2final.setup.FavoritesSingleton;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/22/16.
 */
public class RecyclerViewFlowerAdapter extends RecyclerView.Adapter<FlowersViewHolder> {
    List<Flower> flowers;
    Context mContext;
    int mItemXML;
    private static final String TAG = "RecyclerFlowerAdapter";

    public RecyclerViewFlowerAdapter(List<Flower> flowers, int itemLayout, Context context) {
        this.flowers = flowers;
        this.mItemXML = itemLayout;
        this.mContext = context;
    }
    public RecyclerViewFlowerAdapter(List<Flower> flowers, Context context) {
        this.flowers = flowers;
        this.mContext = context;
    }

    @Override
    public FlowersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_view_items, parent, false);
        FlowersViewHolder viewHolder = new FlowersViewHolder(parentView);
        mContext = parent.getContext();
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final FlowersViewHolder holder, final int position) {
        final Flower flower = flowers.get(position);
        holder.mFlowerName.setText(flowers.get(position).getFlowerName());
        holder.mDescription.setText(flowers.get(position).getFlowerDescription());
        Log.d(TAG, "Flower name: " + flowers.get(position).getFlowerName());
        holder.mPrice.setText("$" + flowers.get(position).getFlowerPrice());
        int imageResource2 = mContext.getResources().getIdentifier(flowers.get(position).getFlowerPhoto().replace(".jpg", ""), "drawable", mContext.getPackageName());

        holder.mPhoto.setImageResource(imageResource2);
        holder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                AddToCartDialogFragment_Flower addToCart = new AddToCartDialogFragment_Flower(flowers, position);
                Log.i(TAG, "onClick: " + flowers.get(position).getFlowerName());
                addToCart.show(fm, "add_to_cart");

            }
        });
        //'mAddedHeart' is set to invisible, it is not displayed until user hits on the 'mHeart' icon which then saves it to the Favorites Singleton.
        //Problem when user removes an item from the favorites list, the 'mAddedHeart' stays visible when the 'mHeart' should isntead. Will fix later
        holder.mAddedHeart.setVisibility(View.INVISIBLE);
        holder.mHeart.setEnabled(true);
        holder.mHeart.setVisibility(View.VISIBLE);
        holder.mHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Added To Favorites", Toast.LENGTH_LONG).show();

                final FavoritesSingleton favoritesSingleton = FavoritesSingleton.getInstance();
                favoritesSingleton.addFavoritesObject(new FavoritesObject(flowers.get(position).getFlowerName(), flowers.get(position).getFlowerDescription(), flowers.get(position).getFlowerPrice(), flowers.get(position).getFlowerPhoto()));
                Log.i(TAG, "onClick: photo: " + flowers.get(position).getFlowerPhoto());
                holder.mAddedHeart.setVisibility(View.VISIBLE);
                holder.mHeart.setEnabled(false);
                holder.mHeart.setVisibility(View.GONE);
                Log.i(TAG, "Test");
            }
        });
    }
    @Override
    public int getItemCount() {
        if (flowers == null) {
            return 0;
        } else {
            return flowers.size();
        }
    }
}
