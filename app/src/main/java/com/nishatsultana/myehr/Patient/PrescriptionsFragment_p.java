package com.nishatsultana.myehr.Patient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.nishatsultana.myehr.Adapters.MyReportsAdapter2;
import com.nishatsultana.myehr.Adapters.PrescriptionsAdapter;
import com.nishatsultana.myehr.Model.Prescription;
import com.nishatsultana.myehr.Model.Reports;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;


public class PrescriptionsFragment_p extends Fragment {



    private RecyclerView rv;
    private PrescriptionsAdapter adapter;
    View v;


    public PrescriptionsFragment_p() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_prescriptions, container, false);

        final String phone = Prevalent.CurrentOnlineUser.getPhone();

        rv = v.findViewById(R.id.recyclerview_prescriptions_patient);

        //set Layout as LinearLayout
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Prescription> options =
                new FirebaseRecyclerOptions.Builder<Prescription>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Prescriptions").child(phone), Prescription.class)
                        .build();

        adapter=new PrescriptionsAdapter(options);
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
