package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by stacyzolnikov on 8/5/16.
 */
public class StoreManager {
    Context context;
    ArrayList<Store> stores;



    private static StoreManager instance;
    private StoreManager(final Context context){
        this.context = context;
        stores = new ArrayList<Store>();

        AsyncTask<Context, Void, ArrayList<Store>> task1 = new AsyncTask<Context, Void, ArrayList<Store>>() {
            @Override
            protected ArrayList<Store> doInBackground(Context... contexts) {
                return (ArrayList<Store>) DatabaseHelper.getInstance(contexts[0]).getStores();}

            @Override
            protected void onPostExecute(ArrayList<Store> stores) {
                super.onPostExecute(stores);
            }

        };
        task1.execute(this.context);
        }

    public static StoreManager getInstance(Context context) {
        if (instance == null) {
            instance = new StoreManager(context);
        }
        return instance;

    }

    public void updateSearch (String search) {
        stores = DatabaseHelper.getInstance(context).searchStoreList(search);
        Toast.makeText(context, "Test", Toast.LENGTH_LONG).show();

    }
    public void forceRecyclerViewRefresh(String storeName){
        for (Store store : stores){
            if (store.getStoreName().equals(storeName)){
                store.setStoreName((store.getStoreName() + ""));
                break;
            }
        }
    }
}
