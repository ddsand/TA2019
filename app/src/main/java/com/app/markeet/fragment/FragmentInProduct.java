package com.app.markeet.fragment;


import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.markeet.R;
import com.app.markeet.data.SharedPref;


public class FragmentInProduct extends Fragment {

    Button imageBtn, buttonProduct;
    private SharedPref sharedPref;
    TextView productname,prices,discounts,stocks,descriptions;
    ImageView imgView;
    String mediaPath, mediaPath1;
    Spinner spCategory,statusProduct;
    String[] mediaColumns = {MediaStore.Video.Media._ID};

    public FragmentInProduct() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_inproduct, null);
        sharedPref = new SharedPref(getActivity());
        final Context context = getActivity();

        imgView = (ImageView) fragmentView.findViewById(R.id.preview);
        imageBtn = (Button) fragmentView.findViewById(R.id.buttonImage);
        buttonProduct = (Button) fragmentView.findViewById(R.id.buttonProduct);
        spCategory = (Spinner) fragmentView.findViewById(R.id.spCategory);
        productname = (TextView) fragmentView.findViewById(R.id.txtProduct);
        statusProduct = (Spinner) fragmentView.findViewById(R.id.statusProduct);
        prices = (TextView) fragmentView.findViewById(R.id.txtPrice);
        discounts = (TextView) fragmentView.findViewById(R.id.txtDiscount);
        stocks = (TextView) fragmentView.findViewById(R.id.txtStock);
        descriptions = (TextView) fragmentView.findViewById(R.id.txtDesc);

        return fragmentView;
    }

}
