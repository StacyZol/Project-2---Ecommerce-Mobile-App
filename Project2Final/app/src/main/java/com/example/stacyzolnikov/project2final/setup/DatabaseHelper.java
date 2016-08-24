package com.example.stacyzolnikov.project2final.setup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.stacyzolnikov.project2final.objects.Flower;
import com.example.stacyzolnikov.project2final.objects.Herb;
import com.example.stacyzolnikov.project2final.objects.Master;
import com.example.stacyzolnikov.project2final.objects.Nursery;
import com.example.stacyzolnikov.project2final.objects.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stacyzolnikov on 8/20/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getCanonicalName();

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NURSERY_DB";

    //Table Names for Main Nursery List and 3 tables for the category items
    public static final String NURSERY_TABLE_NAME = "nurseryList";
    public static final String TREES_TABLE_NAME = "treeList";
    public static final String FLOWERS_TABLE_NAME = "flowerList";
    public static final String HERBS_TABLE_NAME = "herbList";

    //Columns for the stores list
    public static final String COL_ID_NURSERY = "_id";
    public static final String COL_NURSERY_NAME = "nurseryName";
    public static final String COL_NURSERY_STARS = "nurseryStars";
    public static final String COL_NURSERY_NUM_REVIEWS = "nurseryReviews";
    public static final String COL_NURSERY_LOGO = "nurseryLogo";

    public static final String[] NURSERY_COLUMNS = {COL_ID_NURSERY, COL_NURSERY_NAME, COL_NURSERY_STARS, COL_NURSERY_NUM_REVIEWS, COL_NURSERY_NUM_REVIEWS, COL_NURSERY_LOGO};

    //Columns for trees list
    public static final String COL_ID_TREE = "_id";
    public static final String COL_TREE_NAME = "treeName";
    public static final String COL_TREE_DESCRIPTION = "treeDescription";
    public static final String COL_TREE_PRICE = "treePrice";
    public static final String COL_TREE_REVIEWS = "treeReviews";
    public static final String COL_TREE_PHOTO = "treePhoto";
    public static final String[] TREE_COLUMNS = {COL_ID_TREE, COL_TREE_NAME, COL_TREE_DESCRIPTION, COL_TREE_REVIEWS, COL_TREE_PRICE, COL_TREE_PHOTO};

    //Columns for flowers list
    public static final String COL_ID_FLOWER = "_id";
    public static final String COL_FLOWER_NAME = "flowerName";
    public static final String COL_FLOWER_DESCRIPTION = "flowerDescription";
    public static final String COL_FLOWER_PRICE = "flowerPrice";
    public static final String COL_FLOWER_REVIEWS = "flowerReviews";
    public static final String COL_FLOWER_PHOTO = "flowerPhoto";

    //Columns for herbs list
    public static final String COL_ID_HERB = "_id";
    public static final String COL_HERB_NAME = "herbName";
    public static final String COL_HERB_DESCRIPTION = "herbDescription";
    public static final String COL_HERB_PRICE = "herbPrice";
    public static final String COL_HERB_REVIEWS = "herbReviews";
    public static final String COL_HERB_PHOTO = "herbPhoto";

    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context.getApplicationContext());
        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_NURSERY);
        db.execSQL(SQL_CREATE_ENTRIES_TREE);
        db.execSQL(SQL_CREATE_ENTRIES_FLOWER);
        db.execSQL(SQL_CREATE_ENTRIES_HERB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES_NURSERY);
        db.execSQL(SQL_DELETE_ENTRIES_TREE);
        db.execSQL(SQL_DELETE_ENTRIES_FLOWER);
        db.execSQL(SQL_DELETE_ENTRIES_HERB);
    }


    //Creating the entries for tables
    private static final String SQL_CREATE_ENTRIES_NURSERY =
            "CREATE TABLE " + NURSERY_TABLE_NAME +
                    " (" +
                    COL_ID_NURSERY + " INTEGER PRIMARY KEY," +
                    COL_NURSERY_NAME + " TEXT," +
                    COL_NURSERY_STARS + " TEXT," +
                    COL_NURSERY_NUM_REVIEWS + " TEXT," +
                    COL_NURSERY_LOGO + " TEXT" +
                    ")";

    private static final String SQL_CREATE_ENTRIES_TREE =
            "CREATE TABLE " + TREES_TABLE_NAME +
                    " (" +
                    COL_ID_TREE + " INTEGER PRIMARY KEY," +
                    COL_TREE_NAME + " TEXT," +
                    COL_TREE_DESCRIPTION + " TEXT," +
                    COL_TREE_REVIEWS + " TEXT," +
                    COL_TREE_PHOTO + " TEXT," +
                    COL_TREE_PRICE + " TEXT" +
                    ")";
    private static final String SQL_CREATE_ENTRIES_FLOWER =
            "CREATE TABLE " + FLOWERS_TABLE_NAME +
                    " (" +
                    COL_ID_FLOWER + " INTEGER PRIMARY KEY," +
                    COL_FLOWER_NAME + " TEXT," +
                    COL_FLOWER_DESCRIPTION + " TEXT," +
                    COL_FLOWER_REVIEWS + " TEXT," +
                    COL_FLOWER_PHOTO + " TEXT," +
                    COL_FLOWER_PRICE + " TEXT" +
                    ")";
    private static final String SQL_CREATE_ENTRIES_HERB =
            "CREATE TABLE " + HERBS_TABLE_NAME +
                    " (" +
                    COL_ID_HERB + " INTEGER PRIMARY KEY," +
                    COL_HERB_NAME + " TEXT," +
                    COL_HERB_DESCRIPTION + " TEXT," +
                    COL_HERB_REVIEWS + " TEXT," +
                    COL_HERB_PHOTO + " TEXT," +
                    COL_HERB_PRICE + " TEXT" +
                    ")";

    //Deleting tables
    private static final String SQL_DELETE_ENTRIES_NURSERY = "DROP TABLE IF EXISTS " + NURSERY_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_TREE = "DROP TABLE IF EXISTS " + TREES_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_FLOWER = "DROP TABLE IF EXISTS " + FLOWERS_TABLE_NAME;
    private static final String SQL_DELETE_ENTRIES_HERB = "DROP TABLE IF EXISTS " + HERBS_TABLE_NAME;

    //Adding the rows to tables
    public void insertRowNursery(Nursery nursery) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NURSERY_NAME, nursery.getNurseryName());
        values.put(COL_NURSERY_NUM_REVIEWS, nursery.getNurseryReviews());
        values.put(COL_NURSERY_STARS, nursery.getNurseryStars());
        values.put(COL_NURSERY_LOGO, nursery.getNurseryPhoto());
        db.insertOrThrow(NURSERY_TABLE_NAME, "", values);
        db.close();
    }

    public void insertRowTree(Tree tree) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TREE_NAME, tree.getTreeName());
        values.put(COL_TREE_DESCRIPTION, tree.getTreeDescription());
        values.put(COL_TREE_REVIEWS, tree.getTreeReviews());
        values.put(COL_TREE_PHOTO, tree.getTreePhoto());
        values.put(COL_TREE_PRICE, tree.getTreePrice());
        db.insertOrThrow(TREES_TABLE_NAME, "", values);
        db.close();
    }

    public void insertRowFlower(Flower flower) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FLOWER_NAME, flower.getFlowerName());
        values.put(COL_FLOWER_DESCRIPTION, flower.getFlowerDescription());
        values.put(COL_FLOWER_REVIEWS, flower.getFlowerReviews());
        values.put(COL_FLOWER_PHOTO, flower.getFlowerPhoto());
        values.put(COL_FLOWER_PRICE, flower.getFlowerPrice());
        db.insertOrThrow(FLOWERS_TABLE_NAME, "", values);
        db.close();
    }

    public void insertRowHerb(Herb herb) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_HERB_NAME, herb.getHerbName());
        values.put(COL_HERB_DESCRIPTION, herb.getHerbDescription());
        values.put(COL_HERB_REVIEWS, herb.getHerbReviews());
        values.put(COL_HERB_PHOTO, herb.getHerbPhoto());
        values.put(COL_HERB_PRICE, herb.getHerbPrice());
        db.insertOrThrow(HERBS_TABLE_NAME, "", values);
        db.close();
    }

    public List<Nursery> getNurseries() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM nurseryList", null);
        List<Nursery> nursery = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_NURSERY));
                String nurseryName = cursor.getString(cursor.getColumnIndex(COL_NURSERY_NAME));
                String nurseryStars = cursor.getString(cursor.getColumnIndex(COL_NURSERY_STARS));
                String nurseryReviews = cursor.getString(cursor.getColumnIndex(COL_NURSERY_NUM_REVIEWS));
                String nurseryPhoto = cursor.getString(cursor.getColumnIndex(COL_NURSERY_LOGO));

                nursery.add(new Nursery(id, nurseryName, nurseryStars, nurseryReviews, nurseryPhoto));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return nursery;
    }

    public List<Tree> getTrees() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM treeList", null);
        List<Tree> tree = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_TREE));
                String treeName = cursor.getString(cursor.getColumnIndex(COL_TREE_NAME));
                String treeDescription = cursor.getString(cursor.getColumnIndex(COL_TREE_DESCRIPTION));
                String treeReviews = cursor.getString(cursor.getColumnIndex(COL_TREE_REVIEWS));
                String treePhoto = cursor.getString(cursor.getColumnIndex(COL_TREE_PHOTO));
                String treePrice = cursor.getString(cursor.getColumnIndex(COL_TREE_PRICE));

                tree.add(new Tree(id, treeName, treeDescription, treeReviews, treePhoto, treePrice));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return tree;
    }

    public List<Flower> getFlowers() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM flowerList", null);
        List<Flower> flower = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_FLOWER));
                String flowerName = cursor.getString(cursor.getColumnIndex(COL_FLOWER_NAME));
                String flowerDescription = cursor.getString(cursor.getColumnIndex(COL_FLOWER_DESCRIPTION));
                String flowerReviews = cursor.getString(cursor.getColumnIndex(COL_FLOWER_REVIEWS));
                String flowerPhoto = cursor.getString(cursor.getColumnIndex(COL_FLOWER_PHOTO));
                String flowerPrice = cursor.getString(cursor.getColumnIndex(COL_FLOWER_PRICE));

                flower.add(new Flower(id, flowerName, flowerDescription, flowerReviews, flowerPhoto, flowerPrice));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return flower;
    }

    public List<Herb> getHerbs() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM herbList", null);
        List<Herb> herb = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_HERB));
                String herbName = cursor.getString(cursor.getColumnIndex(COL_HERB_NAME));
                String herbDescription = cursor.getString(cursor.getColumnIndex(COL_HERB_DESCRIPTION));
                String herbReviews = cursor.getString(cursor.getColumnIndex(COL_HERB_REVIEWS));
                String herbPhoto = cursor.getString(cursor.getColumnIndex(COL_HERB_PHOTO));
                String herbPrice = cursor.getString(cursor.getColumnIndex(COL_HERB_PRICE));

                herb.add(new Herb(id, herbName, herbDescription, herbReviews, herbPhoto, herbPrice));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return herb;
    }

    //Search nurseries
    public ArrayList<Nursery> searchNurseryList(String search) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Nursery> nursery = new ArrayList<>();
        String query = "SELECT * FROM " + NURSERY_TABLE_NAME + " WHERE " + COL_NURSERY_NAME + " LIKE '" + search + "%'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_NURSERY));
                String nurseryName = cursor.getString(cursor.getColumnIndex(COL_NURSERY_NAME));
                String nurseryStars = cursor.getString(cursor.getColumnIndex(COL_NURSERY_STARS));
                String nurseryReviews = cursor.getString(cursor.getColumnIndex(COL_NURSERY_NUM_REVIEWS));
                String nurseryPhoto = cursor.getString(cursor.getColumnIndex(COL_NURSERY_LOGO));

                nursery.add(new Nursery(id, nurseryName, nurseryStars, nurseryReviews, nurseryPhoto));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return nursery;
    }
    //Search MasterList(adding Trees to MasterList)
    public ArrayList<Master> searchTreeList(String search) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Master> master = new ArrayList<>();
       // ArrayList<Tree> tree= new ArrayList<>();
        String query = "SELECT * FROM " + TREES_TABLE_NAME + " WHERE " + COL_TREE_NAME + " LIKE '" + search + "%'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_TREE));
                String treeName = cursor.getString(cursor.getColumnIndex(COL_TREE_NAME));
                String treeDescription = cursor.getString(cursor.getColumnIndex(COL_TREE_DESCRIPTION));
                String treeReviews = cursor.getString(cursor.getColumnIndex(COL_TREE_REVIEWS));
                String treePhoto = cursor.getString(cursor.getColumnIndex(COL_TREE_PHOTO));
                String treePrice = cursor.getString(cursor.getColumnIndex(COL_TREE_PRICE));

               master.add(new Tree(id, treeName, treeDescription, treePrice, treePhoto));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return master;
    }
    //Search MasterList(adding Flowers to MasterList)
    public ArrayList<Master> searchFlowerList(String search) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Master> master= new ArrayList<>();
        String query = "SELECT * FROM " + FLOWERS_TABLE_NAME + " WHERE " + COL_FLOWER_NAME + " LIKE '" + search + "%'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_FLOWER));
                String flowerName = cursor.getString(cursor.getColumnIndex(COL_FLOWER_NAME));
                String flowerDescription = cursor.getString(cursor.getColumnIndex(COL_FLOWER_DESCRIPTION));
                String flowerReviews = cursor.getString(cursor.getColumnIndex(COL_FLOWER_REVIEWS));
                String flowerPhoto = cursor.getString(cursor.getColumnIndex(COL_FLOWER_PHOTO));
                String flowerPrice = cursor.getString(cursor.getColumnIndex(COL_FLOWER_PRICE));

                master.add(new Flower(id, flowerName, flowerDescription, flowerPrice, flowerPhoto));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return master;
    }
    //Search MasterList (adding Herbs to MasterList)
    public ArrayList<Master> searchHerbList(String search) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Master> master= new ArrayList<>();
        String query = "SELECT * FROM " + HERBS_TABLE_NAME + " WHERE " + COL_HERB_NAME + " LIKE '" + search + "%'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndex(COL_ID_HERB));
                String herbName = cursor.getString(cursor.getColumnIndex(COL_HERB_NAME));
                String herbDescription = cursor.getString(cursor.getColumnIndex(COL_HERB_DESCRIPTION));
                String herbReviews = cursor.getString(cursor.getColumnIndex(COL_HERB_REVIEWS));
                String herbPhoto = cursor.getString(cursor.getColumnIndex(COL_HERB_PHOTO));
                String herbPrice = cursor.getString(cursor.getColumnIndex(COL_HERB_PRICE));

                master.add(new Herb(id,herbName, herbDescription, herbPrice, herbPhoto));
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return master;
    }

    public void addNurseries() {
        SQLiteDatabase db = getWritableDatabase();
        Nursery nursery = new Nursery("Tropical Plants and Orchids Inc", "four_stars", "20 reviews", "tropical_plants_and_orchids");
        Nursery nursery1 = new Nursery("Chelsea Garden", "four_stars", "4 reviews", "chelsea_garden");
        Nursery nursery2 = new Nursery("Plantworks", "one_five_stars", "22 reviews", "liberty_center");
        Nursery nursery3 = new Nursery("Foliage Paradise", "three_five_stars", "25 reviews", "logotree");
        Nursery nursery4 = new Nursery("Foliage Garden", "four_stars", "26 reviews", "chelsea_garden");
        Nursery nursery5 = new Nursery("Noble Plants", "four_stars", "47 reviews", "nursery_five");
        Nursery nursery6 = new Nursery("Saifee Hardware & Garden", "four_stars", "3 reviews", "logotree");
        Nursery nursery7 = new Nursery("David Shannon Florist & Nursery", "four_stars", "2 reviews", "liberty_center");
        Nursery nursery8 = new Nursery("Planter Resources Inc", "four_stars", "0 reviews", "tropical_plants_and_orchids");
        Nursery nursery9 = new Nursery("Liberty Sunset Garden Center", "four_stars", "33 reviews", "liberty_center");
        Nursery nursery10 = new Nursery("Urban Garden Center", "four_stars", "32 reviews", "nursery_five");
        Nursery nursery11 = new Nursery("Kings County Nurseries Inc", "four_stars", "2 reviews", "treeLogo");
        Nursery nursery12 = new Nursery("Garden World", "four_stars", "1 reviews", "liberty_center");

        insertRowNursery(nursery);
        insertRowNursery(nursery1);
        insertRowNursery(nursery2);
        insertRowNursery(nursery3);
        insertRowNursery(nursery4);
        insertRowNursery(nursery5);
        insertRowNursery(nursery6);
        insertRowNursery(nursery7);
        insertRowNursery(nursery8);
        insertRowNursery(nursery9);
        insertRowNursery(nursery10);
        insertRowNursery(nursery11);
        insertRowNursery(nursery12);
    }

    public void addTrees() {
        SQLiteDatabase db = getWritableDatabase();
        //price can be selected by root type
        Tree tree = new Tree("Emerald Arborvitae", "Great for hedges or screens,shimmering emerald green foliage", "reviews", "black_tupelo", "9.50");
        Tree tree1 = new Tree("Woodward Globe Arborvitae", "Round-shaped shrub, great choice for a hedge, fine-textured.", "reviews", "woodward_globe_arborvitae", "8.00");
        Tree tree2 = new Tree("Early Golden Apricot", "Self-fertile variety, great for fresh eating, baking, drying. Early bloom time.", "reviews", "early_golden_apricot", "19.50");
        Tree tree3 = new Tree("Baldcypress", "Magestic orange-red fall color, great urban tree, adaptable to wet or dry conditions.", "reviews", "baldcypress", "10.00");
        Tree tree4 = new Tree("Pink Azaleas Mollis Hybrid", "Spectacular clusters of pink blossoms. Colorful fall foliage.", "reviews", "pink_azaleas_mollis_hybrid", "7.00");
        Tree tree5 = new Tree("European Beech", "Year-round beauty", "reviews", "european_beech", "9.00");
        Tree tree6 = new Tree("Black Tupelo", "One of the most attractive trees around. Spectacular fall foliage.", "reviews", "black_tupelo", "9.49");
        Tree tree7 = new Tree("Red Buckeye", "Deep red springtime flowers, large dark green leaves, blooms at an early age. ", "reviews", "red_buckeye", "10.98");
        Tree tree8 = new Tree("Northern Catalpa", "Showy white flowers that bloom in late spring, fast-growing tree.", "reviews", "northern_catalpa", "12.00");
        Tree tree9 = new Tree("Kanzan Cherry", "Gorgeous double pink flowers, yellow, orange and coppy fall color. Fruitless cultivar.", "reviews", "kwanzan_cherry", "15.00");
        Tree tree10 = new Tree("Chinese Chestnut", "Sweet-flavored nut, highly resistant to chestnut blight.", "reviews", "chinese_chestnut", "13.50");
        Tree tree11 = new Tree("Deodar Cedar", "Interesting branching patterns, excellent warm climate evergreen.", "reviews", "deodar_cedar", "5.50");
        Tree tree12 = new Tree("Japanese Flowering Cherry", "Also known as Yoshino Cherry, fragrant, white pink flowers.", "reviews", "japanese_flowering_cherry", "19.50");

        insertRowTree(tree);
        insertRowTree(tree1);
        insertRowTree(tree2);
        insertRowTree(tree3);
        insertRowTree(tree4);
        insertRowTree(tree5);
        insertRowTree(tree6);
        insertRowTree(tree7);
        insertRowTree(tree8);
        insertRowTree(tree9);
        insertRowTree(tree10);
        insertRowTree(tree11);
        insertRowTree(tree12);

    }

    public void addFlowers() {
        SQLiteDatabase db = getWritableDatabase();

        //Flowers should be purchased by packets(seed quantity)
        //There is an error somewhere that takes in the flower photo in column 3 as opposed to 4. It SHOULD be column 4 as the others but I made an error somewhere and unable to fix it at the moment.
        Flower flower = new  Flower("Cornflower", "NOTE: CURRENTLY OUT OF STOCK. Fragrant mixture of well-doubled flowers on long, strong stems.", "prod_one", "black_tupelo", "3.95");
        Flower flower1 = new Flower("Calla Lilly, Black Star", "Perfect for use in a container on the patio or planted in the garden border.", "prod_two", "european_beech", "19.95");
        Flower flower2 = new Flower("Coleus", "This impressive variety performs well in planters and small space gardens in both sun and shade.", "prod_three", "prod_three", "4.95");
        Flower flower3 = new Flower("Four O'Clock", "Rare, striped, trumpet-shaped flowers with a sweet, orange blossom scent.", "prod_four", "four_o_clocks", "4.95");
        Flower flower4 = new Flower("Begonia", "One of the few plants that bloom under almost any light conditions.", "prod_one", "deodar_cedar", "5.95");
        Flower flower5 = new Flower("Gazania", "Perfect for a hot summer climate. Spectacular array of boldy striped blossoms.", "prod_four", "reviews", "4.95");
        Flower flower6 = new Flower("Impatiens", "Huge, full 2' blooms arrive early and stay all season long.", "prod_four", "@drawablephoto", "3.95");
        Flower flower7 = new Flower("Marigold, Snowball Hybrid", "Strong stemmed upright plants. Grow in full sun space.", "prod_three", "@drawablephoto", "5.95");
        Flower flower8 = new Flower("Hibiscus, Pink Swirl", "Gigantic blooms in swirling shades of pink, rose, and cranberry red. Remarkably easy to grow and fast blooming.", "prod_five", "@drawablephoto", "5.95");
        Flower flower9 = new Flower("Sunflower, Candy Mountain Hybrid", "Flowers and blooms on every leaf node. Fine choice for small spaces or some vertical interest.", "prod_six", "@drawablephoto", "6.95");
        Flower flower10 = new Flower("Elephant Ear, Black", "Bring the tropics into your garden with this large-leaved tropical accent plant.", "prod_two", "@drawablephoto", "17.95");
        Flower flower11 = new Flower("Paeonia, Sorbet", "Buy any 3 Perennial plants or buls and save 20%!!! Enticing double blooms are ringed with soft ping and ivory petals.", "prod_one", "@drawablephoto", "16.35");
        Flower flower12 = new Flower("Rose, Young Lycidas", "Large blooms of very deep magenta, pink and red with strong fragrance.", "prod_three", "@drawablephoto", "34.95");

        insertRowFlower(flower);
        insertRowFlower(flower1);
        insertRowFlower(flower2);
        insertRowFlower(flower3);
        insertRowFlower(flower4);
        insertRowFlower(flower5);
        insertRowFlower(flower6);
        insertRowFlower(flower7);
        insertRowFlower(flower8);
        insertRowFlower(flower9);
        insertRowFlower(flower10);
        insertRowFlower(flower11);
        insertRowFlower(flower12);

    }

    public void addHerbs() {
        SQLiteDatabase db = getWritableDatabase();
        Herb herb = new Herb("Rosemary Arp", "One of the hardiest of the upright forms. The foliage is light gray-green with blue flowers.", "reviews", "rosemary_arp", "6.95");
        Herb herb1 = new Herb("Mint Chocolate", "Good peppermint flavor with rich aromatic overtone of chocolate.", "reviews", "mint_chocolate", "6.95");
        Herb herb2 = new Herb("Tarragon French", "Large framed plants. Pineapple fragrance. Attractive to honeybees and hummingbirds.", "reviews", "kwanzan_cherry", "6.45");
        Herb herb3 = new Herb("Sage Pineapple", "Spicy anise-like flavor. Used for salads, fish, eggs, and vinegars.", "reviews", "rosemary_arp", "7.95");
        Herb herb4 = new Herb("Saffron Bulbs", "World's rarest and most expensive spice. Flower in late fall. ", "reviews", "mint_chocolate", "6.45");
        Herb herb5 = new Herb("Thyme English", "Used in pork, veal, soups and stuffings. Traditional seasoning in clam and fish chowders.", "reviews", "red_buckeye", "6.95");
        Herb herb6 = new Herb("Aristotle Basil", "Vigorous, fragrant and savory fine leaved new Greek Basil.", "reviews", "japanese_flowering_cherry", "2.65");
        Herb herb7 = new Herb("Anise", "Dainty white rounded flower clusters or umbels atop feather like green leaves.", "reviews", "mint_chocolate", "2.45");
        Herb herb8 = new Herb("Hop Root Cascade", "NOTE: CURRENTLY OUT OF STOCK", "reviews", "mint_chocolate", "6.95");
        Herb herb9 = new Herb("Comfrey Roots", "NOTE:WILL SHIP IN SPRING 2017", "reviews", "red_buckeye", "7.95");
        Herb herb10 = new Herb("Walla Walla", "The gourmet onion of the Pacific Northwest. The largest and most fragrant of the onions.", "reviews", "rosemary_arp", "11.45");
        Herb herb11 = new Herb("Greek Dwarf Basil", "Sweetly fragrant tiny leaves, tight compact bushes.", "reviews", "northern_catalpa", "2.45");
        Herb herb12 = new Herb("Mexican Cinnamon Spice Basil", "A refined sweet spicy aroma ideal for fruits, jellies, cookies and cheesecakes. ", "reviews", "mint_chocolate", "2.25");

        insertRowHerb(herb);
        insertRowHerb(herb1);
        insertRowHerb(herb2);
        insertRowHerb(herb3);
        insertRowHerb(herb4);
        insertRowHerb(herb5);
        insertRowHerb(herb6);
        insertRowHerb(herb7);
        insertRowHerb(herb8);
        insertRowHerb(herb9);
        insertRowHerb(herb10);
        insertRowHerb(herb11);
        insertRowHerb(herb12);

    }


}
