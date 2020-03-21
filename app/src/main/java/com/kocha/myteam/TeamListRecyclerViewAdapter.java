package com.kocha.myteam;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeamListRecyclerViewAdapter extends RecyclerView.Adapter<TeamListRecyclerViewAdapter.TeamListViewHolder> {
    private List<TeamItemModel> mDataset;
    private Activity activity;

    public class TeamListViewHolder extends RecyclerView.ViewHolder {
        public TextView textName;
        public TextView textIncome;
        public TextView textWage;

        public TeamListViewHolder(View v) {
            super(v);
            textName = (TextView) itemView.findViewById(R.id.activity_team_recycler_view_holder_text_view);
            textIncome = (TextView) itemView.findViewById(R.id.textIncome);
            textWage = (TextView) itemView.findViewById(R.id.textWage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(activity.getApplicationContext(),
                            "позиция" + getAdapterPosition(),
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
    }

    public TeamListRecyclerViewAdapter(List<TeamItemModel> myDataset, Activity activity) {
        mDataset = myDataset;
        this.activity = activity;
    }

    @Override
    public TeamListRecyclerViewAdapter.TeamListViewHolder onCreateViewHolder(ViewGroup parent,
                                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_team_list_recycler_view_item, parent, false);

        TeamListViewHolder vh = new TeamListViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TeamListViewHolder holder, int position) {
        holder.textName.setText(mDataset.get(position).getName() + " " + mDataset.get(position).getSurname() + " " + mDataset.get(position).getPatronymic());
        holder.textWage.setText("Зарплата: " + mDataset.get(position).getWage() + " руб.");
        holder.textIncome.setText("Приносит доход: " + mDataset.get(position).getEmplIncome() + " руб.");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
