package com.kocha.myteam.system;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesHelper<M> {

    public static final String MY_SETTING_NAME = "mysetting";
    public static final String SAVING_LIST_NAME = "list";
    public final Context context;
    public final SharedPreferences sharedPreferences;
    public final Class modelClass;
    public final Moshi moshi = new Moshi.Builder().build();
    public final JsonAdapter<List<M>> jsonAdapter;

    // Для работы хелпера необходим Контекст
    public SharedPreferencesHelper(@NonNull Context context, Class<M> modelClass) {
        this.context = context;
        this.modelClass = modelClass;
        sharedPreferences = context.getSharedPreferences(MY_SETTING_NAME, Context.MODE_PRIVATE);
        jsonAdapter = moshi.adapter(Types.newParameterizedType(List.class, modelClass));
    }

    // Получение списка сотрудников из Shared Prefs
    public ArrayList<M> getTeamItemModels() {
        return getViaMoshi();
    }

    // Сохранение списка сотрудников в Shared Prefs
    public void saveItemModels(ArrayList<M> models) {
        saveViaMoshi(models);
    }

    private ArrayList<M> getViaMoshi() {
        String json = sharedPreferences.getString(SAVING_LIST_NAME, "НЕТ");
        ArrayList<M> models;
        try {
            List<M> modelList = jsonAdapter.fromJson(json);
            if (modelList == null) {
                throw new NullPointerException();
            }
            models = new ArrayList<>(modelList);
        } catch (Exception e) {
            e.printStackTrace();
            models = new ArrayList<>();
            saveItemModels(models);
        }
        return models;
    }

    private void saveViaMoshi(ArrayList<M> models) {
        String json = jsonAdapter.toJson(models);
        sharedPreferences.edit()
                .putString(SAVING_LIST_NAME, json)
                .apply();
    }
}
