package com.example.task.interviewtask.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.task.interviewtask.data.entities.BasketProductEntity;

import java.util.List;

@Dao
public interface BasketProductDao {

    @Insert
    void insert(BasketProductEntity entity);

    @Query("SELECT * FROM basket_product_table WHERE ProductName = :productName LIMIT 1")
    BasketProductEntity getBasketProduct(String productName);

    @Query("Select * from basket_product_table")
    LiveData<List<BasketProductEntity>> getBasketProducts();

    @Query("DELETE FROM basket_product_table")
    void deleteAllBasketProducts();

    @Delete
    void deleteBasketProduct(BasketProductEntity entity);

    @Update
    void update(BasketProductEntity basketProductEntity);
}
