package com.nishatsultana.myehr.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nishatsultana.myehr.Model.Prescription;
import com.nishatsultana.myehr.Model.Symptoms;
import com.nishatsultana.myehr.R;

public class PrescriptionsAdapter  extends FirebaseRecyclerAdapter<Prescription, PrescriptionsAdapter.prescriptionViewHolder> {

    public PrescriptionsAdapter(@NonNull FirebaseRecyclerOptions<Prescription> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull PrescriptionsAdapter.prescriptionViewHolder holder, int position, @NonNull Prescription model) {
        holder.textViewPresc1.setText("Doctor's Name: "+ model.getDoctorName());
        holder.textViewPresc2.setText("Date: "+ model.getPrescriptionDate());
        holder.textViewPresc2.setText("Doctor's Phone No: " +model.getDoctorPhone());

    }


    @NonNull
    @Override
    public PrescriptionsAdapter.prescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.presc_row_layout,parent,false);
        return new PrescriptionsAdapter.prescriptionViewHolder(view) ;
    }

    public static class prescriptionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPresc1, textViewPresc2, textViewPresc3;
        private final View itemView;
        public prescriptionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            textViewPresc1 = itemView.findViewById(R.id.presc_doctor_name_list);
            textViewPresc2= itemView.findViewById(R.id.presc_date_list);
            textViewPresc3= itemView.findViewById(R.id.presc_doctor_phone);

        }

        public TextView getTextViewPresc1() {
            return textViewPresc1;
        }
        public TextView getTextViewPresc2() {
            return textViewPresc2;
        }
        public TextView getTextViewPresc3() {
            return textViewPresc3;
        }

    }


}
