package com.example.stacyzolnikov.project2final.activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.objects.CartObject;
import com.example.stacyzolnikov.project2final.recycler.RecyclerViewCartAdapter;
import com.example.stacyzolnikov.project2final.setup.ShoppingCartSingleton;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ShoppingCartActivity extends AppCompatActivity implements RecyclerViewCartAdapter.OnChangeQuantityListener {
    private static final String TAG = "ShoppingCartActivity";
    RecyclerView mRecyclerViewCart;
    Button mButtonDelete, mButtonBack, mButtonCheckout;
    TextView mTotalPrice;
    RecyclerViewCartAdapter mAdapter;
    List<CartObject> cartObjectList;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        final ShoppingCartSingleton singleton = ShoppingCartSingleton.getInstance();

        Log.i(TAG, "onCreate: TotalPrice:" + ShoppingCartSingleton.getInstance().getTotalPrice());
        mTotalPrice = (TextView) findViewById(R.id.shoppingCartTotalCost);
        //Need to concatenate the price, which is stored without the '$' in the database so the display will have the '$'+price of item.
        mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));

        double totalPrice = ShoppingCartSingleton.getInstance().getTotalPrice();
        int count = ShoppingCartSingleton.getInstance().getCount();

        mRecyclerViewCart = (RecyclerView) findViewById(R.id.recyclerviewcart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewCart.setLayoutManager(linearLayoutManager);

        cartObjectList = (List<CartObject>) singleton.getCartObjectList1();
        mAdapter = new RecyclerViewCartAdapter(singleton.getCartObjectList1(), ShoppingCartActivity.this, this);
        mButtonDelete = (Button) findViewById(R.id.DeleteAllButton);
        mRecyclerViewCart.setAdapter(mAdapter);
        //Setting the back button to go to previous page, might potentially want to set it to the home activity instead
        mImageView = (ImageView) findViewById(R.id.BackButton);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Adding functionality to delete the entire list. Storing the current list user added in shopping cart to a 'temp' list so when Snackbar displays undo, the 'temp' list is then repopulated.
        mButtonDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (cartObjectList.size() == 0) {
                    Toast.makeText(ShoppingCartActivity.this, "Your shopping cart is empty.", Toast.LENGTH_SHORT).show();
                } else {
                    final List<CartObject> temp = new LinkedList<CartObject>(cartObjectList);
                    cartObjectList.clear();
                    mAdapter.notifyDataSetChanged();

                    mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));
                    Snackbar.make(mRecyclerViewCart, "Removed all items from basket.", Snackbar.LENGTH_LONG).
                            setAction("Undo", new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {
                                    cartObjectList = temp;
                                    mAdapter.setCartObjectList1(cartObjectList);
                                    mAdapter.notifyItemRangeInserted(0, cartObjectList.size());
                                    singleton.getCartObjectList1().addAll(temp);
                                    mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));
                                }
                            })
                            .show();
                    mAdapter.notifyDataSetChanged();

                }
            }
        });

    }

    public void onResume() {
        super.onResume();
        mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));
    }

    @Override
    public void OnChangeQuantity() {
        mAdapter.setChangeQuantityListener(this);
        mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));
    }
}

