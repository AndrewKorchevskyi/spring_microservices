package com.microservices.currencyexchangeservice.controller;

import com.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.microservices.currencyexchangeservice.utils.CurrencyRatesVendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to) {
        logger.info("retrieveExchangeValue called with {} to {}", from, to);
        double conversionMultiple = new CurrencyRatesVendor().getRate(from, to);
        CurrencyExchange currencyExchange = new CurrencyExchange(from, to, conversionMultiple);
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
