package com.kocha.myteam;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TeamListRecyclerViewAdapter extends RecyclerView.Adapter<TeamListRecyclerViewAdapter.TeamListViewHolder> {
    private ArrayList<String> mDataset;
    private Activity activity;

    public class TeamListViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public TeamListViewHolder(View v) {
            super(v);
            textView = (TextView) itemView.findViewById(R.id.activity_team_recycler_view_holder_text_view);

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

    public TeamListRecyclerViewAdapter(ArrayList<String> myDataset, Activity activity) {
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
        holder.textView.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
