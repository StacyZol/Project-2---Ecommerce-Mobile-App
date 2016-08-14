package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Intent;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ShoppingCartActivity2 extends AppCompatActivity {
    private static final String TAG = "ShoppingCart";
    RecyclerView mRecyclerViewCart;
    Button mButtonDelete, mButtonBack, mButtonCheckout;
    UserCart cart;
    TextView mTotalPrice;
    RecyclerViewCartAdapter mAdapter;
    List<CartObject> cartObjectList;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart2);
        ShoppingCartSingleton singleton = ShoppingCartSingleton.getInstance();


        mTotalPrice = (TextView) findViewById(R.id.shoppingCartTotalCost);
        mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));

        cart = UserCart.getInstance();
        //set listview or recyclerview
        mRecyclerViewCart = (RecyclerView) findViewById(R.id.recyclerviewcart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewCart.setLayoutManager(linearLayoutManager);

        cartObjectList = (List<CartObject>) singleton.getCartObjectList1();

        mAdapter = new RecyclerViewCartAdapter(singleton.getCartObjectList1());

        mButtonDelete = (Button) findViewById(R.id.DeleteAllButton);

        mRecyclerViewCart.setAdapter(mAdapter);
        mImageView = (ImageView) findViewById(R.id.BackButton);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShoppingCartActivity2.this, StoreActivity.class);
                startActivity(intent);
            }
        });


 //      mButtonDelete.setOnClickListener(new View.OnClickListener() {
 //          @Override
 //          public void onClick(View view) {
 //              mRecyclerViewCart.removeAllViewsInLayout();
 //              mRecyclerViewCart.removeView(view);
 //              //Need to update price to 0
 //          }
 //      });

        mButtonDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (cartObjectList.size() == 0) {
                    Toast.makeText(ShoppingCartActivity2.this, "Your shopping cart is empty.", Toast.LENGTH_SHORT).show();
                }

                else {
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
                                    mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));

                                }
                            })
                            .show();
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

  }


}


