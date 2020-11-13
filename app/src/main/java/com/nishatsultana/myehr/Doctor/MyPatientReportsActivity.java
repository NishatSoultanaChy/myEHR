package com.nishatsultana.myehr.Doctor;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.Adapters.MyReportsAdapter2;
import com.nishatsultana.myehr.Model.Reports;
import com.nishatsultana.myehr.R;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class MyPatientReportsActivity extends AppCompatActivity {
    RecyclerView recviewRep2;
    MyReportsAdapter2 adapter2;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patient_reports);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        phone  = getIntent().getStringExtra("keyPhone");

        recviewRep2 = findViewById(R.id.recviewRep2);
        recviewRep2.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions.Builder<Reports> reportsBuilder = new FirebaseRecyclerOptions.Builder<Reports>();
        reportsBuilder.setQuery(FirebaseDatabase.getInstance().getReference().child("Reports").child(""+phone), Reports.class);
        FirebaseRecyclerOptions<Reports> options =
                reportsBuilder
                        .build();

        adapter2=new MyReportsAdapter2(options);
        recviewRep2.setAdapter(adapter2);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter2.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter2.stopListening();
    }
}