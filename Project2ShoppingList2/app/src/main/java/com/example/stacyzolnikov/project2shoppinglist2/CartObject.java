package com.example.stacyzolnikov.project2shoppinglist2;

/**
 * Created by stacyzolnikov on 7/28/16.
 */
public class CartObject {
    public String shirtName;
    public String price;
    public String shirtPhotosID;
    public String itemTotal;
    public int mQuantity = 1;

    public CartObject (String shirtName, String price, String shirtPhotosID){
        this.shirtName = shirtName;
        this.shirtPhotosID = shirtPhotosID;
        this.price = price;


    }

    public CartObject (String shirtName, String price, String shirtPhotosID, int quantity, String itemTotal){
        this.shirtName = shirtName;
        this.shirtPhotosID = shirtPhotosID;
        this.price = price;
        this.mQuantity = quantity;
        this.itemTotal = itemTotal;

    }
    //need to fix below, the dialog isn't reading the cartobject quantity of 1
    public CartObject(){
        this.mQuantity = 1;

    }

    public String getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(String itemTotal) {
        this.itemTotal = itemTotal;
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

  //  public void setQuantity(int mQuantity) {
   //     this.mQuantity = mQuantity;
   // }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }
}
