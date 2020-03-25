package com.kocha.myteam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.kocha.myteam.databinding.AddTeamMemberActivityBinding;
import com.kocha.myteam.system.EmployeeModel;
import com.kocha.myteam.system.SharedPreferencesHelper;

import java.util.ArrayList;

public class AddingEmployeeActivity extends AppCompatActivity {

    // Помощник для работы с Shared Prefs
    private SharedPreferencesHelper sharedPreferencesHelper;
    // Биндинг Вьюх
    private AddTeamMemberActivityBinding viewBinding;
    // Локальный список сотрудников
    private ArrayList<EmployeeModel> employeeModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Биндинг Вьюх и развертка верстки
        viewBinding = AddTeamMemberActivityBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        // Создаем помощника работы с Shared Prefs
        sharedPreferencesHelper = new SharedPreferencesHelper(this);

        // Приводим видимость кнопки в стартовое положение
        updateAddButtonVisibility();

        // Настройка коллбеков Вьюх
        viewBinding.editIncome.addTextChangedListener(new SimpleTextWatcher());
        viewBinding.editSalary.addTextChangedListener(new SimpleTextWatcher());
        viewBinding.editName.addTextChangedListener(new SimpleTextWatcher());
        viewBinding.editSurname.addTextChangedListener(new SimpleTextWatcher());
        viewBinding.editPatronymic.addTextChangedListener(new SimpleTextWatcher());
        viewBinding.addMemberButton.setOnClickListener(new AddEmployeeClickListener());

        // Восстанавливаем список сотрудников из Shared Prefs
        employeeModels = sharedPreferencesHelper.getTeamItemModels();
    }

    /**
     * В завивимости от состояния полей делает кнопку видимой или не видимой
     */
    private void updateAddButtonVisibility() {
        if (viewBinding.editName.getText().length() == 0 &&
                viewBinding.editSurname.getText().length() == 0 &&
                viewBinding.editPatronymic.getText().length() == 0 &&
                viewBinding.editSalary.getText().length() == 0 &&
                viewBinding.editIncome.getText().length() == 0) {
            viewBinding.addMemberButton.setVisibility(View.INVISIBLE);
        } else {
            viewBinding.addMemberButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Класс, в котором описана реакция на нажатие кнопки добавления сотрудника
     */
    class AddEmployeeClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            EmployeeModel employeeModel = new EmployeeModel(viewBinding.editName.getText().toString(),
                    viewBinding.editSurname.getText().toString(),
                    viewBinding.editPatronymic.getText().toString(),
                    Integer.parseInt(viewBinding.editIncome.getText().toString()),
                    Integer.parseInt(viewBinding.editSalary.getText().toString()));
            employeeModels.add(employeeModel);
            sharedPreferencesHelper.saveItemModels(employeeModels);
            onBackPressed();
        }
    }

    /**
     * Класс с логикой поведения полей при изменении текста
     */
    class SimpleTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            updateAddButtonVisibility();
        }
    }
}
