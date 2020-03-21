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
import java.util.List;

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
    List<TeamItemModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allViews = AddTeamMemberActivityBinding.inflate(getLayoutInflater());
        setContentView(allViews.getRoot());
        allViews.addMemberButton.setVisibility(View.INVISIBLE);
        collectionType = new TypeToken<List<TeamItemModel>>() {
        }.getType();


        // чекаем состояние полей
        allViews.editEmplIncome.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

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
                } else {
                    allViews.addMemberButton.setVisibility(View.INVISIBLE);
                }
            }
        });
        allViews.editWage.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

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
                } else {
                    allViews.addMemberButton.setVisibility(View.INVISIBLE);
                }
            }
        });
        allViews.editName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

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
                } else {
                    allViews.addMemberButton.setVisibility(View.INVISIBLE);
                }
            }
        });
        allViews.editSurname.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

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
                } else {
                    allViews.addMemberButton.setVisibility(View.INVISIBLE);
                }
            }
        });
        allViews.editPatronymic.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

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
                } else {
                    allViews.addMemberButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        gson = new Gson();
        sharedPreferences = getSharedPreferences("mysetting", Context.MODE_PRIVATE);
        list = new ArrayList<>();
        list = gson.fromJson(sharedPreferences.getString("list", "Не могу получить данные"), collectionType);
        Integer incomeEmpls = sharedPreferences.getInt("incomeEmpls", 0);

        allViews.addMemberButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            TeamItemModel model = new TeamItemModel();
            model.setName(name);
            model.setSurname(surname);
            model.setPatronymic(patronymic);
            model.setEmplIncome(Integer.valueOf(emplIncome));
            model.setWage(wage);

            list.add(model);
            editor.putString("list", gson.toJson(list));
            editor.putString("countEmpl", String.valueOf(list.size()));
            editor.putInt("incomeEmpls", incomeEmpls + Integer.valueOf(emplIncome));
            editor.apply();
            Intent intent = new Intent(AddTeamMemberActivity.this, TeamListActivity.class);
            startActivity(intent);
        });

    }
}
