package com.example.stacyzolnikov.project2shoppinglist2;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 7/28/16.
 */
public class ShoppingCartSingleton {
    private static final String TAG = "Singleton";
    List<CartObject> cartObjectList1;

    private ShoppingCartSingleton() {
        if (cartObjectList1 == null) {
            cartObjectList1 = new ArrayList<>();
        }
    }

    public static ShoppingCartSingleton shoppingCartSingleton;

    public static ShoppingCartSingleton getInstance() {
        if (shoppingCartSingleton == null) {
            shoppingCartSingleton = new ShoppingCartSingleton();
        }
        return shoppingCartSingleton;
    }


    public List<CartObject> getCartObjectList1() {
        return cartObjectList1;
    }

    public void setCartObjectList1(List<CartObject> cartObjectList1) {
        this.cartObjectList1 = cartObjectList1;
    }

    public int getObjectCount() {
        int x = 1;
        return x;
    }

    public void removeCartObject(CartObject object) {
        cartObjectList1.remove(object);
    }

    public void addCartObject(CartObject object) {
        cartObjectList1.add(object);
    }

    public Double getTotalPrice() {
        Double totalCost = 0.0;
        for (CartObject cartObject : cartObjectList1) {
            Double cost = ((double)(cartObject.getmQuantity()) * Double.parseDouble(cartObject.getPrice()));
            totalCost = totalCost + cost;
            //totalCost = totalCost + Double.parseDouble(cartObject.getPrice());

            Log.d(TAG, "getTotalPrice:Qiantity" + cartObject.getmQuantity());
            Log.i(TAG, "getTotalPrice: totalcost" + totalCost);



        }
        return totalCost;


    }

    public int getCount() {
        int totalCount = 0;
        for (CartObject cartObject:cartObjectList1){
            totalCount = totalCount + cartObject.getmQuantity();
            Log.i(TAG, "getCount: " + totalCount);
        }
        return totalCount;
    }








}
