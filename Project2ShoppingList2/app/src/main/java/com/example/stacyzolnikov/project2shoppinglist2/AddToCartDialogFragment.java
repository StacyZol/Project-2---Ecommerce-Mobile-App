package com.example.stacyzolnikov.project2shoppinglist2;




import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by stacyzolnikov on 7/28/16.
 */
public class AddToCartDialogFragment extends DialogFragment {
    public OnItemListener callback;
    public Button mButton;
    public Button mButton2;
    public ImageView itemPhoto;
    List<Shirt> shirts;
    int position;

    public AddToCartDialogFragment () {

    }


    public AddToCartDialogFragment (List<Shirt> shirts, int position) {
        this.shirts = shirts;
        this.position = position;

    }

   // @Override
   // public void onCreate(@Nullable Bundle savedInstanceState) {
   //     super.onCreate(savedInstanceState);
   //
   //     final ShoppingCartSingleton shoppingCartSingleton = ShoppingCartSingleton.getInstance();
   // }
//
    public static AddToCartDialogFragment newInstance (String title){
        AddToCartDialogFragment fragment = new AddToCartDialogFragment();
        Bundle args = new Bundle();
        args.putString("Add To Cart", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_to_cart_dialog, container);




    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       itemPhoto = (ImageView) view.findViewById(R.id.ItemPhotosID);
       mButton = (Button) view.findViewById(R.id.AddToCart);
       mButton2 = (Button) view.findViewById(R.id.NevermindID);

        final ShoppingCartSingleton shoppingCartSingleton = ShoppingCartSingleton.getInstance();

        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                shoppingCartSingleton.addCartObject(new CartObject(shirts.get(position).getShirtName(), shirts.get(position).getPrice(), shirts.get(position).getShirtPhotosID()));
//                shoppingCartSingleton.addCartObject(new CartObject("Testabcd", "123", "blah"));
                getDialog().dismiss();
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

    }

    public interface OnItemListener {
       public void onAddItem(String itemAdded);
   }


  //  @Override
  //  public void onCreate (Bundle savedInstanceState) {
  //      super.onCreate(savedInstanceState);
//
  //      try {
  //          callback = (OnItemListener) getTargetFragment();
  //      }
  //      catch (ClassCastException e) {
  //          throw new ClassCastException("Calling Fragment must implement OnItemListener");
  //      }
  //  }
//
  //@Override
  //public Dialog onCreateDialog (Bundle savedInstanceState){

  //        final View dialogView= LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.add_to_cart_dialog, null);
  //        final ImageView itemPhoto = (ImageView) dialogView.findViewById(R.id.ItemPhotosID);
  //        final Button addToCart = (Button) dialogView.findViewById(R.id.AddToCart);
  //        final Button nevermind = (Button) dialogView.findViewById(R.id.NevermindID);
  //    Dialog builder = new Dialog(getActivity());
  //    return builder;
  //    }

  //@Override
  //    public Dialog onCreateDialog (Bundle savedInstanceState){

  //    final View dialogView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.add_to_cart_dialog,null);
  //   // final ImageView itemPhoto = (ImageView) dialogView.findViewById(R.id.ItemPhotosID);
  //   // final Button addToCart = (Button) dialogView.findViewById(R.id.AddToCart);
  //   // final Button nevermind = (Button) dialogView.findViewById(R.id.NevermindID);
//
  //    builder.setMessage("Add to Cart")
  //            .setPositiveButton("Add to Cart", new DialogInterface.OnClickListener() {

  //                @Override
  //                public void onClick(DialogInterface dialogInterface, int i) {
  //                    //Need to add to save to singleton here
  //                    final String itemAdded = String.valueOf(getId());
  //                    callback.onAddItem(itemAdded);
  //                    Toast.makeText(getActivity().getApplicationContext(), "Item was added to the shopping cart", Toast.LENGTH_SHORT).show();
  //                }
  //            })
  //            .setNegativeButton("Nevermind", new DialogInterface.OnClickListener(){

  //        @Override
  //        public void onClick(DialogInterface dialogInterface, int i) {
  //            AddToCartDialogFragment.this.getDialog().cancel();
  //        }
  //    });


  //    return builder.create();
  //}

}