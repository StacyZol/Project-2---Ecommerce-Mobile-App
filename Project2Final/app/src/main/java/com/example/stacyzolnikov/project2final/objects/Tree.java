package com.example.stacyzolnikov.project2final.objects;

/**
 * Created by stacyzolnikov on 8/20/16.
 */
public class Tree extends Master {
    private int id;
    private String treeName;
    private String treeDescription;
    private String treeReviews;
    private String treePhoto;
    private String treePrice;

    public Tree(int id, String itemName, String itemDescription, String itemPrice, String itemPhoto) {
        super(id, itemName, itemDescription, itemPrice, itemPhoto);
        this.id = id;
    }

    public Tree(int id, String treeName, String treeDescription, String treeReviews, String treePhoto, String treePrice) {
        this.id = id;
        this.treeName = treeName;
        this.treeDescription = treeDescription;
        this.treeReviews = treeReviews;
        this.treePhoto = treePhoto;
        this.treePrice = treePrice;
    }

    public Tree(String treeName, String treeDescription, String treeReviews, String treePhoto, String treePrice) {
        this.treeName = treeName;
        this.treeDescription = treeDescription;
        this.treeReviews = treeReviews;
        this.treePhoto = treePhoto;
        this.treePrice = treePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public String getTreeDescription() {
        return treeDescription;
    }

    public void setTreeDescription(String treeDescription) {
        this.treeDescription = treeDescription;
    }

    public String getTreeReviews() {
        return treeReviews;
    }

    public void setTreeReviews(String treeReviews) {
        this.treeReviews = treeReviews;
    }

    public String getTreePhoto() {
        return treePhoto;
    }

    public void setTreePhoto(String treePhoto) {
        this.treePhoto = treePhoto;
    }

    public String getTreePrice() {
        return treePrice;
    }

    public void setTreePrice(String treePrice) {
        this.treePrice = treePrice;
    }
}
