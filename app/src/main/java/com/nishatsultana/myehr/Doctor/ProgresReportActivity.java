package com.nishatsultana.myehr.Doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.nishatsultana.myehr.Adapters.PrescriptionsAdapter;
import com.nishatsultana.myehr.Adapters.ProgressAdapter;
import com.nishatsultana.myehr.Model.Prescription;
import com.nishatsultana.myehr.Model.Progress;
import com.nishatsultana.myehr.R;

public class ProgresReportActivity extends AppCompatActivity {

    FloatingActionButton fabNewProgress;
    RecyclerView recviewProgress;
    ProgressAdapter adapterProgress;

    String phonePP, namePP, birthDatePP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progres_report);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        fabNewProgress = findViewById(R.id.fab_write_new_progress);

        //from prev activity
        phonePP = getIntent().getStringExtra("keyPatientPhone");
        namePP = getIntent().getStringExtra("keyPatientName");
        birthDatePP = getIntent().getStringExtra("keyPatientBirth");


        fabNewProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(ProgresReportActivity.this, AddProgressActivity.class);

                k.putExtra("keyPatientPhone",phonePP);
                k.putExtra("keyPatientBirth",birthDatePP);
                k.putExtra("keyPatientName",namePP);

                startActivity(k);
            }
        });

        recviewProgress = findViewById(R.id.recviewProgressDoc);
        recviewProgress.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Progress> options =
                new FirebaseRecyclerOptions.Builder<Progress>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("ProgressReport").child(phonePP), Progress.class)
                        .build();

        adapterProgress = new ProgressAdapter(options);
        recviewProgress.setAdapter(adapterProgress);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterProgress.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterProgress.stopListening();
    }
}