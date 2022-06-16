package com.example.currency.controller;


import com.example.currency.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/rate")
@ResponseBody
public class CurrencyController {

    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/{fromCurrency}/{toCurrency}")
    public HashMap getRate(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("from_currency", fromCurrency);
        result.put("to_currency", toCurrency);
        result.put("rate", currencyService.getRate(fromCurrency, toCurrency));
        result.put("timestamp", System.currentTimeMillis());
        return (HashMap) result;
    }

    @PostMapping
    public HashMap getRateByPost(@RequestParam String fromCurrency, @RequestParam String toCurrency) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("from_currency", fromCurrency);
        result.put("to_currency", toCurrency);
        result.put("rate", currencyService.getRate(fromCurrency, toCurrency));
        result.put("timestamp", System.currentTimeMillis());
        return (HashMap) result;
    }

}
