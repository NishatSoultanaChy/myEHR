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
import com.nishatsultana.myehr.Adapters.SymptomsAdapter;
import com.nishatsultana.myehr.Model.Symptoms;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;

public class SymptomsActivity extends AppCompatActivity {

    FloatingActionButton fabSymptoms;
    RecyclerView recviewSymp;
    SymptomsAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        fabSymptoms = findViewById(R.id.fab_add_Symptopms);


        fabSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SymptomsActivity.this,AddSymptomsActivity.class));//loadFragment(fragment);
            }
        });

        final String phone = Prevalent.CurrentOnlineUser.getPhone();

        recviewSymp = findViewById(R.id.recviewSymptoms);
        recviewSymp.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Symptoms> options =
                new FirebaseRecyclerOptions.Builder<Symptoms>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Symptoms").child(phone), Symptoms.class)
                        .build();

        adapter=new SymptomsAdapter(options);
        recviewSymp.setAdapter(adapter);
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