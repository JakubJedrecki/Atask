package com.example.task.interviewtask.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.task.interviewtask.data.entities.ProductEntity;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(ProductEntity productEntity);

    @Query("Select * from product_table WHERE Id = :id")
    ProductEntity getProduct(int id);

    @Query("Select * from product_table")
    LiveData<List<ProductEntity>> getProducts();

    @Query("DELETE FROM product_table")
    void deleteAllProducts();
}
