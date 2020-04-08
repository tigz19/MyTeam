package com.kocha.myteam.system;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kocha.myteam.R;

import java.util.ArrayList;

public class TeamListRecyclerViewAdapter extends RecyclerView.Adapter<TeamListViewHolder> {

    public ArrayList<EmployeeModel> employeeModels;

    public TeamListRecyclerViewAdapter(ArrayList<EmployeeModel> employeeModels) {
        this.employeeModels = employeeModels;
    }

    @NonNull
    @Override
    public TeamListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
}
