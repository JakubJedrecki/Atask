package com.example.task.interviewtask.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.example.task.interviewtask.BuildConfig;
import com.example.task.interviewtask.api.ApiClient;
import com.example.task.interviewtask.api.models.Currencies;
import com.example.task.interviewtask.data.ShopDatabase;
import com.example.task.interviewtask.data.dao.CurrencyDao;
import com.example.task.interviewtask.data.entities.CurrencyEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrenciesRepository {
    private static final String TAG = CurrenciesRepository.class.getSimpleName();

    private CurrencyDao currencyDao;
    private LiveData<List<CurrencyEntity>> currencyEntityList;
    //currencies to be requested from API
    private List<String> currenciesToRequest = Arrays.asList("USD", "GBP", "EUR", "PLN", "AUD", "JPY");

    public CurrenciesRepository(Application application) {
        ShopDatabase shopDatabase = ShopDatabase.getDatabase(application);
        currencyDao = shopDatabase.currencyDao();
        currencyEntityList = currencyDao.getCurrencies();
    }

    /**
     * Get selected currencies from API and insert them in to database
     */
    public void getCurrenciesFromApi() {
        Call<Currencies> call = ApiClient.get().getCurrencies(BuildConfig.API_KEY, TextUtils.join(",", currenciesToRequest));
        call.enqueue(new Callback<Currencies>() {
            @Override
            public void onResponse(Call<Currencies> call, Response<Currencies> response) {
                if(response.isSuccessful()) {
                    if(response.body().getRates() != null) {

                        new deleteAsyncTask(currencyDao).execute();
                        List<CurrencyEntity> entities = new ArrayList<>();
                        entities.add(new CurrencyEntity("USD", response.body().getRates().getUSD()));
                        entities.add(new CurrencyEntity("GBP", response.body().getRates().getGBP()));
                        entities.add(new CurrencyEntity("EUR", response.body().getRates().getEUR()));
                        entities.add(new CurrencyEntity("PLN", response.body().getRates().getPLN()));
                        entities.add(new CurrencyEntity("AUD", response.body().getRates().getAUD()));
                        entities.add(new CurrencyEntity("JPY", response.body().getRates().getJPY()));

                        new insertAsyncTask(currencyDao).execute(entities);
                    }
                }
            }

            @Override
            public void onFailure(Call<Currencies> call, Throwable t) {
                Log.e(TAG, "Failed to get currencies: " + t.getMessage(), t);
                //todo inform user about failed request
            }
        });
    }

    /**
     * Retrieves all currencies
     * @return
     */
    public LiveData<List<CurrencyEntity>> getCurrencyEntityList() {
        return currencyEntityList;
    }

    private static class insertAsyncTask extends AsyncTask<List<CurrencyEntity>, Void, Void> {
        private CurrencyDao asyncTaskDao;

        insertAsyncTask(CurrencyDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(List<CurrencyEntity>... lists) {

            for (CurrencyEntity entity : lists[0]) {
                asyncTaskDao.insert(entity);
            }

            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Void, Void, Void> {
        private CurrencyDao asyncTaskDao;

        deleteAsyncTask(CurrencyDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            asyncTaskDao.deleteAllCurrencies();

            return null;
        }
    }
}
