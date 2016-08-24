package com.example.stacyzolnikov.project2final.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stacyzolnikov.project2final.R;


import com.example.stacyzolnikov.project2final.objects.Master;
import com.example.stacyzolnikov.project2final.objects.Tree;
import com.example.stacyzolnikov.project2final.setup.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    Button mButtonGetStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        //Loading in the list of trees, herbs, and flowers
        databaseHelper.addTrees();
        databaseHelper.addFlowers();
        databaseHelper.addHerbs();
        mButtonGetStarted = (Button) findViewById(R.id.LetsGoButton);
        mButtonGetStarted.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NurseryActivity.class);
                startActivity(intent);
            }
        });
    }
}
