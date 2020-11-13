package com.nishatsultana.myehr.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.Model.Chamber;
import com.nishatsultana.myehr.R;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MyChambersAdapter extends FirebaseRecyclerAdapter<Chamber, MyChambersAdapter.myChambersViewHolder> {
    
    public MyChambersAdapter(@NonNull FirebaseRecyclerOptions<Chamber> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myChambersViewHolder holder, int position, @NonNull Chamber model) {
        holder.textView1.setText(model.getChamber());
        holder.textView2.setText(model.getLocation());
        holder.textView3.setText(model.getVisitingHour());
    }


    @NonNull
    @Override
    public MyChambersAdapter.myChambersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.my_chamber_row_item,parent,false);
        return new myChambersViewHolder(view) ;
    }

    public static class myChambersViewHolder extends RecyclerView.ViewHolder {
        TextView textView1, textView2, textView3;
        private final View itemView;
        public myChambersViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            textView1 = itemView.findViewById(R.id.chamber_name_list);
            textView2 = itemView.findViewById(R.id.chamber_loc_list);
            textView3 = itemView.findViewById(R.id.chamber_hour_list);
        }

        public TextView getTextView1() {
            return textView1;
        }
        public TextView getTextView2() {
            return textView2;
        }
        public TextView getTextView3() {
            return textView3;
        }

        }

}
