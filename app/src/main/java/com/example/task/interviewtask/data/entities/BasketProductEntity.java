package com.example.task.interviewtask.data.entities;

import android.arch.persistence.room.Entity;

@Entity(tableName = "basket_product_table")
public class BasketProductEntity extends ProductEntity {

    private int Quantity = 0;

    public BasketProductEntity() {}

    public BasketProductEntity(String productName, Double price) {
        super(productName, price);
        Quantity = 1;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
