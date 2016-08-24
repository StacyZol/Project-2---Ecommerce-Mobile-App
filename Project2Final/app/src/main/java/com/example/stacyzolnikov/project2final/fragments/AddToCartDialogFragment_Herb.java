package com.example.stacyzolnikov.project2final.fragments;

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
import com.example.stacyzolnikov.project2final.objects.Herb;
import com.example.stacyzolnikov.project2final.objects.Tree;
import com.example.stacyzolnikov.project2final.setup.ShoppingCartSingleton;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/22/16.
 */
public class AddToCartDialogFragment_Herb extends DialogFragment {
    public Button addButton;
    public Button cancelButton;
    public ImageView itemPhoto;
    TextView herbDescription;
    List<Herb> herbs;
    int position;

    private static final String TAG = "AddToCartDialogHerb";
    public AddToCartDialogFragment_Herb(){}

    public AddToCartDialogFragment_Herb(List<Herb> herbs, int position) {
        this.herbs = herbs;
        this.position = position;
    }
    public static AddToCartDialogFragment_Herb newInstance (String title){
        AddToCartDialogFragment_Herb fragment = new AddToCartDialogFragment_Herb();
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
        super.onViewCreated(view, savedInstanceState);
        //This fragment is the same as the Flower fragment. Used to add it to the shopping cart.
        itemPhoto = (ImageView) view.findViewById(R.id.ItemPhotosID);
        addButton = (Button) view.findViewById(R.id.add_button);
        cancelButton = (Button) view.findViewById(R.id.cancel_button);
        herbDescription = (TextView) view.findViewById(R.id.itemDescription);
        herbDescription.setText(herbs.get(position).getHerbDescription());
        final ShoppingCartSingleton shoppingCartSingleton = ShoppingCartSingleton.getInstance();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingCartSingleton.addCartObject(new CartObject(herbs.get(position).getHerbName(), herbs.get(position).getHerbPrice(), herbs.get(position).getHerbPhoto()));
                getDialog().dismiss();
                Toast.makeText(getContext(),"Added to Cart", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: " + herbs.get(position).getHerbPrice());

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
