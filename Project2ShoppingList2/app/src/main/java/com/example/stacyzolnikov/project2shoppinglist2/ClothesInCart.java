package com.example.stacyzolnikov.project2shoppinglist2;

/**
 * Created by stacyzolnikov on 8/8/16.
 */
public class ClothesInCart extends Tree {
    long mUserId;
    int mQuantity;
    //Not being Used

    public ClothesInCart(Tree tree, int quantity, long userId){
        super(tree.getId(), tree.getShirtName(), tree.getHeart(), tree.getPrice(), tree.getShirtPhotosID(), tree.getStoreNameShirt());
        this.mQuantity = quantity;
        this.mUserId = userId;
    }

    public long getmUserId() {
        return mUserId;
    }

    public void setmUserId(long mUserId) {
        this.mUserId = mUserId;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }
}
