package com.example.stacyzolnikov.project2final.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.objects.CartObject;
import com.example.stacyzolnikov.project2final.objects.Flower;
import com.example.stacyzolnikov.project2final.objects.Tree;
import com.example.stacyzolnikov.project2final.setup.ShoppingCartSingleton;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/22/16.
 */
public class AddToCartDialogFragment_Flower extends DialogFragment {
    public Button addButton;
    public Button cancelButton;
    public ImageView itemPhoto;
    TextView flowerDescription;
    List<Flower> flowers;
    int position;

    private static final String TAG = "AddToCartDialogFlower";

    public AddToCartDialogFragment_Flower(){}

    public AddToCartDialogFragment_Flower(List<Flower> flowers, int position) {
        this.flowers = flowers;
        this.position = position;
    }
    public static AddToCartDialogFragment_Flower newInstance (String title){
        AddToCartDialogFragment_Flower fragment = new AddToCartDialogFragment_Flower();
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
        itemPhoto = (ImageView) view.findViewById(R.id.ItemPhotosID);
        addButton = (Button) view.findViewById(R.id.add_button);
        cancelButton = (Button) view.findViewById(R.id.cancel_button);
        //Getting the description for the flower and setting it in the dialog. User can then add that flower to the cart or cancel.
        flowerDescription = (TextView) view.findViewById(R.id.itemDescription);
        flowerDescription.setText(flowers.get(position).getFlowerDescription());

        final ShoppingCartSingleton shoppingCartSingleton = ShoppingCartSingleton.getInstance();
        //If user adds it to cart, that flower gets thrown in the ShoppingCart Singleton. Ideally would be best to implement a UserCart and throw it in an empty Database table but for now, just using a Singleton
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                shoppingCartSingleton.addCartObject(new CartObject(flowers.get(position).getFlowerName(), flowers.get(position).getFlowerPrice(), flowers.get(position).getFlowerPhoto()));
                getDialog().dismiss();
                Toast.makeText(getContext(),"Added to Cart", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onClick: " + flowers.get(position).getFlowerPrice());

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

    }
    //Method below is not being used, had it to test out features. May revisit again later
    public interface OnItemListener {
        public void onAddItem(String itemAdded);
    }


}

