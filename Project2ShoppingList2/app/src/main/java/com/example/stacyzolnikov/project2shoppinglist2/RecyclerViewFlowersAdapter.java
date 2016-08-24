package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/18/16.
 */
public class RecyclerViewFlowersAdapter extends RecyclerView.Adapter<FlowerViewHolder> {
    List<Flower> flowers;
    Context mContext;
    int mItemXML;


    public RecyclerViewFlowersAdapter(List<Flower> flowers, int itemLayout, Context mContext) {
        this.flowers = flowers;
        this.mContext = mContext;
        this.mItemXML = itemLayout;
    }

    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_flower_view, parent, false);
        FlowerViewHolder viewHolder = new FlowerViewHolder(parentView);
        mContext = parent.getContext();
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final FlowerViewHolder holder, final int position) {
        final Flower flower = flowers.get(position);
        holder.mFlowerName.setText(flowers.get(position).getFlowerName());
        holder.mFlowerPrice.setText("$" + flowers.get(position).getFlowerPrice());
        holder.mFlowerDescription.setText(flowers.get(position).getFlowerDescription());
        int imageResource = mContext.getResources().getIdentifier(flowers.get(position).getFlowerHeart().replace(".png", ""), "drawable", mContext.getPackageName());
        holder.mFlowerHeart.setImageResource(imageResource);
        int imageResource2 = mContext.getResources().getIdentifier(flowers.get(position).getflowerPhotosID().replace(".png", ""), "drawable", mContext.getPackageName());
        holder.mFlowerPhotos.setImageResource(imageResource2);

        //       int imageResource3 = mContext.getResources().getIdentifier(trees.get(position).getStorePhoto().replace(".png", ""),"drawable", mContext.getPackageName());
        //       holder.mItemHeader.setImageResource(imageResource3);

        holder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                //  AddToCartDialogFragment addToCart = new AddToCartDialogFragment(flowers, position);
                //  addToCart.show(fm, "add_to_cart");
            }
        });
        holder.mFlowerAddedHeart.setVisibility(View.INVISIBLE);
        holder.mFlowerHeart.setEnabled(true);
        holder.mFlowerHeart.setVisibility(View.VISIBLE);
        holder.mFlowerHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Added To Favorites", Toast.LENGTH_LONG).show();
                final FavoritesSingleton favoritesSingleton = FavoritesSingleton.getInstance();
                favoritesSingleton.addFavoritesObject(new FavoritesObject(flowers.get(position).getFlowerName(), flowers.get(position).getFlowerDescription(), flowers.get(position).getFlowerPrice()));
                holder.mFlowerAddedHeart.setVisibility(View.VISIBLE);
                holder.mFlowerHeart.setEnabled(false);
                holder.mFlowerHeart.setVisibility(View.GONE);


                // shoppingCartSingleton.addCartObject(new CartObject("Testabcd", "123", "blah"));
                //  Intent intent = new Intent(view.getContext(), FavoritesActivity.class);
                //  intent.putExtra("position", position);
                //  view.getContext().startActivity(intent);

            }

        });
    }

    @Override
    public int getItemCount() {
        if(flowers == null){
            return 0;
        }
     else {
            return flowers.size();
        }
    }
}
