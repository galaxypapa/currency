package com.example.currency.service;

import com.example.currency.ExchangeBeanProp;
import com.example.currency.dao.ExchangeDao;
import com.example.currency.entity.Exchange;
import com.example.currency.json.ExchangeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class CurrencyService {

    private final WebClient client;

    private ExchangeDao exchangeDao;

    private ExchangeBeanProp exchangeBeanProp;


    @Autowired
    public CurrencyService(ExchangeDao exchangeDao, ExchangeBeanProp exchangeBeanProp) {
        this.exchangeDao = exchangeDao;
        this.exchangeBeanProp = exchangeBeanProp;
        client = WebClient.builder()
                .baseUrl(this.exchangeBeanProp.getBaseUrl())
                .defaultHeader("apikey", this.exchangeBeanProp.getApiKey())
                .build();
    }

    /**
     * Fetch exchange rate from third party
     *
     * @param base
     * @return
     */
    public ExchangeInfo getExchangeRateFromThirdParty(String base) {

        return client.get()
                .uri(uriBuilder -> uriBuilder.path(this.exchangeBeanProp.getRateEndpoint())
                        .queryParam("base", base)
                        .build())
                .retrieve()
                .bodyToMono(ExchangeInfo.class)
                .block();
    }

    /**
     * Batch update the exchange rate in database
     *
     * @param exchangeInfo
     */
    public void batchUpdateExchangeRate(ExchangeInfo exchangeInfo) {

        exchangeInfo.getRates().forEach(
                (k, v) -> {
                    try {
                        Exchange exchange = new Exchange();
                        exchange.setId(exchangeInfo.getBase() + "_" + k);
                        exchange.setRate(v);
                        exchange.setRateDate(new SimpleDateFormat("yyyy-MM-dd").parse(exchangeInfo.getDate()));
                        exchange.setRateTimeStamp(Long.valueOf(exchangeInfo.getTimestamp()));
                        exchange.setBaseCurrency(exchangeInfo.getBase());
                        exchange.setToCurrency(k);
                        exchangeDao.saveOrUpdate(exchange);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
        );
    }

    /**
     * Calculate and return the rate by giving two currency rate
     *
     * @param fromCurrencyRate
     * @param toCurrencyRate
     * @return
     */
    public Double calculateRate(Double fromCurrencyRate, Double toCurrencyRate) {
        return new BigDecimal(toCurrencyRate / fromCurrencyRate).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * Return currency rate by giving two currency
     *
     * @param fromCurrency
     * @param toCurrency
     * @return
     */
    public Double getRate(String fromCurrency, String toCurrency) {
        Double fromCurrencyRate = exchangeDao.findRateByToCurrency(fromCurrency).getRate();
        Double toCurrencyRate = exchangeDao.findRateByToCurrency(toCurrency).getRate();
        return calculateRate(fromCurrencyRate, toCurrencyRate);
    }

}
