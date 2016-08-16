package com.example.stacyzolnikov.project2shoppinglist2;

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

public class ShoppingCartActivity2 extends AppCompatActivity implements RecyclerViewCartAdapter.OnChangeQuantityListener {
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
        final ShoppingCartSingleton singleton = ShoppingCartSingleton.getInstance();


        updateList();
        Log.i(TAG, "onCreate: TotalPrice:" + ShoppingCartSingleton.getInstance().getTotalPrice());
        mTotalPrice = (TextView) findViewById(R.id.shoppingCartTotalCost);
        mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));
//        mAdapter.notifyDataSetChanged();


        double totalPrice = ShoppingCartSingleton.getInstance().getTotalPrice();
        int count = ShoppingCartSingleton.getInstance().getCount();



        cart = UserCart.getInstance();
        //set listview or recyclerview
        mRecyclerViewCart = (RecyclerView) findViewById(R.id.recyclerviewcart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerViewCart.setLayoutManager(linearLayoutManager);

        cartObjectList = (List<CartObject>) singleton.getCartObjectList1();

        //mAdapter = new RecyclerViewCartAdapter(singleton.getCartObjectList1());







        mAdapter = new RecyclerViewCartAdapter(singleton.getCartObjectList1(),ShoppingCartActivity2.this, this);
        mButtonDelete = (Button) findViewById(R.id.DeleteAllButton);
        mRecyclerViewCart.setAdapter(mAdapter);
        mImageView = (ImageView) findViewById(R.id.BackButton);


        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //Intent intent = new Intent(ShoppingCartActivity2.this, StoreActivity.class);
               // startActivity(intent);
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
                                    singleton.getCartObjectList1().addAll(temp);
                                    mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));
                                   // finish();
                                   // startActivity(getIntent());
                                }
                            })
                            .show();
                    mAdapter.notifyDataSetChanged();

                }
            }
        });

  }

    public void onResume () {
    super.onResume();
        mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));

    }
    public void updateList() {
        // mAdapter.updateList();
        //mAdapter.notifyDataSetChanged();
        //mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));

    }



    @Override
    public void OnChangeQuantity() {
        mAdapter.setChangeQuantityListener(this);
        mTotalPrice.setText("$" + String.format(Locale.ENGLISH, "%.2f", ShoppingCartSingleton.getInstance().getTotalPrice()));
    }


    //  @Override
 //  public double OnChangeQuantity(double total) {
 //      return ShoppingCartSingleton.getInstance().getTotalPrice();
 //  }

  // @Override
  // public void OnChangeQuantity(double total) {

  // }

  // @Override
  // public double onChangeQuantity() {
  //     return ShoppingCartSingleton.getInstance().getTotalPrice();
  // }
}


