package com.nishatsultana.myehr.Doctor;

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
import com.nishatsultana.myehr.Adapters.PrescriptionsAdapter;
import com.nishatsultana.myehr.Adapters.SymptomsAdapter;
import com.nishatsultana.myehr.Model.Prescription;
import com.nishatsultana.myehr.Model.Symptoms;
import com.nishatsultana.myehr.Patient.AddSymptomsActivity;
import com.nishatsultana.myehr.Patient.SymptomsActivity;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;


public class PreviousPrescriptionsActivity extends AppCompatActivity {

    FloatingActionButton fabNewPresc;
    RecyclerView recviewPresc;
    PrescriptionsAdapter adapter;

    String phoneP, nameP, birthDateP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_prescriptions);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        fabNewPresc = findViewById(R.id.fab_write_new_presc);

        //from prev activity
        phoneP = getIntent().getStringExtra("keyPatientPhone");
        nameP = getIntent().getStringExtra("keyPatientName");
        birthDateP = getIntent().getStringExtra("keyPatientBirth");


        fabNewPresc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(PreviousPrescriptionsActivity.this, WritePrescriptionActivity.class);

                k.putExtra("keyPatientPhone",phoneP);
                k.putExtra("keyPatientBirth",birthDateP);
                k.putExtra("keyPatientName",nameP);

                startActivity(k);
            }
        });




        recviewPresc = findViewById(R.id.recviewPrescDoc);
        recviewPresc.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Prescription> options =
                new FirebaseRecyclerOptions.Builder<Prescription>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Prescriptions").child(phoneP), Prescription.class)
                        .build();

        adapter=new PrescriptionsAdapter(options);
        recviewPresc.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}