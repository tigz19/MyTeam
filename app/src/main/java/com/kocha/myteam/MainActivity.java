package com.kocha.myteam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kocha.myteam.databinding.ActivityMainBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding allViews;
    SharedPreferences sharedPreferences;
    Gson gson;
    Type collectionType;
    ArrayList<TeamItemModel> myDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allViews = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(allViews.getRoot());
        sharedPreferences = getSharedPreferences("mysetting", Context.MODE_PRIVATE);
        sharedPreferences = getSharedPreferences("mysetting", Context.MODE_PRIVATE);

        collectionType = new TypeToken<List<TeamItemModel>>() {
        }.getType();
        gson = new Gson();
        sharedPreferences = getSharedPreferences("mysetting", Context.MODE_PRIVATE);
        myDataset = new ArrayList<>();
        try {
            myDataset = gson.fromJson(sharedPreferences.getString("list", "Не могу получить данные"), collectionType);
        }
        catch (Exception e){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("list", gson.toJson(myDataset));
            editor.apply();
        }

        allViews.emplCount.setText(sharedPreferences.getString("countEmpl", String.valueOf(myDataset.size())));
        allViews.incomeCount.setText(sharedPreferences.getString("incomeEmpl", "Не могу получить данные"));

        allViews.getTeamListButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TeamListActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    // Обновляем данные в полях по возвращению назад
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        allViews.emplCount.setText(sharedPreferences.getString("countEmpl", String.valueOf(myDataset.size())));
        allViews.incomeCount.setText(sharedPreferences.getString("incomeEmpl", "Не могу получить данные"));
    }
}
