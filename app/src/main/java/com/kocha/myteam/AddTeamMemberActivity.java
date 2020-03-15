package com.kocha.myteam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kocha.myteam.databinding.AddTeamMemberActivityBinding;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddTeamMemberActivity extends AppCompatActivity {
    private AddTeamMemberActivityBinding allViews;
    private SharedPreferences sharedPreferences;
    Gson gson;
    Type collectionType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allViews = AddTeamMemberActivityBinding.inflate(getLayoutInflater());
        setContentView(allViews.getRoot());
        collectionType = new TypeToken<ArrayList<String>>() {
        }.getType();

        allViews.addMemberButton.setOnClickListener(v -> {
            String name = String.valueOf(allViews.editName.getText());
            String surname = String.valueOf(allViews.editSurname.getText());
            String patronymic = String.valueOf(allViews.editPatronymic.getText());
            gson = new Gson();

            sharedPreferences = getSharedPreferences("mysetting", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            ArrayList<String> list = new ArrayList<>();


            try {
                // это убрать в oncreate. А в Teamlist сразу сохранять пустой список
                list = gson.fromJson(sharedPreferences.getString("list", "Не могу получить данные"), collectionType);
                list.add(name);
            }
            catch (Exception e){
                list.add(name);
                editor.putString("list", gson.toJson(list));
                editor.apply();
                Intent intent = new Intent(AddTeamMemberActivity.this, TeamListActivity.class);
                startActivity(intent);
            }
                editor.putString("list", gson.toJson(list));
                editor.apply();

                Intent intent = new Intent(AddTeamMemberActivity.this, TeamListActivity.class);
                startActivity(intent);
            });

        }

    }
