package com.example.demo.unittest;

import com.example.demo.dto.api.Currency;
import com.example.demo.dto.entity.CurrencyEntity;
import com.example.demo.module.CurrencyModule;
import com.example.demo.service.CurrencyService;
import com.example.demo.shared.HttpUtil;
import com.example.demo.shared.HttpUtilImpl;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrencyUnitTest {

    private final HttpUtil mockHttpUtil = mock(HttpUtilImpl.class);

    private final CurrencyModule target = new CurrencyModule();

    @Test
    public void callApi() throws IOException {
        when(mockHttpUtil.Get(any(), any())).thenReturn(getMockData());
        CurrencyService service = new CurrencyService();
        service.setHttpUtil(mockHttpUtil);
        service.setModule(target);

        List<CurrencyEntity> actual = service.getDataByApi();

        CurrencyEntity usd = actual.get(0);
        CurrencyEntity gbp = actual.get(1);
        CurrencyEntity eur = actual.get(2);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(usd.getCode()).isEqualTo("USD");
        softAssertions.assertThat(usd.getSymbol()).isEqualTo("$");
        softAssertions.assertThat(usd.getRate()).isEqualTo("57,756.298");
        softAssertions.assertThat(usd.getRateFloat()).isEqualTo(new BigDecimal("57756.2984"));
        softAssertions.assertThat(usd.getDescription()).isEqualTo("United States Dollar");

        softAssertions.assertThat(gbp.getCode()).isEqualTo("GBP");
        softAssertions.assertThat(gbp.getSymbol()).isEqualTo("£");
        softAssertions.assertThat(gbp.getRate()).isEqualTo("43,984.02");
        softAssertions.assertThat(gbp.getRateFloat()).isEqualTo(new BigDecimal("43984.0203"));
        softAssertions.assertThat(gbp.getDescription()).isEqualTo("British Pound Sterling");

        softAssertions.assertThat(eur.getCode()).isEqualTo("EUR");
        softAssertions.assertThat(eur.getSymbol()).isEqualTo("€");
        softAssertions.assertThat(eur.getRate()).isEqualTo("52,243.287");
        softAssertions.assertThat(eur.getRateFloat()).isEqualTo(new BigDecimal("52243.2865"));
        softAssertions.assertThat(eur.getDescription()).isEqualTo("Euro");
        softAssertions.assertAll();

    }

    private String getMockData() throws IOException {
        Path path = Paths.get("src/test/resources/response.json");
        return Files.readString(path);
    }

    @Test
    public void convertEntity(){
        // 1. Arrange
        Currency currency = new Currency();
        currency.setCode("USD");
        currency.setSymbol("&#36;");
        currency.setRate("57,756.298");
        currency.setDescription("United States Dollar");
        currency.setRateFloat(new BigDecimal("57756.2984"));

        // 2. Act
        CurrencyEntity actual = target.convertEntity(currency);

        // 3. Assert
        CurrencyEntity excepted = new CurrencyEntity();
        excepted.setId(null);
        excepted.setCode("USD");
        excepted.setSymbol("$");
        excepted.setRate("57,756.298");
        excepted.setDescription("United States Dollar");
        excepted.setRateFloat(new BigDecimal("57756.2984"));
        assertThat(actual).isEqualTo(excepted);
    }
}
