package com.microservices.currencyexchangeservice;

import com.microservices.currencyexchangeservice.bean.CurrencyExchange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CurrencyExchangeServiceApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
        String currencyFrom = "USD";
        String currencyTo = "UAH";
	    ResponseEntity<CurrencyExchange> response = restTemplate
                .getForEntity("/currency-exchange/from/"
                        + currencyFrom + "/to/"
                        + currencyTo, CurrencyExchange.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getFrom(), is(currencyFrom));
        assertThat(response.getBody().getTo(), is(currencyTo));
        assertThat(response.getBody().getConversionMultiple(), notNullValue());
        assertThat(response.getBody().getId(), notNullValue());
        assertThat(response.getBody().getEnvironment(), notNullValue());
	}
}
