package com.techease.aidenassign2application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.aidenassign2application.R;
import com.techease.aidenassign2application.model.getPatientTest.GetPatientTestDataModel;

import java.util.List;

public class PatientsTestAdapter extends RecyclerView.Adapter<PatientsTestAdapter.ViewHolder> {


    private Context context;
    private List<GetPatientTestDataModel> getPatientTestDataModels;


    public PatientsTestAdapter(Context context, List<GetPatientTestDataModel> getPatientTestDataModels) {
        this.context = context;
        this.getPatientTestDataModels = getPatientTestDataModels;


    }


    @NonNull
    @Override
    public PatientsTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_patient_test_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PatientsTestAdapter.ViewHolder viewHolder, final int i) {
        final GetPatientTestDataModel getPatientTestDataModel = getPatientTestDataModels.get(i);

        viewHolder.tvCholesterol.setText(getPatientTestDataModel.getCholesterol());
        viewHolder.tvTemperature.setText(getPatientTestDataModel.getTemperature());
        viewHolder.tvDate.setText(getPatientTestDataModel.getDate());

        viewHolder.tvBloodPressure.setText(getPatientTestDataModel.getPressure());

        if (getPatientTestDataModel.getPressure().equals("Positive")) {
            viewHolder.tvBloodPressure.setText("Positive");
            final int newColor = context.getResources().getColor(R.color.green);
            viewHolder.tvBloodPressure.setTextColor(newColor);
        } else if (getPatientTestDataModel.getPressure().equals("Negative")) {
            viewHolder.tvBloodPressure.setText("Negative");
            final int newColor = context.getResources().getColor(R.color.red);
            viewHolder.tvBloodPressure.setTextColor(newColor);

        }

    }

    @Override
    public int getItemCount() {
        return getPatientTestDataModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTemperature, tvCholesterol, tvBloodPressure, tvDate;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTemperature = itemView.findViewById(R.id.tvTemp);
            tvCholesterol = itemView.findViewById(R.id.tvCholesterol);
            tvBloodPressure = itemView.findViewById(R.id.tvBloodPressure);
            tvDate = itemView.findViewById(R.id.tvDate);


        }
    }


}