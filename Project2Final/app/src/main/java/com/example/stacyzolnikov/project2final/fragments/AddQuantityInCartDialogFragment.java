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
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.objects.CartObject;
import com.example.stacyzolnikov.project2final.objects.Tree;
import com.example.stacyzolnikov.project2final.setup.ShoppingCartSingleton;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/21/16.
 */
public class AddQuantityInCartDialogFragment extends DialogFragment {
    public Button mButtonSave;
    public Button mButtonCancel;
    public NumberPicker mNumberPicker;
    int position;
    List<CartObject> cartObjectList;
    private static final String TAG = "AddQuantityInCart";

    public AddQuantityInCartDialogFragment(Button mButtonSave, Button mButtonCancel, NumberPicker mNumberPicker) {
        this.mButtonSave = mButtonSave;
        this.mButtonCancel = mButtonCancel;
        this.mNumberPicker = mNumberPicker;
    }

    public AddQuantityInCartDialogFragment() {}


    public static AddQuantityInCartDialogFragment newInstance (String title){
        AddQuantityInCartDialogFragment fragment = new AddQuantityInCartDialogFragment();
        Bundle args = new Bundle();
        args.putString("Change Quantity in Cart", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_quantity_in_cart_dialog, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButtonSave = (Button) view.findViewById(R.id.DialogUpdate);
        mButtonCancel = (Button) view.findViewById(R.id.DialogCancel);
        Log.i(TAG, "onViewCreated: name: " + cartObjectList.get(position).getItemName());
        mNumberPicker = (NumberPicker) view.findViewById(R.id.numPicker);

        final ShoppingCartSingleton shoppingCartSingleton = ShoppingCartSingleton.getInstance();
        //Setting the max and min values of number picker. Quantity should be adjusted to reflect the different units(packets for seeds) for items
        mNumberPicker.setMaxValue(100);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setWrapSelectorWheel(false);
        mNumberPicker.setValue(shoppingCartSingleton.getObjectCount());
        //Method below is not being used, to save the quantity, it is being called in the recyclerviewadapter. Put this method as I was testing and playing around with different features. Leaving it here for now in case I want to update it later.
        mButtonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });

    }

    public void show(FragmentManager fm, String add_quantity) {
    }
}
