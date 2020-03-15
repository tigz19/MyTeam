package com.kocha.myteam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kocha.myteam.databinding.TeamListActivityBinding;

public class TeamListActivity extends AppCompatActivity {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private com.kocha.myteam.databinding.TeamListActivityBinding allView;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allView = TeamListActivityBinding.inflate(getLayoutInflater());
        setContentView(allView.getRoot());
        allView.recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        allView.recyclerView.setLayoutManager(layoutManager);

        String[] myDataset;
        sharedPreferences = getSharedPreferences("mysetting", Context.MODE_PRIVATE);
        myDataset = sharedPreferences.getString("list", "Не могу получить данные").split(";");

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("countEmpl", String.valueOf(myDataset.length));
        editor.putString("incomeEmpl", "2000");
        editor.apply();

        mAdapter = new TeamListRecyclerViewAdapter(myDataset, this);
        allView.recyclerView.setAdapter(mAdapter);

        allView.buttonAddMember.setOnClickListener(v -> {
            Intent intent = new Intent(TeamListActivity.this, AddTeamMemberActivity.class);
            startActivity(intent);
        });
    }
}
