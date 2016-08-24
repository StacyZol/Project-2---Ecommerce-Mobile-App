package com.example.stacyzolnikov.project2shoppinglist2;


import android.app.DialogFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
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
    RecyclerView mRecyclerView;
    RecyclerView mRecyclerViewFlowers;
    AsyncTask<Void, Void, List> task;
    DatabaseHelper databaseHelper;


    public PlaceHolderFragment() {}

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
        return placeHolderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;

        mContext = getActivity();
        switch (tabPosition) {
            case 0:
              //  view = inflater.inflate(R.layout.fragment_lists_flower, container, false);
              //  mRecyclerViewFlowers = (RecyclerView) view.findViewById(R.id.FlowersRecyclerView);
              //  RecyclerView.LayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
              //  mRecyclerViewFlowers.setLayoutManager(linearLayoutManager1);
              //  databaseHelper  = DatabaseHelper.getInstance(mContext);
              //  RecyclerViewFlowersAdapter adapter1 = new RecyclerViewFlowersAdapter(databaseHelper.getFlowers(), R.layout.fragment_lists_flower, mContext);
              //  mRecyclerViewFlowers.setAdapter(adapter1);
//
              view = inflater.inflate(R.layout.fragment_lists, container, false);
              mRecyclerView = (RecyclerView) view.findViewById(R.id.ShirtsRecyclerView);
              RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
              mRecyclerView.setLayoutManager(linearLayoutManager);
              databaseHelper = DatabaseHelper.getInstance(mContext);
              RecyclerViewClothesAdapter adapter = new RecyclerViewClothesAdapter(databaseHelper.getShirts(), R.layout.fragment_lists, mContext);
              mRecyclerView.setAdapter(adapter);
              break;
           case 1:
              view = inflater.inflate(R.layout.fragment_lists_flower, container, false);
              mRecyclerViewFlowers = (RecyclerView) view.findViewById(R.id.FlowersRecyclerView);
              RecyclerView.LayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
              mRecyclerViewFlowers.setLayoutManager(linearLayoutManager1);
              databaseHelper  = DatabaseHelper.getInstance(mContext);
              RecyclerViewFlowersAdapter adapter1 = new RecyclerViewFlowersAdapter(databaseHelper.getFlowers(), R.layout.fragment_lists_flower, mContext);
              mRecyclerViewFlowers.setAdapter(adapter1);
//
               break;
            case 2:
             //  view = inflater.inflate(R.layout.fragment_lists, container, false);
             //  mRecyclerview = (RecyclerView) view.findViewById(R.id.ShirtsRecyclerView) ;
             //  RecyclerView.LayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext);
             //  mRecyclerview.setLayoutManager(linearLayoutManager2);
             //  DatabaseHelper databaseHelper2 = DatabaseHelper.getInstance(mContext);
             //  RecyclerViewClothesAdapter adapter2 = new RecyclerViewClothesAdapter(databaseHelper2.getShirts(), R.layout.fragment_lists, mContext);
             //  mRecyclerview.setAdapter(adapter2);

                break;

        }


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}


