package com.example.task.interviewtask;

import com.example.task.interviewtask.data.entities.CurrencyEntity;
import com.example.task.interviewtask.util.PriceHelper;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPriceHelper {

    private PriceHelper priceHelper = new PriceHelper();

    @Test
    public void testRoundToTwoDecimals() {
        double value = 3.2344534;
        double result = priceHelper.roundDecimal(value);

        assertThat(result, is(equalTo(3.23)));
    }

    @Test
    public void testRoundHalfUp() {
        double value = 3.549643;
        double result = priceHelper.roundDecimal(value);

        assertThat(result, is(equalTo(3.55)));
    }

    @Test
    public void testConvertGbpToEur() {
        List<CurrencyEntity> currencies = new ArrayList<>();
        currencies.add(new CurrencyEntity("GBP", 0.8856));

        double result = priceHelper.convertGbpToEur(currencies, 3.1);

        assertThat(result, is(equalTo(3.5)));
    }
}
