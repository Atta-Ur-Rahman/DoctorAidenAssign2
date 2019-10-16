package com.techease.aidenassign2application.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.techease.aidenassign2application.R;
import com.techease.aidenassign2application.fragments.AlertUtils;
import com.techease.aidenassign2application.model.AddPatientResponseModel;
import com.techease.aidenassign2application.networking.APIClient;
import com.techease.aidenassign2application.networking.APIServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPatientsActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {


    EditText etNurseId, etName, etRoomNum;
    RadioButton rbMale, rbFemale;
    RadioGroup radioGroup;
    Spinner spDepartment;
    Button btnAddPatient;
    ImageView ivBack;

    String strNurseId, strName, strRoomNum, strGender="male", strDepartment;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patients);
        getSupportActionBar().hide();
        dialog = AlertUtils.createProgressDialog(this);


        ivBack=findViewById(R.id.iv_back);
        etNurseId = findViewById(R.id.etNurseId);
        etName = findViewById(R.id.etName);
        etRoomNum = findViewById(R.id.etRoomNumber);

        rbMale = findViewById(R.id.radioMale);
        rbFemale = findViewById(R.id.radioFemale);

        spDepartment = findViewById(R.id.spAddPatientDepartment);

        btnAddPatient = findViewById(R.id.btnAddPatient);
        radioGroup = findViewById(R.id.radio);

        btnAddPatient.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        ivBack.setOnClickListener(this);


    }

    private void addPatients() {
        dialog.show();
        APIServices services = APIClient.getApiClient().create(APIServices.class);
        Call<AddPatientResponseModel> addPatientResponseModelCall = services.addPatient(strName, strNurseId, strRoomNum, strDepartment, strGender);

        addPatientResponseModelCall.enqueue(new Callback<AddPatientResponseModel>() {
            @Override
            public void onResponse(Call<AddPatientResponseModel> call, Response<AddPatientResponseModel> response) {

                dialog.dismiss();
                Log.d("response",String.valueOf(response));
                if (response.isSuccessful()) {
                    Toast.makeText(AddPatientsActivity.this, "add patients succssesful", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(AddPatientsActivity.this,MainActivity.class));
                }

            }

            @Override
            public void onFailure(Call<AddPatientResponseModel> call, Throwable t) {
                Toast.makeText(AddPatientsActivity.this, "add patients error", Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddPatient:

                if (validate()){
                    addPatients();
                }
                break;

            case R.id.iv_back:
                finish();
        }
    }


    private boolean validate() {
        boolean valid = true;

        strNurseId = etNurseId.getText().toString();
        strName = etName.getText().toString();
        strRoomNum = etRoomNum.getText().toString();
        strDepartment = spDepartment.getSelectedItem().toString();



        if (strNurseId.isEmpty()) {
            etNurseId.setError("enter a valid nurse id");
            valid = false;
        }
        if (strName.isEmpty()) {
            etName.setError("enter a valid name");
            valid = false;
        }

        if (strRoomNum.isEmpty()) {
            etRoomNum.setError("enter a valid room number");
            valid = false;
        }

        return valid;

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.radioMale:
                strGender = "Male";
                break;
            case R.id.radioFemale:
                strGender = "Female";
                break;
        }
    }
}
