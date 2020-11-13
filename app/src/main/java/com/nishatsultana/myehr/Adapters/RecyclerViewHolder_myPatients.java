package com.nishatsultana.myehr.Adapters;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.Doctor.MyPatientProfileActivity;
import com.nishatsultana.myehr.R;

public class RecyclerViewHolder_myPatients extends RecyclerView.ViewHolder {
    public FrameLayout my_patients_rec_view;
    private final TextView textView1;
    private final TextView textView2;
    private final TextView textView3;

    public RecyclerViewHolder_myPatients(@NonNull final View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MyPatientProfileActivity.class);
                i.putExtra("key",textView3.getText().toString());
                v.getContext().startActivity(i);
            }
        });
        textView1 = itemView.findViewById(R.id.patient_name);
        textView2 = itemView.findViewById(R.id.birth_date);
        textView3 = itemView.findViewById(R.id.patient_phone);
        my_patients_rec_view = itemView.findViewById(R.id.patient_list_item);

    }
    public TextView getTextView1() {
        return textView1;
    }
    public TextView getTextView2() {
        return textView2;
    }
    public TextView getTextView3() {
        return textView3;
    }
}
