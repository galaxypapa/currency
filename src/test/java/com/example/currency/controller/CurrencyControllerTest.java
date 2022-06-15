package com.example.currency.controller;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyControllerTest {

    @Autowired
    private CurrencyController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(controller);
    }

    @Test
    public void getRateTest() throws Exception {
        String result = this.restTemplate.getForObject("http://localhost:" + port + "/api/rate/AUD/CNY",
                String.class);
        assertThat(result).contains("AUD", "CNY", "from_currency", "rate", "to_currency", "timestamp");
    }

}