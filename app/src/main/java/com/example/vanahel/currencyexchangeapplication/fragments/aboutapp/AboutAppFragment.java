package com.example.vanahel.currencyexchangeapplication.fragments.aboutapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vanahel.currencyexchangeapplication.R.id;
import com.example.vanahel.currencyexchangeapplication.R.layout;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutAppFragment extends Fragment {

    @BindView(id.imageView) ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(layout.about_app_fragment, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(this.getActivity())
                .load("http://i.imgur.com/DvpvklR.png")
                .into(this.imageView);

        return view;
    }
}

