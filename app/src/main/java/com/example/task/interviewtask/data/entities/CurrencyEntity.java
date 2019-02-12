package com.example.task.interviewtask.data.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "currency_table")
public class CurrencyEntity {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String name;
    private Double value;

    public CurrencyEntity(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
