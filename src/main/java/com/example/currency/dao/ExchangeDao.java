package com.example.currency.dao;


import com.example.currency.entity.Exchange;

public interface ExchangeDao extends BaseDao<Exchange> {
    Exchange findRateByToCurrency(String toCurrency);
}
