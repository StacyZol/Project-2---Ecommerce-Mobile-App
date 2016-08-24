package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mButtonLetsGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //I dont' think I need this here because it is not on the home screen, but it is in the others
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.addToShirts();


        //This is to go from the Main Home Page to the second activity
        mButtonLetsGo = (Button) findViewById(R.id.LetsGoButton);
        mButtonLetsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StoreActivity.class);
                startActivity(intent);
                // addToDataBase();
            }

        });
    }

    //below is to add to the database
    //need to figure out how to put it in there though
    //Instead  of putting in in the OnCreate method, I want the instance to happen when they click on a button because the idea is that they would be able to filter based on location.
    //It should probably be added to the resources tab instead
/*
    public void addToDataBase () {
        DatabaseHelper helper = DatabaseHelper.getInstance(MainActivity.this);
        stores store = new stores ("5 stars", "Macy's", "Latest fashion trends for Women & Man", "20 reviews", "R.drawable.macys_logo");
        stores store1 = new stores ("4 stars", "ZARA", "", "30 reviews", "@drawable");
        stores store2 = new stores ("5 stars", "Macy's", "Description for ZARA ", "22 reviews", "@drawable");
        stores store3 = new stores ("3 stars", "Nordstrom", "Description for Nordstrom ","25 reviews", "@drawable");
        stores store4 = new stores ("2 stars", "Express", "Description for Express ", "26 reviews","@drawable" );
        stores store5 = new stores ("4 stars", "H&M", "Description for H&M", "47 reviews","@drawable" );
        stores store6 = new stores ("5 stars", "Bloomingdales", "Description for Bloomingdales ", "3 reviews", "@drawable");
        stores store7 = new stores ("3 stars", "Saks Fith Avenue", "Description for Saks ", "2 reviews", "@drawable");
        stores store8 = new stores ("5 stars", "Guess", "Description for Guess ", "0 reviews", "@drawable");
        stores store9 = new stores ("3 stars", "Ralph Lauren", "Description for Ralph Lauren ", "33 reviews", "@drawable");
        stores store10 = new stores ("5 stars", "Target", "Description for Target", "32 reviews", "@drawable");
        stores store11 = new stores ("4 stars", "Calvin Klein", "Description for Calvin Klein", "2 reviews", "@drawable");
        stores store12 = new stores ("3 stars", "Old Navy", "Description for Old Navy", "1 reviews","@drawable");

        helper.insertRowStore(store);
        helper.insertRowStore(store1);
        helper.insertRowStore(store2);
        helper.insertRowStore(store3);
        helper.insertRowStore(store4);
        helper.insertRowStore(store5);
        helper.insertRowStore(store6);
        helper.insertRowStore(store7);
        helper.insertRowStore(store8);
        helper.insertRowStore(store9);
        helper.insertRowStore(store10);
        helper.insertRowStore(store11);
        helper.insertRowStore(store12);


    }
    */
}

