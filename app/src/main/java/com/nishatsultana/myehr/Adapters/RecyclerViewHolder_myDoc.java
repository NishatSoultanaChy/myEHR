package com.nishatsultana.myehr.Adapters;



import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nishatsultana.myehr.R;

public class RecyclerViewHolder_myDoc extends RecyclerView.ViewHolder {

    private final ImageView imageView;
    private final TextView textView1;
    private final TextView textView2;
    public RecyclerViewHolder_myDoc(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_mydoc);
        textView1 = itemView.findViewById(R.id.name_mydoc);
        textView2 = itemView.findViewById(R.id.spec_mydoc);
    }

    public  ImageView getImageView(){
        return imageView;
    }
    public TextView getTextView1() {
        return textView1;
    }
    public TextView getTextView2() {
        return textView2;
    }
}