package com.example.stacyzolnikov.project2shoppinglist2;

/**
 * Created by stacyzolnikov on 7/28/16.
 */
public class CartObject {
    public String shirtName;
    public String price;
    public String shirtPhotosID;

    public CartObject (String shirtName, String price, String shirtPhotosID){
        this.shirtName = shirtName;
        this.shirtPhotosID = shirtPhotosID;
        this.price = price;

    }

    public String getShirtName() {
        return shirtName;
    }

    public void setShirtName(String shirtName) {
        this.shirtName = shirtName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShirtPhotosID() {
        return shirtPhotosID;
    }

    public void setShirtPhotosID(String shirtPhotosID) {
        this.shirtPhotosID = shirtPhotosID;
    }
}
