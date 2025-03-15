package com.example.demo.module;

import com.example.demo.dto.api.Currency;
import com.example.demo.dto.entity.CurrencyEntity;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;

@Component
public class CurrencyModule {

    public CurrencyEntity convertEntity(Currency currency) {
        CurrencyEntity entity = new CurrencyEntity();
        entity.setId(null);
        entity.setCode(currency.getCode());
        String symbol = StringEscapeUtils.unescapeHtml4(currency.getSymbol());
        entity.setSymbol(symbol);
        entity.setRate(currency.getRate());
        entity.setRateFloat(currency.getRateFloat());
        entity.setDescription(currency.getDescription());
        return entity;
    }

}
