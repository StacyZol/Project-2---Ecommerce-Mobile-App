package com.example.stacyzolnikov.project2shoppinglist2;


import android.app.DialogFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by stacyzolnikov on 7/27/16.
 */
public class PlaceHolderFragment extends Fragment {
    Context mContext;
    public OnListItemClickListener mListItemClickListener;
    private int tabPosition;
    private final String TAB_NUMBER = "default_value";
    RecyclerView mRecyclerview;
    AsyncTask<Void, Void, List> task;
    ProgressBar mProgressBar2;
    TextView mTextView2;


    public PlaceHolderFragment() {}

    public PlaceHolderFragment(Context mContext) {
        this.mContext = mContext;
    }



    public interface OnListItemClickListener {
      void OnListItemClicked(int tabPosition, int listPosition);
       // void onListItemClicked();
    }
//
  //Below is for the Dialog
    private void showDialog() {


    }

    public static PlaceHolderFragment newInstance(int tab_number, OnListItemClickListener listener) {
        PlaceHolderFragment placeHolderFragment = new PlaceHolderFragment();
        placeHolderFragment.tabPosition = tab_number;
        placeHolderFragment.mListItemClickListener = listener;
        //tabPosition = tab_number;
        //mListItemClickListener = listener;

     //   Bundle bundle = new Bundle();
     //   bundle.putInt(TAB_NUMBER, tab_number);
//
     //   placeHolderFragment.setArguments(bundle);
     //
        return placeHolderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = null;
    // int tabPosition = getArguments().getInt(TAB_NUMBER, 0);
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.ShirtsRecyclerView);


//        mContext = container.getContext();
        mContext = getActivity();
        final DatabaseHelper[] databaseHelper = {DatabaseHelper.getInstance(mContext)};
        //For progress bar and text view


        //Toast.makeText(mContext, "HAHHHHHHH", Toast.LENGTH_SHORT).show();


        mProgressBar2 = (ProgressBar) view.findViewById(R.id.StoreProgressBar);
        mTextView2 = (TextView)  view.findViewById(R.id.NumberOfShirtsText);

        switch (tabPosition) {
            case 0:
                RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                mRecyclerview.setLayoutManager(linearLayoutManager);
               // databaseHelper = DatabaseHelper.getInstance(mContext);
                Log.d("testfragment1", databaseHelper[0].getShirts().get(0).getHeart());

              //  Toast.makeText(mContext, "Fragment1", Toast.LENGTH_SHORT).show();
             //  RecyclerViewClothesAdapter adapter = new RecyclerViewClothesAdapter(databaseHelper.getShirts(), R.layout.fragment_lists, mContext);

                databaseHelper[0] = DatabaseHelper.getInstance(mContext);
                RecyclerViewClothesAdapter adapter = new RecyclerViewClothesAdapter(databaseHelper[0].getShirts(), R.layout.fragment_lists, mContext);

                mRecyclerview.setAdapter(adapter);

                task = new AsyncTask<Void, Void, List>() {
                    @Override
                    protected List doInBackground(Void... voids) {
                       return databaseHelper[0].getShirts();
                      //  publishProgress();
                    }

                    @Override
                    protected void onProgressUpdate(Void... values) {
                        super.onProgressUpdate(values);
                        mTextView2.setText("Uploading Shirts " + values[0]);
                    }

                    @Override
                    protected void onPostExecute(List list) {
                        super.onPostExecute(list);
                        int count = DatabaseHelper.getInstance(getContext()).getShirts().size();
                       // mProgressBar2.setVisibility(View.INVISIBLE);
                        mTextView2.setText("Shirts found: " +count);

                    }
                };
                task.execute();

                break;
           case 1:
               RecyclerView.LayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
               mRecyclerview.setLayoutManager(linearLayoutManager1);
               DatabaseHelper databaseHelper1 = DatabaseHelper.getInstance(mContext);
               RecyclerViewClothesAdapter adapter1 = new RecyclerViewClothesAdapter(databaseHelper1.getShirts(), R.layout.fragment_lists, mContext);
               mRecyclerview.setAdapter(adapter1);
               Log.d("testFragment2", databaseHelper[0].getShirts().get(0).getHeart());
             //  Toast.makeText(mContext, "Fragment2", Toast.LENGTH_SHORT).show();
               break;
            case 2:
                RecyclerView.LayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext);
                mRecyclerview.setLayoutManager(linearLayoutManager2);
                DatabaseHelper databaseHelper2 = DatabaseHelper.getInstance(mContext);
                RecyclerViewClothesAdapter adapter2 = new RecyclerViewClothesAdapter(databaseHelper2.getShirts(), R.layout.fragment_lists, mContext);
                mRecyclerview.setAdapter(adapter2);
                Log.d("testfragment3", databaseHelper[0].getShirts().get(0).getHeart());
               // Toast.makeText(mContext, "Fragment3", Toast.LENGTH_SHORT).show();
                break;

        }


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}


