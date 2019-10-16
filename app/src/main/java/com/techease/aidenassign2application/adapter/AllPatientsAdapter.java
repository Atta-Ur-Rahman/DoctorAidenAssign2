package com.techease.aidenassign2application.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.techease.aidenassign2application.R;
import com.techease.aidenassign2application.activities.PatientTestActivity;
import com.techease.aidenassign2application.model.getAllPatientsModel.GetAllPatientDateModel;

import java.util.List;

public class AllPatientsAdapter extends RecyclerView.Adapter<AllPatientsAdapter.ViewHolder> {


    private Context context;
    private List<GetAllPatientDateModel> getAllPatientDateModels;


    public AllPatientsAdapter(Context context, List<GetAllPatientDateModel> getAllPatientDateModels) {
        this.context = context;
        this.getAllPatientDateModels = getAllPatientDateModels;


    }


    @NonNull
    @Override
    public AllPatientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_patient_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllPatientsAdapter.ViewHolder viewHolder, final int i) {

        final GetAllPatientDateModel getAllPatientDateModel = getAllPatientDateModels.get(i);
        viewHolder.tvNurseId.setText("Nurse ID # "+getAllPatientDateModel.getNurseid());
        viewHolder.tvPatientName.setText(getAllPatientDateModel.getName());
        viewHolder.tvRoomNum.setText(getAllPatientDateModel.getRoomnum());
        viewHolder.tvDepartment.setText(getAllPatientDateModel.getDepartment());



        if (getAllPatientDateModel.getDepartment().equals("Audiology")){
            viewHolder.tvDepartment.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_orange));
        }else {
            viewHolder.tvDepartment.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_green));

        }



        viewHolder.rlAllPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PatientTestActivity.class);
                intent.putExtra("patient_id", getAllPatientDateModel.getHealthid());
                intent.putExtra("patient_name",getAllPatientDateModel.getName());
                context.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return getAllPatientDateModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPatientName,tvNurseId,tvRoomNum,tvDepartment;
        RelativeLayout rlAllPatient;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPatientName = itemView.findViewById(R.id.tvPatientName);
            tvNurseId = itemView.findViewById(R.id.tvNurseId);
            tvRoomNum = itemView.findViewById(R.id.tvRoomNum);
            tvDepartment = itemView.findViewById(R.id.tvDepartment);
            rlAllPatient=itemView.findViewById(R.id.rlAllPatient);





        }
    }



}