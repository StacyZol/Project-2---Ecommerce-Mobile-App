package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by stacyzolnikov on 7/26/16.
 */
public class ShirtsFragment extends Fragment {
    public static final String FRAGMENT_POSITION = "fragment_position";
    List<Shirt> arrayList;
    RecyclerView mRecyclerView;
    DatabaseHelper databaseHelper;
    Context mContext;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private SectionPagerAdapter mSectionPagerAdapter;
    private PlaceHolderFragment.OnListItemClickListener mItemListener;
    private int mFragmentPosition = 0;
   // private int mImageResourceId;
    //private String mTextString = "";

  //  public static ShirtsFragment newInstance(int tabPosition) {
  //      ShirtsFragment shirtsFragment = new ShirtsFragment();
  //      Bundle args = new Bundle();
  //      args.putInt("tab_position", tabPosition);
  //      shirtsFragment.setArguments(args);
  //      return listsFragment;
  //  }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lists, container, false);
        mViewPager = (ViewPager) rootView.findViewById(R.id.items_maincontent);
        mTabLayout = (TabLayout) rootView.findViewById(R.id.items_tabs);
   //   mRecyclerView = (RecyclerView) rootView.findViewById(R.id.ShirtsRecyclerView);

   // mContext = this.getActivity();
   //  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
   //  mRecyclerView.setLayoutManager(linearLayoutManager);
   //  //getActivity().getBaseContext()
   //  databaseHelper = DatabaseHelper.getInstance(mContext);
   //  databaseHelper.addToShirts();
   //  arrayList = databaseHelper.getShirts();

   //  RecyclerViewClothesAdapter adapter = new RecyclerViewClothesAdapter(arrayList, getContext());
   //  mRecyclerView.setAdapter(adapter);


                return rootView;
        }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //mSectionPagerAdapter = new SectionPagerAdapter(getFragmentManager(), mItemListener);
        mViewPager.setAdapter(mSectionPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    //   final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    //   mRecyclerView.setLayoutManager(linearLayoutManager);

    //   databaseHelper = DatabaseHelper.getInstance(getActivity().getBaseContext());
    //        databaseHelper.addToShirts();
    //     arrayList = databaseHelper.getShirts();

    //       RecyclerViewClothesAdapter adapter = new RecyclerViewClothesAdapter(arrayList, getContext());
    //       mRecyclerView.setAdapter(adapter);
        }

}
