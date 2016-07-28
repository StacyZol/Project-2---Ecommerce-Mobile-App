package com.example.stacyzolnikov.project2shoppinglist2;


import android.content.Context;
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

    public interface OnListItemClickListener {
       // void OnListItemClicked(int tabPosition, int listPosition);
        void onListItemClicked();
    }


    public static Fragment newInstance(int tab_number, OnListItemClickListener listener) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        fragment.tabPosition = tab_number;
        fragment.mListItemClickListener = listener;
        //tabPosition = tab_number;
        //mListItemClickListener = listener;

     //   Bundle bundle = new Bundle();
     //   bundle.putInt(TAB_NUMBER, tab_number);
//
     //   placeHolderFragment.setArguments(bundle);
     //
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View view = null;
     //int tab_number = getArguments().getInt(TAB_NUMBER, 0);
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        mRecyclerview = (RecyclerView) view.findViewById(R.id.ShirtsRecyclerView);
//
//        mContext = container.getContext();
        mContext = getActivity().getApplicationContext();
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(mContext);

        //Toast.makeText(mContext, "HAHHHHHHH", Toast.LENGTH_SHORT).show();

        switch (tabPosition) {
            case 0:
                RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                mRecyclerview.setLayoutManager(linearLayoutManager);
                databaseHelper = DatabaseHelper.getInstance(mContext);
                Log.d("test2", databaseHelper.getShirts().get(0).getHeart());
                Toast.makeText(mContext, "Fragment1", Toast.LENGTH_SHORT).show();
                RecyclerViewClothesAdapter adapter = new RecyclerViewClothesAdapter(databaseHelper.getShirts(), R.layout.fragment_lists);


                mRecyclerview.setAdapter(adapter);
                break;
           case 1:
               RecyclerView.LayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
               mRecyclerview.setLayoutManager(linearLayoutManager1);
               DatabaseHelper databaseHelper1 = DatabaseHelper.getInstance(mContext);
               RecyclerViewClothesAdapter adapter1 = new RecyclerViewClothesAdapter(databaseHelper1.getShirts(), R.layout.fragment_lists);
               mRecyclerview.setAdapter(adapter1);
               break;
        }


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}


