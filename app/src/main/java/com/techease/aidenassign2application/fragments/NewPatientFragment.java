package com.techease.aidenassign2application.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techease.aidenassign2application.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewPatientFragment extends Fragment {


    public NewPatientFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_patient, container, false);
    }

}
