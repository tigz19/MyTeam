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

public class SharedPreferencesHelper<M> {

    public static final String MY_SETTING_NAME = "mysetting";
    public static final String SAVING_LIST_NAME = "list";
    public final Context context;
    public final SharedPreferences sharedPreferences;
    public final Gson gson = new Gson();
    public final Type collectionGsonType = new TypeToken<List<M>>() {
    }.getType();

    // Для работы хелпера необходим Контекст
    public SharedPreferencesHelper(@NonNull Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(MY_SETTING_NAME, Context.MODE_PRIVATE);
    }

    // Получение списка сотрудников из Shared Prefs
    public ArrayList<M> getTeamItemModels() {
        String serializedString = sharedPreferences.getString(SAVING_LIST_NAME, "НЕТ");
        ArrayList<M> models;
        try {
            models = gson.fromJson(serializedString, collectionGsonType);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            Toast.makeText(context, "Сотрудники не найдены! Создана новая база.", Toast.LENGTH_LONG).show();
            models = new ArrayList<M>();
            saveItemModels(models);
        }
        return models;
    }

    // Сохранение списка сотрудников в Shared Prefs
    public void saveItemModels(ArrayList<M> models) {
        String value = gson.toJson(models);
        sharedPreferences.edit()
                .putString(SAVING_LIST_NAME, value)
                .apply();
    }
}
