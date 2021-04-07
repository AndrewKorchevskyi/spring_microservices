package com.microservices.currencyexchangeservice.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Random;

@Entity
public class CurrencyExchange {

    @Id
    private Long id;

    @Column(name = "currency_from")
    private String from;

    @Column(name = "currency_to")
    private String to;
    private BigDecimal conversionMultiple;
    private String environment;

    public CurrencyExchange(String from, String to, double conversionMultiple) {
        this.id = Math.abs(new Random().nextLong());
        this.from = from;
        this.to = to;
        this.conversionMultiple = BigDecimal.valueOf(conversionMultiple);
    }

    public CurrencyExchange() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public void setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
