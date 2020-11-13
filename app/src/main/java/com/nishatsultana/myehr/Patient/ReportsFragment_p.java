package com.nishatsultana.myehr.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.nishatsultana.myehr.Adapters.MyReportsAdapter2;
import com.nishatsultana.myehr.Model.Reports;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;


public class ReportsFragment_p extends Fragment {


    private RecyclerView rv;
    private MyReportsAdapter2 adapter;
    //private DatabaseReference mReference;

    View v;

    FloatingActionButton floatingActionButton;

    public ReportsFragment_p() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        v = inflater.inflate(R.layout.fragment_reports, container, false);

        final String phone = Prevalent.CurrentOnlineUser.getPhone();


        floatingActionButton = v.findViewById(R.id.fab_AddReports);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(getActivity(), AddPatientLabReportActivity.class);
                    startActivity(intent);


            }
        });

        rv = v.findViewById(R.id.recyclerview_reports);

        //set Layout as LinearLayout
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Reports> options =
                new FirebaseRecyclerOptions.Builder<Reports>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Reports").child(phone), Reports.class)
                        .build();

        adapter=new MyReportsAdapter2(options);
        rv.setAdapter(adapter);


        return v;
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
