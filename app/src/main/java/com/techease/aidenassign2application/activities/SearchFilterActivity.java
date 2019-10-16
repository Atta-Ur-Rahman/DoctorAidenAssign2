package com.techease.aidenassign2application.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.techease.aidenassign2application.R;
import com.techease.aidenassign2application.fragments.AlertUtils;

import java.util.Objects;

public class SearchFilterActivity extends AppCompatActivity implements View.OnClickListener {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    EditText etSerchByPatientId;
    Button btnSearchByPatientId, btnSearchByDepartment;
    Spinner spSearchByDepartment;
    Dialog dialog;

    ImageView ivBack;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);
        ivBack = findViewById(R.id.iv_back);
        dialog = AlertUtils.createProgressDialog(this);
        Objects.requireNonNull(getSupportActionBar()).hide();
        etSerchByPatientId = findViewById(R.id.etSearchByPatientId);
        spSearchByDepartment = findViewById(R.id.spSearchByDepartment);
        btnSearchByPatientId = findViewById(R.id.btnSearchByPatientId);
        btnSearchByDepartment = findViewById(R.id.btnSearchByDepartment);
        btnSearchByPatientId.setOnClickListener(this);
        btnSearchByDepartment.setOnClickListener(this);
        ivBack.setOnClickListener(this);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearchByPatientId:


                if (etSerchByPatientId.getText().toString().equals("")) {
                    etSerchByPatientId.setError("enter a valid patient id");
                } else {
                    Intent mIntent = new Intent(this, SearchFilterResultActivity.class);
                    mIntent.putExtra("patientId", etSerchByPatientId.getText().toString());
                    mIntent.putExtra("patient", true);
                    startActivity(mIntent);
                }
                break;

            case R.id.btnSearchByDepartment:


                Intent mIntent = new Intent(this, SearchFilterResultActivity.class);
                mIntent.putExtra("department", spSearchByDepartment.getSelectedItem().toString());
                mIntent.putExtra("patient", false);
                startActivity(mIntent);

                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
