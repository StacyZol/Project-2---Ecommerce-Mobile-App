package com.example.stacyzolnikov.project2final.objects;

/**
 * Created by stacyzolnikov on 8/22/16.
 */
public class Master {
    //Master is the parent object of Flower, Herb and Tree.
    //Method in DatabaseHelper that puts in all objects (Flower, Herb and Tree) into this master object so when user conducts a search on the Items Activity, they are able to search all of these products, vs the Nursery Activity which only allows the user to search nuseries

    public String itemName;
    public String itemDescription;
    public String itemPrice;
    public String itemPhoto;
    int id;
    int numInFavoritesList;

    public Master(int id, String itemName, String itemDescription, String itemPrice, String itemPhoto) {
        this.id=id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemPhoto = itemPhoto;
    }

    public Master() {

    }

    public String getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(String itemPhoto) {
        this.itemPhoto = itemPhoto;
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
