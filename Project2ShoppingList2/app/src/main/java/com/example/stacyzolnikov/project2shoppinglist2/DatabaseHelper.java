package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 7/23/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private DatabaseHelper mHelper;
    private static final String TAG = DatabaseHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "STORE_DB";

    //Below is tables for store. will need to make 3 more tables (or more) for items
    public static final String STORE_TABLE_NAME = "STORE_LIST";
    public static final String SHIRT_TABLE_NAME = "SHIRT_LIST";
    public static final String FLOWER_TABLE_NAME = "FLOWER_LIST";
    public static final String HERB_TABLE_NAME = "HERB_LIST";

    //Below are the columns for the stores List Table
    public static final String COL_ID = "_id";
    public static final String COL_STARS = "STARS";
    public static final String COL_STORE_NAME = "STORE_NAME";
    public static final String COL_STORE_LOGO = "STORE_LOGO";
    public static final String COL_NUM_OF_REVIEWS = "NUM_OF_REVIEWS";

    public static final String[] STORE_COLUMNS = {COL_ID, COL_STARS, COL_STORE_NAME, COL_STORE_LOGO, COL_NUM_OF_REVIEWS};

    //Below are the columns for the Items CategoryOne List Table
    public static final String COL_ID_TOP = "_id";
    public static final String COL_HEART_TOP = "HEART_TOP";
    public static final String COL_TOP_NAME = "TOP_NAME";
    public static final String COL_TOP_PHOTO = "TOP_PHOTO";
    public static final String COL_TOP_PRICE = "TOP_PRICE";
    public static final String COL_STORE_NAME_SHIRT = "STORE_NAME_SHIRT";
   // public static final String COL_STORE_PHOTO = "STORE_PHOTO";

    public static final String[] TOP_COLUMNS = {COL_ID_TOP, COL_HEART_TOP, COL_TOP_NAME, COL_TOP_PHOTO, COL_TOP_PRICE, COL_STORE_NAME_SHIRT};


    //Below are the columns for Item # 2 Table
    public static final String COL_ID_FLOWER = "_id";
   // public static final String COL_FLOWER_STARS = "FLOWER_STARS";
    public static final String COL_FLOWERS_HEART = "FLOWERS_HEART";
    public static final String COL_FLOWER_NAME = "FLOWER_NAME";
    public static final String COL_FLOWER_PHOTO = "FLOWER_PHOTO";
    public static final String COL_FLOWER_PRICE = "FLOWER_PRICE";
    public static final String COL_FLOWER_STORE_NAME = "FLOWER_STORE_NAME";
    public static final String[] FLOWER_COLUMNS = {COL_ID_FLOWER,COL_FLOWERS_HEART, COL_FLOWER_NAME, COL_FLOWER_PHOTO, COL_FLOWER_PRICE, COL_FLOWER_STORE_NAME};


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
        db.execSQL (SQL_CREATE_ENTRIES_FLOWER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES_STORE);
        db.execSQL(SQL_DELETE_ENTRIES_SHIRT);
        db.execSQL(SQL_DELETE_ENTRIES_FLOWER);
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


    //Below is creating the table for the Tree

    private static final String SQL_CREATE_ENTRIES_SHIRT =
            "CREATE TABLE " + SHIRT_TABLE_NAME +
                    " (" +
                    COL_ID_TOP + " INTEGER PRIMARY KEY," +
                    COL_TOP_NAME + " TEXT," +
                    COL_HEART_TOP + " TEXT," +
                    COL_TOP_PRICE + " TEXT," +
                    COL_TOP_PHOTO + " TEXT," +
                    COL_STORE_NAME_SHIRT + " TEXT" +
                    //COL_STORE_PHOTO + " TEXT" +
                    ")";
    //Below is creating the table for the Flower
    private static final String SQL_CREATE_ENTRIES_FLOWER =
            "CREATE TABLE " + FLOWER_TABLE_NAME +
                    " (" +
                    COL_ID_FLOWER + " INTEGER PRIMARY KEY," +
                    COL_FLOWER_NAME + " TEXT," +
                    COL_FLOWERS_HEART + " TEXT," +
                    COL_FLOWER_PRICE + " TEXT," +
                    COL_FLOWER_PHOTO + " TEXT," +
                    COL_FLOWER_STORE_NAME + " TEXT" +
                    //COL_STORE_PHOTO + " TEXT" +
                    ")";


    //Need to add for other tables

    private static final String SQL_DELETE_ENTRIES_STORE = "DROP TABLE IF EXISTS " + STORE_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_SHIRT = "DROP TABLE IF EXISTS " + SHIRT_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_FLOWER = "DROP TABLE IF EXISTS " + FLOWER_TABLE_NAME;

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

    public void insertShirtRow (Tree tree) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_HEART_TOP, tree.getHeart());
        values.put(COL_TOP_NAME, tree.getShirtName());
        values.put(COL_TOP_PHOTO, tree.getShirtPhotosID());
        values.put(COL_TOP_PRICE, tree.getPrice());
        //values.put(COL_STORE_NAME_SHIRT, tree.getStoreNameShirt());
        //values.put(COL_STORE_PHOTO, tree.getStorePhoto());
        db.insertOrThrow(SHIRT_TABLE_NAME,"", values);
        db.close();

    }

   public void insertFlowerRow (Flower flower){
       SQLiteDatabase db = getWritableDatabase();
       ContentValues values = new ContentValues();
       values.put(COL_FLOWER_NAME, flower.getFlowerHeart());
       values.put(COL_FLOWERS_HEART, flower.getFlowerHeart());
       values.put(COL_FLOWER_PHOTO, flower.getflowerPhotosID());
       values.put(COL_FLOWER_PRICE,flower.getFlowerPrice());
       db.insertOrThrow(FLOWER_TABLE_NAME,"",values);
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


    public List<Tree> getShirts() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SHIRT_LIST", null);
        List<Tree> trees = new ArrayList<>();
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    int id = cursor.getInt(cursor.getColumnIndex(COL_ID_TOP));
                    String shirtName = cursor.getString(cursor.getColumnIndex(COL_TOP_NAME));
                    String heart = cursor.getString(cursor.getColumnIndex(COL_HEART_TOP));
                    String price = cursor.getString(cursor.getColumnIndex(COL_TOP_PRICE));
                    String shirtPhotosID = cursor.getString(cursor.getColumnIndex(COL_TOP_PHOTO));
                    String storeNameShirt = cursor.getString(cursor.getColumnIndex(COL_STORE_NAME_SHIRT));
                   // String storePhoto = cursor.getString(cursor.getColumnIndex(COL_STORE_PHOTO));

                    trees.add(new Tree(id, shirtName, heart, price, shirtPhotosID, storeNameShirt));

                    cursor.moveToNext();
                }
            }
        cursor.close();
        return trees;
    }
    public List<Flower> getFlowers(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM FLOWER_LIST", null);
        List<Flower> flowers = new ArrayList<>();
        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_FLOWER));
                String flowerName = cursor.getString(cursor.getColumnIndex(COL_FLOWER_NAME));
                String flowerHeart = cursor.getString(cursor.getColumnIndex(COL_FLOWERS_HEART));
                String flowerPrice = cursor.getString(cursor.getColumnIndex(COL_FLOWER_PRICE));
                String flowerPhotosID = cursor.getString(cursor.getColumnIndex(COL_FLOWER_PHOTO));
                String flowerStoreName = cursor.getString(cursor.getColumnIndex(COL_FLOWER_STORE_NAME));

                flowers.add(new Flower(id, flowerName, flowerHeart, flowerPrice, flowerPhotosID, flowerStoreName));
           cursor.moveToNext();
            }
        }
        cursor.close();
        return flowers;
    }


    public void addToDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        Store store = new Store ("Tropical Plants and Orchids Inc" , "four_stars", "20 reviews", "tree_one");
        Store store1 = new Store ("Chelsea Garden", "four_stars", "4 reviews", "tree_two");
        Store store2 = new Store ("Plantworks", "one_five_stars", "22 reviews", "tree_three");
        Store store3 = new Store ("Foliage Paradise", "three_five_stars", "25 reviews", "@drawable");
        Store store4 = new Store ("Foliage Garden", "four_stars", "26 reviews","@drawable" );
        Store store5 = new Store ("Noble Plants", "four_stars",  "47 reviews","@drawable" );
        Store store6 = new Store ("Saifee Hardware & Garden", "four_stars", "3 reviews", "@drawable");
        Store store7 = new Store ("David Shannon Florist & Nursery", "four_stars",  "2 reviews", "@drawable");
        Store store8 = new Store ("Planter Resources Inc", "four_stars", "0 reviews", "@drawable");
        Store store9 = new Store ("Liberty Sunset Garden Center", "four_stars",  "33 reviews", "@drawable");
        Store store10 = new Store ("Urban Garden Center", "four_stars", "32 reviews", "@drawable");
        Store store11 = new Store ("Kings County Nurseries Inc", "four_stars",  "2 reviews", "@drawable");
        Store store12 = new Store ("Garden World", "four_stars",  "1 reviews","@drawable");

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
        Tree tree = new Tree( "Item One" , "@drawableheart", "6.00", "@drawablephoto", "Macys");
        Tree tree1 = new Tree("Item Two" , "@drawableheart", "12.00", "@drawablephoto", "ZARA");
        Tree tree2 = new Tree("Item Three" , "@drawableheart", "14.00", "@drawablephoto", "ZARA");
        Tree tree3 = new Tree("Item Four" , "@drawableheart", "24.00", "@drawablephoto", "Macys");
        Tree tree4 = new Tree("Item Five" , "@drawableheart", "4.00", "@drawablephoto", "ZARA");
        Tree tree5 = new Tree("Item Six" , "@drawableheart", "5.00", "@drawablephoto", "Macys");
        Tree tree6 = new Tree("Item Seven" , "@drawableheart", "4.00", "@drawablephoto", "Bloomingdales");
        Tree tree7 = new Tree("Item Eight" , "@drawableheart", "24", "@drawablephoto", "Bloomingdales");
        Tree tree8 = new Tree("Item Nine" , "@drawableheart", "24", "@drawablephoto", "ZARA");
        Tree tree9 = new Tree("Item Ten" , "@drawableheart", "24", "@drawablephoto", "Macys");
        Tree tree10 = new Tree("Item Eleven" , "@drawableheart", "24", "@drawablephoto", "Bloomingdales");
        Tree tree11 = new Tree("Item Twelve" , "@drawableheart", "24", "@drawablephoto", "Bloomingdales");
        Tree tree12 = new Tree("Item Thirteen" , "@drawableheart", "24", "@drawablephoto", "Bloomingdales");

        insertShirtRow(tree);
        insertShirtRow(tree1);
        insertShirtRow(tree2);
        insertShirtRow(tree3);
        insertShirtRow(tree4);
        insertShirtRow(tree5);
        insertShirtRow(tree6);
        insertShirtRow(tree7);
        insertShirtRow(tree8);
        insertShirtRow(tree9);
        insertShirtRow(tree10);
        insertShirtRow(tree11);
        insertShirtRow(tree12);
    }

    public void addToFlowers () {
        SQLiteDatabase db = getWritableDatabase();
        Flower flower = new Flower ( "Item One" , "@drawableheart", "7.00", "@drawablephoto", "Macys");
        Flower flower1 = new Flower ("Item Two" , "@drawableheart", "8.00", "@drawablephoto", "ZARA");
        Flower flower2 = new Flower ("Item Three" , "@drawableheart", "13.00", "@drawablephoto", "ZARA");
        Flower flower3 = new Flower ("Item Four" , "@drawableheart", "24.00", "@drawablephoto", "Macys");
        Flower flower4 = new Flower ("Item Five" , "@drawableheart", "4.00", "@drawablephoto", "ZARA");
        Flower flower5 = new Flower ("Item Six" , "@drawableheart", "5.00", "@drawablephoto", "Macys");
        Flower flower6 = new Flower ("Item Seven" , "@drawableheart", "4.00", "@drawablephoto", "Bloomingdales");
        Flower flower7 = new Flower ("Item Eight" , "@drawableheart", "24", "@drawablephoto", "Bloomingdales");
        Flower flower8 = new Flower ("Item Nine" , "@drawableheart", "24", "@drawablephoto", "ZARA");
        Flower flower9 = new Flower ("Item Ten" , "@drawableheart", "24", "@drawablephoto", "Macys");
        Flower flower10 = new Flower ("Item Eleven" , "@drawableheart", "24", "@drawablephoto", "Bloomingdales");
        Flower flower11 = new Flower ("Item Twelve" , "@drawableheart", "24", "@drawablephoto", "Bloomingdales");
        Flower flower12 = new Flower ("Item Thirteen" , "@drawableheart", "24", "@drawablephoto", "Bloomingdales");

        insertFlowerRow(flower);
        insertFlowerRow(flower1);
        insertFlowerRow(flower2);
        insertFlowerRow(flower3);
        insertFlowerRow(flower4);
        insertFlowerRow(flower5);
        insertFlowerRow(flower6);
        insertFlowerRow(flower7);
        insertFlowerRow(flower8);
        insertFlowerRow(flower9);
        insertFlowerRow(flower10);
        insertFlowerRow(flower11);
        insertFlowerRow(flower12);
    }

}
