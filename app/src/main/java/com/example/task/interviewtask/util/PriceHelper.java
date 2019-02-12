package com.example.task.interviewtask.util;

import com.example.task.interviewtask.data.entities.BasketProductEntity;
import com.example.task.interviewtask.data.entities.CurrencyEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PriceHelper {

    /**
     * Rounds up to 2 decimal places
     * @param price - variable to be rounded up
     * @return
     */
    public Double roundDecimal(Double price) {
        BigDecimal bd = BigDecimal.valueOf(price);
        bd = bd.setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    /**
     * Converts price in GBP to EUR
     * @param currencies - list of currencies
     * @param originalPrice - price in GBP
     * @return
     */
    public Double convertGbpToEur(List<CurrencyEntity> currencies, double originalPrice) {
        for (CurrencyEntity entity : currencies) {
            if (entity.getName().equals("GBP")) {
                double priceInEuro = originalPrice / entity.getValue();

                return roundDecimal(priceInEuro);
            }
        }

        return 0.0;
    }

    /**
     * Calculate price of all given items
     * @param basketProducts
     * @return
     */
    public Double calculateTotalPrice(List<BasketProductEntity> basketProducts) {
        Double totalPrice = 0.0;
        for(BasketProductEntity product : basketProducts) {
            totalPrice += (product.getPrice() * product.getQuantity());
        }

        return roundDecimal(totalPrice);
    }

    /**
     * Convert price to given currency
     * @param currency - currency to convert to
     * @param priceToConvert - price to be converted
     * @return
     */
    public Double calculateNewPrice(CurrencyEntity currency, double priceToConvert) {
        double newPrice = 0.0;
        if( currency.getValue() >= 1.0) {
            newPrice = priceToConvert * currency.getValue();
        } else {
            newPrice = priceToConvert / currency.getValue();
        }

        return roundDecimal(newPrice);
    }
}
