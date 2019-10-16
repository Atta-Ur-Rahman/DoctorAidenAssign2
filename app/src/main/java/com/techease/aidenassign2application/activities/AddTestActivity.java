package com.techease.aidenassign2application.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techease.aidenassign2application.R;
import com.techease.aidenassign2application.fragments.AlertUtils;
import com.techease.aidenassign2application.fragments.AllPatientsFragment;
import com.techease.aidenassign2application.model.AddTestResponseModel;
import com.techease.aidenassign2application.networking.APIClient;
import com.techease.aidenassign2application.networking.APIServices;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTestActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    TextView tvTestDate;
    RadioGroup radioGroup;
    EditText etCholesterol, etTemperature;
    Button btnAddTest;
    RadioButton rbPositive, rvNegative;
    ImageView ivBack;

    Spinner spAddPatientName;


    ArrayList<String> arrayList = new ArrayList<>();


    Dialog dialog;

    String strPatientName, strBloodPressure = "Positive", strCholesterol, strTemperature, strDate, strPatientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);
        dialog = AlertUtils.createProgressDialog(this);
        ivBack = findViewById(R.id.iv_back);

        getSupportActionBar().hide();
        tvTestDate = findViewById(R.id.tvTestDate);
        radioGroup = findViewById(R.id.radio);
        etCholesterol = findViewById(R.id.etCholesterol);
        etTemperature = findViewById(R.id.etTemperature);
        btnAddTest = findViewById(R.id.btnAddTest);
        spAddPatientName = findViewById(R.id.sp_patient_name);
        rbPositive = findViewById(R.id.radioPositive);
        rvNegative = findViewById(R.id.radioNegative);
        tvTestDate.setOnClickListener(this);
        btnAddTest.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);


        for (int i = 0; i < AllPatientsFragment.allPatientDateModels.size(); i++) {
            arrayList.add(AllPatientsFragment.allPatientDateModels.get(i).getName());
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAddPatientName.setAdapter(arrayAdapter);
        spAddPatientName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strPatientName = parent.getItemAtPosition(position).toString();
                strPatientId = AllPatientsFragment.allPatientDateModels.get(position).getHealthid();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        strPatientId = AllPatientsFragment.allPatientDateModels.get(0).getHealthid();
    }

    private void datePickerDailog() {

        DatePickerDialog picker;
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(AddTestActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvTestDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        picker.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTestDate:
                datePickerDailog();
                break;

            case R.id.btnAddTest:
                if (validate()) {
                    addPatientsTest();
                }
                break;

            case R.id.iv_back:
                finish();
                break;


        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioPositive:
                strBloodPressure = "Positive";
                break;
            case R.id.radioNegative:
                strBloodPressure = "Negative";
                break;
        }
    }


    private boolean validate() {
        boolean valid = true;

        strPatientName = spAddPatientName.getSelectedItem().toString();
        strCholesterol = etCholesterol.getText().toString();
        strTemperature = etTemperature.getText().toString();
        strDate = tvTestDate.getText().toString();


        if (strPatientName.isEmpty()) {
            Toast.makeText(this, "enter a valid patient name", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (strCholesterol.isEmpty()) {
            etCholesterol.setError("enter cholesterol");
            valid = false;
        }

        if (strTemperature.isEmpty()) {
            etTemperature.setError("enter a valid temperature");
            valid = false;
        }

        if (strDate.isEmpty()) {
            tvTestDate.setError("enter a valid date");
            valid = false;
        }

        return valid;

    }


    private void addPatientsTest() {


        Log.d("all_data", strPatientId + "  " + strBloodPressure + "  " + strCholesterol + "  " + strTemperature + "   " + strDate);
        dialog.show();
        APIServices services = APIClient.getApiClient().create(APIServices.class);
        Call<AddTestResponseModel> addPatientResponseModelCall = services.addTest(strPatientId, strBloodPressure, strCholesterol, strTemperature, strDate);

        addPatientResponseModelCall.enqueue(new Callback<AddTestResponseModel>() {
            @Override
            public void onResponse(Call<AddTestResponseModel> call, Response<AddTestResponseModel> response) {
                dialog.dismiss();

                Log.d("response", String.valueOf(response));
                if (response.isSuccessful()) {
                    Toast.makeText(AddTestActivity.this, "add test succssesful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddTestActivity.this, MainActivity.class));

                }

            }

            @Override
            public void onFailure(Call<AddTestResponseModel> call, Throwable t) {
                Toast.makeText(AddTestActivity.this, " error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
