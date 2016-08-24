package com.example.stacyzolnikov.project2shoppinglist2;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

/**
 * Created by stacyzolnikov on 7/28/16.
 */
public class RecyclerViewCartAdapter extends RecyclerView.Adapter<CartObjectViewHolder> {
    private Context mContext;
    List<CartObject> cartObjectList1;
    List<ClothesInCart> mClothesItemList;
    OnChangeQuantityListener mListener;
    private static final String TAG = "RecyclerViewCartAdapter";

    public RecyclerViewCartAdapter(List<CartObject> cartObjectList) {
        cartObjectList1 = cartObjectList;

    }
    public void setCartItemList (List<CartObject> cartObjectList1){
        this.cartObjectList1=cartObjectList1;
    }

    public void setCartObjectList1(List<CartObject> cartObjectList1) {
        this.cartObjectList1 = cartObjectList1;
    }

    public interface OnChangeQuantityListener{
        void OnChangeQuantity();

    }
    public void setChangeQuantityListener(OnChangeQuantityListener quantity){
        this.mListener=quantity;
    }


    public RecyclerViewCartAdapter(List<CartObject> cartObjectList, Context context, OnChangeQuantityListener listener) {
        this.cartObjectList1 = cartObjectList;
        this.mContext = context;
        this.mListener = listener;
    }


    //  public RecyclerViewCartAdapter(List<CartObject> mClothesItemList, Context context) {
    //      mClothesItemList = cartObjectList1;
    //      mContext = context;
//
    //  }

    @Override
    public CartObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_cart_object_view, parent, false);
        CartObjectViewHolder viewHolder = new CartObjectViewHolder(parentView);
        mContext = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CartObjectViewHolder holder, int position) {
        final CartObject cartObject = cartObjectList1.get(position);
        final int mPosition = position;
        //final ClothesInCart clothesInCart = mClothesItemList.get(position);
        holder.mShirtName.setText(cartObjectList1.get(position).getShirtName());
        String cost = "$" + cartObjectList1.get(position).getPrice();
        holder.mPrice.setText(cost);

        double costInt = Double.parseDouble(cartObjectList1.get(position).getPrice());
        int count = cartObject.getmQuantity();
        double total = count * costInt;
       //String cartCost = "$" + cartObjectList1.get(position).getPrice();


        String cartCostUpdated = "$" + String.format(Locale.ENGLISH, "%.2f", total);
        holder.mItemTotal.setText(cartCostUpdated);


        Log.i(TAG, "onBindViewHolder: "+cartCostUpdated);
        //holder.mItemTotal.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getCartObjectPrice()));
        holder.mItemQuantity.setText(String.valueOf(cartObject.getmQuantity()));

        holder.mChangeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // FragmentManager fm = ((AppCompatActivity) mContext).getSupportFragmentManager();
                // AddQuantityInCartDialogFragment addToQuantity = new AddQuantityInCartDialogFragment(cartObjectList1, position);
                // addToQuantity.show(fm, "add_quantity");
                show(cartObject, mPosition);

            }
        });

    }
    public Double getCartObjectPrice() {
        Double objectTotal = 0.0;
        for (CartObject cartObject : cartObjectList1) {
            //objectTotal = objectTotal + Double.parseDouble(cartObject.getPrice());
            objectTotal = cartObject.getmQuantity() * Double.parseDouble(cartObject.getPrice());
            Log.i(TAG, "getCartObjectPrice: "+  objectTotal);
        }
        return objectTotal;
    }

            public void show(final CartObject cartObject, final int position) {

                //Toast.makeText(mContext, "Change Quantity", Toast.LENGTH_LONG).show();
                final Dialog cartDialog = new Dialog(mContext);
                cartDialog.setTitle("Change Quantity");
                cartDialog.setContentView(R.layout.add_quantity_in_cart_dialog);
                Button mButtonSave = (Button) cartDialog.findViewById(R.id.DialogUpdate);
                Button mButtonCancel = (Button) cartDialog.findViewById(R.id.DialogCancel);
                final NumberPicker numberPicker = (NumberPicker) cartDialog.findViewById(R.id.numPicker);
                numberPicker.setMaxValue(100);
                numberPicker.setMinValue(0);
                numberPicker.setWrapSelectorWheel(false);
                numberPicker.setValue(cartObject.getmQuantity());
                mButtonSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (numberPicker.getValue() == 0) {
                            cartObjectList1.remove(position);
                            notifyDataSetChanged();
                        } else {
                           cartObjectList1.get(position).setmQuantity(numberPicker.getValue());
                            notifyDataSetChanged();
                        }
                        //need to change below
                       // Double TotalPrice = ShoppingCartSingleton.getInstance().getTotalPrice();
                      //  double TotalPrice = mListener.OnChangeQuantity(ShoppingCartSingleton.getInstance().getTotalPrice());
                        mListener.OnChangeQuantity();

                        cartDialog.dismiss();
                    }
                });
                numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener(){

                                                           @Override
                                                           public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                                                               Toast.makeText(mContext, "Quantity is changed", Toast.LENGTH_LONG).show();
                                                           }
                                                       });
                mButtonCancel.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        cartDialog.dismiss();
                    }
                });
                cartDialog.show();
            }



    @Override
    public int getItemCount() {
        if (cartObjectList1 == null) {
            return 0;
        } else {
            return cartObjectList1.size();
        }
    }
    public void updateList(NumberPicker picker, int oldVal, int newVal) {
        cartObjectList1 = ShoppingCartSingleton.getInstance().getCartObjectList1();
        notifyDataSetChanged();
        ShoppingCartSingleton.getInstance().getTotalPrice();



    }

}

