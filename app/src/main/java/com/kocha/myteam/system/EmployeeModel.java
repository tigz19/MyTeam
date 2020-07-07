package com.kocha.myteam.system;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Модель для хранения данных одного сотрудника
 */
// Аннотируем Модель как сущность БД Room
@Entity
public class EmployeeModel {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String surname;
    public String patronymic;
    public int salary;
    public int income;

    public EmployeeModel(String name, String surname, String patronymic, int salary, int income) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.salary = salary;
        this.income = income;
    }
}
