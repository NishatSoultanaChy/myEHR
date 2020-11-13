package com.nishatsultana.myehr.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.nishatsultana.myehr.Adapters.MyReportsAdapter2;
import com.nishatsultana.myehr.Model.Reports;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;

public class PatientReportList extends AppCompatActivity {

    FloatingActionButton fb;
    RecyclerView recview;
    MyReportsAdapter2 adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_report_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fb = findViewById(R.id.floatingActionButton_Add_report);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientReportList.this, AddPatientLabReportActivity.class));
            }
        });

        final String phone = Prevalent.CurrentOnlineUser.getPhone();

        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<Reports> options =
                new FirebaseRecyclerOptions.Builder<Reports>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Reports").child(phone), Reports.class)
                        .build();

        adapter=new MyReportsAdapter2(options);
        recview.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}