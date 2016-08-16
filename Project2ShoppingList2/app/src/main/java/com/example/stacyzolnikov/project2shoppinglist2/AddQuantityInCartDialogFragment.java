package com.example.stacyzolnikov.project2shoppinglist2;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/14/16.
 */
public class AddQuantityInCartDialogFragment extends DialogFragment {
    public Button mButtonSave;
    public Button mButtonCancel;
    public NumberPicker mNumberPicker;
    List<Shirt> mShirts;
    int position;
    Context mContext;

    public AddQuantityInCartDialogFragment(Button mButtonSave, Button mButtonCancel, NumberPicker mNumberPicker) {
        this.mButtonSave = mButtonSave;
        this.mButtonCancel = mButtonCancel;
        this.mNumberPicker = mNumberPicker;
    }
   // public AddQuantityInCartDialogFragment (List<Shirt> mShirts, int position) {
   //     this.mShirts = mShirts;
   //     this.position=position;
   // }

    public AddQuantityInCartDialogFragment() {

    }

    public AddQuantityInCartDialogFragment(Context mContext, int position) {
        this.mContext=mContext;
        this.position=position;
    }

    public AddQuantityInCartDialogFragment(List<CartObject> cartObjectList1, int position) {
    }


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
       // return inflater.inflate(R.layout.add_quantity_in_cart_dialog, container);
        return null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButtonSave = (Button) view.findViewById(R.id.DialogUpdate);
        mButtonCancel = (Button) view.findViewById(R.id.DialogCancel);
        mNumberPicker = (NumberPicker) view.findViewById(R.id.numPicker);

        final ShoppingCartSingleton shoppingCartSingleton = ShoppingCartSingleton.getInstance();
        final Shirt cartObject = mShirts.get(position);
        mNumberPicker.setMaxValue(100);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setWrapSelectorWheel(false);
        mNumberPicker.setValue(shoppingCartSingleton.getObjectCount());

        mButtonSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });

    }


    public void show(FragmentManager fm, String add_quantity) {
    }
}
