package com.kocha.myteam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kocha.myteam.databinding.AddTeamMemberActivityBinding;

public class AddTeamMemberActivity extends AppCompatActivity {
    private AddTeamMemberActivityBinding allViews;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allViews = AddTeamMemberActivityBinding.inflate(getLayoutInflater());
        setContentView(allViews.getRoot());


        allViews.addMemberButton.setOnClickListener(v -> {
            String name = String.valueOf(allViews.editName.getText());
            String surname = String.valueOf(allViews.editSurname.getText());
            String patronymic = String.valueOf(allViews.editPatronymic.getText());

            sharedPreferences = getSharedPreferences("mysetting", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String list = sharedPreferences.getString("list", "");
            editor.putString("list", list+";"+name);
            editor.apply();

            Intent intent = new Intent(AddTeamMemberActivity.this, TeamListActivity.class);
            startActivity(intent);
        });

    }
}
