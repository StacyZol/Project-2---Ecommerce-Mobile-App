package com.example.stacyzolnikov.project2shoppinglist2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 7/28/16.
 */
public class ShoppingCartSingleton {
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

    public void setCartObjectList1(List<CartObject> cartObjectList1){
        this.cartObjectList1 = cartObjectList1;
    }

    public void addCartObject (CartObject object) {
        cartObjectList1.add(object);
    }


}
