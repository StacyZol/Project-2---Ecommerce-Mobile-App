package com.example.stacyzolnikov.project2shoppinglist2;

/**
 * Created by stacyzolnikov on 7/26/16.
 */
public class Shirt {
    private int id;
    private String shirtName;
    private String heart;
    private String price;
    private String moreColors;
    private String shirtPhotosID;
    private String storeNameShirt;
    private String storePhoto;
    private int quantity;

    public Shirt (int id, String shirtName, String heart, String price, String shirtPhotosID, String storeNameShirt){
        this.id = id;
        this.shirtName = shirtName;
        this.shirtPhotosID = shirtPhotosID;
        this.heart = heart;
        this.price = price;
        this.storeNameShirt = storeNameShirt;

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Shirt (String shirtName, String heart, String price, String shirtPhotosID, String storeNameShirt){
        this.shirtName = shirtName;
        this.shirtPhotosID = shirtPhotosID;
        this.heart = heart;
        this.price = price;
        this.storeNameShirt = storeNameShirt;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShirtName() {
        return shirtName;
    }

    public void setShirtName(String shirtName) {
        this.shirtName = shirtName;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMoreColors() {
        return moreColors;
    }

    public void setMoreColors(String moreColors) {
        this.moreColors = moreColors;
    }

    public String getShirtPhotosID() {
        return shirtPhotosID;
    }

    public void setShirtPhotosID(String shirtPhotosID) {
        this.shirtPhotosID = shirtPhotosID;
    }

    public String getStoreNameShirt() {
        return storeNameShirt;
    }

    public void setStoreNameShirt(String storeNameShirt) {
        this.storeNameShirt = storeNameShirt;
    }

    public String getStorePhoto() {
        return storePhoto;
    }

    public void setStorePhoto(String storePhoto) {
        this.storePhoto = storePhoto;
    }
}
