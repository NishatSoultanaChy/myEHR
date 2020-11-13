package com.nishatsultana.myehr.Adapters;



import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.R;

public class RecyclerViewHolder_myReport extends RecyclerView.ViewHolder {

    private final TextView textView1;
    private final TextView textView2;
    private final TextView textView3;
    public RecyclerViewHolder_myReport(@NonNull View itemView) {
        super(itemView);
        textView1 = itemView.findViewById(R.id.test_name);
        textView2 = itemView.findViewById(R.id.test_date);
        textView3 = itemView.findViewById(R.id.assigned_by);

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