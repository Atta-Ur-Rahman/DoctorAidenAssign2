package com.techease.aidenassign2application.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.aidenassign2application.R;
import com.techease.aidenassign2application.adapter.SerchByPatientsAdapter;
import com.techease.aidenassign2application.fragments.AlertUtils;
import com.techease.aidenassign2application.model.GetByPatientId.GetByPatientDataMoldel;
import com.techease.aidenassign2application.model.GetByPatientId.GetByPatientResponseModel;
import com.techease.aidenassign2application.networking.APIClient;
import com.techease.aidenassign2application.networking.APIServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFilterResultActivity extends AppCompatActivity {


    Dialog dialog;
    RecyclerView rvAllPatients;
    SerchByPatientsAdapter serchByPatientsAdapter;
    public List<GetByPatientDataMoldel> getByPatientDataMoldels = new ArrayList<>();
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_filter_result);
        rvAllPatients = findViewById(R.id.rvPatient);
        dialog = AlertUtils.createProgressDialog(this);

        getSupportActionBar().hide();
        ivBack = findViewById(R.id.iv_back);
        rvAllPatients.hasFixedSize();
        rvAllPatients.setLayoutManager(new LinearLayoutManager(this));
        rvAllPatients.setItemAnimator(new DefaultItemAnimator());
        rvAllPatients.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        serchByPatientsAdapter = new SerchByPatientsAdapter(SearchFilterResultActivity.this, getByPatientDataMoldels);
        rvAllPatients.setAdapter(serchByPatientsAdapter);


        if (getIntent().getExtras().getBoolean("patient")) {
            apiCallAllPatient(getIntent().getExtras().getString("patientId"));

        } else {

            apiCallAllPatienByDepartmentt(getIntent().getExtras().getString("department"));

        }


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void apiCallAllPatient(String strPatienId) {

        dialog.show();
        APIServices services = APIClient.getApiClient().create(APIServices.class);

        Call<GetByPatientResponseModel> getByPatientResponseModelCall = services.getByPatientId(strPatienId);

        getByPatientResponseModelCall.enqueue(new Callback<GetByPatientResponseModel>() {
            @Override
            public void onResponse(Call<GetByPatientResponseModel> call, Response<GetByPatientResponseModel> response) {

                dialog.dismiss();
                getByPatientDataMoldels.addAll(response.body().getData());
                serchByPatientsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<GetByPatientResponseModel> call, Throwable t) {

                dialog.dismiss();
            }
        });

    }


    private void apiCallAllPatienByDepartmentt(String strPatienDepartment) {

        dialog.show();
        APIServices services = APIClient.getApiClient().create(APIServices.class);

        Call<GetByPatientResponseModel> getByPatientResponseModelCall = services.getByPatientDepartment(strPatienDepartment);

        getByPatientResponseModelCall.enqueue(new Callback<GetByPatientResponseModel>() {
            @Override
            public void onResponse(Call<GetByPatientResponseModel> call, Response<GetByPatientResponseModel> response) {

                dialog.dismiss();

                if (response.isSuccessful()) {
                    getByPatientDataMoldels.addAll(response.body().getData());
                    serchByPatientsAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<GetByPatientResponseModel> call, Throwable t) {

                dialog.dismiss();
            }
        });

    }
}