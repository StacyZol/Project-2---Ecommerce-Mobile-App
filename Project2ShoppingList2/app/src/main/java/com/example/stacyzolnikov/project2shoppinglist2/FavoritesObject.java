package com.example.stacyzolnikov.project2shoppinglist2;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/11/16.
 */
public class FavoritesObject {
    public String itemName;
    public String itemDescription;
    public String itemPrice;
    int numInFavoritesList;

    public FavoritesObject(String itemName, String itemDescription, String itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }


    public int getNumInFavoritesList() {
        return numInFavoritesList;
    }

    public void setNumInFavoritesList(int numInFavoritesList) {
        this.numInFavoritesList = numInFavoritesList;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
