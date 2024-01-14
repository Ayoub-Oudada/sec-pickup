package com.secpickup.android_front.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import com.secpickup.android_front.EleveList_Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secpickup.android_front.R;

public class HomeFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}