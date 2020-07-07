package com.kocha.myteam.system;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EmployeeModelDao {

    // Добавление EmployeeModel в БД>
    @Insert
    void insertAll(EmployeeModel... employeeModels);

    // Добавление EmployeeModel в БД>
    @Insert
    void insertAll(List<EmployeeModel> employeeModels);

    // Удаление EmployeeModel из БД
    @Delete
    void delete(EmployeeModel employeeModel);

    // Получение всех EmployeeModel из БД
    @Query("SELECT * FROM EmployeeModel")
    List<EmployeeModel> getAllEmployeeModels();
}

