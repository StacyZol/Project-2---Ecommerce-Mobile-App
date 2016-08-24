package com.example.stacyzolnikov.project2final.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by stacyzolnikov on 8/20/16.
 */
public class SectionPagerAdapter extends FragmentPagerAdapter {
    int mPageCount;
    Context context;
    public PlaceHolderFragment.OnListItemClickListener mItemClickListener;

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public SectionPagerAdapter(FragmentManager fm,int pageCount, PlaceHolderFragment.OnListItemClickListener listener) {
        super(fm);
        mItemClickListener = listener;
        mPageCount = pageCount;
    }

    @Override
    public int getCount() {
        return mPageCount;
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceHolderFragment.newInstance(position, mItemClickListener);
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            default:
            case 0:
                return "Trees";
            case 1:
                return "Flowers";
            case 2:
                return "Herbs";
        }
    }

}
