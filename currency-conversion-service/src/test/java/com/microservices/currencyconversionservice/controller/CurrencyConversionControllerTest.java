package com.microservices.currencyconversionservice.controller;

import com.microservices.currencyconversionservice.CurrencyExchangeProxy;
import com.microservices.currencyconversionservice.bean.CurrencyConversion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CurrencyConversionController.class)
public class CurrencyConversionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyExchangeProxy currencyExchangeProxy;

    @Test
    public void testCurrencyConversionController() throws Exception {

        long id = 10101L;
        String currencyFrom = "USD";
        String currencyTo = "UAH";
        BigDecimal currencyConversionMultiple = BigDecimal.valueOf(13.23013);
        BigDecimal quantity = BigDecimal.valueOf(100);
        BigDecimal totalCalculatedAmount = quantity.multiply(currencyConversionMultiple);
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

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/currency-conversion/from/"
                        + currencyFrom + "/to/" + currencyTo + "/quantity/" + quantity)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(currencyConversion.toString()))
                .andReturn();
    }

}
