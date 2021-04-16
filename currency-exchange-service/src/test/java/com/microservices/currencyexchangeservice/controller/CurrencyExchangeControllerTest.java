package com.microservices.currencyexchangeservice.controller;

import com.microservices.currencyexchangeservice.bean.CurrencyExchange;
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
@WebMvcTest(CurrencyExchangeController.class)
public class CurrencyExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyExchangeController currencyExchangeController;

    @Test
    public void testCurrencyExchangeController() throws Exception {

        String currencyFrom = "USD";
        String currencyTo = "UAH";
        BigDecimal currencyConversionMultiple = BigDecimal.valueOf(13.23013);
        String environment = "8000";

        CurrencyExchange currencyExchange = new CurrencyExchange(currencyFrom, currencyTo, currencyConversionMultiple)
                .setEnvironment(environment);

        when(currencyExchangeController.retrieveExchangeValue(currencyFrom, currencyTo))
                .thenReturn(currencyExchange);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/currency-exchange/from/" + currencyFrom + "/to/" + currencyTo)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json(currencyExchange.toString()))
                .andReturn();
    }
}
