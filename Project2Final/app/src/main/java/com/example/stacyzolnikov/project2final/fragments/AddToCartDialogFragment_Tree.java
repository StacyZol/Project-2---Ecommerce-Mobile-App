package com.example.stacyzolnikov.project2final.fragments;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.objects.CartObject;
import com.example.stacyzolnikov.project2final.objects.Tree;
import com.example.stacyzolnikov.project2final.recycler.RecyclerViewTreeAdapter;
import com.example.stacyzolnikov.project2final.setup.DatabaseHelper;
import com.example.stacyzolnikov.project2final.setup.NurseryManager;
import com.example.stacyzolnikov.project2final.setup.ShoppingCartSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 8/21/16.
 */
public class AddToCartDialogFragment_Tree extends DialogFragment {
    public Button addButton;
    public Button cancelButton;
    public ImageView itemPhoto;
    List<Tree> trees;
    TextView treeDescription;
    int position;
    private static final String TAG = "AddToCartDialogTree";
    public AddToCartDialogFragment_Tree(){

    }

    public AddToCartDialogFragment_Tree(List<Tree> trees, int position) {
        this.trees = trees;
        this.position = position;
    }
    public static AddToCartDialogFragment_Tree newInstance (String title){
        AddToCartDialogFragment_Tree fragment = new AddToCartDialogFragment_Tree();
        Bundle args = new Bundle();
        args.putString("Add To Cart", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_to_cart, container);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Same as the flower & herb fragments
        super.onViewCreated(view, savedInstanceState);
        itemPhoto = (ImageView) view.findViewById(R.id.ItemPhotosID);
        addButton = (Button) view.findViewById(R.id.add_button);
        cancelButton = (Button) view.findViewById(R.id.cancel_button);
        treeDescription = (TextView) view.findViewById(R.id.itemDescription);
        treeDescription.setText(trees.get(position).getTreeDescription());
        setHasOptionsMenu(true);

        final ShoppingCartSingleton shoppingCartSingleton = ShoppingCartSingleton.getInstance();

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                shoppingCartSingleton.addCartObject(new CartObject(trees.get(position).getTreeName(), trees.get(position).getTreePrice(), trees.get(position).getTreePhoto()));
                getDialog().dismiss();
                Toast.makeText(getContext(),"Added to Cart", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: " + trees.get(position).getTreePrice());

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

    }

    public interface OnItemListener {
        public void onAddItem(String itemAdded);
    }


}
