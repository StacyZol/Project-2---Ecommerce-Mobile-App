package com.example.stacyzolnikov.project2final.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stacyzolnikov.project2final.R;
import com.example.stacyzolnikov.project2final.objects.Nursery;

import java.util.List;

/**
 * Created by stacyzolnikov on 8/20/16.
 */
public class MainFragment extends Fragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    public SectionPagerAdapter mSectionPagerAdapter;
    public static PlaceHolderFragment.OnListItemClickListener mItemListener;
    public CollapsingToolbarLayout mCollapsingToolbarLayout;
    public ImageView mItemHeader;
    ImageView imageView;

    public static Fragment newInstance(PlaceHolderFragment.OnListItemClickListener listener) {
        MainFragment fragment = new MainFragment();
        fragment.mItemListener = listener;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.items_header);
        mViewPager = (ViewPager) rootView.findViewById(R.id.items_viewpager);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.items_tabs);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.items_collapse_toolbar);
        mItemHeader = (ImageView) rootView.findViewById(R.id.items_header);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSectionPagerAdapter = new SectionPagerAdapter(getFragmentManager(), 3, mItemListener);
        mViewPager.setAdapter(mSectionPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // case R.id.SearchStores:
            // return true;
            case R.id.ShoppingCart:
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
