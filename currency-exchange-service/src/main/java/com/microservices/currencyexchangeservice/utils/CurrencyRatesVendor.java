package com.microservices.currencyexchangeservice.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Objects;

public class CurrencyRatesVendor {

    public double getRate(String currency1, String currency2) {
        String currency_rate_url =
                String
                        .format(
                                "https://free.currconv.com/api/v7/convert?q=%s_%s&compact=ultra&apiKey=e9cf86819dc9df9c62b1",
                                currency1,
                                currency2);

        if (!isCurrencyPairAvailable(currency1, currency2)) {
            throw new RuntimeException("Currency pair is not available or incorrect");
        }
        String currencyPair = currency1 + "_" + currency2;

        ResponseEntity<LinkedHashMap> responseEntity =
                new RestTemplate()
                        .getForEntity(currency_rate_url, LinkedHashMap.class);

        if (responseEntity.getStatusCodeValue() != 200) {
            throw new RuntimeException("Something went wrong, please try again later :(");
        }
        return Double.parseDouble(Objects.requireNonNull(responseEntity.getBody()).get(currencyPair).toString());
    }

    private boolean isCurrencyAvailable(String currency) {
        String currency_list_url = "https://free.currconv.com/api/v7/currencies?apiKey=e9cf86819dc9df9c62b1";
        LinkedHashMap responseEntity =
                (LinkedHashMap) new RestTemplate()
                        .getForEntity(currency_list_url, LinkedHashMap.class).getBody().get("results");
        return responseEntity.keySet().stream().anyMatch(x -> x.equals(currency));
    }

    private boolean isCurrencyPairAvailable(String currency1, String currency2) {
        return isCurrencyAvailable(currency1) && isCurrencyAvailable(currency2);
    }
}
