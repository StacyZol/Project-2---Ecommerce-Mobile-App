package com.example.stacyzolnikov.project2final.objects;

/**
 * Created by stacyzolnikov on 8/21/16.
 */
public class CartObject {
    public String itemName;
    public String itemPrice;
    public String itemPhoto;
    public String itemTotal;
    public int mQuantity = 1;

    public CartObject(String itemName, String itemPrice, String itemPhoto) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemPhoto = itemPhoto;
    }

    public CartObject(String itemName, int mQuantity, String itemTotal, String itemPhoto, String itemPrice) {
        this.itemName = itemName;
        this.mQuantity = mQuantity;
        this.itemTotal = itemTotal;
        this.itemPhoto = itemPhoto;
        this.itemPrice = itemPrice;
    }
    public CartObject(){
        this.mQuantity = 1;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(String itemTotal) {
        this.itemTotal = itemTotal;
    }

    public String getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(String itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
