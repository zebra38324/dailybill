package com.example.dailybill;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView category;
    TextView amount;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        category = itemView.findViewById(R.id.text_category);
        amount = itemView.findViewById(R.id.text_amount);
    }
}