package com.example.stacyzolnikov.project2shoppinglist2;

/**
 * Created by stacyzolnikov on 7/23/16.
 */
public class Store {
    private int id;
     String storeName;
    private String stars;
    private String reviews;
    private String photosID;

    public Store () {}

    public Store (int id, String storeName, String stars, String reviews, String photosID){
       this.id = id;
        this.storeName = storeName;
        this.photosID = photosID;
        this.reviews = reviews;
        this.stars = stars;
    }
    public Store (String storeName, String stars, String reviews, String photosID){
        this.storeName = storeName;
        this.photosID = photosID;
        this.reviews = reviews;
        this.stars = stars;
    }




    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getPhotos() {
        return photosID;
    }

    public void setPhotos(String photos) {
        this.photosID = photosID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
