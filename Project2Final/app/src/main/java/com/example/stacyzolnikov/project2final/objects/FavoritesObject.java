package com.example.stacyzolnikov.project2final.objects;

/**
 * Created by stacyzolnikov on 8/21/16.
 */
public class FavoritesObject {
    //This object is used for both Favorites and Search Activity
    public String itemName;
    public String itemDescription;
    public String itemPrice;
    public String itemPhoto;
    int numInFavoritesList;

    public FavoritesObject(String itemName, String itemDescription, String itemPrice, String itemPhoto) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemPhoto = itemPhoto;
    }

    public FavoritesObject(String flowerName, String flowerDescription, String flowerPrice) {
    }



    public String getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(String itemPhoto) {
        this.itemPhoto = itemPhoto;
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
