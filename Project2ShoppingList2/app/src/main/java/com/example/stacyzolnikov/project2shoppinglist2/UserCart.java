package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by stacyzolnikov on 8/8/16.
 */
public class UserCart {
    //Not Being Used
    private static final String TAG = "UserCart";
    private Context context;
   List<ClothesInCart> mClothesItemList;

    private static UserCart instance = new UserCart();

    public static UserCart getInstance() {
        return instance;
    }

    public UserCart() {
        mClothesItemList = new LinkedList<>();
    }

    public void addToCart (ClothesInCart cartObject){
        mClothesItemList.add(cartObject);
    }

    public List<ClothesInCart> getClothesItemInCartList(long id) {
        return mClothesItemList;
    }

    public ClothesInCart getClothesItemInCartListById(long id) {
        ClothesInCart clothesInCart = null;

        for(ClothesInCart shirt: mClothesItemList){
            if (id == shirt.getId()){
                clothesInCart = shirt;
                break;
            }
        }
        return clothesInCart;
    }

    public Double getTotalPrice(){
        Double totalCost = 0.0;
        for (ClothesInCart clothesInCart : mClothesItemList){
            totalCost +=  totalCost + Double.parseDouble(clothesInCart.getPrice());
            Log.d(TAG, "getTotalPrice: Test");
            Log.i(TAG, "getTotalPrice: totalcost" + totalCost);
        }
        return totalCost;

    }

}
