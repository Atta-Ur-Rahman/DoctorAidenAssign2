package com.techease.aidenassign2application.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techease.aidenassign2application.R;
import com.techease.aidenassign2application.model.getAllPatientsModel.GetAllPatientDateModel;
import com.techease.aidenassign2application.model.getPatientTest.GetPatientTestDataModel;

import java.util.ArrayList;
import java.util.List;

public class PatientTestFragment extends Fragment {

    View parentView;


    public List<GetPatientTestDataModel> getPatientTestDataModels = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_patient_test, container, false);










        return parentView;
    }

}
