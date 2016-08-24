package com.example.stacyzolnikov.project2final.recycler;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.activities.ShoppingCartActivity;
import com.example.stacyzolnikov.project2final.objects.CartObject;
import com.example.stacyzolnikov.project2final.setup.ShoppingCartSingleton;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by stacyzolnikov on 8/21/16.
 */
public class RecyclerViewCartAdapter extends RecyclerView.Adapter<CartObjectViewHolder> {
    Context mContext;
    List<CartObject> cartObjectList1;
    OnChangeQuantityListener mListener;
    private static final String TAG = "RecyclerViewCartAdapter";

    public RecyclerViewCartAdapter(List<CartObject> cartObjectList1) {
        this.cartObjectList1 = cartObjectList1;
    }

    public List<CartObject> getCartObjectList1() {
        return cartObjectList1;
    }

    public void setCartObjectList1(List<CartObject> cartObjectList1) {
        this.cartObjectList1 = cartObjectList1;
    }

    public interface OnChangeQuantityListener {
        void OnChangeQuantity();
    }

    public void setChangeQuantityListener(OnChangeQuantityListener quantity) {
        this.mListener = quantity;
    }

    public RecyclerViewCartAdapter(List<CartObject> cartObjectList1, Context mContext, OnChangeQuantityListener mListener) {
        this.mContext = mContext;
        this.cartObjectList1 = cartObjectList1;
        this.mListener = mListener;
    }

    @Override
    public CartObjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View parentView = inflater.inflate(R.layout.custom_cart_object_view, parent, false);
        CartObjectViewHolder viewHolder = new CartObjectViewHolder(parentView);
        mContext = parent.getContext();
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(final CartObjectViewHolder holder, final int position) {
        final CartObject cartObject = cartObjectList1.get(position);
        final int mPosition = position;
        holder.mItemName.setText(cartObjectList1.get(position).getItemName());
        String cost = "$" + cartObjectList1.get(position).getItemPrice();
        holder.mItemPrice.setText(cost);
        holder.mItemDelete.setOnClickListener(new View.OnClickListener() {
            //Creating an object called 'temp' that when deleted, a snackbar shows up that reinserts the 'temp' when user clicks on Undo
            //Right now the temp is being re-inserted at the bottom of the list instead of the previous position
            //The total price (on the Shopping Cart Activity) is NOT being calcualted when user removes cart object. Will need to fix this
            CartObject temp = new CartObject();

            @Override
            public void onClick(View view) {
                temp = cartObjectList1.get(position);
                cartObjectList1.remove(position);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartObjectList1.size());

                Snackbar.make(view, "Removed Item from Cart", Snackbar.LENGTH_LONG).
                        setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cartObjectList1.add(temp);
//                                notifyItemInserted(position);
                                notifyDataSetChanged();
                            }
                        })
                        .show();
                notifyDataSetChanged();
            }

        });
        //'costInt' is the price of the item. 'count' is the number of the quantity. 'total' is for the objects total, NOT the shopping cart total. The objects total will get adjusted as user changes the quantity size. Simple multiplication (quantity X price) to achieve this
        double costInt = Double.parseDouble(cartObjectList1.get(position).getItemPrice());
        int count = cartObject.getmQuantity();
        double total = count * costInt;
        //Below concatenates the total price of the cart object with '$'
        String cartCostUpdated = "$" + String.format(Locale.ENGLISH, "%.2f", total);
        holder.mItemTotal.setText(cartCostUpdated);
        Log.i(TAG, "onBindViewHolder: " + cartCostUpdated);
        Log.i(TAG, "onBindViewHolder: " + cartObjectList1.get(position).getItemPhoto());
        holder.mItemQuantity.setText(String.valueOf(cartObject.getmQuantity()));
        int imageResource = mContext.getResources().getIdentifier(cartObjectList1.get(position).getItemPhoto(), "drawable", mContext.getPackageName());
        holder.mItemPhoto.setImageResource(imageResource);

        holder.mChangeQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Created a method called 'showDialog' that displays the dialog so user can change the quantity of the item in the shopping cart
                showDialog(cartObject, mPosition);

            }
        });

    }

    public Double getCartObjectPrice() {
        Double objectTotal = 0.0;
        for (CartObject cartObject : cartObjectList1) {
            //objectTotal = objectTotal + Double.parseDouble(cartObject.getPrice());
            objectTotal = cartObject.getmQuantity() * Double.parseDouble(cartObject.getItemPrice());
            Log.i(TAG, "getCartObjectPrice: " + objectTotal);
        }
        return objectTotal;
    }

    public void showDialog(final CartObject cartObject, final int position) {
        //Dialog that allows user to change the quantity of the price
        final Dialog cartDialog = new Dialog(mContext);
        cartDialog.setTitle(cartObjectList1.get(position).getItemName());
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
                    Toast.makeText(mContext, "Removed Position", Toast.LENGTH_LONG).show();
                } else {
                    cartObjectList1.get(position).setmQuantity(numberPicker.getValue());
                    notifyDataSetChanged();
                }
                //need to change below
                // Double TotalPrice = ShoppingCartSingleton.getInstance().getTotalPrice();
                //  double TotalPrice = mListener.OnChangeQuantity(ShoppingCartSingleton.getInstance().getTotalPrice());
                mListener.OnChangeQuantity();
                cartDialog.dismiss();
                Toast.makeText(mContext, "Quantity is changed", Toast.LENGTH_LONG).show();

            }
        });
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

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


