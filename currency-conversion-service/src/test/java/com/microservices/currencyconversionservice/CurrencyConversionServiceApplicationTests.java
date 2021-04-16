package com.microservices.currencyconversionservice;

import com.microservices.currencyconversionservice.bean.CurrencyConversion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CurrencyConversionServiceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private CurrencyExchangeProxy currencyExchangeProxy;

	@Test
	void contextLoads() {
		long id = 10101L;
		String currencyFrom = "USD";
		String currencyTo = "UAH";
		BigDecimal currencyConversionMultiple = BigDecimal.valueOf(13.23013);
		BigDecimal quantity = BigDecimal.valueOf(100);
		BigDecimal totalCalculatedAmount = quantity.multiply(currencyConversionMultiple).setScale(2, RoundingMode.HALF_EVEN);
		String environment = "8000";

		CurrencyConversion currencyConversion = new CurrencyConversion(
				id,
				currencyFrom,
				currencyTo,
				quantity,
				currencyConversionMultiple,
				totalCalculatedAmount,
				environment);

		when(currencyExchangeProxy.retrieveExchangeValue(currencyFrom, currencyTo))
				.thenReturn(currencyConversion);

		ResponseEntity<CurrencyConversion> response = restTemplate
				.getForEntity("/currency-conversion/from/"
						+ currencyFrom + "/to/" + currencyTo + "/quantity/" + quantity, CurrencyConversion.class);

		assertThat(response.getStatusCode(), is(HttpStatus.OK));
		assertThat(response.getBody().getId(), is(id));
		assertThat(response.getBody().getFrom(), is(currencyFrom));
		assertThat(response.getBody().getTo(), is(currencyTo));
		assertThat(response.getBody().getQuantity(), is(quantity));
		assertThat(response.getBody().getConversionMultiple(), is(currencyConversionMultiple));
		assertThat(response.getBody().getTotalCalculatedAmount(), is(totalCalculatedAmount));
		assertThat(response.getBody().getEnvironment(), is(environment));
	}

}
