package com.example.task.interviewtask.ui.checkout;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.task.interviewtask.data.entities.CurrencyEntity;
import com.example.task.interviewtask.data.entities.BasketProductEntity;
import com.example.task.interviewtask.data.repositories.CurrenciesRepository;
import com.example.task.interviewtask.ui.basket.BasketViewModel;
import com.example.task.interviewtask.util.PriceHelper;

import java.util.List;

public class CheckoutViewModel extends BasketViewModel {
    private LiveData<List<CurrencyEntity>> currencyEntities;
    private CurrenciesRepository currenciesRepository;
    private PriceHelper priceHelper;
    private double originalPrice;
    private double originalPriceInEuro;

    public CheckoutViewModel(@NonNull Application application) {
        super(application);
        currenciesRepository = new CurrenciesRepository(application);
        currenciesRepository.getCurrenciesFromApi();
        currencyEntities = currenciesRepository.getCurrencyEntityList();
        priceHelper = new PriceHelper();
    }

    /**
     * Returns all currencies
     * @return
     */
    public LiveData<List<CurrencyEntity>> getCurrencyList() {
        return currencyEntities;
    }

    public Double getTotalPrice(List<BasketProductEntity> basketProducts) {
        originalPrice = priceHelper.calculateTotalPrice(basketProducts);

        return originalPrice;
    }

    public void calculatePriceInEuro(List<CurrencyEntity> currencies) {
        originalPriceInEuro = priceHelper.convertGbpToEur(currencies, originalPrice);
    }

    public Double currencyChanged(CurrencyEntity entity) {
        return priceHelper.calculateNewPrice(entity, originalPriceInEuro);
    }

    public void refreshCurrencies() {
        currenciesRepository.getCurrenciesFromApi();
    }
}
