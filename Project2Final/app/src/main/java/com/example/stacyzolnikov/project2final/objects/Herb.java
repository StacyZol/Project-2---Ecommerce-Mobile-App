package com.example.stacyzolnikov.project2final.objects;

/**
 * Created by stacyzolnikov on 8/20/16.
 */
public class Herb extends Master {
    private int id;
    private String HerbName;
    private String HerbDescription;
    private String HerbReviews;
    private String HerbPhoto;
    private String HerbPrice;

    public Herb(int id, String itemName, String itemDescription, String itemPrice, String itemPhoto) {
        super(id, itemName, itemDescription, itemPrice, itemPhoto);
        this.id = id;
    }

    public Herb(int id, String herbName, String herbDescription, String herbReviews, String herbPhoto, String herbPrice) {
        this.id = id;
        HerbName = herbName;
        HerbDescription = herbDescription;
        HerbReviews = herbReviews;
        HerbPhoto = herbPhoto;
        HerbPrice = herbPrice;
    }

    public Herb(String herbName, String herbDescription, String herbReviews, String herbPhoto, String herbPrice) {
        HerbName = herbName;
        HerbDescription = herbDescription;
        HerbReviews = herbReviews;
        HerbPhoto = herbPhoto;
        HerbPrice = herbPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHerbName() {
        return HerbName;
    }

    public void setHerbName(String herbName) {
        HerbName = herbName;
    }

    public String getHerbDescription() {
        return HerbDescription;
    }

    public void setHerbDescription(String herbDescription) {
        HerbDescription = herbDescription;
    }

    public String getHerbReviews() {
        return HerbReviews;
    }

    public void setHerbReviews(String herbReviews) {
        HerbReviews = herbReviews;
    }

    public String getHerbPhoto() {
        return HerbPhoto;
    }

    public void setHerbPhoto(String herbPhoto) {
        HerbPhoto = herbPhoto;
    }

    public String getHerbPrice() {
        return HerbPrice;
    }

    public void setHerbPrice(String herbPrice) {
        HerbPrice = herbPrice;
    }
}

