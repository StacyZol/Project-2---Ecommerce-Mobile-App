package com.example.stacyzolnikov.project2shoppinglist2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class ShoppingCartActivity2 extends AppCompatActivity {
    RecyclerView mRecyclerViewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart2);

        ShoppingCartSingleton singleton = ShoppingCartSingleton.getInstance();

        List<CartObject> testarraylist = singleton.getCartObjectList1();

        //set listvie or recyclerview
        mRecyclerViewCart = (RecyclerView) findViewById(R.id.recyclerviewcart);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerViewCart.setLayoutManager(linearLayoutManager);

        RecyclerViewCartAdapter adapter = new RecyclerViewCartAdapter(singleton.getCartObjectList1());
        mRecyclerViewCart.setAdapter(adapter);
        //new adapter to singleton.getCartObjectList1()


        //set listview.adapter


    }
}
