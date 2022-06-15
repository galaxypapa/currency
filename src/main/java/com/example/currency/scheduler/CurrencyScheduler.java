package com.example.currency.scheduler;

import com.example.currency.ExchangeBeanProp;
import com.example.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class CurrencyScheduler {

    private CurrencyService currencyService;

    private ExchangeBeanProp exchangeBeanProp;

    @Autowired
    public CurrencyScheduler(CurrencyService currencyService, ExchangeBeanProp exchangeBeanProp) {
        this.currencyService = currencyService;
        this.exchangeBeanProp = exchangeBeanProp;
    }

    /**
     * The Scheduled Task is to fetch the exchange rate from third party
     * and perform a batch update in database every 30 minutes
     */
    @Scheduled(fixedRate = 1800000)
    @Transactional
    public void scheduledTask() {
        currencyService.batchUpdateExchangeRate(currencyService.getExchangeRateFromThirdParty(exchangeBeanProp.getBaseCurrency()));
    }
}
