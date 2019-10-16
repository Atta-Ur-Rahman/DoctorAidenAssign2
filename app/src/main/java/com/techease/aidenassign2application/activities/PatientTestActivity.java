package com.techease.aidenassign2application.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.aidenassign2application.R;
import com.techease.aidenassign2application.adapter.PatientsTestAdapter;
import com.techease.aidenassign2application.fragments.AlertUtils;
import com.techease.aidenassign2application.model.getPatientTest.GetPatientTestDataModel;
import com.techease.aidenassign2application.model.getPatientTest.GetPatientTestResponseModel;
import com.techease.aidenassign2application.networking.APIClient;
import com.techease.aidenassign2application.networking.APIServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientTestActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView ivBack, ivSearch;
    TextView tvPatientTitleName, tvNoTestFound;
    ArrayList<GetPatientTestDataModel> getPatientTestDataModels = new ArrayList<>();
    PatientsTestAdapter patientsTestAdapter;
    RecyclerView rvPatientsTest;
    Dialog dialog;
    String strPatientId, strPatientName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_test);

        getSupportActionBar().hide();

        ivBack = findViewById(R.id.iv_back);
        ivSearch = findViewById(R.id.iv_search);
        tvPatientTitleName = findViewById(R.id.tvPatientNameTitle);
        rvPatientsTest = findViewById(R.id.rvPatientTest);
        tvNoTestFound = findViewById(R.id.tvNoTestFound);
        ivSearch = findViewById(R.id.iv_search);

        ivBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);


        dialog = AlertUtils.createProgressDialog(this);

        rvPatientsTest.hasFixedSize();
        rvPatientsTest.setLayoutManager(new LinearLayoutManager(this));
        rvPatientsTest.setItemAnimator(new DefaultItemAnimator());
        rvPatientsTest.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        patientsTestAdapter = new PatientsTestAdapter(this, getPatientTestDataModels);
        rvPatientsTest.setAdapter(patientsTestAdapter);


        Bundle extras = getIntent().getExtras();
        assert extras != null;
        strPatientId = extras.getString("patient_id");
        strPatientName = extras.getString("patient_name");

        apiCallAllPatient(strPatientId);
        tvPatientTitleName.setText(strPatientName);


    }

    private void apiCallAllPatient(String strPatientId) {

        dialog.show();
        APIServices services = APIClient.getApiClient().create(APIServices.class);
        Call<GetPatientTestResponseModel> getPatientTestResponseModelCall = services.getByPatientTest(strPatientId);

        getPatientTestResponseModelCall.enqueue(new Callback<GetPatientTestResponseModel>() {
            @Override
            public void onResponse(Call<GetPatientTestResponseModel> call, Response<GetPatientTestResponseModel> response) {
                dialog.dismiss();
                if (response.isSuccessful()) {
                    getPatientTestDataModels.addAll(response.body().getData());
                    patientsTestAdapter.notifyDataSetChanged();
                    if (getPatientTestDataModels.size() <= 0) {
                        tvNoTestFound.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetPatientTestResponseModel> call, Throwable t) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                startActivity(new Intent(PatientTestActivity.this, SearchFilterActivity.class));
                break;
        }
    }
}
