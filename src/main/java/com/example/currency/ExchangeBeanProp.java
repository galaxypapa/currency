package com.example.currency;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "exchange")
@Configuration
public class ExchangeBeanProp {

    private String baseUrl;

    private String apiKey;

    private String rateEndpoint;

    private String baseCurrency;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getRateEndpoint() {
        return rateEndpoint;
    }

    public void setRateEndpoint(String rateEndpoint) {
        this.rateEndpoint = rateEndpoint;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

}
