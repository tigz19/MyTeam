package com.kocha.myteam.system;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kocha.myteam.databinding.ActivityTeamListRecyclerViewItemBinding;

import java.util.List;

public class TeamListRecyclerViewAdapter extends RecyclerView.Adapter<TeamListViewHolder> {

    public List<EmployeeModel> employeeModels;
    public Activity activity;

    // Для работы Адаптера необходим список Сотрудников и Активити, в котором работает Recycler View
    public TeamListRecyclerViewAdapter(List<EmployeeModel> employeeModels, Activity activity) {
        this.employeeModels = employeeModels;
        this.activity = activity;
    }

    @NonNull
    @Override
    // Описываем тут создание нового Холдера
    public TeamListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Получаем из Активити LayoutInflater, который нужен для разворачивания Биндинга
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        // Разворачиваем Биндинг с помощью LayoutInflater и со специальными параметрами
        ActivityTeamListRecyclerViewItemBinding binding = ActivityTeamListRecyclerViewItemBinding
                .inflate(layoutInflater, parent, false);
        // Создаем новый Холдер, передает емя для работы развернутый Биндинг
        return new TeamListViewHolder(binding);
    }

    @Override
    // Описываем, как данные о Сотрудниках заполняются во View в Холдере
    public void onBindViewHolder(TeamListViewHolder holder, int position) {
        // Сохраняем текущую модель Сотрудника из списка в переменную для удобства
        EmployeeModel employeeModel = employeeModels.get(position);
        // Формируем текстовые поля из модели Сотрудника
        String number = (position + 1) + ".";
        String name = employeeModel.name + " " + employeeModel.surname + " " + employeeModel.patronymic;
        String salary = "Зарплата: " + employeeModel.salary + " руб.";
        String income = "Доход: " + employeeModels.get(position).income + " руб.";
        // Заполняем View элемента списка через Холдер
        holder.binding.number.setText(number);
        holder.binding.name.setText(name);
        holder.binding.salary.setText(salary);
        holder.binding.income.setText(income);
    }

    @Override
    // Описываем, как Адаптер может узнать количество Сотрудников
    public int getItemCount() {
        return employeeModels.size();
    }
}
