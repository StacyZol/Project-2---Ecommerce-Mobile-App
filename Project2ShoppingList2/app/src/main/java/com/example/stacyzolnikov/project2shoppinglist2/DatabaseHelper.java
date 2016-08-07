package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.provider.MediaStore;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 7/23/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private DatabaseHelper mHelper;
    private static final String TAG = DatabaseHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "STORE_DB";

    //Below is tables for store. will need to make 3 more tables (or more) for items
    public static final String STORE_TABLE_NAME = "STORE_LIST";
    public static final String SHIRT_TABLE_NAME = "SHIRT_LIST";

    //Below are the columns for the stores List Table
    public static final String COL_ID = "_id";
    public static final String COL_STARS = "STARS";
    public static final String COL_STORE_NAME = "STORE_NAME";
    public static final String COL_STORE_LOGO = "STORE_LOGO";
    public static final String COL_NUM_OF_REVIEWS = "NUM_OF_REVIEWS";

    public static final String[] STORE_COLUMNS = {COL_ID, COL_STARS, COL_STORE_NAME, COL_STORE_LOGO, COL_NUM_OF_REVIEWS};

    //Below are the columns for the Items List Table
    public static final String COL_ID_TOP = "_id";
    public static final String COL_HEART_TOP = "HEART_TOP";
    public static final String COL_TOP_NAME = "TOP_NAME";
    public static final String COL_TOP_PHOTO = "TOP_PHOTO";
    public static final String COL_TOP_PRICE = "TOP_PRICE";
    public static final String COL_STORE_NAME_SHIRT = "STORE_NAME_SHIRT";
   // public static final String COL_STORE_PHOTO = "STORE_PHOTO";

    public static final String[] TOP_COLUMNS = {COL_ID_TOP, COL_HEART_TOP, COL_TOP_NAME, COL_TOP_PHOTO, COL_TOP_PRICE, COL_STORE_NAME_SHIRT};


    //Below are the columns for Item # 2 Table
    //Below are the columns for Item #3 Table



    private static DatabaseHelper instance;
    public static synchronized  DatabaseHelper getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context.getApplicationContext());
        return instance;
    }


    public DatabaseHelper(Context context) {
        super(context,"db", null, 1);
    }

    //Need to add for other tables below
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL (SQL_CREATE_ENTRIES_STORE);
        db.execSQL (SQL_CREATE_ENTRIES_SHIRT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES_STORE);
        db.execSQL(SQL_DELETE_ENTRIES_SHIRT);
        onCreate(db);
    }


    //Below is creating the table for the Store

    private static final String SQL_CREATE_ENTRIES_STORE =
            "CREATE TABLE " + STORE_TABLE_NAME +
                    " (" +
                    COL_ID + " INTEGER PRIMARY KEY," +
                    COL_STORE_NAME + " TEXT," +
                    COL_STARS + " TEXT," +
                    COL_NUM_OF_REVIEWS + " TEXT," +
                    COL_STORE_LOGO + " TEXT" + ")";


    //Below is creating the table for the Shirt

    private static final String SQL_CREATE_ENTRIES_SHIRT =
            "CREATE TABLE " + SHIRT_TABLE_NAME +
                    " (" +
                    COL_ID_TOP + " INTEGER PRIMARY KEY," +
                    COL_TOP_NAME + " TEXT," +
                    COL_HEART_TOP + " TEXT," +
                    COL_TOP_PRICE + " TEXT," +
                    COL_TOP_PHOTO + " TEXT," +
                    COL_STORE_NAME_SHIRT + " TEXT," +
                    //COL_STORE_PHOTO + " TEXT" +
                    ")";


    //Need to add for other tables

    private static final String SQL_DELETE_ENTRIES_STORE = "DROP TABLE IF EXISTS " + STORE_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_SHIRT = "DROP TABLE IF EXISTS " + SHIRT_TABLE_NAME;

    //Need to add for other tables


    //This is for adding the Rows
    public void insertRowStore (Store store) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_STORE_NAME, store.getStoreName());
        values.put(COL_NUM_OF_REVIEWS, store.getReviews());
        values.put(COL_STARS, store.getStars());
        values.put(COL_STORE_LOGO, store.getPhotos());
        db.insertOrThrow(STORE_TABLE_NAME,"", values);
        db.close();
    }

    public void insertShirtRow (Shirt shirt) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_HEART_TOP, shirt.getHeart());
        values.put(COL_TOP_NAME, shirt.getShirtName());
        values.put(COL_TOP_PHOTO, shirt.getShirtPhotosID());
        values.put(COL_TOP_PRICE, shirt.getPrice());
        //values.put(COL_STORE_NAME_SHIRT, shirt.getStoreNameShirt());
        //values.put(COL_STORE_PHOTO, shirt.getStorePhoto());
        db.insertOrThrow(SHIRT_TABLE_NAME,"", values);
        db.close();

    }



    //Search Querys will go below

    public List<Store> getStores () {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM STORE_LIST", null);
        List<Store> stores = new ArrayList<>();
        String[] value={"Shirts"};
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                int id =cursor.getInt(cursor.getColumnIndex(COL_ID));
                String storeName = cursor.getString(cursor.getColumnIndex(COL_STORE_NAME));
                String stars = cursor.getString(cursor.getColumnIndex(COL_STARS));
                String reviews = cursor.getString(cursor.getColumnIndex(COL_NUM_OF_REVIEWS));
                String photosID = cursor.getString(cursor.getColumnIndex(COL_STORE_LOGO));

                stores.add(new Store(id, storeName, stars, reviews, photosID));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return stores;
    }

    //Below is to search Stores
    public ArrayList<Store> searchStoreList(String search) {
        SQLiteDatabase db = getReadableDatabase();

        ArrayList<Store> stores = new ArrayList<>();

        String query= "SELECT * FROM " + STORE_TABLE_NAME + " WHERE " + COL_STORE_NAME + " LIKE '" + search + "%'";

        //String query = "SELECT " + COL_ID +"," + COL_STORE_NAME + " FROM " + STORE_TABLE_NAME + " WHERE " + COL_STORE_NAME +  " LIKE ?" + "%";


      Cursor cursor = db.rawQuery(query,null);
      if(cursor.moveToFirst()){
          while(!cursor.isAfterLast()){
              int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
              String storeName = cursor.getString(cursor.getColumnIndex(COL_STORE_NAME));
              String stars = cursor.getString(cursor.getColumnIndex(COL_STARS));
              String reviews = cursor.getString(cursor.getColumnIndex(COL_NUM_OF_REVIEWS));
              String photosID = cursor.getString(cursor.getColumnIndex(COL_STORE_LOGO));
              stores.add(new Store(id, storeName, stars, reviews, photosID));
              cursor.moveToNext();
          }
      }
        cursor.close();
        db.close();
        return stores;
    }


    public List<Shirt> getShirts() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SHIRT_LIST", null);
        List<Shirt> shirts = new ArrayList<>();
       // try {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex(COL_ID_TOP));
                    String shirtName = cursor.getString(cursor.getColumnIndex(COL_TOP_NAME));
                    String heart = cursor.getString(cursor.getColumnIndex(COL_HEART_TOP));
                    String price = cursor.getString(cursor.getColumnIndex(COL_TOP_PRICE));
                    String shirtPhotosID = cursor.getString(cursor.getColumnIndex(COL_TOP_PHOTO));
                    String storeNameShirt = cursor.getString(cursor.getColumnIndex(COL_STORE_NAME_SHIRT));
                   // String storePhoto = cursor.getString(cursor.getColumnIndex(COL_STORE_PHOTO));

                    shirts.add(new Shirt(id, shirtName, heart, price, shirtPhotosID, storeNameShirt));

                    cursor.moveToNext();
                }
            }
          //  cursor.close();
          //  return shirts;

      //  }
      //  catch (IllegalStateException e) {
       //     e.printStackTrace();
      //  }
        cursor.close();
        return shirts;
    }


    public void addToDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        Store store = new Store ("Macy's" , "four_stars", "20 reviews", "macys_logo");
        Store store1 = new Store ("ZARA", "3 stars", "4 reviews", "zara_logo.png");
        Store store2 = new Store ("Coach", "5 stars", "22 reviews", "coach_logo.png");
        Store store3 = new Store ("3 stars", "Nordstrom", "25 reviews", "@drawable");
        Store store4 = new Store ("2 stars", "Express", "26 reviews","@drawable" );
        Store store5 = new Store ("4 stars", "H&M",  "47 reviews","@drawable" );
        Store store6 = new Store ("5 stars", "Bloomingdales", "3 reviews", "@drawable");
        Store store7 = new Store ("3 stars", "Saks Fith Avenue",  "2 reviews", "@drawable");
        Store store8 = new Store ("5 stars", "Guess", "0 reviews", "@drawable");
        Store store9 = new Store ("3 stars", "Ralph Lauren",  "33 reviews", "@drawable");
        Store store10 = new Store ("5 stars", "Target", "32 reviews", "@drawable");
        Store store11 = new Store ("4 stars", "Calvin Klein",  "2 reviews", "@drawable");
        Store store12 = new Store ("3 stars", "Old Navy",  "1 reviews","@drawable");

        insertRowStore(store);
        insertRowStore(store1);
        insertRowStore(store2);
        insertRowStore(store3);
        insertRowStore(store4);
        insertRowStore(store5);
        insertRowStore(store6);
        insertRowStore(store7);
        insertRowStore(store8);
        insertRowStore(store9);
        insertRowStore(store10);
        insertRowStore(store11);
        insertRowStore(store12);

    }

    public void addToShirts () {
        SQLiteDatabase db = getWritableDatabase();
        Shirt shirt = new Shirt ( "Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "Macys");
        Shirt shirt1 = new Shirt ("Off Shoulder Shirt" , "@drawableheart", "$6.00", "@drawablephoto", "ZARA");
        Shirt shirt2 = new Shirt ("Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "ZARA");
        Shirt shirt3 = new Shirt ("Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "Macys");
        Shirt shirt4 = new Shirt ("Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "ZARA");
        Shirt shirt5 = new Shirt ("Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "Macys");
        Shirt shirt6 = new Shirt ("Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "Bloomingdales");
        Shirt shirt7 = new Shirt ("Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "Bloomingdales");
        Shirt shirt8 = new Shirt ("Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "ZARA");
        Shirt shirt9 = new Shirt ("Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "Macys");
        Shirt shirt10 = new Shirt ("Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "Bloomingdales");
        Shirt shirt11 = new Shirt ("Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "Bloomingdales");
        Shirt shirt12 = new Shirt ("Tie Neck Tunic" , "@drawableheart", "$6.00", "@drawablephoto", "Bloomingdales");

        insertShirtRow(shirt);
        insertShirtRow(shirt1);
        insertShirtRow(shirt2);
        insertShirtRow(shirt3);
        insertShirtRow(shirt4);
        insertShirtRow(shirt5);
        insertShirtRow(shirt6);
        insertShirtRow(shirt7);
        insertShirtRow(shirt8);
        insertShirtRow(shirt9);
        insertShirtRow(shirt10);
        insertShirtRow(shirt11);
        insertShirtRow(shirt12);
    }
    
}
