package com.CurrencyExchange.Services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExchangeRateServiceTest
{
    @InjectMocks
    ExchangeRateService exchangeRateService;

    @Mock
    RestTemplate restTemplate;

    public ExchangeRateServiceTest() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(exchangeRateService, "apiKey", "dummy-key");
        ReflectionTestUtils.setField(exchangeRateService, "apiUrl", "https://fake-api.com/");
    }

    @Test
    void testConvertCurrency() {
        Map<String, Object> fakeResponse = Map.of(
                "rates", Map.of("EUR", 0.9)
        );
        RestTemplate restTemplate = mock(RestTemplate.class);
        when(restTemplate.getForObject(anyString(), eq(Map.class)))
                .thenReturn(fakeResponse);

        ExchangeRateService service = new ExchangeRateService();
        ReflectionTestUtils.setField(service, "apiKey", "dummy-key");
        ReflectionTestUtils.setField(service, "apiUrl", "https://fake-api.com/");

        double result = service.convert("USD", "EUR", 100);
        assertEquals(90, result, 0.1);
    }
}
