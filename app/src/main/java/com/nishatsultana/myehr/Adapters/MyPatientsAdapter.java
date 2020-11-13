package com.nishatsultana.myehr.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.Model.Patients;
import com.nishatsultana.myehr.R;


import java.util.List;

public class MyPatientsAdapter extends RecyclerView.Adapter<RecyclerViewHolder_myPatients> {


    Context patientContext;
    private final List<Patients> patientData;


    @Override
    public int getItemViewType(final int position) {
        return R.layout.my_patients_rec_view;
    }
    public MyPatientsAdapter(Context context, List<Patients> patientData) {
        this.patientContext =  context;
        this.patientData = patientData;
    }

    @NonNull
    @Override
    public RecyclerViewHolder_myPatients onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view2= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_patients_rec_view,parent,false);

        //final RecyclerViewHolder_myPatients vHolder = new RecyclerViewHolder_myPatients(view2);


        return new RecyclerViewHolder_myPatients(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder_myPatients holder, int position) {
        Patients patients=patientData.get(position);
        String phone_no = patientData.get(position).getPhone();

        holder.getTextView1().setText(patients.getFullname());
        holder.getTextView2().setText(patients.getDateofBirth());
        holder.getTextView3().setText(patients.getPhone());

        }



    @Override
    public int getItemCount() {
        return patientData.size();
    }



}

