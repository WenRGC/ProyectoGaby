package com.apps.proyectomenu.models;

public class Backup {
    private String name;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Backup(String name, String date){
        this.name = name;
        this.date = date;
    }
}
