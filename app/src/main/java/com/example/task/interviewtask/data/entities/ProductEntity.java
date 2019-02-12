package com.example.task.interviewtask.data.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "product_table")
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String ProductName;
    private Double Price;

    public ProductEntity() {}

    public ProductEntity(String productName, Double price) {
        ProductName = productName;
        Price = price;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
