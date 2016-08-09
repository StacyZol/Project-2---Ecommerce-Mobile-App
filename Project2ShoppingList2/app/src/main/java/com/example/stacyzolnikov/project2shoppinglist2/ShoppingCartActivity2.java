package com.example.stacyzolnikov.project2shoppinglist2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        mAdapter = new RecyclerViewCartAdapter(singleton.getCartObjectList1());

        mButtonDelete = (Button) findViewById(R.id.DeleteAllButton);

        mRecyclerViewCart.setAdapter(mAdapter);
        mButtonDelete.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                mRecyclerViewCart.removeAllViewsInLayout();
                mRecyclerViewCart.removeView(view);
                //Need to update price to 0
            }
        }


        );

        //set listview.adapter

    }


    }


