package com.kocha.myteam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

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
    String name;
    String surname;
    String patronymic;
    String wage;
    String emplIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allViews = AddTeamMemberActivityBinding.inflate(getLayoutInflater());
        setContentView(allViews.getRoot());
        allViews.addMemberButton.setVisibility(View.INVISIBLE);
        collectionType = new TypeToken<ArrayList<String>>() {
        }.getType();

        // TODO: 18.03.2020 Надо обрабатывать все поля, а не только последнее. 
        allViews.editEmplIncome.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                name = String.valueOf(allViews.editName.getText());
                surname = String.valueOf(allViews.editSurname.getText());
                patronymic = String.valueOf(allViews.editPatronymic.getText());
                wage = String.valueOf(allViews.editWage.getText());
                emplIncome = String.valueOf(allViews.editEmplIncome.getText());
                if (!name.equals("") && !surname.equals("") && !patronymic.equals("") && !wage.equals("") && !emplIncome.equals("")) {
                    allViews.addMemberButton.setVisibility(View.VISIBLE);
                }
                else {
                    allViews.addMemberButton.setVisibility(View.INVISIBLE);
                }
            }
        });


        // TODO: 15.03.2020 скрыть кнопку пока не заполнены все поля 
        allViews.addMemberButton.setOnClickListener(v -> {
            gson = new Gson();

            sharedPreferences = getSharedPreferences("mysetting", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            ArrayList<String> list = new ArrayList<>();


            try {
                // TODO: 15.03.2020   это убрать в oncreate. А в Teamlist сразу сохранять пустой список
                list = gson.fromJson(sharedPreferences.getString("list", "Не могу получить данные"), collectionType);
                list.add(name);
            } catch (Exception e) {
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
