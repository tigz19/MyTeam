package ru.engineers.my_team.system;

/**
 * Модель для хранения данных одного сотрудника
 */
public class EmployeeModel {

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
