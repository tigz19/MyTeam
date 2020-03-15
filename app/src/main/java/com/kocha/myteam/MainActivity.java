package com.kocha.myteam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kocha.myteam.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding allViews;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allViews = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(allViews.getRoot());
        sharedPreferences = getSharedPreferences("mysetting", Context.MODE_PRIVATE);
        sharedPreferences = getSharedPreferences("mysetting", Context.MODE_PRIVATE);

        String[] myDataset = sharedPreferences.getString("list", "").split(";");

        allViews.emplCount.setText(sharedPreferences.getString("countEmpl", String.valueOf(myDataset.length)));
        allViews.incomeCount.setText(sharedPreferences.getString("incomeEmpl", "Не могу получить данные"));

        allViews.getTeamListButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TeamListActivity.class);
            startActivity(intent);
        });

    }
}
