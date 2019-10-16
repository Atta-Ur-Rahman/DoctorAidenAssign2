package com.techease.aidenassign2application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techease.aidenassign2application.R;
import com.techease.aidenassign2application.model.GetByPatientId.GetByPatientDataMoldel;
import com.techease.aidenassign2application.model.getAllPatientsModel.GetAllPatientDateModel;

import java.util.List;

public class SerchByPatientsAdapter extends RecyclerView.Adapter<SerchByPatientsAdapter.ViewHolder> {


    private Context context;
    private List<GetByPatientDataMoldel> getAllPatientDateModels;


    public SerchByPatientsAdapter(Context context, List<GetByPatientDataMoldel> getAllPatientDateModels) {
        this.context = context;
        this.getAllPatientDateModels = getAllPatientDateModels;


    }


    @NonNull
    @Override
    public SerchByPatientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_patient_layout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SerchByPatientsAdapter.ViewHolder viewHolder, final int i) {

        final GetByPatientDataMoldel getAllPatientDateModel = getAllPatientDateModels.get(i);
        viewHolder.tvNurseId.setText("Nurse ID # "+getAllPatientDateModel.getNurseid());
        viewHolder.tvPatientName.setText(getAllPatientDateModel.getName());
        viewHolder.tvRoomNum.setText(getAllPatientDateModel.getRoomnum());
        viewHolder.tvDepartment.setText(getAllPatientDateModel.getDepartment());




    }

    @Override
    public int getItemCount() {
        return getAllPatientDateModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPatientName,tvNurseId,tvRoomNum,tvDepartment;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPatientName = itemView.findViewById(R.id.tvPatientName);
            tvNurseId = itemView.findViewById(R.id.tvNurseId);
            tvRoomNum = itemView.findViewById(R.id.tvRoomNum);
            tvDepartment = itemView.findViewById(R.id.tvDepartment);





        }
    }



}