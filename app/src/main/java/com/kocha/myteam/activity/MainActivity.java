package com.kocha.myteam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kocha.myteam.databinding.ActivityMainBinding;
import com.kocha.myteam.system.AppDatabase;
import com.kocha.myteam.system.EmployeeModel;
import com.kocha.myteam.system.EmployeeModelDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Локальный список сотрдуников
    public List<EmployeeModel> employeeModels;
    // Биндинг Вьюх
    public ActivityMainBinding viewBinding;

    public EmployeeModelDao employeeModelDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Биндинг Вьюх и развертка верстки
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
        employeeModelDao = AppDatabase.getAppDatabase(this).getEmployeeModelDao();

        // Добавление коллбека кнопки списка сотрудников
        viewBinding.getTeamListButton.setOnClickListener(new TeamListOnClickListener());
    }

    // Обновляем данные в полях по возвращению назад
    @Override
    public void onResume() {
        super.onResume();
        // Получение сохраненного списка сотрудников или иннициализация нового
        employeeModels = employeeModelDao.getAllEmployeeModels();
        // Если полученный писок сотрудников null, то создаем новый пустой и сохраняем его
        if (employeeModels == null) {
            Toast.makeText(this, "Иннициируем базу Сотрудников...", Toast.LENGTH_LONG).show();
            employeeModels = new ArrayList<>();
            employeeModelDao.insertAll(employeeModels);
        }

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
