package com.example.stacyzolnikov.project2final.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.recycler.RecyclerViewFlowerAdapter;
import com.example.stacyzolnikov.project2final.recycler.RecyclerViewHerbAdapter;
import com.example.stacyzolnikov.project2final.recycler.RecyclerViewTreeAdapter;
import com.example.stacyzolnikov.project2final.setup.DatabaseHelper;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/20/16.
 */
public class PlaceHolderFragment extends Fragment {
    Context mContext;
    public OnListItemClickListener mListItemClickListener;
    private int tabPosition;
    RecyclerView mRecyclerViewFlowers;
    RecyclerView mRecyclerViewTrees;
    RecyclerView mRecyclerViewHerbs;
    public PlaceHolderFragment() {}

    public interface OnListItemClickListener {
        void OnListItemClicked(int tabPosition, int listPosition);}


    public static PlaceHolderFragment newInstance(int tab_number, OnListItemClickListener listener) {
        PlaceHolderFragment placeHolderFragment = new PlaceHolderFragment();
        placeHolderFragment.tabPosition = tab_number;
        placeHolderFragment.mListItemClickListener = listener;
        return placeHolderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mContext = getActivity();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(mContext);
        mContext = getActivity();
        switch (tabPosition) {
            //Each of the cases below replace the list of items (trees, flowers, herbs) using the placeholder fragment.
            //Each of them have a separate XML layout, but they all use the same custom view
            case 0:
                view = inflater.inflate(R.layout.fragment_list_trees, container, false);
                mRecyclerViewTrees = (RecyclerView) view.findViewById(R.id.treesRecyclerView);
                RecyclerView.LayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
                mRecyclerViewTrees.setLayoutManager(linearLayoutManager1);
                databaseHelper = DatabaseHelper.getInstance(mContext);
                RecyclerViewTreeAdapter adapter1 = new RecyclerViewTreeAdapter(databaseHelper.getTrees(), R.layout.fragment_list_trees, mContext);
                mRecyclerViewTrees.setAdapter(adapter1);
                break;
            case 1:
                view = inflater.inflate(R.layout.fragment_list_flower, container, false);
                mRecyclerViewFlowers = (RecyclerView) view.findViewById(R.id.flowerRecyclerView);
                RecyclerView.LayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext);
                mRecyclerViewFlowers.setLayoutManager(linearLayoutManager2);
                databaseHelper = DatabaseHelper.getInstance(mContext);
                RecyclerViewFlowerAdapter adapter2 = new RecyclerViewFlowerAdapter(databaseHelper.getFlowers(), R.layout.fragment_list_flower, mContext);
                mRecyclerViewFlowers.setAdapter(adapter2);

                break;
            case 2:
                view = inflater.inflate(R.layout.fragment_list_herb, container, false);
                 mRecyclerViewHerbs = (RecyclerView) view.findViewById(R.id.herbRecyclerView) ;
                 RecyclerView.LayoutManager linearLayoutManager3 = new LinearLayoutManager(mContext);
                 mRecyclerViewHerbs.setLayoutManager(linearLayoutManager3);
                 databaseHelper = DatabaseHelper.getInstance(mContext);
                 RecyclerViewHerbAdapter adapter3 = new RecyclerViewHerbAdapter(databaseHelper.getHerbs(), R.layout.fragment_list_herb, mContext);
                 mRecyclerViewHerbs.setAdapter(adapter3);

                break;

        }

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
