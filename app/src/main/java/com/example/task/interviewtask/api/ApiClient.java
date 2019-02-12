package com.example.task.interviewtask.api;

import com.example.task.interviewtask.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static CurrenciesAPI REST_CLIENT;
    private static final String BASE_URL = BuildConfig.BASE_URL;

    static {
        setUpRestClient();
    }

    public static CurrenciesAPI get() { return REST_CLIENT; }

    /**
     * Sets up API client
     */
    private static void setUpRestClient() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        REST_CLIENT = retrofit.create(CurrenciesAPI.class);
    }
}
