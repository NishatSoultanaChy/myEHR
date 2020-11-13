package com.nishatsultana.myehr.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.nishatsultana.myehr.Model.Reports;
import com.nishatsultana.myehr.Patient.ViewPDFActivity;
import com.nishatsultana.myehr.R;

public class MyReportsAdapter2 extends FirebaseRecyclerAdapter<Reports, MyReportsAdapter2.myReportsViewHolder> {

    public MyReportsAdapter2(@NonNull FirebaseRecyclerOptions<Reports> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MyReportsAdapter2.myReportsViewHolder holder, int position, @NonNull final Reports model) {

        holder.textView1.setText(model.getTestName());
        holder.textView2.setText(model.getDate());
        holder.textView3.setText(model.getAssignedBy());

        holder.method(model.getTestName(), model.getUrl());
    }

    @NonNull
    @Override
    public MyReportsAdapter2.myReportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_report_rec_view,parent,false);
        return new myReportsViewHolder(view);
    }

    public static class myReportsViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3;
        private final View itemView;
        public myReportsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            textView1 = itemView.findViewById(R.id.test_name);
            textView2 = itemView.findViewById(R.id.test_date);
            textView3 = itemView.findViewById(R.id.assigned_by);
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
        public void method(final String testname, final String fileurl) {
            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), ViewPDFActivity.class);
                    intent.putExtra("filename",testname);
                    intent.putExtra("fileurl", fileurl);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
