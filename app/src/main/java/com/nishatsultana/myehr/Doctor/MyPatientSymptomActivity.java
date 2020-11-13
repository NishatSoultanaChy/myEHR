
package com.nishatsultana.myehr.Doctor;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.nishatsultana.myehr.Adapters.SymptomsAdapter;
import com.nishatsultana.myehr.Model.Symptoms;
import com.nishatsultana.myehr.R;

public class MyPatientSymptomActivity extends AppCompatActivity {
    RecyclerView recviewSymp2;
    SymptomsAdapter adapter2;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patient_symptom);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        phone  = getIntent().getStringExtra("keyPhone");

        recviewSymp2 = findViewById(R.id.recviewSymptomsD);
        recviewSymp2.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<Symptoms> options =
                new FirebaseRecyclerOptions.Builder<Symptoms>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Symptoms").child(phone), Symptoms.class)
                        .build();

        adapter2=new SymptomsAdapter(options);
        recviewSymp2.setAdapter(adapter2);

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