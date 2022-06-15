package com.example.currency.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;


    @Test
    void calculateRateTest() {
        Double result = currencyService.calculateRate(1.432265, 6.713498);
        Assert.isInstanceOf(Double.class, result);
        Assert.isTrue(result > 0);
        Assert.isTrue(result == 4.68733);
    }

    @Test
    void getRateTest() {
        Double result = currencyService.getRate("AUD", "CNY");
        Assert.isInstanceOf(Double.class, result);
        Assert.isTrue(result > 0);
    }
}