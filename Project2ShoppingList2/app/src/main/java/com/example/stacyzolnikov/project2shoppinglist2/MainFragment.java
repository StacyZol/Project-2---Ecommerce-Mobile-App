package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by stacyzolnikov on 7/26/16.
 */
public class MainFragment extends Fragment {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Toolbar toolbar;
    public SectionPagerAdapter mSectionPagerAdapter;
    public static PlaceHolderFragment.OnListItemClickListener mItemListener;
    public CollapsingToolbarLayout mCollapsingToolbarLayout;
    TextView testTestView;
    public ImageView mItemHeader;

  //  public static MainFragment newInstance(PlaceHolderFragment.OnListItemClickListener listener) {
  //      MainFragment fragment = new MainFragment();
  //      fragment.mItemListener = listener;
  //      return fragment;
  //  }

//
    public static Fragment newInstance(PlaceHolderFragment.OnListItemClickListener listener) {
        MainFragment fragment = new MainFragment();
        fragment.mItemListener = listener;
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_items, container, false);
        toolbar = (Toolbar) rootView.findViewById(R.id.items_toolbar);

        //Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        mViewPager = (ViewPager) rootView.findViewById(R.id.items_viewpager);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.items_tabs);
//        testTestView = (TextView) rootView.findViewById(R.id.testTextView);
       // mItemHeader = (ImageView) rootView.findViewById(R.id.items_header);
      //  int imageResource = this.getResources().getIdentifier(DatabaseHelper.getInstance().stores.)
       // mItemHeader.setImageResource();


      //  holder.mShirtPhotos.setImageResource(imageResource2);
       // testTestView.setText("Hey, this works!");

       mCollapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.clothes_collapse_toolbar);
       // mCollapsingToolbarLayout.setTitle("Macys");
       // mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));


        return rootView;


    }

   @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


      mSectionPagerAdapter  = new SectionPagerAdapter(getFragmentManager(),3, mItemListener);
        mViewPager.setAdapter(mSectionPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.SearchStores:
                return true;
            case R.id.ShoppingCart:
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
        super.onSaveInstanceState(outState);

}

}
