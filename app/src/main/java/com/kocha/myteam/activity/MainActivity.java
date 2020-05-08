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
    public SharedPreferencesHelper<EmployeeModel> sharedPreferencesHelper;
    // Локальный список сотрдуников
    public ArrayList<EmployeeModel> employeeModels;
    // Биндинг Вьюх
    public ActivityMainBinding viewBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Биндинг Вьюх и развертка верстки
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        // Создаем помощника работы с Shared Prefs
        sharedPreferencesHelper = new SharedPreferencesHelper<>(this, EmployeeModel.class);

        // Добавление коллбека кнопки списка сотрудников
        viewBinding.getTeamListButton.setOnClickListener(new TeamListOnClickListener());
    }

    // Обновляем данные в полях по возвращению назад
    @Override
    public void onResume() {
        super.onResume();
        // Получение сохраненного списка сотрудников или иннициализация нового
        employeeModels = sharedPreferencesHelper.getTeamItemModels();

        // Заполннеие количества сотрудников и общего дохода
        viewBinding.emplCount.setText(String.valueOf(employeeModels.size()));

        int income = 0;
        for (EmployeeModel employeeModel : employeeModels) {
            income += employeeModel.income;
        }
        viewBinding.incomeCount.setText(String.valueOf(income));
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Сюда можно записать код для выполнения
    }

    /**
     * Класс с коллбеком нажатия на кнопку списка сотрдуников
     */
    public class TeamListOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // Запуск нового Активити
            Intent intent = new Intent(MainActivity.this, TeamListActivity.class);
            startActivityForResult(intent, 1);
        }
    }
}
