package com.kocha.myteam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.kocha.myteam.databinding.TeamListActivityBinding;

import java.util.ArrayList;

public class TeamListActivity extends AppCompatActivity {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private com.kocha.myteam.databinding.TeamListActivityBinding allView;
    SharedPreferences sharedPreferences;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allView = TeamListActivityBinding.inflate(getLayoutInflater());
        setContentView(allView.getRoot());
        allView.recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        allView.recyclerView.setLayoutManager(layoutManager);

        ArrayList<String> myDataset = new ArrayList<>();
        sharedPreferences = getSharedPreferences("mysetting", Context.MODE_PRIVATE);

        // String[] list = (sharedPreferences.getString("list", "Не могу получить данные").split(";"));
        ArrayList<String> list = new ArrayList<>();
        

        for (String name : list) {
            myDataset.add(name);
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("countEmpl", String.valueOf(myDataset.size()));
        editor.putString("incomeEmpl", "2000");
        editor.apply();

        mAdapter = new TeamListRecyclerViewAdapter(myDataset, this);
        allView.recyclerView.setAdapter(mAdapter);

        allView.buttonAddMember.setOnClickListener(v -> {
            Intent intent = new Intent(TeamListActivity.this, AddTeamMemberActivity.class);
            startActivity(intent);
        });


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(TeamListActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(TeamListActivity.this, "on Swiped ", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                myDataset.remove(position);
                mAdapter.notifyDataSetChanged();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(allView.recyclerView);
    }
}
