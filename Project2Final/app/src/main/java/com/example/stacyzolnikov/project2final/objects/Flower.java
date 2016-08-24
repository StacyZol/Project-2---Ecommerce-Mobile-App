package com.example.stacyzolnikov.project2final.objects;

/**
 * Created by stacyzolnikov on 8/20/16.
 */
public class Flower extends Master {
    private int id;
    private String FlowerName;
    private String FlowerDescription;
    private String FlowerReviews;
    private String FlowerPhoto;
    private String FlowerPrice;

    public Flower(int id, String flowerName, String flowerDescription, String flowerPhoto, String flowerReviews, String flowerPrice) {
        this.id = id;
        FlowerName = flowerName;
        FlowerDescription = flowerDescription;
        FlowerPhoto = flowerPhoto;
        FlowerReviews = flowerReviews;
        FlowerPrice = flowerPrice;
    }
    public Flower(int id, String itemName, String itemDescription, String itemPhoto, String itemPrice) {
        super(id, itemName, itemDescription, itemPrice, itemPhoto);
        this.id = id;
    }


    public Flower(String flowerName, String flowerDescription, String flowerReviews, String flowerPhoto, String flowerPrice) {
        FlowerName = flowerName;
        FlowerDescription = flowerDescription;
        FlowerReviews = flowerReviews;
        FlowerPhoto = flowerPhoto;
        FlowerPrice = flowerPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlowerName() {
        return FlowerName;
    }

    public void setFlowerName(String flowerName) {
        FlowerName = flowerName;
    }

    public String getFlowerDescription() {
        return FlowerDescription;
    }

    public void setFlowerDescription(String flowerDescription) {
        FlowerDescription = flowerDescription;
    }

    public String getFlowerReviews() {
        return FlowerReviews;
    }

    public void setFlowerReviews(String flowerReviews) {
        FlowerReviews = flowerReviews;
    }

    public String getFlowerPhoto() {
        return FlowerPhoto;
    }

    public void setFlowerPhoto(String flowerPhoto) {
        FlowerPhoto = flowerPhoto;
    }

    public String getFlowerPrice() {
        return FlowerPrice;
    }

    public void setFlowerPrice(String flowerPrice) {
        FlowerPrice = flowerPrice;
    }
}
