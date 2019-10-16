package com.techease.aidenassign2application.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.aidenassign2application.R;
import com.techease.aidenassign2application.adapter.AllPatientsAdapter;
import com.techease.aidenassign2application.model.getAllPatientsModel.AllPatientResponseModel;
import com.techease.aidenassign2application.model.getAllPatientsModel.GetAllPatientDateModel;
import com.techease.aidenassign2application.networking.APIClient;
import com.techease.aidenassign2application.networking.APIServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPatientsFragment extends Fragment {

    View parentView;
    Dialog dialog;
    RecyclerView rvAllPatients;
    AllPatientsAdapter allPatientsAdapter;
    public List<GetAllPatientDateModel> allPatientDateModels = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_my_patients, container, false);
        dialog = AlertUtils.createProgressDialog(getActivity());

        rvAllPatients=parentView.findViewById(R.id.rvPatient);
        rvAllPatients.hasFixedSize();
        rvAllPatients.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAllPatients.setItemAnimator(new DefaultItemAnimator());
        rvAllPatients.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        Collections.reverse(allPatientDateModels);
        allPatientsAdapter = new AllPatientsAdapter(getActivity(), allPatientDateModels);
        rvAllPatients.setAdapter(allPatientsAdapter);


        apiCallAllPatient();

        return parentView;
    }

    private void apiCallAllPatient() {

        dialog.show();
        APIServices services = APIClient.getApiClient().create(APIServices.class);
        Call<AllPatientResponseModel> getRecipiesResponseModelCall = services.allPatients();

        getRecipiesResponseModelCall.enqueue(new Callback<AllPatientResponseModel>() {
            @Override
            public void onResponse(Call<AllPatientResponseModel> call, Response<AllPatientResponseModel> response) {
                dialog.dismiss();
                if (response.isSuccessful()){
                    allPatientDateModels.addAll(response.body().getData());
                    allPatientsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<AllPatientResponseModel> call, Throwable t) {
                dialog.dismiss();
            }
        });

    }

}
