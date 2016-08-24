package com.example.stacyzolnikov.project2shoppinglist2;

/**
 * Created by stacyzolnikov on 8/16/16.
 */
public class Flower {
    private int id;
    private String flowerName;
    private String flowerHeart;
    private String flowerPrice;
    private String flowerDescription;
    private String flowerPhotosID;
    private String flowerStoreName;
    private String storePhoto;
    private int quantity;

    public Flower(int id, String flowerName, String flowerHeart, String flowerPrice, String flowerPhotosID, String flowerStoreName) {
        this.id = id;
        this.flowerName = flowerName;
        this.flowerHeart = flowerHeart;
        this.flowerPrice = flowerPrice;
        this.flowerPhotosID = flowerPhotosID;
        this.flowerStoreName = flowerStoreName;
    }

    public Flower(String flowerName, String flowerHeart, String flowerPrice, String flowerPhotosID, String flowerStoreName) {
        this.id = id;
        this.flowerName = flowerName;
        this.flowerHeart = flowerHeart;
        this.flowerPrice = flowerPrice;
        this.flowerPhotosID = flowerPhotosID;
        this.flowerStoreName = flowerStoreName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlowerHeart() {
        return flowerHeart;
    }

    public void setFlowerHeart(String flowerHeart) {
        this.flowerHeart = flowerHeart;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public String getFlowerPrice() {
        return flowerPrice;
    }

    public void setFlowerPrice(String flowerPrice) {
        this.flowerPrice = flowerPrice;
    }

    public String getFlowerDescription() {
        return flowerDescription;
    }

    public void setFlowerDescription(String flowerDescription) {
        this.flowerDescription = flowerDescription;
    }

    public String getflowerPhotosID() {
        return flowerPhotosID;
    }

    public void setShirtPhotosID(String flowerPhotosID) {
        this.flowerPhotosID = flowerPhotosID;
    }

    public String getFlowerStoreName() {
        return flowerStoreName;
    }

    public void setFlowerStoreName(String flowerStoreName) {
        this.flowerStoreName = flowerStoreName;
    }

    public String getStorePhoto() {
        return storePhoto;
    }

    public void setStorePhoto(String storePhoto) {
        this.storePhoto = storePhoto;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
