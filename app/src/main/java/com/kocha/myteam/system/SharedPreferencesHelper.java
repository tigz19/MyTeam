package com.kocha.myteam.system;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesHelper {

    public static final String MY_SETTING_NAME = "mysetting";
    public static final String EMPLOYEE_LIST_NAME = "list";
    private final Context context;
    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();
    private Type collectionGsonType = new TypeToken<List<EmployeeModel>>() {
    }.getType();

    // Для работы хелпера необходим Контекст
    public SharedPreferencesHelper(@NonNull Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(MY_SETTING_NAME, Context.MODE_PRIVATE);
    }

    // Получение списка сотрудников из Shared Prefs
    public ArrayList<EmployeeModel> getTeamItemModels() {
        String serializedString = sharedPreferences.getString(EMPLOYEE_LIST_NAME, "НЕТ");
        ArrayList<EmployeeModel> employeeModels;
        try {
            employeeModels = gson.fromJson(serializedString, collectionGsonType);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Toast.makeText(context, "Сотрдуники не найдены! Создана новая база.", Toast.LENGTH_LONG).show();
            employeeModels = new ArrayList<>();
            saveItemModels(employeeModels);
        }
        return employeeModels;
    }

    // Сохранение списка сотрудников в Shared Prefs
    public void saveItemModels(ArrayList<EmployeeModel> employeeModels) {
        sharedPreferences.edit()
                .putString(EMPLOYEE_LIST_NAME, gson.toJson(employeeModels))
                .apply();
    }
}
