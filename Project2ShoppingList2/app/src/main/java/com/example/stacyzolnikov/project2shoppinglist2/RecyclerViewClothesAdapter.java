package com.example.stacyzolnikov.project2shoppinglist2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

/**
 * Created by stacyzolnikov on 7/26/16.
 */
public class RecyclerViewClothesAdapter extends RecyclerView.Adapter<ClothesViewHolder> {
    List<Shirt> shirts;
    Context mContext;
    int mItemXML;
    private static final String TAG = "RecyclerViewClothesAdapter";

    public RecyclerViewClothesAdapter() {

    }


    public RecyclerViewClothesAdapter(List<Shirt> shirts, int itemLayout, Context context) {
        this.shirts = shirts;
        this.mItemXML = itemLayout;
        this.mContext = context;
    }
    public RecyclerViewClothesAdapter(List<Shirt> shirts, Context context) {
        this.shirts = shirts;
        this.mContext = context;
    }

    @Override
    public ClothesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_items_view, parent, false);
        ClothesViewHolder viewHolder = new ClothesViewHolder(parentView);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ClothesViewHolder holder, final int position) {
        final Shirt shirt = shirts.get(position);
        holder.mShirtName.setText(shirts.get(position).getShirtName());
        //int price = shirts.get(position).getPrice();
        //int cost = shirts.get(position).getPrice();
        //  "$" + String.valueOf(shirts.get(position).getPrice());
        //int cost = Integer.parseInt(String.valueOf(shirts.get(position).getPrice()));
        //  holder.mPrice.setText("$ " + price);
        holder.mPrice.setText("$" + shirts.get(position).getPrice());
        int imageResource = mContext.getResources().getIdentifier(shirts.get(position).getHeart().replace(".png", ""), "drawable", mContext.getPackageName());
        holder.mHeart.setImageResource(imageResource);
        int imageResource2 = mContext.getResources().getIdentifier(shirts.get(position).getShirtPhotosID().replace(".png", ""), "drawable", mContext.getPackageName());
        holder.mShirtPhotos.setImageResource(imageResource2);

        //       int imageResource3 = mContext.getResources().getIdentifier(shirts.get(position).getStorePhoto().replace(".png", ""),"drawable", mContext.getPackageName());
        //       holder.mItemHeader.setImageResource(imageResource3);

        holder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                AddToCartDialogFragment addToCart = new AddToCartDialogFragment(shirts, position);
                addToCart.show(fm, "add_to_cart");

            }
        });
        holder.mAddedHeart.setVisibility(View.INVISIBLE);
        holder.mHeart.setEnabled(true);
        holder.mHeart.setVisibility(View.VISIBLE);
        holder.mHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Added To Favorites", Toast.LENGTH_LONG).show();

                final FavoritesSingleton favoritesSingleton = FavoritesSingleton.getInstance();
                favoritesSingleton.addFavoritesObject(new FavoritesObject(shirts.get(position).getShirtName(), shirts.get(position).getShirtName(), shirts.get(position).getPrice()));
                         holder.mAddedHeart.setVisibility(View.VISIBLE);
                         holder.mHeart.setEnabled(false);
                         holder.mHeart.setVisibility(View.GONE);
                         Log.i(TAG, "Test");







//                shoppingCartSingleton.addCartObject(new CartObject("Testabcd", "123", "blah"));
                //  Intent intent = new Intent(view.getContext(), FavoritesActivity.class);
                //  intent.putExtra("position", position);
                //  view.getContext().startActivity(intent);

            }
        });


        //     if (FavoritesSingleton.getInstance().getFavoritesList().size()>0){
        //         holder.mAddedHeart.setVisibility(View.VISIBLE);
        //         holder.mHeart.setEnabled(false);
        //         holder.mHeart.setVisibility(View.VISIBLE);
        //     }
        //     else {
        //         holder.mAddedHeart.setVisibility(View.INVISIBLE);
        //         holder.mHeart.setEnabled(true);
        //         holder.mHeart.setVisibility(View.VISIBLE);
        //     }


        //   if (holder.mHeart.isClickable()){
        //       holder.mAddedHeart.setVisibility(View.VISIBLE);
        //       holder.mHeart.setEnabled(false);
        //       holder.mHeart.setVisibility(View.VISIBLE);
        //   } else {
        //       holder.mAddedHeart.setVisibility(View.INVISIBLE);
        //       holder.mHeart.setEnabled(true);
        //       holder.mHeart.setVisibility(View.VISIBLE);
        //   }
    }


    @Override
    public int getItemCount() {
        if (shirts == null) {
            return 0;
        } else {
            return shirts.size();

        }
    }
}
