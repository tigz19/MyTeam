package com.kocha.myteam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kocha.myteam.databinding.ActivityMainBinding;
import com.kocha.myteam.system.EmployeeModel;
import com.kocha.myteam.system.SharedPreferencesHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Помощник для работы с Shared Prefs
    private SharedPreferencesHelper sharedPreferencesHelper;
    // Локальный список сотрдуников
    private ArrayList<EmployeeModel> employeeModels;
    // Биндинг Вьюх
    private ActivityMainBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Биндинг Вьюх и развертка верстки
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        // Создаем помощника работы с Shared Prefs
        sharedPreferencesHelper = new SharedPreferencesHelper(this);

        // Добавление коллбека кнопки списка сотрудников
        viewBinding.getTeamListButton.setOnClickListener(new TeamListOnClickListener());
    }

    // Обновляем данные в полях по возвращению назад
    @Override
    protected void onResume() {
        super.onResume();
        // Получение сохраненного списка сотрудников или иннициализация нового
        employeeModels = sharedPreferencesHelper.getTeamItemModels();
        if (employeeModels == null) {
            employeeModels = new ArrayList<>();
            sharedPreferencesHelper.saveItemModels(employeeModels);
        }

        // Заполннеие количества сотрудников и общего дохода
        viewBinding.emplCount.setText(String.valueOf(employeeModels.size()));

        int income = 0;
        for (EmployeeModel employeeModel : employeeModels) {
            income += employeeModel.income;
        }
        viewBinding.incomeCount.setText(String.valueOf(income));
    }

    /**
     * Класс с коллбеком нажатия на кнопку списка сотрдуников
     */
    class TeamListOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // Запуск нового Активити
            Intent intent = new Intent(MainActivity.this, TeamListActivity.class);
            startActivityForResult(intent, 1);
        }
    }
}
