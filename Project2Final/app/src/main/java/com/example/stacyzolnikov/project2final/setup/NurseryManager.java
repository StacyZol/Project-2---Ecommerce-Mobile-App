package com.example.stacyzolnikov.project2final.setup;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.stacyzolnikov.project2final.objects.Nursery;

import java.util.ArrayList;

/**
 * Created by stacyzolnikov on 8/20/16.
 */
public class NurseryManager {
    Context context;
    ArrayList<Nursery> nurseries;
    private static NurseryManager instance;
    private NurseryManager(final Context context){
        this.context = context;
        nurseries = new ArrayList<Nursery>();


        AsyncTask<Context, Void, ArrayList<Nursery>> task1 = new AsyncTask<Context, Void, ArrayList<Nursery>>() {
            @Override
            protected ArrayList<Nursery> doInBackground(Context... contexts) {
                return (ArrayList<Nursery>) DatabaseHelper.getInstance(contexts[0]).getNurseries();}

            @Override
            protected void onPostExecute(ArrayList<Nursery> stores) {
                super.onPostExecute(nurseries);
            }

        };
        task1.execute(this.context);
    }

    public static NurseryManager getInstance(Context context) {
        if (instance == null) {
            instance = new NurseryManager(context);
        }
        return instance;

    }

    public void updateSearch (String search) {
        nurseries = DatabaseHelper.getInstance(context).searchNurseryList(search);
        Toast.makeText(context, "Test", Toast.LENGTH_LONG).show();

    }
    public void forceRecyclerViewRefresh(String nurseryName){
        for (Nursery nursery : nurseries){
            if (nursery.getNurseryName().equals(nurseryName)){
                nursery.setNurseryName((nursery.getNurseryName() + ""));
                break;
            }
        }
    }


}
