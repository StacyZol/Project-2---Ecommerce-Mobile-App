package com.example.stacyzolnikov.project2shoppinglist2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by stacyzolnikov on 8/10/16.
 */
public class DrawerItemClickListener extends Fragment implements ListView.OnItemClickListener {
    @Override
  public void onItemClick(AdapterView parent, View view, int position, long id) {

  }

   // private void selectItem(int position){
   //     //Intent intent = new Intent();
   //     Fragment fragment = new StoreFragment();
   //     Bundle args = new Bundle();
   //     //args.putInt(StoreFragment.ARG_STORE_NUMBER, position);
   //     fragment.setArguments(args);
//
   //     FragmentManager fragmentManager = getFragmentManager();
   //     fragmentManager.beginTransaction().replace(R.id.store_content_frame, fragment).commit();
//
//
   // }
}
