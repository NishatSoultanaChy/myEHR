package com.nishatsultana.myehr.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.Adapters.MyChambersAdapter;
import com.nishatsultana.myehr.Model.Chamber;
import com.nishatsultana.myehr.Prevalent.CurrentUser;
import com.nishatsultana.myehr.Prevalent.Prevalent;
import com.nishatsultana.myehr.R;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import static com.nishatsultana.myehr.Prevalent.Prevalent.*;


public class MyChambersFragment extends Fragment {

    private RecyclerView rv;
    private MyChambersAdapter adapter;

    String phone;

    public MyChambersFragment() {
        // Required empty public constructor
    }

    View v;

    FloatingActionButton floatingActionButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_my_chambers, container, false);

        phone = CurrentUser.phoneUser;

        floatingActionButton = v.findViewById(R.id.fab_AddChamber);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AddchambersActivity.class);
                startActivity(intent);


            }
        });

        rv = v.findViewById(R.id.recyclerview_chambers);

        //set Layout as LinearLayout
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Chamber> options =
                new FirebaseRecyclerOptions.Builder<Chamber>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Chambers").child(""+phone), Chamber.class)
                        .build();

        adapter=new MyChambersAdapter(options);
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