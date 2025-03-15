package com.example.demo.dto.api;

import java.math.BigDecimal;

public class Currency {
    private String code;
    private String symbol;
    private String rate;
    private String description;
    private BigDecimal rate_float;

    // Getters & Setters
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public BigDecimal getRateFloat() {
        return rate_float;
    }
    public void setRateFloat(BigDecimal rate_float) {
        this.rate_float = rate_float;
    }
}
