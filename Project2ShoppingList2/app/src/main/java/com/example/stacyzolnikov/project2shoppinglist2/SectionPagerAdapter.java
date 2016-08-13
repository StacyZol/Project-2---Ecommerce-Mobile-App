package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by stacyzolnikov on 7/26/16.
 */
public class SectionPagerAdapter extends FragmentPagerAdapter {

    int mPageCount;
    Context context;
    public PlaceHolderFragment.OnListItemClickListener mItemClickListener;

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

      //  public SectionPagerAdapter(FragmentManager fm, int pageCount) {
      //  super(fm);
      // this.mPageCount = pageCount;

    public SectionPagerAdapter(FragmentManager fm,int pageCount, PlaceHolderFragment.OnListItemClickListener listener) {
        super(fm);
        mItemClickListener = listener;
        mPageCount = pageCount;
    }



    @Override
    public int getCount() {
        return mPageCount;
    }

    public Fragment getInstance(int position) {
        return PlaceHolderFragment.newInstance(position, mItemClickListener);

    }

    @Override
    public Fragment getItem(int position) {
      return PlaceHolderFragment.newInstance(position, mItemClickListener);
        //Bundle bundledArgs = new Bundle();
        //  bundledArgs.putInt(ShirtsFragment.FRAGMENT_POSITION, position);
        //switch(position) {
        //    default:
        //    case 0:
        //        //bundledArgs.putString(ShirtsFragment.TEXT_STRING, "Need to put list1");
        //        //return ShirtsFragment.newInstance(bundledArgs);

        //       // return new ShirtsFragment();
        //    case 1:
        //        //bundledArgs.putString(ShirtsFragment.TEXT_STRING, "Need to put list2");
        //       // return ShirtsFragment.newInstance(bundledArgs);
        //        return new ShirtsFragment();
        //    case 2:
        //       // bundledArgs.putString(ShirtsFragment.TEXT_STRING, "Need to put list3");
        //       // return ShirtsFragment.newInstance(bundledArgs);
        //        return new ShirtsFragment();


    }


    public CharSequence getPageTitle(int position) {
        switch (position) {
            default:
            case 0:
                return "Category One";
            case 1:
                return "Category Two";
            case 2:
                return "Category Three";
        }
    }


}
