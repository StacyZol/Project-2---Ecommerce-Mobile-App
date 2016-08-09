package com.example.stacyzolnikov.project2shoppinglist2;

/**
 * Created by stacyzolnikov on 8/8/16.
 */
public class ClothesInCart extends Shirt {
    long mUserId;
    int mQuantity;
    //Not being Used

    public ClothesInCart(Shirt shirt, int quantity, long userId){
        super(shirt.getId(), shirt.getShirtName(), shirt.getHeart(), shirt.getPrice(), shirt.getShirtPhotosID(), shirt.getStoreNameShirt());
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
