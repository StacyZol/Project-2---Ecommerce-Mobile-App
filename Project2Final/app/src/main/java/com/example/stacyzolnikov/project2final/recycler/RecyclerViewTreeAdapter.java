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
import com.example.stacyzolnikov.project2final.fragments.AddToCartDialogFragment_Tree;
import com.example.stacyzolnikov.project2final.objects.FavoritesObject;
import com.example.stacyzolnikov.project2final.objects.Tree;
import com.example.stacyzolnikov.project2final.setup.FavoritesSingleton;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/21/16.
 */
public class RecyclerViewTreeAdapter extends RecyclerView.Adapter<TreesViewHolder> {
    List<Tree> trees;
    Context mContext;
    int mItemXML;
    private static final String TAG = "RecyclerViewTreeAdapter";

    public RecyclerViewTreeAdapter(List<Tree> trees, int itemLayout, Context context) {
        this.trees = trees;
        this.mItemXML = itemLayout;
        this.mContext = context;
    }
    public RecyclerViewTreeAdapter(List<Tree> trees, Context context) {
        this.trees = trees;
        this.mContext = context;
    }

    @Override
    public TreesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_view_items, parent, false);
        TreesViewHolder viewHolder = new TreesViewHolder(parentView);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TreesViewHolder holder, final int position) {
        final Tree tree = trees.get(position);
        holder.mTreeName.setText(trees.get(position).getTreeName());
        holder.mDescription.setText(trees.get(position).getTreeDescription());
        holder.mPrice.setText("$" + trees.get(position).getTreePrice());
       // int imageResource = mContext.getResources().getIdentifier(trees.get(position).getHeart().replace(".png", ""), "drawable", mContext.getPackageName());
       // holder.mHeart.setImageResource(imageResource);
        int imageResource2 = mContext.getResources().getIdentifier(trees.get(position).getTreePhoto().replace(".png", ""), "drawable", mContext.getPackageName());
        holder.mPhoto.setImageResource(imageResource2);
        holder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                AddToCartDialogFragment_Tree addToCart = new AddToCartDialogFragment_Tree(trees, position);
                Log.i(TAG, "onClick: " + trees.get(position).getTreeName() + trees.get(position).getTreePhoto());
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
                favoritesSingleton.addFavoritesObject(new FavoritesObject(trees.get(position).getTreeName(), trees.get(position).getTreeDescription(), trees.get(position).getTreePrice(), trees.get(position).getTreePhoto()));
                Log.i(TAG, "onClick: photo: " + trees.get(position).getTreePhoto());
                holder.mAddedHeart.setVisibility(View.VISIBLE);
                holder.mHeart.setEnabled(false);
                holder.mHeart.setVisibility(View.GONE);
                Log.i(TAG, "Test");
            }
        });
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
