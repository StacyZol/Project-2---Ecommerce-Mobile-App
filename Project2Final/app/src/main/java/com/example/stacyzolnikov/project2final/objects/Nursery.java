package com.example.stacyzolnikov.project2final.objects;

/**
 * Created by stacyzolnikov on 8/20/16.
 */
public class Nursery {
    private int id;
    public String nurseryName;
    String nurseryStars;
    String nurseryReviews;
    String nurseryPhoto;

    public Nursery(int id, String nurseryName, String nurseryStars, String nurseryReviews, String nurseryPhoto) {
        this.id = id;
        this.nurseryName = nurseryName;
        this.nurseryStars = nurseryStars;
        this.nurseryReviews = nurseryReviews;
        this.nurseryPhoto = nurseryPhoto;
    }

    public Nursery(String nurseryName, String nurseryStars, String nurseryReviews, String nurseryPhoto) {
        this.nurseryName = nurseryName;
        this.nurseryStars = nurseryStars;
        this.nurseryReviews = nurseryReviews;
        this.nurseryPhoto = nurseryPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNurseryStars() {
        return nurseryStars;
    }

    public void setNurseryStars(String nurseryStars) {
        this.nurseryStars = nurseryStars;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    public String getNurseryReviews() {
        return nurseryReviews;
    }

    public void setNurseryReviews(String nurseryReviews) {
        this.nurseryReviews = nurseryReviews;
    }

    public String getNurseryPhoto() {
        return nurseryPhoto;
    }

    public void setNurseryPhoto(String nurseryPhoto) {
        this.nurseryPhoto = nurseryPhoto;
    }
}
