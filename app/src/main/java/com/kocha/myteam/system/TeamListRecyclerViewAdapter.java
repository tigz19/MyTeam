package com.kocha.myteam.system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kocha.myteam.R;

import java.util.ArrayList;

public class TeamListRecyclerViewAdapter extends RecyclerView.Adapter<TeamListRecyclerViewAdapter.TeamListViewHolder> {

    private ArrayList<EmployeeModel> employeeModels;

    public TeamListRecyclerViewAdapter(ArrayList<EmployeeModel> employeeModels) {
        this.employeeModels = employeeModels;
    }

    @NonNull
    @Override
    public TeamListRecyclerViewAdapter.TeamListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_team_list_recycler_view_item, parent, false);
        return new TeamListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TeamListViewHolder holder, int position) {
        holder.number.setText(position + 1 + ".");
        holder.name.setText(employeeModels.get(position).name + " " + employeeModels.get(position).surname + " " + employeeModels.get(position).patronymic);
        holder.salary.setText("Зарплата: " + employeeModels.get(position).salary + " руб.");
        holder.income.setText("Доход: " + employeeModels.get(position).income + " руб.");
    }

    @Override
    public int getItemCount() {
        return employeeModels.size();
    }

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
}
