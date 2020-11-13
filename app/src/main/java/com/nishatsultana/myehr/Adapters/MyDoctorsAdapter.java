package com.nishatsultana.myehr.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.Model.Doctors;
import com.nishatsultana.myehr.R;


import java.util.List;

public class MyDoctorsAdapter extends RecyclerView.Adapter<RecyclerViewHolder_myDoc> {


    private final List<Doctors> listData;


    @Override
    public int getItemViewType(final int position) {
        return R.layout.my_doc_rec_view;
    }
    public MyDoctorsAdapter(Context context, List<Doctors> listData) {
        this.listData = listData;
    }

    @NonNull
    @Override
    public RecyclerViewHolder_myDoc onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_doc_rec_view,parent,false);
        return new RecyclerViewHolder_myDoc(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder_myDoc holder, int position) {

        Doctors ld=listData.get(position);
        holder.getTextView1().setText(ld.getFullname());
        holder.getTextView2().setText(ld.getSpeciality());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
