package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
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
 * Created by stacyzolnikov on 7/26/16.
 */
public class RecyclerViewClothesAdapter extends RecyclerView.Adapter<TreesViewHolder> {
    List<Tree> trees;
    Context mContext;
    int mItemXML;
    private static final String TAG = "RecyclerViewClothesAdapter";

    public RecyclerViewClothesAdapter() {

    }


    public RecyclerViewClothesAdapter(List<Tree> trees, int itemLayout, Context context) {
        this.trees = trees;
        this.mItemXML = itemLayout;
        this.mContext = context;
    }

    public RecyclerViewClothesAdapter(List<Tree> trees, Context context) {
        this.trees = trees;
        this.mContext = context;
    }

    @Override
    public TreesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_items_view, parent, false);
        TreesViewHolder viewHolder = new TreesViewHolder(parentView);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TreesViewHolder holder, final int position) {
        final Tree tree = trees.get(position);
        holder.mShirtName.setText(trees.get(position).getShirtName());
        //int price = trees.get(position).getPrice();
        //int cost = trees.get(position).getPrice();
        //  "$" + String.valueOf(trees.get(position).getPrice());
        //int cost = Integer.parseInt(String.valueOf(trees.get(position).getPrice()));
        //  holder.mPrice.setText("$ " + price);
        holder.mPrice.setText("$" + trees.get(position).getPrice());
        int imageResource = mContext.getResources().getIdentifier(trees.get(position).getHeart().replace(".png", ""), "drawable", mContext.getPackageName());
        holder.mHeart.setImageResource(imageResource);
        int imageResource2 = mContext.getResources().getIdentifier(trees.get(position).getShirtPhotosID().replace(".png", ""), "drawable", mContext.getPackageName());
        holder.mShirtPhotos.setImageResource(imageResource2);

        //       int imageResource3 = mContext.getResources().getIdentifier(trees.get(position).getStorePhoto().replace(".png", ""),"drawable", mContext.getPackageName());
        //       holder.mItemHeader.setImageResource(imageResource3);

        holder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                AddToCartDialogFragment addToCart = new AddToCartDialogFragment(trees, position);
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
                favoritesSingleton.addFavoritesObject(new FavoritesObject(trees.get(position).getShirtName(), trees.get(position).getShirtName(), trees.get(position).getPrice()));
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
        if (trees == null) {
            return 0;
        } else {
            return trees.size();

        }
    }
}
