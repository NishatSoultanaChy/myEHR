package com.nishatsultana.myehr.Patient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.Adapters.MyDoctorsAdapter;
import com.nishatsultana.myehr.Model.Doctors;
import com.nishatsultana.myehr.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyDoctorsFragment_P extends Fragment {
    private List<Doctors> listData;
    private RecyclerView rv;
    private MyDoctorsAdapter adapter;

    private DatabaseReference mReference;

    View v;

    public MyDoctorsFragment_P() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_my_doctors, container, false);

        //recycler view setup
        rv = v.findViewById(R.id.recyclerview);
        listData = new ArrayList<>();
        adapter = new MyDoctorsAdapter(getContext(), listData);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);

        //set Layout as LinearLayout
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        //send Query FirebaseDatabase

        mReference = FirebaseDatabase.getInstance().getReference("Doctors");

        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        Doctors l = npsnapshot.getValue(Doctors.class);
                        listData.add(l);
                    }
                    //adapter=new MyDoctorsAdapter(listData);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}

