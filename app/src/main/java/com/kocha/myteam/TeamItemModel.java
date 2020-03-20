package com.kocha.myteam;

public class TeamItemModel {
    private String name;
    private String surname;
    private String patronymic;
    private String wage;
    private String emplIncome;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public String getEmplIncome() {
        return emplIncome;
    }

    public void setEmplIncome(String emplIncome) {
        this.emplIncome = emplIncome;
    }
}
