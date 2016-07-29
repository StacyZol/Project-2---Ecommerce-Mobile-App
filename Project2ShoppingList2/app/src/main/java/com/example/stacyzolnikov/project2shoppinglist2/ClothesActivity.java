package com.example.stacyzolnikov.project2shoppinglist2;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import java.util.List;

public class ClothesActivity extends AppCompatActivity implements  PlaceHolderFragment.OnListItemClickListener {

    List<Shirt> arrayList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content_container,
                        MainFragment.newInstance(this)).commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SearchStores:
                return true;
            case R.id.ShoppingCart:
                return true;
            case R.id.NavBar:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//  @Override
//   public void onListItemClicked() {


//   }

   @Override
   public void OnListItemClicked(int tabPosition, int listPosition) {

   }
}
