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

    public CurrencyExchange(String from, String to, BigDecimal conversionMultiple) {
        this.id = Math.abs(new Random().nextLong());
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public CurrencyExchange() {
    }

    public Long getId() {
        return id;
    }

    public CurrencyExchange setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public CurrencyExchange setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getTo() {
        return to;
    }

    public CurrencyExchange setTo(String to) {
        this.to = to;
        return this;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }

    public CurrencyExchange setConversionMultiple(BigDecimal conversionMultiple) {
        this.conversionMultiple = conversionMultiple;
        return this;
    }

    public String getEnvironment() {
        return environment;
    }

    public CurrencyExchange setEnvironment(String environment) {
        this.environment = environment;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", from:'" + from + '\'' +
                ", to:'" + to + '\'' +
                ", conversionMultiple:" + conversionMultiple +
                ", environment:'" + environment + '\'' +
                '}';
    }
}
