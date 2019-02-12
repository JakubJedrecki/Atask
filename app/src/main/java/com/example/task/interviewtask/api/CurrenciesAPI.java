package com.example.task.interviewtask.api;

import com.example.task.interviewtask.api.models.Currencies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrenciesAPI {

    @GET("latest")
    Call<Currencies> getCurrencies(@Query("access_key") String accessKey, @Query("symbols") String symbols);

}
