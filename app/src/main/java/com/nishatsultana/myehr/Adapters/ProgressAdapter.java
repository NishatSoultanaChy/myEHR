package com.nishatsultana.myehr.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.Model.Progress;
import com.nishatsultana.myehr.Model.Symptoms;
import com.nishatsultana.myehr.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ProgressAdapter extends FirebaseRecyclerAdapter<Progress, ProgressAdapter.progressViewHolder> {

    public ProgressAdapter(@NonNull FirebaseRecyclerOptions<Progress> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull ProgressAdapter.progressViewHolder holder, int position, @NonNull Progress model) {
        holder.textViewProg1.setText("Doctor's Name: "+ model.getDoctorName());
        holder.textViewProg2.setText("Date: " + model.getProgressReportnDate());
        holder.textViewProg3.setText("Doctor's Phone No: " +model.getDoctorPhone());
    }


    @NonNull
    @Override
    public ProgressAdapter.progressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_row_layout,parent,false);
        return new ProgressAdapter.progressViewHolder(view) ;
    }

    public static class progressViewHolder extends RecyclerView.ViewHolder {
        TextView textViewProg1, textViewProg2, textViewProg3;
        private final View itemView;
        public progressViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            textViewProg1 = itemView.findViewById(R.id.progress_doctor_name_list);
            textViewProg2 = itemView.findViewById(R.id.progress_date_list);
            textViewProg3 = itemView.findViewById(R.id.progress_doctor_phone);

        }

        public TextView getTextViewProg1() {
            return textViewProg1;
        }
        public TextView getTextViewProg2() {
            return textViewProg2;
        }
        public TextView getTextViewProg3() {
            return textViewProg3;
        }

    }


}
