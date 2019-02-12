package com.example.task.interviewtask.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.task.interviewtask.data.entities.CurrencyEntity;

import java.util.List;

@Dao
public interface CurrencyDao {

    @Insert
    void insert(CurrencyEntity currency);

    @Query("SELECT * FROM currency_table")
    LiveData<List<CurrencyEntity>> getCurrencies();

    @Query("DELETE FROM currency_table")
    void deleteAllCurrencies();
}
