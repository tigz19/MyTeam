package com.kocha.myteam.system;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.kocha.myteam.R;

public class TeamListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView number;
    public TextView name;
    public TextView income;
    public TextView salary;

    public TeamListViewHolder(View v) {
        super(v);
        number = itemView.findViewById(R.id.number);
        name = itemView.findViewById(R.id.name);
        income = itemView.findViewById(R.id.income);
        salary = itemView.findViewById(R.id.edit_salary);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Позиция: " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
    }
}
