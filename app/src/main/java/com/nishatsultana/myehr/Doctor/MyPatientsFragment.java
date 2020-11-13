package com.nishatsultana.myehr.Doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.Adapters.MyPatientsAdapter;
import com.nishatsultana.myehr.Model.Patients;
import com.nishatsultana.myehr.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyPatientsFragment extends Fragment {

    private List<Patients> patientData;
    private RecyclerView rv;
    private MyPatientsAdapter adapter;

    private DatabaseReference mReference;

    View v2;

    public MyPatientsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v2 = inflater.inflate(R.layout.fragment_my_patients, container, false);

        // Notice that I changed the order of RecyclerView setup
        rv = v2.findViewById(R.id.recyclerview_myPatients);
        patientData = new ArrayList<>();
        adapter = new MyPatientsAdapter(getContext(), patientData);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        //set Layout as LinearLayout
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        //send Query FirebaseDatabase

        mReference = FirebaseDatabase.getInstance().getReference("Patients");

        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        Patients dataP = npsnapshot.getValue(Patients.class);
                        patientData.add(dataP);
                    }
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return v2;
    }
}