package com.nishatsultana.myehr.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.Model.Symptoms;
import com.nishatsultana.myehr.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class SymptomsAdapter extends FirebaseRecyclerAdapter<Symptoms, SymptomsAdapter.symptomViewHolder> {

    public SymptomsAdapter(@NonNull FirebaseRecyclerOptions<Symptoms> options) {
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull SymptomsAdapter.symptomViewHolder holder, int position, @NonNull Symptoms model) {
        holder.textViewSymp1.setText(model.getSymptomdate());
        holder.textViewSymp2.setText(model.getSymptomdescription());
    }


    @NonNull
    @Override
    public SymptomsAdapter.symptomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.symptomps_row_layout,parent,false);
        return new SymptomsAdapter.symptomViewHolder(view) ;
    }

    public static class symptomViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSymp1, textViewSymp2;
        private final View itemView;
        public symptomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            textViewSymp1 = itemView.findViewById(R.id.symptomDate);
            textViewSymp2 = itemView.findViewById(R.id.symtomDescription);
        }

        public TextView getTextViewSymp1() {
            return textViewSymp1;
        }
        public TextView getTextViewSymp2() {
            return textViewSymp2;
        }

    }


}
